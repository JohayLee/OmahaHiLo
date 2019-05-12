package OmahaHiLo.JavaPractice.RankingRules;

import java.util.Map;

import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.CardsRankingUtils;

public class FullHouse extends RankingHigh {

	public FullHouse() {
		super("Full House");
	}

	public boolean ApplyToHandOfCards(Card[] aHandOfCards)
	{
		Map<Integer, Integer> mapRankToCount = CardsRankingUtils.GetRankToCountMap(aHandOfCards, true);
		return mapRankToCount.containsValue(3) && mapRankToCount.containsValue(2);
	}

	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		// Sort - put the rank kinds with three identical at the beginning
		Card[] sortHandA = CardsRankingUtils.SortByRepetitionAhead(handA, 3);
		Card[] sortHandB = CardsRankingUtils.SortByRepetitionAhead(handB, 3);
		// Compare
		return CardsRankingUtils.CompareCardsOneByOne(true, sortHandA, sortHandB, true);
	}

}
