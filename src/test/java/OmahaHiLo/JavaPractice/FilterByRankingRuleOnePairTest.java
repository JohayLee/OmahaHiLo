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
public class FilterByRankingRuleOnePairTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FilterByRankingRuleOnePairTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FilterByRankingRuleOnePairTest.class );
    }

    /**
     * Test filtering a hand of cards by 4-of-a-kind.
     */
    public void testAFilterByHighRankingRuleTwoPairs()
    {
    	// Test  data
    	Player playerA = new Player("HandA");
    	playerA.ReceiveCards("2s-4h-8c-6d");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ts-3h-9c-As-Qc");
    	List<Card[]> handA=playerA.CombineAsFiveCards(boardCards.PickCards());
    	// Filter by ranking rule
    	Map<RankingRule, List<Card[]>> rankedListHandA = PokerUtils.FilterByRankingRule(handA, OmahaComp.OmahaHiRankingRules[6]);
    	assertTrue(rankedListHandA.size() == 0);
    	
    }
    public void testBFilterByHighRankingRuleTwoPairs()
    {
    	// Test  data
    	Player player = new Player("HandB");
    	player.ReceiveCards("Jh-Ts-Qs-8h");
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("2s-8h-9c-As-Tc");
    	List<Card[]> hand=player.CombineAsFiveCards(boardCards.PickCards());
    	// Filter by ranking rule
    	Map<RankingRule, List<Card[]>> rankedListHand = PokerUtils.FilterByRankingRule(hand, OmahaComp.OmahaHiRankingRules[6]);
    	assertTrue(rankedListHand.size() == 1);
    	
    }
 
}
