package OmahaHiLo.JavaPractice.RankingRules;

import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.CardsRankingUtils;

public abstract class RankingHigh implements RankingRule {
	private String ruleName;
	public RankingHigh(String ruleName)
	{
		this.ruleName = ruleName;
	}
	public String GetName()
	{
		return ruleName;
	}
	public abstract boolean ApplyToHandOfCards(Card[] aHandOfCards);
	
	// Return - A=B: equal, A>B: positive value, A<B: negative value
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		handA = CardsRankingUtils.SortCardsDescending(handA, true);
		handB = CardsRankingUtils.SortCardsDescending(handB, true);
		return CardsRankingUtils.CompareCardsOneByOne(true, handA, handB, true);
	}
	
	
}
