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
public class FilterByRankingRuleFlushTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FilterByRankingRuleFlushTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FilterByRankingRuleFlushTest.class );
    }

    /**
     * Test filtering a hand of cards by flush.
     */
    public void testFilterByRankingRuleFlush()
    {
    	// Test  data
    	Player player = new Player("HandA");
    	player.ReceiveCards("Ac-Kd-Jd-3d");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ad-Kh-5s-2d-Qd");
    	TestHelper.CombineAndRank(player, boardCards);
    	assertTrue(player.highRanked.size() == 1 && player.highRanked.entrySet().iterator().next().getKey().GetName() == "Flush");
    	assertTrue(player.low8Ranked.size() == 0);
    	
    }
    
 
}
