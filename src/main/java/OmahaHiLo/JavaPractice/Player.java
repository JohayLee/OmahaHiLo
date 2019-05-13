package OmahaHiLo.JavaPractice;

import java.util.*;

import OmahaHiLo.JavaPractice.RankingRules.Low8;
import OmahaHiLo.JavaPractice.RankingRules.RankingHigh;
import OmahaHiLo.JavaPractice.RankingRules.RankingRule;

public class Player {
	public static final int TOTAL_RECEIVED_CARDS = 4;
	private	String playerName; // e.g., HandA, HandB
	private Card receivedCards[] = new Card[TOTAL_RECEIVED_CARDS];
	RankingRule[] omahaHiLoRankingRules;
	RankingRule low8;
	List<Card[]> combinationList;
	
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
	// Set the ranking rule to the player
	public void SetRankingRules(RankingRule[] omahaHiLoRankingRules, RankingRule low8)
	{
		this.omahaHiLoRankingRules = omahaHiLoRankingRules;
		this.low8 = low8;
	}
	// A cardNotationString contains card notations linked by a dash, e.g. Ac-Kd-Jd-3d
	public void ReceiveCards(String cardNotationsString)
	{
		ReceiveCards(Presentation.GetCardsFromNotationsString(cardNotationsString));		
	}
	public void ReceiveCards(Card[] cards)
	{
		receivedCards = Arrays.copyOf(cards, Math.min(receivedCards.length, cards.length));
	}
	
	public List<Card[]> PickCards() 
	{
		//Need to pick 2 cards from received cards.
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
	
	public void CombineAsFiveCards(List<Card[]> boardCardsList)
	{
		combinationList = new ArrayList<Card[]>();
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
				combinationList.add(fiveCardCards);
			}
		}
		
	}
	public List<Card[]> GetCombinationList()
	{
		return combinationList;
	}
	
	// Get the combinations which match the high ranking rules
	public Map<RankingRule, Card[]> FilterByTopRankingRule()
	{
		Map<RankingRule, Card[]> maxValuableMap = new HashMap<RankingRule, Card[]>();
		for (int i = 0; i < omahaHiLoRankingRules.length; ++i)
		{
			Map<RankingRule, List<Card[]>> map = CardsRankingUtils.FilterByRankingRule(combinationList, omahaHiLoRankingRules[i]);
			maxValuableMap = GetMaxValuedHandOfRankingRule(map);
			if (maxValuableMap != null && !maxValuableMap.isEmpty())
			{
	        	return maxValuableMap; // The first matching rank is the highest.
			}
		}
		return maxValuableMap;
	}

	// Get the combinations which match the high ranking rules
	public Map<RankingRule, Card[]> FilterByHighRanking(int ruleIndex)
	{
		Map<RankingRule, List<Card[]>> map = CardsRankingUtils.FilterByRankingRule(combinationList, omahaHiLoRankingRules[ruleIndex]);
		Map<RankingRule, Card[]> maxValuableMap = GetMaxValuedHandOfRankingRule(map);
		return maxValuableMap;
	}
	
	// Get the combinations which match one ranking rule
	public Map<RankingRule, Card[]> FilterByLow8()
	{
		Map<RankingRule, List<Card[]>> map = new HashMap<RankingRule, List<Card[]>>();
		map.put(low8, combinationList);
		return GetMaxValuedHandOfRankingRule(map);
	}
	
	// Get the combinations which has the highest value of the specific rule
	private Map<RankingRule, Card[]> GetMaxValuedHandOfRankingRule(Map<RankingRule, List<Card[]>> map)
	{
		Map<RankingRule, Card[]> maxValuableMap = new HashMap<RankingRule, Card[]>();
    	Iterator<Map.Entry<RankingRule, List<Card[]>>> itr = map.entrySet().iterator();
    	while (itr.hasNext())
    	{
        	Map.Entry<RankingRule, List<Card[]>> entry = itr.next();
        	Card[] maxValuableHandOfTheRule = CardsRankingUtils.GetMaxValuedHandOfRankingRule(entry.getKey(), entry.getValue());
	        if (maxValuableHandOfTheRule != null && maxValuableHandOfTheRule.length != 0)
	        {
	        	maxValuableMap.put(entry.getKey(), maxValuableHandOfTheRule);
	        	return maxValuableMap; // The first matching rank is the highest.
	        }
	    		
    	}
    	return maxValuableMap;
	}
	
}
