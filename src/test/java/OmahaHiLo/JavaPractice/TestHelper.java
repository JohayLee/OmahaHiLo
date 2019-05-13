package OmahaHiLo.JavaPractice;

public class TestHelper {
    public static void CombineAndRank(Player player, BoardCards boardCards)
    {
    	// Combine a hand of cards
		player.CombineAsFiveCards(boardCards.PickCards());
		// Set ranking rules
    	player.SetRankingRules(OmahaComp.omahaHiRankingRules, OmahaComp.low8);
    	// Filter by ranking rules
		player.highRanked = player.FilterByTopRankingRule();
		player.low8Ranked = player.FilterByLow8();
    	
    }

}
