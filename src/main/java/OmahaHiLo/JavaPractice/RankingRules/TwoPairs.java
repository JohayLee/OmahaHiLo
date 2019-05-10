package OmahaHiLo.JavaPractice.RankingRules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.PokerUtils;

public class TwoPairs extends RankingHigh {

	public TwoPairs() {
		super("Two Pairs");
	}

	public boolean ApplyToHandOfCards(Card[] aHandOfCards)
	{
		Map<Integer, Integer> mapRankToCount = PokerUtils.GetRankToCountMap(aHandOfCards, true);
		Collection<Integer> counts = mapRankToCount.values();
		int NumberOfPairOccurence = Collections.frequency(counts, 2);
		return NumberOfPairOccurence == 2;
	}
	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		// Sort - put the rank kind with three of them at the beginning
		Card[] sortHandA = PokerUtils.SortByRepetitionAhead(handA, 2);
		Card[] sortHandB = PokerUtils.SortByRepetitionAhead(handB, 2);
		// Compare 
		return PokerUtils.CompareCardsOneByOne(true, sortHandA, sortHandB, true);
	}
	
//	public Card[] SortByRepetitionsWith2RanksAhead(Card[] hand)
//	{
//		Map<Integer, Integer> mapRankToCount = PokerUtils.GetRankToCountMap(hand, true);
//		List<Integer> rankList = PokerUtils.GetKeyListFromValue(mapRankToCount, 2);
//		Collections.sort(rankList);
//		Collections.reverse(rankList);
//		List<Integer> rankListAll = new ArrayList<Integer>();
//		List<Integer> rankListOneOccurence = PokerUtils.GetKeyListFromValue(mapRankToCount, 1);
//		Collections.sort(rankListOneOccurence);
//		Collections.reverse(rankListOneOccurence);
//		rankListAll.addAll(rankListOneOccurence);
//		// Pick up the cards based on the order
//		return PokerUtils.SortCardsByRankList(hand, rankListAll);
//	}
	
}
