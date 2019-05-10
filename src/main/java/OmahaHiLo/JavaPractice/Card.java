package OmahaHiLo.JavaPractice;

public class Card implements Comparable<Card> {
	// By default, The value of Ace is considered as 14.
	public Card(Character rank, Character suit)
	{
		this(rank, suit, false);
	}

	public Card(Character rank, Character suit, boolean isAceAsOne)
	{
		this.rank = rank;
		this.suit = suit;
		this.isAceAsOne = isAceAsOne;
	}
	
	private Character rank;
	private Character suit;
	private boolean isAceAsOne = false;
	public void SetAceAsOne(boolean isAceAsOne)
	{
		this.isAceAsOne = isAceAsOne;
	}
	
	public int GetRankValue(boolean isAceTopRanking)
	{
		return PokerUtils.GetRankValue(rank, isAceTopRanking);
	}
	public Character GetRankCharacter()
	{
		return rank;
	}
	public Character GetSuit()
	{
		return suit;
	}
	
	@Override
	  public int compareTo(Card c) {
		return GetRankValue(!isAceAsOne) - c.GetRankValue(!c.isAceAsOne);
	  }	
}
