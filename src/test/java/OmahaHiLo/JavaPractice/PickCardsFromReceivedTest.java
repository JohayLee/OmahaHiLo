package OmahaHiLo.JavaPractice;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for picking up 2 cards from palyer's received cards.
 */
public class PickCardsFromReceivedTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PickCardsFromReceivedTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PickCardsFromReceivedTest.class );
    }


    /**
     * Test picking up cards from palyer's received cards
     */
    public void testPickCardsFromReceived()
    {
    	Player player = new Player("PlayerTest");
    	player.ReceiveCards("Ac-Kd-Jd-3d");
    	List<Card[]> cardsList = player.PickCards();
        assertTrue( cardsList.size() == 6 );
        assertTrue( cardsList.get(0)[0].GetRankCharacter().equals('A'));
        assertTrue( cardsList.get(0)[0].GetSuit().equals('c'));
        assertTrue( cardsList.get(0)[1].GetRankCharacter().equals('K'));
        assertTrue( cardsList.get(0)[1].GetSuit().equals('d'));
        assertTrue( cardsList.get(1)[0].GetRankCharacter().equals('A'));
        assertTrue( cardsList.get(1)[0].GetSuit().equals('c'));
        assertTrue( cardsList.get(1)[1].GetRankCharacter().equals('J'));
        assertTrue( cardsList.get(1)[1].GetSuit().equals('d'));
        assertTrue( cardsList.get(2)[0].GetRankCharacter().equals('A'));
        assertTrue( cardsList.get(2)[0].GetSuit().equals('c'));
        assertTrue( cardsList.get(2)[1].GetRankCharacter().equals('3'));
        assertTrue( cardsList.get(2)[1].GetSuit().equals('d'));
        assertTrue( cardsList.get(3)[0].GetRankCharacter().equals('K'));
        assertTrue( cardsList.get(3)[0].GetSuit().equals('d'));
        assertTrue( cardsList.get(3)[1].GetRankCharacter().equals('J'));
        assertTrue( cardsList.get(3)[1].GetSuit().equals('d'));
        assertTrue( cardsList.get(4)[0].GetRankCharacter().equals('K'));
        assertTrue( cardsList.get(4)[0].GetSuit().equals('d'));
        assertTrue( cardsList.get(4)[1].GetRankCharacter().equals('3'));
        assertTrue( cardsList.get(4)[1].GetSuit().equals('d'));
        assertTrue( cardsList.get(5)[0].GetRankCharacter().equals('J'));
        assertTrue( cardsList.get(5)[0].GetSuit().equals('d'));
        assertTrue( cardsList.get(5)[1].GetRankCharacter().equals('3'));
        assertTrue( cardsList.get(5)[1].GetSuit().equals('d'));
    }
}
