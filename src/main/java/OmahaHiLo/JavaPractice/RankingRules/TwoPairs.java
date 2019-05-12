package OmahaHiLo.JavaPractice.RankingRules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.CardsRankingUtils;

public class TwoPairs extends RankingHigh {

	public TwoPairs() {
		super("Two Pairs");
	}

	public boolean ApplyToHandOfCards(Card[] aHandOfCards)
	{
		Map<Integer, Integer> mapRankToCount = CardsRankingUtils.GetRankToCountMap(aHandOfCards, true);
		Collection<Integer> counts = mapRankToCount.values();
		int NumberOfPairOccurence = Collections.frequency(counts, 2);
		return NumberOfPairOccurence == 2;
	}
	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		// Sort - put the rank kinds with two identical at the beginning
		Card[] sortHandA = CardsRankingUtils.SortByRepetitionAhead(handA, 2);
		Card[] sortHandB = CardsRankingUtils.SortByRepetitionAhead(handB, 2);
		// Compare 
		return CardsRankingUtils.CompareCardsOneByOne(true, sortHandA, sortHandB, true);
	}
	
}
