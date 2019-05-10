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
        return new TestSuite( FilterByRankingRuleThreeOfAKindTest.class );
    }

    /**
     * Test filtering a hand of cards by high Flush.
     */
    public void testAFilterByHighRankingRuleFlush()
    {
    	// Test  data
    	Player playerA = new Player("HandA");
    	playerA.ReceiveCards("Ac-Kd-Jd-3d");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ad-Kh-5s-2d-Qd");
    	List<Card[]> handA=playerA.CombineAsFiveCards(boardCards.PickCards());
    	// Filter by ranking rule
    	Map<RankingRule, List<Card[]>> rankedListHandA = playerA.FilterByRankingRule(handA, OmahaComp.OmahaHiRankingRules[3]);
    	assertTrue(rankedListHandA.size() == 1);
    	
    }
    public void testBFilterByHighRankingRuleFlush()
    {
    	// Test  data
    	Player player = new Player("HandB");
    	player.ReceiveCards("5c-5d-6c-6d");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ad-Kh-5s-2d-Qd");
    	List<Card[]> hand=player.CombineAsFiveCards(boardCards.PickCards());
    	// Filter by ranking rule
    	Map<RankingRule, List<Card[]>> rankedListHand = player.FilterByRankingRule(hand, OmahaComp.OmahaHiRankingRules[3]);
    	assertTrue(rankedListHand.size() == 1);
    	
    }
 
}
