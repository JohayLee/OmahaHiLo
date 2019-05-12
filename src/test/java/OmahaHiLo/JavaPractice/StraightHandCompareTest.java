package OmahaHiLo.JavaPractice;


import OmahaHiLo.JavaPractice.RankingRules.Straight;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class StraightHandCompareTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StraightHandCompareTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( StraightHandCompareTest.class );
    }


    /**
     * Test a hand of cards by high card ranking rule.
     */
    public void testStraightHandCompare()
    {
    	// Mock data
    	Card[] cards1 = Presentation.GetCardsFromNotationsString("Ah-Ks-Jc-Qh-Th");
    	Card[] cards2 = Presentation.GetCardsFromNotationsString("Qh-Js-Tc-9h-8d");
    	Straight r = new Straight();
    	assertTrue(r.CompareCards(cards1, cards2) > 0 );
    }
    public void testStraightHandCompareLess()
    {
    	// Mock data
    	Card[] cards1 = Presentation.GetCardsFromNotationsString("Ah-2s-3c-4h-5h");
    	Card[] cards2 = Presentation.GetCardsFromNotationsString("7h-6s-8c-9h-Td");
    	Straight r = new Straight();
    	assertTrue(r.CompareCards(cards1, cards2) < 0 );
    }
}
