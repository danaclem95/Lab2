package pkgPokerBLL;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.net.ssl.SSLEngineResult.HandshakeStatus;

import pkgPokerEnum.eCardNo;
import pkgPokerEnum.eHandStrength;
import pkgPokerEnum.eRank;
import pkgPokerEnum.eSuit;



public class Hand {

	private UUID HandID;
    private boolean bIsScored;
	private HandScore HS;
	private ArrayList<Card> CardsInHand = new ArrayList<Card>();
	
	public Hand()
	{
		
	}
	
	public void AddCardToHand(Card c)
	{
		CardsInHand.add(c);
	}

	public ArrayList<Card> getCardsInHand() {
		return CardsInHand;
	}
	
	public HandScore getHandScore()
	{
		return HS;
	}
	
	public Hand EvaluateHand()
	{
		Hand h = Hand.EvaluateHand(this);
		return h;
	}
	
	private static Hand EvaluateHand(Hand h)  {

		Collections.sort(h.getCardsInHand());
		
		//	Another way to sort
		//	Collections.sort(h.getCardsInHand(), Card.CardRank);
		
		HandScore hs = new HandScore();
		try {
			Class<?> c = Class.forName("pkgPokerBLL.Hand");

			for (eHandStrength hstr : eHandStrength.values()) {
				Class[] cArg = new Class[2];
				cArg[0] = pkgPokerBLL.Hand.class;
				cArg[1] = pkgPokerBLL.HandScore.class;

				Method meth = c.getMethod(hstr.getEvalMethod(), cArg);
				Object o = meth.invoke(null, new Object[] { h, hs });

				// If o = true, that means the hand evaluated- skip the rest of
				// the evaluations
				if ((Boolean) o) {
					break;
				}
			}

			h.bIsScored = true;
			h.HS = hs;

		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return h;
	}
	
	public static boolean isFlush(ArrayList<Card> cards){
		// Making this just kinda makes it easier for the rest.
		// A flush is when all the cards have the same suit. 
		boolean isFlush = false;
		int a = 0;
		for (eSuit b : eSuit.values()){
			a = 0;
			for (Card c : cards){
				if (c.geteSuit() == b){
					a ++;
				}
				
				if (a == 5){
					isFlush = true;
					break;
				}
			}
		}
		return isFlush;
	}
	
	public static boolean isStraight(ArrayList<Card> cards, Card c) {
		boolean isStraight = false;
		int i = (Hand.isAce(cards)) ? eCardNo.SecondCard.getCardNo() : eCardNo.FirstCard.getCardNo();

		for (; i < 4; i++) {
			if (cards.get(i).geteRank().getiRankNbr() - 1 == cards.get(i + 1).geteRank().getiRankNbr()) {
				isStraight = true;
			} else {
				isStraight = false;
			}
		}
		
		if (isStraight) {
			if (cards.get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE) {
				if (cards.get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.KING) {
					c.seteRank(cards.get(eCardNo.FirstCard.getCardNo()).geteRank());
					c.seteSuit(cards.get(eCardNo.FirstCard.getCardNo()).geteSuit());
				} else if (cards.get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.FIVE) {
					c.seteRank(cards.get(eCardNo.SecondCard.getCardNo()).geteRank());
					c.seteSuit(cards.get(eCardNo.SecondCard.getCardNo()).geteSuit());

				}
			} else {
				c.seteRank(cards.get(eCardNo.FirstCard.getCardNo()).geteRank());
				c.seteSuit(cards.get(eCardNo.FirstCard.getCardNo()).geteSuit());
			}
		}

		return isStraight;
	}
	
	

	public static boolean isAce(ArrayList<Card> cards) {
		if ((cards.get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE) && (cards.get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.KING) || (cards.get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.FIVE)) {
			return true;
		} else {
			return false;
		}
	}
	

	public static boolean isHandRoyalFlush(Hand h, HandScore hs)
	// Royal flush is when the hand is a 10, J, Q, K, and A of the same suit. CRAZY. 
	{
		boolean isHandRoyalFlush = false;
		Card card = new Card();
		if ((Hand.isFlush(h.getCardsInHand())) && (Hand.isStraight(h.getCardsInHand(), card)) && (Hand.isAce(h.getCardsInHand()))){
			hs.setHandStrength(eHandStrength.RoyalFlush);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			isHandRoyalFlush = true;
		}
		
		return isHandRoyalFlush;	
	}
	
	public static boolean isHandStraightFlush(Hand h, HandScore hs){
		boolean isHandStraightFlush = false;
		
		Card card = new Card();
		if ((Hand.isFlush(h.getCardsInHand())) && (Hand.isStraight(h.getCardsInHand(), card))){
			hs.setHandStrength(eHandStrength.StraightFlush);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			isHandStraightFlush = true;
		}
		return isHandStraightFlush;
	}	

	public static boolean isHandFourOfAKind(Hand h, HandScore hs){
		boolean isHandFourofAKind = false;
		
		ArrayList<Card> hand = new ArrayList<Card>();
		// Comparing first and fourth
		if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()){
			isHandFourofAKind = true;
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hand.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		}
		// Comparing fifth and second
		else if (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank()){
			isHandFourofAKind = true;
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hand.add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
		}
		if (isHandFourofAKind){
			hs.setHandStrength(eHandStrength.FourOfAKind);
			hs.setLoHand(null);
			hs.setKickers(hand);
		}
		return isHandFourofAKind;
	}	
	
	public static boolean isHandFlush(Hand h, HandScore hs){
		boolean isHandFlush = false;
		if (isFlush(h.getCardsInHand())){
			hs.setHandStrength(eHandStrength.Flush);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
			
			isHandFlush = true;
		}
		
		return isHandFlush;
	}		

	public static boolean isHandStraight(Hand h, HandScore hs)
	{
		boolean isHandStraight = false;
		
		Card card = new Card();
		if (isStraight(h.getCardsInHand(), card)){
			hs.setHandStrength(eHandStrength.Straight);
			hs.setLoHand(null);
			hs.setHiHand(card.geteRank());
			isHandStraight = true;
		}
		return isHandStraight;
	}	
	
	public static boolean isHandThreeOfAKind(Hand h, HandScore hs)
	{
		boolean isHandThreeOfAKind = false;
		ArrayList<Card> cards = new ArrayList<Card>();
		if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank()){
			isHandThreeOfAKind = true;
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			cards.add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			cards.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		}
		else if (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()){
			isHandThreeOfAKind = true;
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			cards.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
			cards.add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
		}
		else if (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank()){
			isHandThreeOfAKind = true;
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			cards.add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			cards.add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
		}
		
		if (isHandThreeOfAKind){
			hs.setHandStrength(eHandStrength.ThreeOfAKind);
			hs.setLoHand(null);
			hs.setKickers(cards);
			
		}
		return isHandThreeOfAKind;
	}		
	
	public static boolean isHandTwoPair(Hand h, HandScore hs)
	{
		boolean isHandTwoPair = false;
		
		ArrayList<Card> cards = new ArrayList<Card>();
		
		// one and two, three and four
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank()) && (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank())) {
			isHandTwoPair = true;
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			cards.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		}
		// one and two, four and five
		else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank()) && (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isHandTwoPair = true;
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
			cards.add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
		}
		
		// two and three, four and five
		else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank()) && (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isHandTwoPair = true;
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
			cards.add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
		}
		
		if (isHandTwoPair){
			hs.setHandStrength(eHandStrength.TwoPair);
			hs.setKickers(cards);
		}
		
		return isHandTwoPair;
	}	
	
	public static boolean isHandPair(Hand h, HandScore hs){
		boolean isHandPair = false;
		
		ArrayList<Card> cards = new ArrayList<Card>();
		// one and two
		if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank())){
			isHandPair = true;
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			cards.add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
			cards.add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			cards.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		}
		// two and three
		else if (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank())){
			isHandPair = true;
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			cards.add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			cards.add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			cards.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		}
		// three and four
		else if (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank())){
			isHandPair = true;
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			cards.add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			cards.add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
			cards.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		}
		// four and five
		else if (h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank() == (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank())){
			isHandPair = true;
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
			cards.add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			cards.add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
			cards.add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
		}
		
		if (isHandPair){
			hs.setHandStrength(eHandStrength.Pair);
			hs.setLoHand(null);
			hs.setKickers(cards);
		}
		
		return isHandPair;
	}	
	
	public static boolean isHandHighCard(Hand h, HandScore hs)
	// In the document, the probability is 100%, so there is no if statement here. It will return true.
	{
		hs.setHandStrength(eHandStrength.HighCard);
		hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
		hs.getKickers().add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
		hs.getKickers().add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
		hs.getKickers().add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
		hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		hs.setLoHand(null);
		return true;
	}
	
	
	public static boolean isAcesAndEights(Hand h, HandScore hs){
		boolean isAcesAndEights = false;
		
		if (Hand.isHandTwoPair(h, hs)){
			if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE) && (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.EIGHT)){
			hs.setHandStrength(eHandStrength.AcesandEights);
	// I checked the eHandStrength.java file, and there is no scoring for this. Checked Piazza, and 
			// I scored it as a 35, right above two pair. 
				isAcesAndEights = true;
			}
		}
		
		return isAcesAndEights;
		
	}	
	
	
	public static boolean isHandFullHouse(Hand h, HandScore hs) {

		boolean isFullHouse = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
		} else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
		}

		return isFullHouse;

	}
}
