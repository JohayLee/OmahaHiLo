package OmahaHiLo.JavaPractice.RankingRules;

import java.util.Arrays;
//import java.lang.Math;
import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.CardsRankingUtils;

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
		else if (CheckStraightHandContainsSpecificRankCharacter(aHandOfCards, 'A') 
				&& CheckStraightHandContainsSpecificRankCharacter(aHandOfCards, '2'))
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
		// Sort the cards before checking straight.
		aHandOfCards = CardsRankingUtils.SortCardsDescending(aHandOfCards, isAceTopRanking);
		// The delta between two elements shall be 1.
		for (int i = 0; i < aHandOfCards.length - 1; i++)
		{
			int nextRank = aHandOfCards[i+1].GetRankValue(isAceTopRanking);
			int thisRank = aHandOfCards[i].GetRankValue(isAceTopRanking);
			if (nextRank == Card.INVALID_RANK_VALUE || thisRank == Card.INVALID_RANK_VALUE)
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
	public static boolean CheckStraightHandContainsSpecificRankCharacter(Card[] hand, Character specificCharacter)
	{
		for (int i = 0; i < hand.length; i++)
		{
			if (hand[i].GetRankCharacter() == specificCharacter)
			{
				return true;
			}
		}
		return false;
	}
	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	public static int CompareStraightCards(Card[] handA, Card[] handB)
	{
		// Sort the cards before checking straight.
		boolean isAceTopRankingforHandA = CheckAceTopRankingInStraightHand(handA);
		boolean isAceTopRankingforHandB = CheckAceTopRankingInStraightHand(handB);
		// If both Ace is considered as top ranking, they are both either TJQKA or A2345, so equal
		if (isAceTopRankingforHandA == isAceTopRankingforHandB)
		{
			handA = CardsRankingUtils.SortCardsDescending(handA, isAceTopRankingforHandA);
			handB = CardsRankingUtils.SortCardsDescending(handB, isAceTopRankingforHandB);
			return CardsRankingUtils.CompareCardsOneByOne(isAceTopRankingforHandA, handA, handB, true);
		}
		// the one with Ace as top ranking bigger.
		else
		{
			return isAceTopRankingforHandA ? 1 : -1;
		}
	}
	
	public static boolean CheckAceTopRankingInStraightHand(Card[] hand)
	{
		// If the straight hand has both 'A' and '2', it is the A12345, consider A as lowest rank.
		if (CheckStraightHandContainsSpecificRankCharacter(hand, 'A') && CheckStraightHandContainsSpecificRankCharacter(hand, '2'))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static boolean CheckStraightHandContainsSpecificRankValue(Card[] hand, int specificRankValue)
	{
		for (int i = 0; i < hand.length; i++)
		{
			if (hand[i].GetRankValue(true) == specificRankValue)
			{
				return true;
			}
		}
		return false;
	}
	
	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		return CompareStraightCards(handA, handB);
	}
	
}
