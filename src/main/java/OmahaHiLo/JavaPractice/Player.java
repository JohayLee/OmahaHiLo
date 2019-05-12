package OmahaHiLo.JavaPractice;

import java.util.*;

import OmahaHiLo.JavaPractice.RankingRules.Low8;
import OmahaHiLo.JavaPractice.RankingRules.RankingHigh;
import OmahaHiLo.JavaPractice.RankingRules.RankingRule;

public class Player {
	public static final int TOTAL_RECEIVED_CARDS = 4;
	private	String playerName; // e.g., HandA, HandB
	private Card receivedCards[] = new Card[TOTAL_RECEIVED_CARDS];
	
	public Player(String playerName)
	{
		this.playerName = playerName;
	}
	public String GetName()
	{
		return playerName;
	}
	public void SetName(String playerName)
	{
		this.playerName = playerName;
	}
	
	public Map<RankingRule, Card[]> highRanked;
	public Map<RankingRule, Card[]> low8Ranked;
	
	// A cardNotationString contains card notations linked by a dash, e.g. Ac-Kd-Jd-3d
	public void ReceiveCards(String cardNotationsString)
	{
		ReceiveCards(PokerUtils.GetCardsFromNotationsString(cardNotationsString));		
	}
	public void ReceiveCards(Card[] cards)
	{
		receivedCards = Arrays.copyOf(cards, Math.min(receivedCards.length, cards.length));
	}
	
	public List<Card[]> PickCards() 
	{
		//Need to pick 2 cards from received cards.
		final int NUM_CARDS_TO_PICK = 2;
		List<Card[]> pickList = new ArrayList<Card[]>();
		int maxIndexOfFirstCard = receivedCards.length - NUM_CARDS_TO_PICK;
		for (int i = 0; i <= maxIndexOfFirstCard; i++) {
			for (int j = i + 1; j <= maxIndexOfFirstCard + 1; j++) {
				Card[] pickedCard = new Card[NUM_CARDS_TO_PICK];
				pickedCard[0] = receivedCards[i];
				pickedCard[1] = receivedCards[j];
				pickList.add(pickedCard);
			}
		}
		return pickList;
	}
	
	public List<Card[]> CombineAsFiveCards(List<Card[]> boardCardsList)
	{
		List<Card[]> fiveCardList = new ArrayList<Card[]>();
		List<Card[]> listOf2ReceivedCards = PickCards();
		for (int i = 0; i < listOf2ReceivedCards.size(); ++i)
		{
			Card[] receivedCards = listOf2ReceivedCards.get(i);
			for (int j = 0; j < boardCardsList.size(); ++j)
			{
				Card[] boardCards = boardCardsList.get(j);
				Card[] fiveCardCards = new Card[receivedCards.length + boardCards.length];
				System.arraycopy(receivedCards, 0, fiveCardCards, 0, receivedCards.length);
				System.arraycopy(boardCards, 0, fiveCardCards, receivedCards.length, boardCards.length);
				fiveCardList.add(fiveCardCards);
			}
		}
		
		return fiveCardList;
	}
	
}
