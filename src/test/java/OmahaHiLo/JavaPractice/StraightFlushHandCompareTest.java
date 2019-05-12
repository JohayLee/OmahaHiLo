package OmahaHiLo.JavaPractice;


import OmahaHiLo.JavaPractice.RankingRules.StraightFlush;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class StraightFlushHandCompareTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StraightFlushHandCompareTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( StraightFlushHandCompareTest.class );
    }


    /**
     * Test a hand of cards by high card ranking rule.
     */
    public void testStraightFlushHandCompare()
    {
    	// Mock data
    	Card[] cards1 = Presentation.GetCardsFromNotationsString("Ah-Kh-Jh-Qh-Th");
    	Card[] cards2 = Presentation.GetCardsFromNotationsString("Qs-Js-Ts-9s-8s");
    	StraightFlush r = new StraightFlush();
    	assertTrue(r.CompareCards(cards1, cards2) > 0 );
    }
    public void testStraightFlushHandCompareLess()
    {
    	// Mock data
    	Card[] cards1 = Presentation.GetCardsFromNotationsString("Ad-2d-3d-4d-5d");
    	Card[] cards2 = Presentation.GetCardsFromNotationsString("7c-6c-8c-9c-Tc");
    	StraightFlush r = new StraightFlush();
    	assertTrue(r.CompareCards(cards1, cards2) < 0 );
    }
    public void testStraightFlushA1HandCompareSame()
    {
    	// Mock data
    	Card[] cards1 = Presentation.GetCardsFromNotationsString("Ad-2d-3d-4d-5d");
    	Card[] cards2 = Presentation.GetCardsFromNotationsString("5c-2c-Ac-4c-3c");
    	StraightFlush r = new StraightFlush();
    	assertTrue(r.CompareCards(cards1, cards2) == 0 );
    }
    public void testStraightFlushHandCompareSame()
    {
    	// Mock data
    	Card[] cards1 = Presentation.GetCardsFromNotationsString("Ah-Kh-Jh-Qh-Th");
    	Card[] cards2 = Presentation.GetCardsFromNotationsString("Qs-Js-Ts-Ks-As");
    	StraightFlush r = new StraightFlush();
    	assertTrue(r.CompareCards(cards1, cards2) == 0 );
    }
    
}
