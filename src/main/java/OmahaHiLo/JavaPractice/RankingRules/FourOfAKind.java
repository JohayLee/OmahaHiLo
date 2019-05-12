package OmahaHiLo.JavaPractice.RankingRules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.CardsRankingUtils;

public class FourOfAKind extends RankingHigh {

	public FourOfAKind() {
		super("4-of-a-Kind");
	}

	public boolean ApplyToHandOfCards(Card[] aHandOfCards)
	{
		Map<Integer, Integer> mapRankToCount = CardsRankingUtils.GetRankToCountMap(aHandOfCards, true);
		return mapRankToCount.containsValue(4);
	}
	
	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		// Sort - put the rank kinds with 4 identical of them at the beginning
		Card[] sortHandA = CardsRankingUtils.SortByRepetitionAhead(handA, 4);
		Card[] sortHandB = CardsRankingUtils.SortByRepetitionAhead(handB, 4);
		// Compare 
		return CardsRankingUtils.CompareCardsOneByOne(true, sortHandA, sortHandB, true);
	}
}
