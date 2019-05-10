package OmahaHiLo.JavaPractice.RankingRules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import OmahaHiLo.JavaPractice.Card;
import OmahaHiLo.JavaPractice.PokerUtils;

public class ThreeOfAKind extends RankingHigh {

	public ThreeOfAKind() {
		super("3-of-a-Kind");
	}

	public boolean ApplyToHandOfCards(Card[] aHandOfCards)
	{
		Map<Integer, Integer> mapRankToCount = PokerUtils.GetRankToCountMap(aHandOfCards, true);
		return mapRankToCount.containsValue(3);
	}
	// Return - A=B: equal, A>B: positive value, A<B: negative value 
	@Override
	public int CompareCards(Card[] handA, Card[] handB)
	{
		// Sort - put the rank kind with three of them at the beginning
		Card[] sortHandA = PokerUtils.SortByRepetitionAhead(handA, 3);
		Card[] sortHandB = PokerUtils.SortByRepetitionAhead(handB, 3);
		// Compare
		return PokerUtils.CompareCardsOneByOne(true, sortHandA, sortHandB, true);
	}
	
}
