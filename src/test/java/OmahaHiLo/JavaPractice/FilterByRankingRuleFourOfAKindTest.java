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
public class FilterByRankingRuleFourOfAKindTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FilterByRankingRuleFourOfAKindTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FilterByRankingRuleFourOfAKindTest.class );
    }

    /**
     * Test filtering a hand of cards by 4-of-a-kind.
     */
    public void testAFilterByHighRankingRuleFourOfAKind()
    {
    	// Test  data
    	Player playerA = new Player("HandA");
    	playerA.ReceiveCards("Ac-Kd-Jd-3d");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ad-Kh-5s-2d-Qd");
    	List<Card[]> handA=playerA.CombineAsFiveCards(boardCards.PickCards());
    	// Filter by ranking rule
    	Map<RankingRule, List<Card[]>> rankedListHandA = playerA.FilterByRankingRule(handA, OmahaComp.OmahaHiRankingRules[1]);
    	assertTrue(rankedListHandA.size() == 0);
    	
    }
    public void testBFilterByHighRankingRuleFourOfAKind()
    {
    	// Test  data
    	Player player = new Player("HandB");
    	player.ReceiveCards("5c-5d-6c-6d");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ad-5h-5s-2d-Qd");
    	List<Card[]> hand=player.CombineAsFiveCards(boardCards.PickCards());
    	// Filter by ranking rule
    	Map<RankingRule, List<Card[]>> rankedListHand = player.FilterByRankingRule(hand, OmahaComp.OmahaHiRankingRules[1]);
    	assertTrue(rankedListHand.size() == 1);
    	
    }
 
}
