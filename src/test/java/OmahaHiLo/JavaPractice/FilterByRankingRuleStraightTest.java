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
public class FilterByRankingRuleStraightTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FilterByRankingRuleStraightTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FilterByRankingRuleStraightTest.class );
    }

    /**
     * Test filtering a hand of cards by high straight.
     */
    public void testAFilterByHighRankingRuleStraight()
    {
    	// Test  data
    	Player player = new Player("HandA");
    	player.ReceiveCards("Qc-Jd-Td-3d");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ad-Kh-Qs-2d-3c");
    	TestHelper.CombineAndRank(player, boardCards);
    	assertTrue(player.highRanked.size() == 1 && player.highRanked.entrySet().iterator().next().getKey().GetName() == "Straight");
    	assertTrue(player.low8Ranked.size() == 0);
    	
    }
    public void testBFilterByHighRankingRuleStraight()
    {
    	// Test  data
    	Player player = new Player("HandB");
    	player.ReceiveCards("Tc-Jc-8h-6d");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ad-Kh-Qs-2d-3c");
    	TestHelper.CombineAndRank(player, boardCards);
    	assertTrue(player.highRanked.size() == 1 && player.highRanked.entrySet().iterator().next().getKey().GetName() == "Straight");
    	assertTrue(player.low8Ranked.size() == 1 && player.low8Ranked.entrySet().iterator().next().getKey().GetName() == "Low8");
    	
    }
 
}
