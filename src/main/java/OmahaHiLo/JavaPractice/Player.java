package OmahaHiLo.JavaPractice;

import java.util.*;

import OmahaHiLo.JavaPractice.RankingRules.Low8;
import OmahaHiLo.JavaPractice.RankingRules.RankingHigh;
import OmahaHiLo.JavaPractice.RankingRules.RankingRule;

public class Player {
	public static final int TOTAL_RECEIVED_CARDS = 4;
	private	String playerName; // e.g., HandA, HandB
	private Card receivedCards[] = new Card[TOTAL_RECEIVED_CARDS];
	
	public Player(String playerName)
	{
		this.playerName = playerName;
	}
	public String GetName()
	{
		return playerName;
	}
	public void SetName(String playerName)
	{
		this.playerName = playerName;
	}
	
	public Map<RankingRule, Card[]> highRanked;
	public Map<RankingRule, Card[]> low8Ranked;
	
	// A cardNotationString contains card notations linked by a dash, e.g. Ac-Kd-Jd-3d
	public void ReceiveCards(String cardNotationsString)
	{
		ReceiveCards(PokerUtils.GetCardsFromNotationsString(cardNotationsString));		
	}
	public void ReceiveCards(Card[] cards)
	{
		receivedCards = Arrays.copyOf(cards, Math.min(receivedCards.length, cards.length));
	}
	
	public List<Card[]> PickCards() 
	{
		//Need to pick 2 cards for high combinations, then the other two will be for low8 combination.
		//Sort the cards first?
		final int NUM_CARDS_TO_PICK = 2;
		List<Card[]> pickList = new ArrayList<Card[]>();
		int maxIndexOfFirstCard = receivedCards.length - NUM_CARDS_TO_PICK;
		for (int i = 0; i <= maxIndexOfFirstCard; i++) {
			for (int j = i + 1; j <= maxIndexOfFirstCard + 1; j++) {
				Card[] pickedCard = new Card[NUM_CARDS_TO_PICK];
				pickedCard[0] = receivedCards[i];
				pickedCard[1] = receivedCards[j];
				pickList.add(pickedCard);
			}
		}
		return pickList;
	}
	
	public List<Card[]> CombineAsFiveCards(List<Card[]> boardCardsList)
	{
		List<Card[]> fiveCardList = new ArrayList<Card[]>();
		List<Card[]> listOf2ReceivedCards = PickCards();
		for (int i = 0; i < listOf2ReceivedCards.size(); ++i)
		{
			Card[] receivedCards = listOf2ReceivedCards.get(i);
			for (int j = 0; j < boardCardsList.size(); ++j)
			{
				Card[] boardCards = boardCardsList.get(j);
				Card[] fiveCardCards = new Card[receivedCards.length + boardCards.length];
				System.arraycopy(receivedCards, 0, fiveCardCards, 0, receivedCards.length);
				System.arraycopy(boardCards, 0, fiveCardCards, receivedCards.length, boardCards.length);
				fiveCardList.add(fiveCardCards);
			}
		}
		
		return fiveCardList;
	}
	// Get the combinations which match the high ranking rules
	public static Map<RankingRule, Card[]> FilterByHighRankingRules(List<Card[]> fiveCardCombinations, RankingRule[] OmahaHiLoRankingRules)
	{
		Map<RankingRule, Card[]> maxValuableMap = new HashMap<RankingRule, Card[]>();
		for (int i = 0; i < OmahaHiLoRankingRules.length; ++i)
		{
			Map<RankingRule, List<Card[]>> map = FilterByRankingRule(fiveCardCombinations, OmahaHiLoRankingRules[i]);
			maxValuableMap = GetMaxValuedHandOfRankingRule(map);
			if (maxValuableMap != null && !maxValuableMap.isEmpty())
			{
	        	return maxValuableMap; // The first matching rank is the highest.
			}
		}
		return maxValuableMap;
	}

	// Get the combinations which match one ranking rule
	public static Map<RankingRule, Card[]> FilterByOneRankingRule(List<Card[]> fiveCardCombinations, RankingRule rule)
	{
		Map<RankingRule, List<Card[]>> map = new HashMap<RankingRule, List<Card[]>>();
		map.put(rule, fiveCardCombinations);
		return GetMaxValuedHandOfRankingRule(map);
	}
	
	private static Map<RankingRule, Card[]> GetMaxValuedHandOfRankingRule(Map<RankingRule, List<Card[]>> map)
	{
		Map<RankingRule, Card[]> maxValuableMap = new HashMap<RankingRule, Card[]>();
    	Iterator<Map.Entry<RankingRule, List<Card[]>>> itr = map.entrySet().iterator();
    	while (itr.hasNext())
    	{
        	Map.Entry<RankingRule, List<Card[]>> entry = itr.next();
        	Card[] maxValuableHandOfTheRule = PokerUtils.GetMaxValuedHandOfRankingRule(entry.getKey(), entry.getValue());
	        if (maxValuableHandOfTheRule != null && maxValuableHandOfTheRule.length != 0)
	        {
	        	maxValuableMap.put(entry.getKey(), maxValuableHandOfTheRule);
	        	return maxValuableMap; // The first matching rank is the highest.
	        }
	    		
    	}
    	return maxValuableMap;
	}

	// Get the combinations which match a ranking rule
	public static Map<RankingRule, List<Card[]>> FilterByRankingRule(List<Card[]> combinations, RankingRule rule)
	{
		Map<RankingRule, List<Card[]>> mapRankingRuleToCombinations = new HashMap<RankingRule, List<Card[]>>();
		Iterator<Card[]> itr = combinations.iterator();
		while (itr.hasNext())
		{
			Card[] handOfCards = (Card[])itr.next();
			// If the hand of cards matches the ranking rule, collect it and remove from combinations
			if (rule.ApplyToHandOfCards(handOfCards))
			{
				if (!mapRankingRuleToCombinations.containsKey(rule))
				{
					mapRankingRuleToCombinations.put(rule, new ArrayList<Card[]>());
				}
				List<Card[]> listOfHandOfCards = mapRankingRuleToCombinations.get(rule);
				listOfHandOfCards.add(handOfCards);
				mapRankingRuleToCombinations.replace(rule, listOfHandOfCards);
				
				itr.remove();
			}
		}
		return mapRankingRuleToCombinations;
	}
	
}
