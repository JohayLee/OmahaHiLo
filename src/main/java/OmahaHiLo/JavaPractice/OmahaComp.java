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
 * The general modeling follows the real world activities:
 * Each line of input file is considered as a round of game with two players and
 * cards on board. Reading a line from the input file is like dispatching cards. 
 * the application then tries to EvaluatePlayers, which tells each player to combine
 * 2 of his/her received cards with the 3 picked from the board. All the 60 combination
 * possibilities are filtered with ranking rules, i.e., high ranks like straight, 
 * flush, 4-of-a-kind etc. and the low8 rank rule for each player. Then each the player 
 * finds the max-valued combinations for both high and low8. The comparison text result
 * between the max-valued combinations of the two players are generated to write out to
 * the output file. 
 * For every and each ranking rule, it has two import methods: 
 * 1. ApplyToHandOfCards - to check if a hand of 5 cards is qualified for the rule.
 * 2. CompareCards - to decide which one of the two hand of cards is more valuable.
 * The algorithms for the above methods are described below for the ranking rules:
 * Straight: 
 * ApplyToHandOfCards -sort rank descending and then check if the neighbor difference is 1.
 * CompareCards -compare rank one by one.
 * Flush:
 * ApplyToHandOfCards - Check if suits identical.
 * CompareCards -same as above.
 * StraightFlush:
 * ApplyToHandOfCards - Check both Straight and Flush.
 * CompareCards -same as above.
 * 4-of-a-kind:
 * ApplyToHandOfCards - Construct rank to same-rank-count map, check if the map 
 * 						contains same-rank-count 4.
 * CompareCards -sort cards as most-repeated rank first, then compare by rank one by one.
 * FullHouse:
 * ApplyToHandOfCards - Construct rank to same-rank-count map, check if the map contains
 * 						both same-rank-count 3 and same-rank-count 2.
 * CompareCards -sort cards as most-repeated rank first, then compare by rank one by one.
 * 3-of-a-kind:
 * ApplyToHandOfCards - Construct rank to same-rank-count map, check if the map 
 * 						contains same-rank-count 3.
 * CompareCards -sort cards as most-repeated rank first, then rank descending. Compare by 
 * 						rank one by one.
 * One pair:
 * ApplyToHandOfCards - Construct rank to same-rank-count map, check if the same-ranks occur
 * 						once only. 
 * CompareCards -sort cards as most-repeated rank first, then rank descending. Compare by 
 * 						rank one by one.
 * Two pairs:
 * ApplyToHandOfCards - Construct rank to same-rank-count map, check if the same-ranks occur
 * 						twice. 
 * CompareCards -sort cards as most-repeated rank first, as there are two pairs, which pair 
 * 						goes first based on its rank descending. Compare by	rank one by one.
 * High card:
 * ApplyToHandOfCards - Always returns true. 
 * CompareCards -sort cards descending. Compare by rank one by one.
 * 
 * Low 8:
 * ApplyToHandOfCards - Check to make sure none of the card is higher than 8. Also collect a
 * 						distinct-ranked hash-set, return false at the element adding if any
 * 						rank already exists. If none identical rank, return true.    
 * CompareCards -sort cards descending. Compare by rank one by one.
 *
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
	public static int GetRankRulePriority(RankingRule rule)
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
				Presentation presentation = new Presentation();
				String result =  presentation.GeneratePresentation(players);
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
			players[i].highRanked = CardsRankingUtils.FilterByHighRankingRules(players[i].CombineAsFiveCards(boardCards.PickCards()), OmahaHiRankingRules);
			players[i].low8Ranked = CardsRankingUtils.FilterByOneRankingRule(players[i].CombineAsFiveCards(boardCards.PickCards()), low8);
		}
	}

	
	/**
	 * DispatchCards 
	 * 1. Each player receives his/her four cards, 
	 * 2. Five cards are set to the board.
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

}
