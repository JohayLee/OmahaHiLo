package OmahaHiLo.JavaPractice.RankingRules;

import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.PokerUtils;

public class Flush extends RankingHigh {

	public Flush() {
		super("Flush");
	}

	public boolean ApplyToHandOfCards(Card[] aHandOfCards)
	{
		// All the suits of the card shall be same
		return CheckIdenticalSuits(aHandOfCards);
	}
	
	public static boolean CheckIdenticalSuits(Card[] aHandOfCards)
	{
		// All the suits of the cards shall be identical
		for (int i = 0; i < aHandOfCards.length; ++i)
		{
			if (aHandOfCards[i].GetSuit() != aHandOfCards[0].GetSuit())
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
		handA = PokerUtils.SortCardsDescending(handA, true);
		handB = PokerUtils.SortCardsDescending(handB, true);
		return PokerUtils.CompareCardsOneByOne(true, handA, handB, true);
	}
	
}
