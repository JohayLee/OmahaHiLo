package OmahaHiLo.JavaPractice;

import OmahaHiLo.JavaPractice.RankingRules.TwoPairs;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class TwoPairsHandCompareTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TwoPairsHandCompareTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TwoPairsHandCompareTest.class );
    }


    /**
     * Test a hand of cards by TwoPairs ranking rule.
     */
    public void testTwoPairsHandCompare2RepetitionDiff()
    {
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("Ad-Ah-Ks-Kh-Qs");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("Ts-Td-Jh-Jd-Ad");
    	TwoPairs rule = new TwoPairs();
    	assertTrue(rule.CompareCards(cards1, cards2) > 0 );
    }
    public void testTwoPairsHandCompareSingleDiff()
    {
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("8h-8c-Tc-Ts-Jc");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("Qs-Qh-4d-4s-7d");
    	TwoPairs rule = new TwoPairs();
    	assertTrue(rule.CompareCards(cards1, cards2) < 0 );
    }
    public void testTwoPairsHandCompareSame()
    {
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("8h-8c-Tc-Ts-Jc");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("Tc-8h-8c-Ts-Jd");
    	TwoPairs rule = new TwoPairs();
    	assertTrue(rule.CompareCards(cards1, cards2) == 0 );
    }

}
