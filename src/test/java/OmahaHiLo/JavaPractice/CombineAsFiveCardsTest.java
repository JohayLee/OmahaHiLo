package OmahaHiLo.JavaPractice;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for picking up 2 cards from palyer's received cards.
 */
public class CombineAsFiveCardsTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CombineAsFiveCardsTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CombineAsFiveCardsTest.class );
    }


    /**
     * Test combinations as a full hand from 2 cards from received and 3 from board
     */
    public void testCombineAsFiveCards()
    {
    	// Mock data
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ah-Kh-5s-2s-Qd");
    	Player playerA = new Player("HandA");
    	playerA.ReceiveCards("Ac-Kd-Jd-3d");
 	
 	
		// All combinations for the 2 out of the 4 received cards
		List<Card[]> cardsListA = playerA.CombineAsFiveCards(boardCards.PickCards());
        assertTrue( cardsListA.size() == 60 );

        assertTrue( cardsListA.get(0)[0].GetRankCharacter().equals('A'));
        assertTrue( cardsListA.get(0)[0].GetSuit().equals('c'));
        assertTrue( cardsListA.get(0)[1].GetRankCharacter().equals('K'));
        assertTrue( cardsListA.get(0)[1].GetSuit().equals('d'));
        assertTrue( cardsListA.get(0)[2].GetRankCharacter().equals('A'));
        assertTrue( cardsListA.get(0)[2].GetSuit().equals('h'));
        assertTrue( cardsListA.get(0)[3].GetRankCharacter().equals('K'));
        assertTrue( cardsListA.get(0)[3].GetSuit().equals('h'));
        assertTrue( cardsListA.get(0)[4].GetRankCharacter().equals('5'));
        assertTrue( cardsListA.get(0)[4].GetSuit().equals('s'));
        assertTrue( cardsListA.get(59)[0].GetRankCharacter().equals('J'));
        assertTrue( cardsListA.get(59)[0].GetSuit().equals('d'));
        assertTrue( cardsListA.get(59)[1].GetRankCharacter().equals('3'));
        assertTrue( cardsListA.get(59)[1].GetSuit().equals('d'));
        assertTrue( cardsListA.get(59)[2].GetRankCharacter().equals('5'));
        assertTrue( cardsListA.get(59)[2].GetSuit().equals('s'));
        assertTrue( cardsListA.get(59)[3].GetRankCharacter().equals('2'));
        assertTrue( cardsListA.get(59)[3].GetSuit().equals('s'));
        assertTrue( cardsListA.get(59)[4].GetRankCharacter().equals('Q'));
        assertTrue( cardsListA.get(59)[4].GetSuit().equals('d'));
    }
}
