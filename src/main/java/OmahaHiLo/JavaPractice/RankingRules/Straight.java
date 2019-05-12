package OmahaHiLo.JavaPractice.RankingRules;

import java.util.Arrays;
//import java.lang.Math;
import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.PokerUtils;

public class Straight extends RankingHigh {

	public Straight() {
		super("Straight");
	}

	public boolean ApplyToHandOfCards(Card[] aHandOfCards)
	{
		return CheckStraight(aHandOfCards);
	}
	
	public static boolean CheckStraight(Card[] aHandOfCards)
	{
		// Try A as both top ranking, i.e, bigger than K, if it is straight, it is other straight or TJQKA
		if(CheckStraight(aHandOfCards, true))
		{
			return true;
		}
		// If not straight when consider A as bigger than K, check if it is A2345
		else if (PokerUtils.CheckStraightHandContainsSpecificRankCharacter(aHandOfCards, 'A') 
				&& PokerUtils.CheckStraightHandContainsSpecificRankCharacter(aHandOfCards, '2'))
		{
			return CheckStraight(aHandOfCards, false);
		}
		// It is not a straight.
		else
		{
			return false;
		}
		
	}
	public static boolean CheckStraight(Card[] aHandOfCards, boolean isAceTopRanking)
	{
		// Sort the cards based on ranks
		boolean is23456 = false;
		boolean is65432 = false;
		if (aHandOfCards[0].GetRankValue(isAceTopRanking) == 2 
			&& aHandOfCards[1].GetRankValue(isAceTopRanking) == 3
			&& aHandOfCards[2].GetRankValue(isAceTopRanking) == 4
			&& aHandOfCards[3].GetRankValue(isAceTopRanking) == 5
			&& aHandOfCards[4].GetRankValue(isAceTopRanking) == 6)
		{
			is23456 = true;
		}
		aHandOfCards = PokerUtils.SortCardsDescending(aHandOfCards, isAceTopRanking);
		if (aHandOfCards[0].GetRankValue(isAceTopRanking) == 6 
			&& aHandOfCards[1].GetRankValue(isAceTopRanking) == 5
			&& aHandOfCards[2].GetRankValue(isAceTopRanking) == 4
			&& aHandOfCards[3].GetRankValue(isAceTopRanking) == 3
			&& aHandOfCards[4].GetRankValue(isAceTopRanking) == 2)
		{
			is65432 = true;
		}
		boolean isAny = false;
		if (is23456 || is65432)
		{
			isAny = true;
		}
		// The delta between two elements shall be 1.
		for (int i = 0; i < aHandOfCards.length - 1; i++)
		{
			int nextRank = aHandOfCards[i+1].GetRankValue(isAceTopRanking);
			int thisRank = aHandOfCards[i].GetRankValue(isAceTopRanking);
			if (nextRank == PokerUtils.INVALID_RANK_VALUE || thisRank == PokerUtils.INVALID_RANK_VALUE)
			{
				return false;
			}
			if (Math.abs(aHandOfCards[i+1].GetRankValue(isAceTopRanking) - aHandOfCards[i].GetRankValue(isAceTopRanking)) != 1)
			{
				return false;
			}
		}
		is23456 = false;
		is65432 = false;
		if (aHandOfCards[0].GetRankValue(isAceTopRanking) == 2 
			&& aHandOfCards[1].GetRankValue(isAceTopRanking) == 3
			&& aHandOfCards[2].GetRankValue(isAceTopRanking) == 4
			&& aHandOfCards[3].GetRankValue(isAceTopRanking) == 5
			&& aHandOfCards[4].GetRankValue(isAceTopRanking) == 6)
		{
			is23456 = true;
		}
		aHandOfCards = PokerUtils.SortCardsDescending(aHandOfCards, isAceTopRanking);
		if (aHandOfCards[0].GetRankValue(isAceTopRanking) == 6 
			&& aHandOfCards[1].GetRankValue(isAceTopRanking) == 5
			&& aHandOfCards[2].GetRankValue(isAceTopRanking) == 4
			&& aHandOfCards[3].GetRankValue(isAceTopRanking) == 3
			&& aHandOfCards[4].GetRankValue(isAceTopRanking) == 2)
		{
			is65432 = true;
		}
		isAny = false;
		if (is23456 || is65432)
		{
			isAny = true;
		}
		
		return true;
		
	}
	
	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		return PokerUtils.CompareStraightCards(handA, handB);
	}
	
}
