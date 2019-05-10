package OmahaHiLo.JavaPractice;

import OmahaHiLo.JavaPractice.RankingRules.FullHouse;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class FullHouseHandCompareTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FullHouseHandCompareTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FullHouseHandCompareTest.class );
    }


    /**
     * Test a hand of cards by FullHouse ranking rule.
     */
    public void testFullHouseHandCompare3RepetitionDiff()
    {
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("Kc-Ah-Ac-Kc-As");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("Ks-Ad-Kh-Kd-Ad");
    	FullHouse rule = new FullHouse();
    	assertTrue(rule.CompareCards(cards1, cards2) > 0 );
    }
    public void testFullHouseHandCompare2RepetitionDiff()
    {
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("3h-Kc-3c-3s-Ks");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("3s-3h-Ad-3d-Ad");
    	FullHouse rule = new FullHouse();
    	assertTrue(rule.CompareCards(cards1, cards2) < 0 );
    }
    public void testFullHouseHandCompareSame()
    {
    	Card[] cards1 = PokerUtils.GetCardsFromNotationsString("8h-8c-Tc-Ts-8c");
    	Card[] cards2 = PokerUtils.GetCardsFromNotationsString("Tc-8h-8c-Ts-8d");
    	FullHouse rule = new FullHouse();
    	assertTrue(rule.CompareCards(cards1, cards2) == 0 );
    }
 
}
