package OmahaHiLo.JavaPractice;

import java.util.*;

import OmahaHiLo.JavaPractice.RankingRules.Flush;
import OmahaHiLo.JavaPractice.RankingRules.FourOfAKind;
import OmahaHiLo.JavaPractice.RankingRules.FullHouse;
import OmahaHiLo.JavaPractice.RankingRules.HighCard;
import OmahaHiLo.JavaPractice.RankingRules.Low8;
import OmahaHiLo.JavaPractice.RankingRules.OnePair;
import OmahaHiLo.JavaPractice.RankingRules.RankingRule;
import OmahaHiLo.JavaPractice.RankingRules.Straight;
import OmahaHiLo.JavaPractice.RankingRules.StraightFlush;
import OmahaHiLo.JavaPractice.RankingRules.ThreeOfAKind;
import OmahaHiLo.JavaPractice.RankingRules.TwoPairs;
/** General poker programming utilities.
 * 
 */
public class CardsRankingUtils {
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

	
	public static final int NUM_CARDS_OF_A_HAND = 5;
	
	// For high rank, A means 14. However in low8 rank, it means 1.
	public static Card[] SortCardsAscending(Card[] cards, boolean isAceTopRanking)
	{
		// If not high rank, i.e., low8 rank, set the Ace as 1 for the card rank value. 
		for (int i = 0; i < cards.length; ++i)
		{
			cards[i].SetAceAsOne(!isAceTopRanking);
		}
		List<Card> listOfCard = Arrays.asList(cards);
		Collections.sort(listOfCard);
		return (Card[])listOfCard.toArray();
	}
	public static Card[] SortCardsDescending(Card[] cards, boolean isAceTopRanking)
	{
		Card[] ascendingCards = SortCardsAscending(cards, isAceTopRanking);
		List<Card> listOfCard = Arrays.asList(ascendingCards);
		Collections.reverse(listOfCard);
		return (Card[])listOfCard.toArray();
	}
	public static String ConcatCardRankCharacterToString(Card[] cards)
	{
		String ranks = "";
    	for (int i = 0; i < cards.length; ++i)
    	{
    		ranks += cards[i].GetRankCharacter();
    	}
    	return ranks;
	}
	public static Map<Integer, Integer> GetRankToCountMap(Card[] cards, boolean isAceTopRank)
	{
		Map<Integer, Integer> mapRankValueToCount = new HashMap<Integer, Integer>();
		for (int i = 0; i < cards.length; ++i)
		{
			Integer cardHighRankValue = cards[i].GetRankValue(isAceTopRank);
			if(mapRankValueToCount.containsKey(cardHighRankValue))
			{
					mapRankValueToCount.put(cardHighRankValue, mapRankValueToCount.get(cardHighRankValue) + 1); 
			}
			else
			{
					mapRankValueToCount.put(cardHighRankValue, 1);
			}
		}
		return mapRankValueToCount;
	}
	public static List<Integer> GetKeyListFromValue(Map<Integer, Integer> map, Integer value) 
	{
		List<Integer> keyList = new ArrayList<Integer>();
		for (Integer obj : map.keySet()) 
		{
		      if (map.get(obj).equals(value)) 
		      {
		    	  keyList.add(obj);
		      }
		 }
		 return keyList;
	}
	public static int CompareCardsOneByOne(boolean isAceTopRanking, Card[] handA, Card[] handB, boolean higherStronger)
	{
		return CompareCardsOneByOne(isAceTopRanking, handA, handB,higherStronger, 0);
	}
	
	public static int CompareCardsOneByOne(boolean isAceTopRanking, Card[] handA, Card[] handB, boolean higherStronger, int startIndex)
	{
		for (int i = 0; i < handA.length; ++i)
		{
			// For low8, smaller is stronger, so swap A and B
			int diff = higherStronger ? 
					handA[i].GetRankValue(isAceTopRanking) - handB[i].GetRankValue(isAceTopRanking) : // For high cards
						handB[i].GetRankValue(isAceTopRanking) - handA[i].GetRankValue(isAceTopRanking); // For low8
			if (diff != 0)
			{
				return diff;
			}
		}
		return 0;
	}

	public static Card[] SortByRepetitionListAhead(Card[] hand, List<Integer> numRepetitionList)
	{
		// The repetition element value in the list is descending
		Collections.sort(numRepetitionList);
		Collections.reverse(numRepetitionList);
		Map<Integer, Integer> mapRankToCount = GetRankToCountMap(hand, true);
		List<Integer> rankListAll = new ArrayList<Integer>();
		for (int i = 0; i < numRepetitionList.size(); ++i)
		{
			List<Integer> rankList = GetKeyListFromValue(mapRankToCount, numRepetitionList.get(i));
			Collections.sort(rankList);
			Collections.reverse(rankList);
			rankListAll.addAll(rankList);
		}
		// Pick up the cards based on the order
		return SortCardsByRankList(hand, rankListAll);
	}	
	public static Card[] SortByRepetitionAhead(Card[] hand, int numRepetition)
	{
		List<Integer> numRepetitionList = new ArrayList<Integer>();
		for (int i = numRepetition; i > 0; --i)
		{
			numRepetitionList.add(i);
		}
		return SortByRepetitionListAhead(hand, numRepetitionList);
	}
	public static Card[] SortCardsByRankList(Card[] hand, List<Integer> rankList)
	{
		// Pick up the cards based on the order
		List<Card> sourceCardList = Arrays.asList(hand);
		List<Card> destCardList = new ArrayList<Card>();
		Iterator<Integer> itr1 = rankList.iterator();
		while(itr1.hasNext())
		{
			Integer rankValue = itr1.next();
			Iterator<Card> itr2 = sourceCardList.iterator();
			while(itr2.hasNext())
			{
				Card card2=itr2.next();
				if (card2.GetRankValue(true) == rankValue)
				{
					destCardList.add(card2);
				}
			}
		}
		return destCardList.toArray(new Card[destCardList.size()]);
		
	}
	
	// Get the combinations which has the highest value of the specific rule
	public static Card[] GetMaxValuedHandOfRankingRule(RankingRule rule, List<Card[]> matchedHands)
	{
		if (matchedHands.size() > 0)
		{
			// If the ranking rule is low 8, filter out those not qualified
			List<Card[]> qualifiedHands = new ArrayList<Card[]>();
			if (rule instanceof Low8)
			{
				for (int i = 0; i < matchedHands.size(); ++i)
				{
					Card[] hand = matchedHands.get(i);
					if (rule.ApplyToHandOfCards(hand))
					{
						qualifiedHands.add(hand);
					}
				}
				if (qualifiedHands.isEmpty())
				{
					return null;
				}
			}
			else
			{
				qualifiedHands = matchedHands;
			}
			
			// Find the most valued hand
			Card[] cardsMaxValued = qualifiedHands.get(0);
			for (int i = 1; i < qualifiedHands.size(); ++i)
			{
		    	Card[] cards = qualifiedHands.get(i);
		    	// Find the better one
		    	if (rule.CompareCards(cards, cardsMaxValued) > 0)
		    	{
		    		cardsMaxValued = cards;
		    	}
			}
			return cardsMaxValued;
		}
		else
		{
			return null;
		}
	}
	
	// Get the combinations which has the highest value of the specific rule
	private static Map<RankingRule, Card[]> GetMaxValuedHandOfRankingRule(Map<RankingRule, List<Card[]>> map)
	{
		Map<RankingRule, Card[]> maxValuableMap = new HashMap<RankingRule, Card[]>();
    	Iterator<Map.Entry<RankingRule, List<Card[]>>> itr = map.entrySet().iterator();
    	while (itr.hasNext())
    	{
        	Map.Entry<RankingRule, List<Card[]>> entry = itr.next();
        	Card[] maxValuableHandOfTheRule = GetMaxValuedHandOfRankingRule(entry.getKey(), entry.getValue());
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


