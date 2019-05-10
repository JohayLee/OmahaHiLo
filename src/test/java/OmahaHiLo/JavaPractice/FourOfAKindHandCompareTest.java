package OmahaHiLo.JavaPractice;

import OmahaHiLo.JavaPractice.RankingRules.FourOfAKind;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class FourOfAKindHandCompareTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FourOfAKindHandCompareTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FourOfAKindHandCompareTest.class );
    }


    /**
     * Test a hand of cards by FourOfAKind ranking rule.
     */
    public void testFourOfAKindHandCompare4RepetitionDiff()
    {
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("Jc-Kh-Kc-Kd-Ks");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("Qs-3d-Qh-Qd-Qc");
    	FourOfAKind rule = new FourOfAKind();
    	assertTrue(rule.CompareCards(cards1, cards2) > 0 );
    }
    public void testFourOfAKindHandCompareSingleDiff()
    {
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("3h-3d-3c-3s-Jc");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("3s-3h-3c-3d-Ad");
    	FourOfAKind rule = new FourOfAKind();
    	assertTrue(rule.CompareCards(cards1, cards2) < 0 );
    }
    public void testFourOfAKindHandCompareSame()
    {
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("3s-3h-3c-3d-Ad");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("3s-Ad-3h-3c-3d");
    	FourOfAKind rule = new FourOfAKind();
    	assertTrue(rule.CompareCards(cards1, cards2) == 0 );
    }
   
}
