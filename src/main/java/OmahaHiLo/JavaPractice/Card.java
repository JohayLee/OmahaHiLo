package OmahaHiLo.JavaPractice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
		return GetRankValue(rank, isAceTopRanking);
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
	  public int compareTo(Card c) 
	{
		return GetRankValue(!isAceAsOne) - c.GetRankValue(!c.isAceAsOne);
	}
	
	public static final int INVALID_RANK_VALUE = 0;
	private static final Map<Character, Integer> mapRankCharToValueHigh = new HashMap<Character, Integer>() {
		{
			put('2', 2);
			put('3', 3);
			put('4', 4);
			put('5', 5);
			put('6', 6);
			put('7', 7);
			put('8', 8);
			put('9', 9);
			put('T', 10);
			put('J', 11);
			put('Q', 12);
			put('K', 13);
			put('A', 14);
		}
	};
	private static final Map<Character, Integer> mapRankCharToValueLow8 = new HashMap<Character, Integer>() {
		{
			put('A', 1);
			put('2', 2);
			put('3', 3);
			put('4', 4);
			put('5', 5);
			put('6', 6);
			put('7', 7);
			put('8', 8);
		}
	};
	
	public final static char[] RankChars = {
			'A', '2','3','4','5','6','7','8','9','T','J','Q','K'
			};
	
	public static int GetRankValue(char rankCharacter, boolean isHigh)
	{
		final Map<Character, Integer> map = (isHigh ? mapRankCharToValueHigh : mapRankCharToValueLow8);
        Iterator<Map.Entry<Character, Integer> >  itr = map.entrySet().iterator(); 
        // Iterate over the Map 
        while (itr.hasNext()) { 
        	Map.Entry<Character, Integer> entry = itr.next(); 
        	if (rankCharacter == entry.getKey()) {
        		return entry.getValue();
        	} 
        }
        return INVALID_RANK_VALUE;
	}

	
}
