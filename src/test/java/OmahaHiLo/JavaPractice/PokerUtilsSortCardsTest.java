package OmahaHiLo.JavaPractice;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test.
 */
public class PokerUtilsSortCardsTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PokerUtilsSortCardsTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PokerUtilsSortCardsTest.class );
    }

    /**
     * testPokerUtilsHighRankSortCards
     */
    public void testPokerUtilsHighRankSortCardsAscending()
    {
    	// Test  data
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ad-Kh-Qs-2d-3c");
    	Card[] cards = boardCards.GetAllCards();
    	
    	// Sort the cards by rank high
    	boolean isHigh = true;
    	Card[] sortedCards = PokerUtils.SortCardsAscending(cards, isHigh);
    	assertTrue(sortedCards[0].GetRankValue(isHigh) == 2);
    	assertTrue(sortedCards[1].GetRankValue(isHigh) == 3);
    	assertTrue(sortedCards[2].GetRankValue(isHigh) == 12);
    	assertTrue(sortedCards[3].GetRankValue(isHigh) == 13);
    	assertTrue(sortedCards[4].GetRankValue(isHigh) == 14);
    }

    /**
     * testPokerUtilsHighRankSortCards
     */
    public void testPokerUtilsHighRankSortCardsDescending()
    {
    	// Test  data
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ad-Kh-Qs-2d-3c");
    	Card[] cards = boardCards.GetAllCards();
    	
    	// Sort the cards by rank high
    	boolean isHigh = true;
    	Card[] sortedCards = PokerUtils.SortCardsDescending(cards, isHigh);
    	assertTrue(sortedCards[0].GetRankValue(isHigh) == 14);
    	assertTrue(sortedCards[1].GetRankValue(isHigh) == 13);
    	assertTrue(sortedCards[2].GetRankValue(isHigh) == 12);
    	assertTrue(sortedCards[3].GetRankValue(isHigh) == 3);
    	assertTrue(sortedCards[4].GetRankValue(isHigh) == 2);
    }

}
