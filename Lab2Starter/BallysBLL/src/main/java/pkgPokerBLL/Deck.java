package pkgPokerBLL;

import java.util.ArrayList;
import java.util.UUID;

import pkgPokerEnum.eRank;
import pkgPokerEnum.eSuit;

public class Deck {

	private UUID DeckID;
	private ArrayList<Card> DeckCards = new ArrayList<Card>();
	
	public Deck()
	{
		super();
		int cardNumber = 0;
		for (eSuit suit : eSuit.values()){ // four different suits 
			for (eRank Rank : eRank.values()) { // fourteen different cards of each suit
				DeckCards.add(new Card(Rank, suit));
				cardNumber = cardNumber + 1; // This counts each car. 
			}
		}

	}
	
	public Card DrawCard(){
		return DeckCards.remove(0); // return the first card from the deck
	}
	
	public int numberOfCards(){
		return this.DeckCards.size();
	}
}