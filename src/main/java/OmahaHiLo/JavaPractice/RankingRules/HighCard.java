package OmahaHiLo.JavaPractice.RankingRules;

import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.PokerUtils;

public class HighCard extends RankingHigh {

	public HighCard() {
		super("High Card");
	}

	public boolean ApplyToHandOfCards(Card[] aHandOfCards)
	{
		return true; // Any combinations
	}

	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		handA = PokerUtils.SortCardsDescending(handA, true);
		handB = PokerUtils.SortCardsDescending(handB, true);
		return PokerUtils.CompareCardsOneByOne(true, handA, handB, true);
	}
}
