package OmahaHiLo.JavaPractice;


import OmahaHiLo.JavaPractice.RankingRules.Low8;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class Low8HandCompareTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Low8HandCompareTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( Low8HandCompareTest.class );
    }


    /**
     * Test a hand of cards by low 8 ranking rule.
     */
    public void testLow8HandCompare()
    {
    	// Mock data
    	Card[] cards1 = Presentation.GetCardsFromNotationsString("Ah-7d-6c-2s-5c");
    	Card[] cards2 = Presentation.GetCardsFromNotationsString("3d-8s-4c-2s-5c");
    	Low8 low8 = new Low8();
    	assertTrue(low8.CompareCards(cards1, cards2) > 0 );
    }
    public void testLow8HandCompareSame()
    {
    	// Mock data
    	Card[] cards1 = Presentation.GetCardsFromNotationsString("Ah-7d-6c-2s-5c");
    	Card[] cards2 = Presentation.GetCardsFromNotationsString("2d-6s-7c-5s-Ac");
    	Low8 low8 = new Low8();
    	assertTrue(low8.CompareCards(cards1, cards2) == 0 );
    }
}
