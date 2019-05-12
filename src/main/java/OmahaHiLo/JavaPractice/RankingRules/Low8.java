package OmahaHiLo.JavaPractice.RankingRules;

import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.PokerUtils;

import java.util.HashSet;
import java.util.Set;

public class Low8 implements RankingRule 
{
	// Low8 ranking rule has no specific name. 
	public String GetName()
	{
		return null;
	}
		
	public boolean ApplyToHandOfCards(Card[] aHandOfCards)
	{
		// 1. None of the cards is higher than 8
		for (int i = 0; i < aHandOfCards.length; ++i)
		{
			if (aHandOfCards[i].GetRankValue(false) == Card.INVALID_RANK_VALUE || aHandOfCards[i].GetRankValue(false) > 8 )
			{
				return false;
			}
		}
		// 2. All cards should have different rank
		if (!DistinctRanks(aHandOfCards))
		{
			return false;
		}
		return true;
	}
	// Check if all distinct ranks
	private static boolean DistinctRanks(Card[] cards)
	{
	    Set<Character> foundRanks = new HashSet<Character>();
	    for (Card card : cards) 
	    {
	    	if (foundRanks.contains(card.GetRankCharacter()))
	    	{
	            return false;
	        }
	    	foundRanks.add(card.GetRankCharacter());
	    }              
	    return true;          
	}

	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		handA = PokerUtils.SortCardsDescending(handA, false);
		handB = PokerUtils.SortCardsDescending(handB, false);
		return PokerUtils.CompareCardsOneByOne(false, handA, handB, false);
	}

}
