package OmahaHiLo.JavaPractice;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import OmahaHiLo.JavaPractice.RankingRules.Low8;
import OmahaHiLo.JavaPractice.RankingRules.RankingRule;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class FilterByRankingRuleLow8Test 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FilterByRankingRuleLow8Test( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FlushHandCompareTest.class );
    }


    /**
     * Test filtering a hand of cards by low 8 ranking rule.
     */
    public void testFilterByRankingRuleLow8()
    {
    	Player player = new Player("HandA");
    	player.ReceiveCards("5c-5d-6c-7d");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ah-Kh-5s-2s-Qd");
    	TestHelper.CombineAndRank(player, boardCards);
    	assertTrue(player.highRanked.size() == 1 && player.highRanked.entrySet().iterator().next().getKey().GetName() != "Flush");
    	assertTrue(player.low8Ranked.size() == 0);
    	
    }

 
}
