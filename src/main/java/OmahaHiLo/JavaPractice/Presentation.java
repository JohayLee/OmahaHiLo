package OmahaHiLo.JavaPractice;

import java.util.Iterator;
import java.util.Map;

import OmahaHiLo.JavaPractice.RankingRules.RankingRule;

public class Presentation {
	
	public static Card[] GetCardsFromNotationsString(String notationsString)
	{
		// Convert card annotation string to card object
		String[] cardNotation = notationsString.split("-");
		Card[] cards = new Card[cardNotation.length];
		for (int i = 0; i < cards.length; ++i)
		{
			cards[i] = new Card(cardNotation[i].charAt(0), cardNotation[i].charAt(1));
		}
		return cards;
	}
	
	// Generate the output text result
	public String GeneratePresentation(Player[] players)
	{
		// Compare the the rankedHands from player A and B to get the result.
		String displayText = "";
		
		Player playerA = players[0];
		Player playerB = players[1];
		
		// A Hi; B Hi - Hi always exists, compare to win
		if (CheckRankedHandExist(playerA.highRanked) && CheckRankedHandExist(playerB.highRanked))
		{
			// Compare who is the winner
			Map.Entry<RankingRule, Card[]> entryA = playerA.highRanked.entrySet().iterator().next();
			Map.Entry<RankingRule, Card[]> entryB = playerB.highRanked.entrySet().iterator().next();
			int priorityA = OmahaComp.GetRankRulePriority(entryA.getKey());
			int priorityB = OmahaComp.GetRankRulePriority(entryB.getKey());
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
	
	private boolean CheckRankedHandExist(Map<RankingRule, Card[]> rankedHand)
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
	private String GetRankChars(Map<RankingRule, Card[]> rankedCards)
	{
		Iterator<Map.Entry<RankingRule, Card[]>> itr = rankedCards.entrySet().iterator();
    	Card[] cardsSorted = CardsRankingUtils.SortCardsDescending(itr.next().getValue(), false);
    	return CardsRankingUtils.ConcatCardRankCharacterToString(cardsSorted);
	}
	

}
