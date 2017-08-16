import java.util.ArrayList;
import java.util.Stack;

public abstract class Player{
	protected ArrayList<Card> hand;
	
	public int getSizeOfHand(){
		return hand.size();
	}
	
	public int countPoints() {
		int points = 0;
		for (int i = 0; i < getSizeOfHand(); i++) {
			points += hand.get(i).getRank();
		}
		return points;
	}
	
	/*
	 * The only power card to be handled is the 2
	 * Checks the top of discardPile and if it is 2, player
	 */
	public boolean handlePowerCard(Stack<Card> drawPile, DiscardPile discardPile) {
		if (discardPile.top().getRank() == 2) {
			if (!drawPile.isEmpty()) {
				drawCard(drawPile);
			} else {
				return true;
			}
			if (!drawPile.isEmpty()) {
				drawCard(drawPile);
			} else {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * If playing an 8, choose the suit by removing the current 8 from hand
	 * and adding the new 8 (with chosen suit) to the end of the hand
	 */
	public void changeSuit(Card card, String suit) {
		if (card.getRank() == 8) {
			Card newCard = new Card(suit, "8");
			hand.remove(card);
			hand.add(newCard);
		}
	}
	
	/*
	 * Checks if a card is valid to be played
	 * AKA has same suit or same rank than top of discardPile card
	 */
	public static boolean isValid(DiscardPile discardPile, Card playedCard){
		Card lastPlayedCard = discardPile.top();
		
		if(lastPlayedCard.getSuit().equals(playedCard.getSuit()) ||
			lastPlayedCard.getRank() == playedCard.getRank()||
			playedCard.getRank() == 8){ return true; }
			
			else {return false;}
	}
	
	/*
	 * draw a card until you get a valid one to play
	 */
	public Card drawUntilValid(Stack<Card> drawPile, DiscardPile discardPile) {
		while (!drawPile.isEmpty()) {
			Card newCard = drawPile.pop();
			System.out.println("  Drawing card: " + newCard);
			if (isValid(discardPile, newCard)) {
				return newCard;
			} else {
				hand.add(newCard);
			}
		}
		return null;
	}
	
	/*
	 * Checks if the card to be played is a power card
	 * One of the 4 power cards 8,2,4,7 
	 */
	public boolean isPowerCard (Card card){
		if (card.getRank() == 8 ||
			card.getRank() == 2 ||
			card.getRank() == 4 ||
			card.getRank() == 7){
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Draw a card from the drawPile and add it to the player's hand
	 */
	public void drawCard(Stack<Card> drawPile){
		Card newCard = drawPile.pop();
		this.hand.add(newCard);
		System.out.println("  Drawing card: " + newCard);
	}
	
	
	/* play a card  */
	public abstract boolean play(DiscardPile       discardPile, 
	                             Stack<Card>       drawPile, 
										          	ArrayList<Player> players);
	// return true if player wins game by playing last card
	// returns false otherwise
	// side effects: plays a card to top of discard Pile, possibly taking zero
	//               or more cards from the top of the drawPile
	//               card played must be valid card
	// draws unless has power card, only takes one, if person has 1 card left they play a power card
	
	
}
