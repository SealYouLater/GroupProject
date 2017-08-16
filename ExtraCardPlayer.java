import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;




public class ExtraCardPlayer extends Player{
	private static int rounds = 0;
	
	public ExtraCardPlayer(Card[] cards){this.hand = new ArrayList<Card>(Arrays.asList(cards));}
 

	public boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players){
		//assume for testing that ExtraCardPlayer is always in position [0] therefore next player is always [1] for purpose of seeing number of cards remaining
		
		//increment rounds to determine if drawing more than once
		rounds++;
		
		//track top card in discard pile
		Card lastPlayedCard = discardPile.top();
		
		//variable to track if holding a power card
		boolean hasPower = false;
		//variable to make sure the drawn card is allowed to be played
		boolean validPlay = false;
		
		//variable for tracking the location of a valid power card
		int validCardLocation = -1;
		
		//iterate through hand and search for a power card
		
		for(Card c: this.hand){
			if(isPowerCard(c) && isValid(discardPile, c)){
				hasPower = true;
				validPlay = true;				
				validCardLocation = this.hand.indexOf(c);
				break;
			}
			else{
				continue;
			}
		}
		
		//middle step if a valid power card was found in the above for-each loop then play the card, remove it from the hand, and return the function true
		if(hasPower && validPlay && validCardLocation != -1){
			discardPile.add(this.hand.remove(validCardLocation));
		}
		
		
		
		//continue drawing cards from the drawPile and appending them to hand until a power card that is able to be played is drawn
		while(!hasPower || !validPlay){
			Card newCard = drawPile.pop();
			boolean isDrawPowerCard = isPowerCard(newCard);
			this.hand.add(newCard);
			
			if(isDrawPowerCard && isValid(discardPile, newCard)){
				hasPower = true;
				validPlay = true;
				break;
			}
		}
		
		
		//Since the above loop does not stop drawing until a valid power card is drawn, the card in the last hand position will be valid and a power card
		//now we play that card by removing the card at hand.get(hand.size()-1) which is the last card in our hand
		
		if(hasPower && validPlay){
			discardPile.add(this.hand.remove(this.hand.size()-1));
			return true;
		}	
		//if under some circumstance a valid powercard cannot be drawn, iterate through the hand and play the first valid card
		else if(!hasPower){
			for(Card c : hand){
				if(isValid(discardPile, c)){
					discardPile.add(c);
					this.hand.remove(c);
					return true;
				}
			}
		}
		else{
			return false;
		}
		
		if(hasPower && validPlay){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public boolean isPowerCard(Card c){
		if(c.getRank() == 8 || c.getRank() == 7 || c.getRank() == 4 || c.getRank() ==  2){
			return true;
		}
		else{
			return false;
		}
	}					
  
}
		