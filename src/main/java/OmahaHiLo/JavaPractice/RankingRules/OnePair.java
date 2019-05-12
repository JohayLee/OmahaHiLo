package OmahaHiLo.JavaPractice.RankingRules;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.CardsRankingUtils;

public class OnePair extends RankingHigh {

	public OnePair() {
		super("One Pair");
	}

	public boolean ApplyToHandOfCards(Card[] aHandOfCards)
	{
		Map<Integer, Integer> mapRankToCount = CardsRankingUtils.GetRankToCountMap(aHandOfCards, true);
		Collection<Integer> occurenceCount = mapRankToCount.values();
		int NumberOfPairOccurence = Collections.frequency(occurenceCount, 2);
		return NumberOfPairOccurence == 1;
	}

	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		// Sort - put the rank kind with 2 identical at the beginning
		Card[] sortHandA = CardsRankingUtils.SortByRepetitionAhead(handA, 2);
		Card[] sortHandB = CardsRankingUtils.SortByRepetitionAhead(handB, 2);
		// Compare one by one
		return CardsRankingUtils.CompareCardsOneByOne(true, sortHandA, sortHandB, true);
	}

}
