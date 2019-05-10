package OmahaHiLo.JavaPractice;

import OmahaHiLo.JavaPractice.RankingRules.OnePair;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class OnePairHandCompareTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OnePairHandCompareTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( OnePairHandCompareTest.class );
    }


    /**
     * Test a hand of cards by OnePair ranking rule.
     */
    public void testOnePairHandCompare2RepetitionDiff()
    {
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("Jd-Ah-Qs-Kh-As");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("Ks-3d-Kh-Td-Ad");
    	OnePair rule = new OnePair();
    	assertTrue(rule.CompareCards(cards1, cards2) > 0 );
    }
    public void testOnePairHandCompareSingleDiff()
    {
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("4h-Kc-3c-3s-Jc");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("3s-2h-4d-3d-Ad");
    	OnePair rule = new OnePair();
    	assertTrue(rule.CompareCards(cards1, cards2) < 0 );
    }
    public void testOnePairHandCompareSame()
    {
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("4h-Kc-3c-3s-Jc");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("3s-3h-4d-Jd-Kd");
    	OnePair rule = new OnePair();
    	assertTrue(rule.CompareCards(cards1, cards2) == 0 );
    }
}
