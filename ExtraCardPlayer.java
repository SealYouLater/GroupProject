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
		
		
		//search through hand to see if you are holding a power card
		for(int i=0; i<cards.length; i++){
			if(cards[i].getRank() == 8 || cards[i].getRank() == 7 || cards[i].getRank() == 4 || cards[i].getRank() == 2){
				hasPower = true;
			}
			else{
				continue;
			}
		}
		
		//draw until you have a power card
		while ((!hasPower) && (players[1].getSizeOfHand() == 1)){
			hand.add(drawPile.pop());
			
			
			if(hand.get(hand.length-1).isPowerCard() && isValid(discardPile, hand.get(hand.length-1)){
				hasPower = true;
				break;
			}
			else{
				continue;
			}
		}
		
		//play a power card only if the next person has 1 card left, if not play a non-power card
		if(players[1].getSizeOfHand() == 1){
			for(Card c : hand){
				if(c.isPowerCard() && isValid(discardPile, c)){
					discardPile.add(this.hand.remove(c));
				}
				else{
					continue;
				}
			}
				
		}
		else if(players[1].getSizeOfHand() != 1){
			for (card c : hand){
				if(!c.isPowerCard() && isValid(discardPile, c)){
					discardPile.add(this.hand.remove(c));
				}					
		
		}
		
		
		
		
	}
	
	private boolean isPowerCard(Card c){
		if(c.getRank() == 8 || c.getRank() == 7 || c.getRank() == 4 || c.getRank() ==  2){
			return true;
		}
		else{
			return false;
		}
	}					
}		