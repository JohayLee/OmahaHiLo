package OmahaHiLo.JavaPractice;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for picking up 3 cards from board cards.
 */
public class PickCardsFromBoardTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PickCardsFromBoardTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PickCardsFromBoardTest.class );
    }

    /**
     * Test picking up cards from board cards
     */
    public void testPickCardsFromBoard()
    {
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ah-Kh-5s-2s-Qd");
    	List<Card[]> cardsList = boardCards.PickCards();
        assertTrue( cardsList.size() == 10 );
        assertTrue( cardsList.get(0)[0].GetRankCharacter().equals('A'));
        assertTrue( cardsList.get(0)[0].GetSuit().equals('h'));
        assertTrue( cardsList.get(0)[1].GetRankCharacter().equals('K'));
        assertTrue( cardsList.get(0)[1].GetSuit().equals('h'));
        assertTrue( cardsList.get(0)[2].GetRankCharacter().equals('5'));
        assertTrue( cardsList.get(0)[2].GetSuit().equals('s'));
        assertTrue( cardsList.get(1)[0].GetRankCharacter().equals('A'));
        assertTrue( cardsList.get(1)[0].GetSuit().equals('h'));
        assertTrue( cardsList.get(1)[1].GetRankCharacter().equals('K'));
        assertTrue( cardsList.get(1)[1].GetSuit().equals('h'));
        assertTrue( cardsList.get(1)[2].GetRankCharacter().equals('2'));
        assertTrue( cardsList.get(1)[2].GetSuit().equals('s'));
        assertTrue( cardsList.get(2)[0].GetRankCharacter().equals('A'));
        assertTrue( cardsList.get(2)[0].GetSuit().equals('h'));
        assertTrue( cardsList.get(2)[1].GetRankCharacter().equals('K'));
        assertTrue( cardsList.get(2)[1].GetSuit().equals('h'));
        assertTrue( cardsList.get(2)[2].GetRankCharacter().equals('Q'));
        assertTrue( cardsList.get(2)[2].GetSuit().equals('d'));
        assertTrue( cardsList.get(3)[0].GetRankCharacter().equals('A'));
        assertTrue( cardsList.get(3)[0].GetSuit().equals('h'));
        assertTrue( cardsList.get(3)[1].GetRankCharacter().equals('5'));
        assertTrue( cardsList.get(3)[1].GetSuit().equals('s'));
        assertTrue( cardsList.get(3)[2].GetRankCharacter().equals('2'));
        assertTrue( cardsList.get(3)[2].GetSuit().equals('s'));
        assertTrue( cardsList.get(4)[0].GetRankCharacter().equals('A'));
        assertTrue( cardsList.get(4)[0].GetSuit().equals('h'));
        assertTrue( cardsList.get(4)[1].GetRankCharacter().equals('5'));
        assertTrue( cardsList.get(4)[1].GetSuit().equals('s'));
        assertTrue( cardsList.get(4)[2].GetRankCharacter().equals('Q'));
        assertTrue( cardsList.get(4)[2].GetSuit().equals('d'));
        assertTrue( cardsList.get(5)[0].GetRankCharacter().equals('A'));
        assertTrue( cardsList.get(5)[0].GetSuit().equals('h'));
        assertTrue( cardsList.get(5)[1].GetRankCharacter().equals('2'));
        assertTrue( cardsList.get(5)[1].GetSuit().equals('s'));
        assertTrue( cardsList.get(5)[2].GetRankCharacter().equals('Q'));
        assertTrue( cardsList.get(5)[2].GetSuit().equals('d'));
        assertTrue( cardsList.get(6)[0].GetRankCharacter().equals('K'));
        assertTrue( cardsList.get(6)[0].GetSuit().equals('h'));
        assertTrue( cardsList.get(6)[1].GetRankCharacter().equals('5'));
        assertTrue( cardsList.get(6)[1].GetSuit().equals('s'));
        assertTrue( cardsList.get(6)[2].GetRankCharacter().equals('2'));
        assertTrue( cardsList.get(6)[2].GetSuit().equals('s'));
        assertTrue( cardsList.get(7)[0].GetRankCharacter().equals('K'));
        assertTrue( cardsList.get(7)[0].GetSuit().equals('h'));
        assertTrue( cardsList.get(7)[1].GetRankCharacter().equals('5'));
        assertTrue( cardsList.get(7)[1].GetSuit().equals('s'));
        assertTrue( cardsList.get(7)[2].GetRankCharacter().equals('Q'));
        assertTrue( cardsList.get(7)[2].GetSuit().equals('d'));
        assertTrue( cardsList.get(8)[0].GetRankCharacter().equals('K'));
        assertTrue( cardsList.get(8)[0].GetSuit().equals('h'));
        assertTrue( cardsList.get(8)[1].GetRankCharacter().equals('2'));
        assertTrue( cardsList.get(8)[1].GetSuit().equals('s'));
        assertTrue( cardsList.get(8)[2].GetRankCharacter().equals('Q'));
        assertTrue( cardsList.get(8)[2].GetSuit().equals('d'));
        assertTrue( cardsList.get(9)[0].GetRankCharacter().equals('5'));
        assertTrue( cardsList.get(9)[0].GetSuit().equals('s'));
        assertTrue( cardsList.get(9)[1].GetRankCharacter().equals('2'));
        assertTrue( cardsList.get(9)[1].GetSuit().equals('s'));
        assertTrue( cardsList.get(9)[2].GetRankCharacter().equals('Q'));
        assertTrue( cardsList.get(9)[2].GetSuit().equals('d'));
    }
}
