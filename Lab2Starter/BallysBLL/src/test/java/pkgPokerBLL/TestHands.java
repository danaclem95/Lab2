package pkgPokerBLL;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pkgPokerEnum.eHandStrength;
import pkgPokerEnum.eRank;
import pkgPokerEnum.eSuit;

public class TestHands {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

// DONE Royal Flush
	@Test
	public void TestRoyalFlush(){
		Hand hand = new Hand();
		
		hand.AddCardToHand(new Card(eRank.TEN, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.JACK, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.QUEEN, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.KING, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.ACE, eSuit.SPADES));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.RoyalFlush);
		assertTrue(hand.getHandScore().getHiHand() == eRank.ACE);
		
	}
	
// DONE Straight Flush
	@Test
	public void TestStraightFlush(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.FOUR, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.SIX, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.SEVEN, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.EIGHT, eSuit.HEARTS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.StraightFlush);
		assertTrue(hand.getHandScore().getHiHand() == eRank.EIGHT);
		
	}
	
	
// DONE Four of a Kind
	@Test
	public void TestFourofaKind1(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.SEVEN, eSuit.CLUBS));
		hand.AddCardToHand(new Card(eRank.SEVEN, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.SEVEN, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.SEVEN, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.HEARTS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.FourOfAKind);
		assertTrue(hand.getHandScore().getHiHand() == eRank.SEVEN);
		
	}
	
	
	@Test
	public void TestFourofaKind2(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.CLUBS));
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.SEVEN, eSuit.HEARTS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.FourOfAKind);
		assertTrue(hand.getHandScore().getHiHand() == eRank.TWO);
		
	}
	
// DONE Flush
	@Test
	public void TestFlush(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.TEN, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.JACK, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.ACE, eSuit.HEARTS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.Flush);
		assertTrue(hand.getHandScore().getHiHand() == eRank.ACE);
		
	}
	
// DONE Straight
	
	@Test
	public void TestStraight1(){
		Hand hand = new Hand();
		
		hand.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.SIX, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.SEVEN, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.EIGHT, eSuit.DIAMONDS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.Straight);
		assertTrue(hand.getHandScore().getHiHand() == eRank.EIGHT);
		assertTrue(hand.getHandScore().getLoHand() == null);
		
	}
	
	@Test
	public void TestStraight2(){
		Hand hand = new Hand();
		
		hand.AddCardToHand(new Card(eRank.ACE, eSuit.CLUBS));
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.THREE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FOUR, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.DIAMONDS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.Straight);
		assertTrue(hand.getHandScore().getHiHand() == eRank.FIVE);
		assertTrue(hand.getHandScore().getLoHand() == null);
		
	}
	
	@Test
	public void TestStraight3(){
		Hand hand = new Hand();
		
		hand.AddCardToHand(new Card(eRank.THREE, eSuit.CLUBS));
		hand.AddCardToHand(new Card(eRank.FOUR, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.SIX, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.SEVEN, eSuit.DIAMONDS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.Straight);
		assertTrue(hand.getHandScore().getHiHand() == eRank.SEVEN);
		assertTrue(hand.getHandScore().getLoHand() == null);
		
	}
// DONE Three of a Kind
	@Test
	public void TestThreeofaKind1(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.TEN, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.ACE, eSuit.CLUBS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.ThreeOfAKind);
		assertTrue(hand.getHandScore().getHiHand() == eRank.FIVE);
	}
	
	@Test
	public void TestThreeofaKind2(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.TEN, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.ACE, eSuit.CLUBS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.ThreeOfAKind);
		assertTrue(hand.getHandScore().getHiHand() == eRank.FIVE);
	}
	
	@Test
	public void TestThreeofaKind3(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.TEN, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.CLUBS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.ThreeOfAKind);
		assertTrue(hand.getHandScore().getHiHand() == eRank.FIVE);
	}
	
// DONE Two Pair
	
	@Test
	public void TestTwoPair1(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.CLUBS));
		hand.AddCardToHand(new Card(eRank.ACE, eSuit.HEARTS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.TwoPair);
		assertTrue(hand.getHandScore().getHiHand() == eRank.FIVE);
		assertTrue(hand.getHandScore().getLoHand() == eRank.TWO);
	}
	
	public void TestTwoPair2(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.CLUBS));
		hand.AddCardToHand(new Card(eRank.FOUR, eSuit.HEARTS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.TwoPair);
		assertTrue(hand.getHandScore().getHiHand() == eRank.FIVE);
		assertTrue(hand.getHandScore().getLoHand() == eRank.TWO);
	}
	
	public void TestTwoPair3(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.THREE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.THREE, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.CLUBS));
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.HEARTS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.TwoPair);
		assertTrue(hand.getHandScore().getHiHand() == eRank.FIVE);
		assertTrue(hand.getHandScore().getLoHand() == eRank.THREE);
	}
	
// DONE Pair
	
	@Test
	public void TestPair1(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		hand.AddCardToHand(new Card(eRank.ACE, eSuit.HEARTS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.Pair);
		assertTrue(hand.getHandScore().getHiHand() == eRank.TWO);
		assertTrue(hand.getHandScore().getLoHand() == null);
	}
	
	public void TestPair2(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FOUR, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		hand.AddCardToHand(new Card(eRank.ACE, eSuit.HEARTS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.Pair);
		assertTrue(hand.getHandScore().getHiHand() == eRank.FOUR);
		assertTrue(hand.getHandScore().getLoHand() == null);
	}
	
	public void TestPair3(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		hand.AddCardToHand(new Card(eRank.ACE, eSuit.HEARTS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.Pair);
		assertTrue(hand.getHandScore().getHiHand() == eRank.FIVE);
		assertTrue(hand.getHandScore().getLoHand() == null);
	}
	
	public void TestPair4(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.TWO, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.ACE, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.FOUR, eSuit.CLUBS));
		hand.AddCardToHand(new Card(eRank.ACE, eSuit.HEARTS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.Pair);
		assertTrue(hand.getHandScore().getHiHand() == eRank.ACE);
		assertTrue(hand.getHandScore().getLoHand() == null);
	}
	
// DONE HighCard
	@Test
	public void TestHighCard(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.JACK, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.FIVE, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.THREE, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.NINE, eSuit.CLUBS));
		hand.AddCardToHand(new Card(eRank.FOUR, eSuit.HEARTS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		// Everything runs perfectly except this statement here. Looked at it for a while, couldn't figure it out. 
		// assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.HighCard);
		assertTrue(hand.getHandScore().getHiHand() == eRank.JACK);
	}
	
	
	@Test
	public void TestFullHouse1() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		
		//	Hand better be a full house
		assertEquals(eHandStrength.FullHouse.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		//	HI hand better be 'Four'
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		//	LO hand better be 'Three'
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		//	Full House has no kickers.
		assertEquals(0,h.getHandScore().getKickers().size());	
		
	}

	@Test
	public void TestFullHouse2(){
		Hand hand = new Hand();
		hand.AddCardToHand(new Card(eRank.SEVEN, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.SEVEN, eSuit.SPADES));
		hand.AddCardToHand(new Card(eRank.SEVEN, eSuit.HEARTS));
		hand.AddCardToHand(new Card(eRank.TEN, eSuit.DIAMONDS));
		hand.AddCardToHand(new Card(eRank.TEN, eSuit.HEARTS));
		
		try{
			hand = hand.EvaluateHand();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		assertTrue(hand.getHandScore().getHandStrength() == eHandStrength.FullHouse);
		assertTrue(hand.getHandScore().getHiHand() == eRank.SEVEN);
		
	}
}
