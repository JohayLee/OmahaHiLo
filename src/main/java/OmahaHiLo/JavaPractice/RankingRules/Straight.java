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
		return true;
		
	}
	
	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		return PokerUtils.CompareStraightCards(handA, handB);
	}
	
}
