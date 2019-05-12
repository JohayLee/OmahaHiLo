package OmahaHiLo.JavaPractice;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class BoardCards {
	private static final int TOTAL_BOARD_CARDS = 5;
	private Card boardCards[] = new Card[TOTAL_BOARD_CARDS];
	
	// A cardNotationString contains card notations linked by a dash, e.g. Ac-Kd-Jd-3d-Qd
	public void SetCards(String cardNotationsString)
	{
		SetCards(Presentation.GetCardsFromNotationsString(cardNotationsString));
	}
	public void SetCards(Card[] cards)
	{
		boardCards = Arrays.copyOf(cards, Math.min(boardCards.length, cards.length));
	}
	public Card[] GetAllCards()
	{
		return boardCards;
	}
	
	
	public List<Card[]> PickCards() 
	{
		final int NUM_CARDS_TO_PICK = 3;
		List<Card[]> pickList = new ArrayList<Card[]>();
		int maxIndexOfFirstCard = boardCards.length - NUM_CARDS_TO_PICK;
		for (int i = 0; i <= maxIndexOfFirstCard; i++) {
			for (int j = i + 1; j <= maxIndexOfFirstCard + 1; j++) {
				for (int k = j + 1; k <= maxIndexOfFirstCard + 2; k++) {
				Card[] pickedCard = new Card[NUM_CARDS_TO_PICK];
				pickedCard[0] = boardCards[i];
				pickedCard[1] = boardCards[j];
				pickedCard[2] = boardCards[k];
				pickList.add(pickedCard);
				}
			}
		}
		return pickList;
	}
	
}
