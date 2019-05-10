package OmahaHiLo.JavaPractice;

import OmahaHiLo.JavaPractice.RankingRules.Flush;
import OmahaHiLo.JavaPractice.RankingRules.FourOfAKind;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class FlushHandCompareTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FlushHandCompareTest( String testName )
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
     * Test a hand of cards by flush ranking rule.
     */
    public void testFlushHandCompareDiff()
    {
    	// Mock data
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("3c-6c-Tc-Jc-Kc");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("2d-8d-4d-3d-Ad");
    	Flush rule = new Flush();
    	assertTrue(rule.CompareCards(cards1, cards2) < 0 );
    }
    public void testFourOfAKindHandCompareSame()
    {
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("2s-3h-8c-Ad-9d");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("3s-Ac-2h-9c-8c");
    	FourOfAKind rule = new FourOfAKind();
    	assertTrue(rule.CompareCards(cards1, cards2) == 0 );
    }
   
}
