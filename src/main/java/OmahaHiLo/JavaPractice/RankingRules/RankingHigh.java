package OmahaHiLo.JavaPractice.RankingRules;

import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.PokerUtils;

public abstract class RankingHigh implements RankingRule {
	private String ruleName;
	public RankingHigh(String ruleName)
	{
		this.ruleName = ruleName;
	}
	public String GetRuleName()
	{
		return ruleName;
	}
	public abstract boolean ApplyToHandOfCards(Card[] aHandOfCards);
	
	// Return - A=B: equal, A>B: positive value, A<B: negative value
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		handA = PokerUtils.SortCardsDescending(handA, true);
		handB = PokerUtils.SortCardsDescending(handB, true);
		return PokerUtils.CompareCardsOneByOne(true, handA, handB, true);
	}
	
	
}
