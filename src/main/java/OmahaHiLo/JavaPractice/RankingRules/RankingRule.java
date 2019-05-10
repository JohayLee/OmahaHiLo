package OmahaHiLo.JavaPractice.RankingRules;

import OmahaHiLo.JavaPractice.Card;

public interface RankingRule {
	// Check if successfully apply the ranking rule, e.g., 3-of-a-Kind, Flush, Straight Flush etc., or Low 8.
	public boolean ApplyToHandOfCards(Card[] aHandOfCards);
	// Compare two hands of cards. Return: Positive: A is greater than B; 0: A is equals to B; Negative: A is less than B.
	public int CompareCards(Card[] handA, Card[] handB);
}
