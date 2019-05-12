package OmahaHiLo.JavaPractice;

import OmahaHiLo.JavaPractice.RankingRules.ThreeOfAKind;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class ThreeOfAKindHandCompareTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ThreeOfAKindHandCompareTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ThreeOfAKindHandCompareTest.class );
    }


    /**
     * Test a hand of cards by ThreeOfAKind ranking rule.
     */
    public void testThreeOfAKindHandCompare3RepetitionDiff()
    {
    	Card[] cards1 = Presentation.GetCardsFromNotationsString("Jc-Ah-Ac-Kc-As");
    	Card[] cards2 = Presentation.GetCardsFromNotationsString("Ks-3d-Kh-Kd-Ad");
    	ThreeOfAKind rule = new ThreeOfAKind();
    	assertTrue(rule.CompareCards(cards1, cards2) > 0 );
    }
    public void testThreeOfAKindHandCompareSingleDiff()
    {
    	Card[] cards1 = Presentation.GetCardsFromNotationsString("3h-Kc-3c-3s-Jc");
    	Card[] cards2 = Presentation.GetCardsFromNotationsString("3s-3h-4d-3d-Ad");
    	ThreeOfAKind rule = new ThreeOfAKind();
    	assertTrue(rule.CompareCards(cards1, cards2) < 0 );
    }
    public void testThreeOfAKindHandCompareSame()
    {
    	Card[] cards1 = Presentation.GetCardsFromNotationsString("3h-Kc-3c-3s-Jc");
    	Card[] cards2 = Presentation.GetCardsFromNotationsString("Jh-3d-Kc-3c-3s");
    	ThreeOfAKind rule = new ThreeOfAKind();
    	assertTrue(rule.CompareCards(cards1, cards2) == 0 );
    }
 
}
