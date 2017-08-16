import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;

public class MindTheEights extends Player
{
  public MindTheEights (Card[] cards)
  {
    this.hand = new ArrayList<Card>(Arrays.asList(cards));
  }
  @Override
  public boolean play(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players)
  {
    Player lowestHand = null;
    int powerCardNumber = 0;
    int eights = 0;
    if (discardPile.top().getSuit() == "None" && discardPile.top().getRank() == -1 ){
      discardPile.add(this.hand.remove(0));
      if( this.hand.size() == 0 )
      {
        return true;
      }
      return false;
    }
    
    //Finds player with the least amount of cards in hand
    for (int j = 0; j < players.size(); j++)
    {
      Player playerHand = players.get(j);
      if (j == 0 || playerHand.getSizeOfHand() < lowestHand.getSizeOfHand())
      {
        lowestHand = players.get(j); 
      }
    }
    //Finds out how many 8s there are in hand
    for (int i = 0; i < this.hand.size(); i++)
    {
      if (this.hand.get(i).getRank() == 8)
      {
        eights += 1;
        powerCardNumber = i;
      }
    }
    //Plays eights depending on the size of the lowest opponent's hand and how many eights you are holding
    if (lowestHand.getSizeOfHand() == 1 && eights == 1)
    {
      discardPile.add(this.hand.remove(powerCardNumber));
      if (this.hand.size() == 0)
      {
        return true;
      }
      return false;
    }
    else if (lowestHand.getSizeOfHand() == 2 && eights == 2)
    {
      discardPile.add(this.hand.remove(powerCardNumber));
      if (this.hand.size() == 0)
      {
        return true;
      }
      return false;
    }
    else if (lowestHand.getSizeOfHand() == 3 && eights == 3)
    {
      discardPile.add(this.hand.remove(powerCardNumber));
      if (this.hand.size() == 0)
      {
        return true;
      }
      return false;
    }
    else if (lowestHand.getSizeOfHand() == 4 && eights == 4)
    {
      discardPile.add(this.hand.remove(powerCardNumber));
      if (this.hand.size() == 0)
      {
        return true;
      }
      return false;
    }
    //Checks for same rank and suit as top of discardPile and plays it if it can
    else
    {
      for (int k = 0; k < this.hand.size() ; k++)
      {
        if (this.hand.get(k).getRank() == discardPile.top().getRank() || this.hand.get(k).getSuit() == discardPile.top().getSuit())
        {
          discardPile.add(this.hand.remove(k)); 
        }
        if (this.hand.size() == 0)
        {
          return true; 
        }
        return false;
      }
    }
    //Draws until we can play a valid card
    drawUntilValid(drawPile, discardPile);
    return false;
  }
}