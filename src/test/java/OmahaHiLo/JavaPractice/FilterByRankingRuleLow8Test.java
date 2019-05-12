package OmahaHiLo.JavaPractice;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import OmahaHiLo.JavaPractice.RankingRules.Low8;
import OmahaHiLo.JavaPractice.RankingRules.RankingRule;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for filtering hand of cards by ranking rule.
 */
public class FilterByRankingRuleLow8Test 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FilterByRankingRuleLow8Test( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FlushHandCompareTest.class );
    }


    /**
     * Test filtering a hand of cards by low 8 ranking rule.
     */
    public void testFilterByRankingRuleLow8()
    {
    	// Mock data
    	List<Card[]> handB = CombineAsFiveCardsB();
    	//Player playerB = new Player("HandB");
    	// Filter by ranking low8
    	Map<RankingRule, List<Card[]>> rankedListHandB = CardsRankingUtils.FilterByRankingRule(handB, new Low8());
    	assertTrue(rankedListHandB.size() == 1);
    	List<Card[]> cardsList = rankedListHandB.entrySet().iterator().next().getValue();
    	Card[] cards0 = cardsList.get(0);
    	Card[] cardsSorted = CardsRankingUtils.SortCardsAscending(cards0, false);
    	String ranks = CardsRankingUtils.ConcatCardRankCharacterToString(cardsSorted);
    	assertTrue(ranks.compareTo("A2567") == 0);
    	
    	cards0 = cardsList.get(0);
    	cardsSorted = CardsRankingUtils.SortCardsDescending(cards0, false);
    	assertTrue(CardsRankingUtils.ConcatCardRankCharacterToString(cardsSorted).compareTo("7652A") == 0);
    }

    public void testFilterByRankingRuleLow8MaxValued()
    {
    	// Mock data
    	List<Card[]> handB = CombineAsFiveCardsB();
    	//Player playerB = new Player("HandB");
    	// Filter by ranking low8
    	Map<RankingRule, List<Card[]>> rankedListHandB = CardsRankingUtils.FilterByRankingRule(handB, new Low8());
    	assertTrue(rankedListHandB.size() == 1);
    	Iterator<Map.Entry<RankingRule, List<Card[]>>> itr = rankedListHandB.entrySet().iterator();
    	if (itr.hasNext())
    	{
        	Map.Entry<RankingRule, List<Card[]>> entry = itr.next();
        	Card[] cards = CardsRankingUtils.GetMaxValuedHandOfRankingRule(entry.getKey(), entry.getValue());
        	String ranks = CardsRankingUtils.ConcatCardRankCharacterToString(cards);
        	assertTrue(ranks.compareTo("A2567") == 0);
    		
    	}
    }

    // Helper method
    public List<Card[]>  CombineAsFiveCardsB()
    {
    	// Mock data
    	BoardCards boardCards = new BoardCards();
    	boardCards.SetCards("Ah-Kh-5s-2s-Qd");
    	Player playerB = new Player("HandB");
    	playerB.ReceiveCards("5c-5d-6c-7d");
 	
		// All combinations for the 2 out of the 4 received cards
		return playerB.CombineAsFiveCards(boardCards.PickCards());
    }
 
}
