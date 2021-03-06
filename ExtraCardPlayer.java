import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;




public class ExtraCardPlayer extends Player{
	
	
	public ExtraCardPlayer(Card[] cards){this.hand = new ArrayList<Card>(Arrays.asList(cards));}
 

	public boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players){
		//assume for testing that ExtraCardPlayer is always in position [0] therefore next player is always [1] for purpose of seeing number of cards remaining
		
		//print current hand for testing
		for(Card c : hand){
			System.out.print(" " + c.toString());
		}
		System.out.println();
		//increment rounds to determine if drawing more than once
		
		
		//track top card in discard pile
		Card lastPlayedCard = discardPile.top();
		
		//variable to track if holding a power card
		boolean hasPower = false;
		//variable to make sure the drawn card is allowed to be played
		boolean validPlay = false;
		
		
		//iterate through hand and search for a power card
		
		for(Card c: this.hand){
			if(isPowerCard(c) && isValid(discardPile, c)){
				System.out.println("Found a valid power card ln 33");
				hasPower = true;
				validPlay = true;				
				break;
			}
			else{
				continue;
			}
		}
		
		
		
		//continue drawing cards from the drawPile and appending them to hand until a power card that is able to be played is drawn or if the next player only has 1 card remaining
		while(!hasPower || !validPlay || players.get(1).getSizeOfHand() == 1){
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
		
		if(hasPower && validPlay && players.get(1).getSizeOfHand()==1){
			discardPile.add(this.hand.remove(this.hand.size()-1));
			return true;
		}	
		//if under some circumstance a valid powercard cannot be drawn, or the next player has more than 1 card remaining 
		else if(!hasPower || players.get(1).getSizeOfHand() != 1){
			for(Card c : hand){
				if(isValid(discardPile, c)){
					discardPile.add(c);
					int cardLocation = this.hand.indexOf(c);
					this.hand.remove(cardLocation);
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
		