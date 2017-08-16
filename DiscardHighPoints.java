import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;

public class DiscardHighPoints extends Player {
  
 public int getPointValue(Card card) {
 String rank = card.getRankString();
 String suite = card.getSuit();
 if (rank.equals("8")){
  return 50;
 }
 else if (rank.equals("2") || rank.equals("4")){
  return 25;
 }
 else if (rank.equals("7")){
  return 20;
 }
 else if ((rank.equals("Jack")) || (rank.equals("Queen")) || (rank.equals("King"))){
 return 10;
 }
 else if (rank.equals("Ace")){
 return 1;
 }
 else{ //return the face value
  return card.getRank();

 }
}

 public DiscardHighPoints(Card[] cards){
   this.hand = new ArrayList<Card>(Arrays.asList(cards));
 }
 

  /**
   * find a card to be played based on the DiscardHighPoints play strategy
   * @param lastDiscard the topmost card of the discard pile
   * @return a high point card to change suits if one exists, or the higheset
   * point card that can be played, or null if neither exist
   */
  public Card getHighPointCard(DiscardPile pile, Card lastDiscard) {
    Card highPointCard = null;
    
    //loop over the player's hand
    for(Card thisCard : hand) {
      //LOGIC: is nextCard valid? need to check if suit matches, or rank matches
      //if not valid, continue the loop. remember 8 is always valid
      if(isValid(pile,thisCard)){
        //the card is valid to be played
        
        //if suit is diff and it is a high point card, it qualifies as a highPointSuitChange card
        //we should return the highPointSuitChange card right away
        //suppose HighPointCard >= 10;
        if(!thisCard.getSuit().equals(lastDiscard.getSuit()) && (getPointValue(thisCard) >= 10)) {
          return thisCard;
        }
        //set first valid card to highPointCard to start
  if(highPointCard == null) {
   highPointCard = thisCard;
  }
  // check if it has a higher point value than current highPointCard
        if(getPointValue(thisCard) > getPointValue(highPointCard)){
          highPointCard = thisCard;
        }
      }
      //not valid to be played
    }
    //could not find a HighPointSuitChange, return the highest value card, or null if not found
    return highPointCard;
  }
  
  public boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players) {
    //look for high point cards
    Card toPlay = getHighPointCard(discardPile,discardPile.top());
    
    if(toPlay != null) {
      //place card in discard pile and remove from the player's hand
      //if the player's hand is empty, we win! return true
      discardPile.add(toPlay);
      hand.remove(toPlay);
      
      // if the hand is empty we won!
      if(hand.isEmpty()){
        return true;
      }
    }
    else {
  //take cards from the draw pile until valid (but could keep drawing) or drawPile empty
        //if pile empty, skip turn (return false)
        //if this card is playable, play it right away
  while(true){
   if(drawPile.empty()){
    break;
   }
   else {
    toPlay = drawPile.pop();
    hand.add(toPlay);
    if(isValid(discardPile, toPlay)) {
     hand.remove(toPlay);
     discardPile.add(toPlay);
     break;
    }
   }
  }

    }
    
    //if we get here we must have at least one card in our hand
    return false;
  }

}
