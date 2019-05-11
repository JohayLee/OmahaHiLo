package OmahaHiLo.JavaPractice;
import OmahaHiLo.JavaPractice.RankingRules.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Poker Ohama Hi/Lo evaulation.
 * The general modeling simulates the real world activity:
 * Each line of input file is consider as a game with two players and cards on board.
 * Reading a line from the input file is like dispatching cards (DispatchCards), then 
 * the application tries to EvaluatePlayers, in fact it actually asks each player to
 * combine 2 of their received cards with the 3 board cards for all the combination 
 * possibilities. For each and all combinations the player tries to evaluate and get 
 * all the rank values for both high and low 8. Since all combination possibilities 
 * are evaluated, all the possible ranks are achieved. The player picks the best high 
 * rank value and the best low one to compare with the other player, who has done the 
 * same processing.
 * 
 * To simplify, the program is not segregated to different layers, e.g., The ranking 
 * rule name etc. is just put into a rule object although it is supposed to be in the
 * presentation layer.
 *
 */
public class OmahaComp 
{
	public final static int NUM_PLAYERS = 2;
	// The ranking value in the array is from high to low, i.e. index 0 has the highest ranking value..
	public final static RankingRule OmahaHiRankingRules[] = {
			new StraightFlush(),
			new FourOfAKind(),
			new FullHouse(),
			new Flush(),
			new Straight(),
			new ThreeOfAKind(),
			new TwoPairs(),
			new OnePair(),
			new HighCard(),
	};
	public final static RankingRule low8 = new Low8();


	public static void main(String[] args) 
	{
		String inputTxt = System.getProperty("user.dir") + "/" + args[0];
		String outputTxt = System.getProperty("user.dir") + "/" + args[1];
		System.out.println("input.txt = " + inputTxt + "\noutput.txt = " + outputTxt);

		List<String> inputList = new ArrayList<String>();
		// Read all lines of input
		Scanner fileReader;
		try 
		{
			fileReader = new Scanner(new File(inputTxt));
			while (fileReader.hasNext()) 
			{
				String inputLine = fileReader.nextLine();
				inputList.add(inputLine);
			}
			fileReader.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		// Evaluate the lines and write the results to output file
		try 
		{
			FileWriter fileWriter = new FileWriter(outputTxt);
			// Evaluate every input line
			for (String inputLine : inputList) 
			{
				// 1: Get players and cards on the board from each input line.
				Player players[] = new Player[NUM_PLAYERS]; // index 0 - player A, 1 - player B
				BoardCards boardCards = new BoardCards();
				DispatchCards(inputLine, players, boardCards);

				// 2: Now evaluate for each player.
				EvaluatePlayers(players, boardCards);
				// 3: Output the result to a line in the output file
				String result =  GeneratePresentation(players);
				fileWriter.write(inputLine + System.lineSeparator()); // Write the input line first
				fileWriter.write("=> " + result); // Write the result
				fileWriter.write(System.lineSeparator()); // Write a blank line
			}
			fileWriter.close();
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}

	}

	/**
	 * Evaluate all the combinations 2 received cards and the 3 board cards for each player.
	 * 
	 * @param players
	 * @param boardCards
	 * @return
	 */
	public static void EvaluatePlayers(Player[] players, BoardCards boardCards) 
	{
		// Evaluate for both player A and B
		for (int i = 0; i < players.length; ++i) 
		{
			// All combinations for the 2 out of the 4 received cards
			// Handle the ranking result for both players
			players[i].highRanked = Player.FilterByHighRankingRules(players[i].CombineAsFiveCards(boardCards.PickCards()), OmahaHiRankingRules);
			players[i].low8Ranked = Player.FilterByOneRankingRule(players[i].CombineAsFiveCards(boardCards.PickCards()), low8);
		}
	}

	private static String GetRankChars(Map<RankingRule, Card[]> rankedCards)
	{
		Iterator<Map.Entry<RankingRule, Card[]>> itr = rankedCards.entrySet().iterator();
    	Card[] cardsSorted = PokerUtils.SortCardsDescending(itr.next().getValue(), false);
    	return PokerUtils.ConcatCardRankCharacterToString(cardsSorted);
	}
	private static int GetRankRulePriority(RankingRule rule)
	{
		int priority = 0; // smaller is higher priority
		for (int i = 0; i < OmahaHiRankingRules.length; ++i)
		{
			if (rule.getClass().toString().compareToIgnoreCase(OmahaHiRankingRules[i].getClass().toString()) == 0 )
			{
				priority = i;
				break;
			}
		}
		
		return priority;
	}
	
	/**
	 * DispatchCards - 1. to dispatch cards for the two players, 2. to set the board
	 * cards
	 * 
	 * @param inputLine
	 * @param players
	 * @param boardCards
	 */
	public static void DispatchCards(String inputLine, Player[] players, BoardCards boardCards) 
	{
		// Hands of players and Board are delimited by a space
		String[] splited = inputLine.split(" ");
		// The first two items are player/hand
		int itemIndex = 0;
		for (; itemIndex < NUM_PLAYERS; ++itemIndex) 
		{
			// Player name and received cards are delimited by a colon
			String[] playerNameAndReceivedCards = splited[itemIndex].split(":");
			players[itemIndex] = new Player(playerNameAndReceivedCards[0]);
			players[itemIndex].ReceiveCards(playerNameAndReceivedCards[1]);
		}
		// The third item is the cards on board.
		String[] boardNameAndReceivedCards = splited[itemIndex].split(":");
		//boardCards = new BoardCards();
		boardCards.SetCards(boardNameAndReceivedCards[1]);
	}

	// Generate the output text result
	public static String GeneratePresentation(Player[] players)
	{
		// Compare the the rankedHands from player A and B to get the result.
		String displayText = "";
		
		Player playerA = players[0];
		Player playerB = players[1];
		// A
		
		
		// A Hi; B Hi - Hi always exists, compare to win
		if (CheckRankedHandExist(playerA.highRanked) && CheckRankedHandExist(playerB.highRanked))
		{
			// Compare who is the winner
			Map.Entry<RankingRule, Card[]> entryA = playerA.highRanked.entrySet().iterator().next();
			Map.Entry<RankingRule, Card[]> entryB = playerB.highRanked.entrySet().iterator().next();
			int priorityA = GetRankRulePriority(entryA.getKey());
			int priorityB = GetRankRulePriority(entryB.getKey());
			if (priorityA == priorityB)
			{
				// Compare
				RankingRule rule = entryA.getKey();
		    	int compareResult = rule.CompareCards(entryA.getValue(), entryB.getValue());
		    	if (compareResult < 0)
		    	{
		    		displayText += " HandB wins Hi (";
		    		displayText += entryB.getKey().GetName() + ")";
		    	}
		    	else if (compareResult > 0)
		    	{
		    		displayText += " HandA wins Hi (";
		    		displayText += entryA.getKey().GetName() + ")";
		    	}
		    	else
		    	{
			    	displayText += " Split Pot Hi (";
		    		displayText += entryA.getKey().GetName() + ")";
		    	}

			}
			else if (priorityA > priorityB)
			{
	    		displayText += " HandB wins Hi (";
	    		displayText += entryB.getKey().GetName() + ")";
			}
			else
			{
	    		displayText += " HandA wins Hi (";
	    		displayText += entryA.getKey().GetName() + ")";
			}
		}
		
		// A Lo, B Lo - compare to win
		if (CheckRankedHandExist(playerA.low8Ranked) && CheckRankedHandExist(playerB.low8Ranked))
		{
			// Compare who is the winner
			Map.Entry<RankingRule, Card[]> entryA = playerA.low8Ranked.entrySet().iterator().next();
			Map.Entry<RankingRule, Card[]> entryB = playerB.low8Ranked.entrySet().iterator().next();
			// Compare
			RankingRule rule = entryA.getKey();
		    int compareResult = rule.CompareCards(entryA.getValue(), entryB.getValue());
		   	if (compareResult < 0)
		    {
		    	displayText += " HandB wins Lo (" + GetRankChars(playerB.low8Ranked) + ")";
		    }
		    else
		    {
		    	displayText += " Split Pot Lo (" + GetRankChars(playerB.low8Ranked) + ")";
		    }
		}
		// A Lo, B no-Low
		else if (CheckRankedHandExist(playerA.low8Ranked) && !CheckRankedHandExist(playerB.low8Ranked))
		{
			displayText += " HandA wins Lo (" + GetRankChars(playerA.low8Ranked) + ")";
		}
		// A no-Low, B Low
		else if (!CheckRankedHandExist(playerA.low8Ranked) && CheckRankedHandExist(playerB.low8Ranked))
		{
			displayText += " HandB wins Lo (" + GetRankChars(playerB.low8Ranked) + ")";
		}
		// A no-Low, B no-Low
		else if (!CheckRankedHandExist(playerA.low8Ranked) && !CheckRankedHandExist(playerB.low8Ranked))
		{
			displayText += " No hand qualified for Lo";
		
		}
		return displayText;
	}
	
	private static boolean CheckRankedHandExist(Map<RankingRule, Card[]> rankedHand)
	{
		
		boolean result = false;
		try
		{
			result = rankedHand != null 
				&& rankedHand.entrySet() != null
				&& rankedHand.entrySet().iterator() != null
				&& rankedHand.entrySet().iterator().next() != null;
		}
		catch(Exception e)
		{
			result = false;
		}
		return result;
	}
}
