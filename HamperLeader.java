import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

public class HamperLeader extends Player
{
  
  // the leader should has the less number of cards 
  // the hamper leader will hampering the next and the preivios player  
  // the hamperleader have to has power car  
  /* the hamperleader has to foucs on the person who has the leaset card and if the leader played a power card then 
   * stay the leader other wise the plaer should take a cred from the */
  
  //constructor for the HamperLeader player
  public HamperLeader(Card[] cards){
    this.hand = new ArrayList<Card>(Arrays.asList(cards));
  }
  
  /* to add card to the floor 
   *   discardPile.add(this.hand.remove(0));
   *   if( this.hand.size() == 0 ){return true;}
   *     return false; 
   */
  @Override
  public boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players){
    //check if the discardPile is empty
    if (discardPile.top().getSuit() == "None" && discardPile.top().getRank() == -1 ){
      discardPile.add(this.hand.remove(0));
      if( this.hand.size() == 0 ){return true;}
      return false;
    }
    // intializations for the variable to be used in the code 
    int nextPlayer = 0, powerCardNumber = 0;
    Player leader = null;
    boolean powerCard = false;
    for (int i = 0; i < players.size(); i++){
      Player tempPlayer = players.get(i);
      if (this == tempPlayer){
        nextPlayer = i+1;
      }
      if (i == 0 || tempPlayer.getSizeOfHand() < leader.getSizeOfHand() ){
        leader = players.get(i);
      }
    }
    
    // check if a power card exist in the hand of this player
    for (int i = 0; i < this.hand.size() ; i++){
      if (this.hand.get(i).getRank() == 8){
        powerCard = true;
        powerCardNumber = i;
      }
    }
    
    // check if the nextplayer is the leader and we have a powerCard to pref
    if (players.get(nextPlayer) == leader && powerCard){
      //play any card with the same suit or get a card from the drcardpile
      discardPile.add(this.hand.remove(powerCardNumber));
      if( this.hand.size() == 0 ){return true;}
      return false;
    }else{
      // check for the suit and rank
      for (int i = 0; i < this.hand.size() ; i++){
        if (this.hand.get(i).getRank() == discardPile.top().getRank() || this.hand.get(i).getSuit() == discardPile.top().getSuit()){
          discardPile.add(this.hand.remove(i));
          if( this.hand.size() == 0){return true;}
          return false;
        }
      }
      
      /* getting to this point means that this player does not have a card with the same rank or suit 
       * start getting card from the drawPile and check for it 
       */
      Card tempCard = null;
      for (;;){
        if (!drawPile.empty()){
          //if not empty 
          tempCard = drawPile.pop();
        }else{
          //if empty pass
          return false;
        }
        if (tempCard.getRank() == tempCard.getRank() || tempCard.getSuit() == tempCard.getSuit()){
          discardPile.add(tempCard);
          if( this.hand.size() == 0){return true;}
          return false;
        }else{
          this.hand.add(tempCard);
        }
      }
    }
  }
}