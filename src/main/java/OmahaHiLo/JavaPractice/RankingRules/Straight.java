package OmahaHiLo.JavaPractice.RankingRules;

import java.util.Arrays;

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
		// Try A as both top ranking 14 and low ranking 1
		if(CheckStraight(aHandOfCards, true))
		{
			return true;
		}
		else
		{
			return CheckStraight(aHandOfCards, false);
		}
		
	}
	public static boolean CheckStraight(Card[] aHandOfCards, boolean isAceTopRanking)
	{
		// Sort the cards based on ranks
		PokerUtils.SortCardsDescending(aHandOfCards, isAceTopRanking);
		
		// The delta between two elements shall be 1.
		for (int i = 0; i < aHandOfCards.length - 1; i++)
		{
			if (aHandOfCards[i+1].GetRankValue(isAceTopRanking) - aHandOfCards[i].GetRankValue(isAceTopRanking) != 1)
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
