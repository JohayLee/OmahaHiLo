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
public class FilterByRankingRuleHighCardTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FilterByRankingRuleHighCardTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FilterByRankingRuleHighCardTest.class );
    }

    /**
     * Test filtering a hand of cards by 4-of-a-kind.
     */
    public void testAFilterByHighRankingRuleHighCard()
    {
    	// Test  data
    	Player playerA = new Player("HandA");
    	playerA.ReceiveCards("2s-4h-8c-6d");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("5s-3h-9c-As-Qc");
    	List<Card[]> handA=playerA.CombineAsFiveCards(boardCards.PickCards());
    	// Filter by ranking rule
    	Map<RankingRule, List<Card[]>> rankedListHandA = playerA.FilterByRankingRule(handA, OmahaComp.OmahaHiRankingRules[7]);
    	assertTrue(rankedListHandA.size() == 1);
    	
    }
    public void testBFilterByHighRankingRuleHighCard()
    {
    	// Test  data
    	Player player = new Player("HandB");
    	player.ReceiveCards("5h-Ks-Qs-8h");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("2s-3h-9c-As-Tc");
    	List<Card[]> hand=player.CombineAsFiveCards(boardCards.PickCards());
    	// Filter by ranking rule
    	Map<RankingRule, List<Card[]>> rankedListHand = player.FilterByRankingRule(hand, OmahaComp.OmahaHiRankingRules[7]);
    	assertTrue(rankedListHand.size() == 1);
    	
    }
 
}
