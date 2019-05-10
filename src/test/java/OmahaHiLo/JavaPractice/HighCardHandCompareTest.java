package OmahaHiLo.JavaPractice;


import OmahaHiLo.JavaPractice.RankingRules.HighCard;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class HighCardHandCompareTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public HighCardHandCompareTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( HighCardHandCompareTest.class );
    }


    /**
     * Test a hand of cards by high card ranking rule.
     */
    public void testHighCardHandCompare()
    {
    	// Mock data
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("Ah-Ks-Jc-Qh-9h");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("Ah-Ks-Jc-Qh-7d");
    	HighCard r = new HighCard();
    	assertTrue(r.CompareCards(cards1, cards2) > 0 );
    }
    public void testHighCardHandCompareHighLess()
    {
    	// Mock data
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("Kh-Qs-Tc-Jh-8h");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("Ah-Ks-Jc-Qh-7d");
    	HighCard r = new HighCard();
    	assertTrue(r.CompareCards(cards1, cards2) < 0 );
    }
}
