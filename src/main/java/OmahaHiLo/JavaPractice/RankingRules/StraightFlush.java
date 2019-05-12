package OmahaHiLo.JavaPractice.RankingRules;

import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.PokerUtils;

public class StraightFlush extends RankingHigh {

	public StraightFlush() {
		super("Straight Flush");
	}

	public boolean ApplyToHandOfCards(Card[] aHandOfCards)
	{
		return Flush.CheckIdenticalSuits(aHandOfCards) && Straight.CheckStraight(aHandOfCards);
	}

	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		return Straight.CompareStraightCards(handA, handB);
	}
}
