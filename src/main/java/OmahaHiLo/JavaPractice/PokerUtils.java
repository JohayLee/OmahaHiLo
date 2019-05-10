package OmahaHiLo.JavaPractice;

import java.util.*;

import OmahaHiLo.JavaPractice.RankingRules.Low8;
import OmahaHiLo.JavaPractice.RankingRules.RankingRule;
/** General poker programming utilities.
 * 
 */
public class PokerUtils {
	public static final int NUM_CARDS_OF_A_HAND = 5;
	public static final int INVALID_RANK_VALUE = 0;
	private static final Map<Character, Integer> mapRankCharToValueHigh = new HashMap<Character, Integer>() {
		{
			put('2', 2);
			put('3', 3);
			put('4', 4);
			put('5', 5);
			put('6', 6);
			put('7', 7);
			put('8', 8);
			put('9', 9);
			put('T', 10);
			put('J', 11);
			put('Q', 12);
			put('K', 13);
			put('A', 14);
		}
	};
	private static final Map<Character, Integer> mapRankCharToValueLow8 = new HashMap<Character, Integer>() {
		{
			put('A', 1);
			put('2', 2);
			put('3', 3);
			put('4', 4);
			put('5', 5);
			put('6', 6);
			put('7', 7);
			put('8', 8);
		}
	};
	
	public final static char[] RankChars = {
			'A', '2','3','4','5','6','7','8','9','T','J','Q','K'
			};
	
	public static int GetRankValue(char rankCharacter, boolean isHigh)
	{
		final Map<Character, Integer> map = (isHigh ? mapRankCharToValueHigh : mapRankCharToValueLow8);
        Iterator<Map.Entry<Character, Integer> >  itr = map.entrySet().iterator(); 
        // Iterate over the Map 
        while (itr.hasNext()) { 
        	Map.Entry<Character, Integer> entry = itr.next(); 
        	if (rankCharacter == entry.getKey()) {
        		return entry.getValue();
        	} 
        }
        return INVALID_RANK_VALUE;
	}

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
	public static Integer GetKeyFromValue(Map<Integer, Integer> map, Integer value) 
	{
		for (Integer obj : map.keySet()) 
		{
		      if (map.get(obj).equals(value)) 
		      {
		    	  return obj;
		      }
		 }
		 return null;
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
		Map<Integer, Integer> mapRankToCount = PokerUtils.GetRankToCountMap(hand, true);
		List<Integer> rankListAll = new ArrayList<Integer>();
		for (int i = 0; i < numRepetitionList.size(); ++i)
		{
			List<Integer> rankList = PokerUtils.GetKeyListFromValue(mapRankToCount, numRepetitionList.get(i));
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
	
	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	public static int CompareStraightCards(Card[] handA, Card[] handB)
	{
		
		// If a straight has rank 2, the Ace is considered as 1.
		boolean isAceTopRankingforHandA = CheckStraightHandContainsTwo(handA) ? false : true;
		boolean isAceTopRankingforHandB = CheckStraightHandContainsTwo(handB) ?  false : true;
		handA = PokerUtils.SortCardsDescending(handA, isAceTopRankingforHandA);
		handB = PokerUtils.SortCardsDescending(handB, isAceTopRankingforHandB);
		if (isAceTopRankingforHandA == isAceTopRankingforHandB)
		{
			return PokerUtils.CompareCardsOneByOne(isAceTopRankingforHandA, handA, handB, true);
		}
		else
		{
			return isAceTopRankingforHandA ? 1 : -1;
		}
	}
	
	public static boolean CheckStraightHandContainsTwo(Card[] hand)
	{
		for (int i = 0; i < hand.length; i++)
		{
			if (hand[i].GetRankValue(true) == 2)
			{
				return true;
			}
		}
		return false;
	}
	// Get the combinations which has the highest value of the specific rule
	public static Card[] GetMaxValuedHandOfRankingRule(RankingRule rule, List<Card[]> matchedHands)
	{
		if (matchedHands.size() > 0)
		{
			// Find the first valid hand of cards, check Low8 specifically
			Card[] cardsMaxValued = null;
			int validIndex = 0;
			do
			{
				cardsMaxValued = matchedHands.get(validIndex++);
			} while (!QualifyLow8(cardsMaxValued, rule) && validIndex <matchedHands.size());
			if (!QualifyLow8(cardsMaxValued, rule))
			{
				return null;
			}

			for (int i = 1; i < matchedHands.size(); ++i)
			{
		    	Card[] cards = matchedHands.get(i);
		    	// Specifically for Low8
		    	if (!QualifyLow8(cards, rule))
		    	{
		    		continue;
		    	}
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
	public static boolean QualifyLow8(Card[] cards, RankingRule rule)
	{
    	return (!(rule instanceof Low8) || rule.ApplyToHandOfCards(cards));
	}
	
}


