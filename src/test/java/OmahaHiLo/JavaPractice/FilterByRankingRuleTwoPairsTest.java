package OmahaHiLo.JavaPractice;

import java.util.List;
import java.util.Map;

import OmahaHiLo.JavaPractice.RankingRules.RankingRule;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class FilterByRankingRuleTwoPairsTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FilterByRankingRuleTwoPairsTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FilterByRankingRuleTwoPairsTest.class );
    }

    /**
     * Test filtering a hand of cards by 4-of-a-kind.
     */
    public void testAFilterByHighRankingRuleTwoPairs()
    {
    	// Test  data
    	Player player = new Player("HandA");
    	player.ReceiveCards("2s-4h-8c-6d");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ts-3h-9c-As-Ac");
    	TestHelper.CombineAndRank(player, boardCards);
    	assertTrue(player.highRanked.size() == 1 && player.highRanked.entrySet().iterator().next().getKey().GetName() == "One Pair");
    	assertTrue(player.low8Ranked.size() == 0 );
    	
    }
    public void testBFilterByHighRankingRuleTwoPairs()
    {
    	// Test  data
    	Player player = new Player("HandB");
    	player.ReceiveCards("Jh-Js-Qs-8h");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("2s-3h-9c-As-Ac");
    	TestHelper.CombineAndRank(player, boardCards);
    	assertTrue(player.highRanked.size() == 1 && player.highRanked.entrySet().iterator().next().getKey().GetName() == "Two Pairs");
    	assertTrue(player.low8Ranked.size() == 0);
    	
    }
 
}
