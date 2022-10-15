import java.util.ArrayList;

public class Player {
  ArrayList<Card> playerCards;

  public Player(ArrayList<Card> playerCards) {
    this.playerCards = playerCards;
  }

  @Override
  public String toString() {
    return "Player{" +
      "playerCards=" + playerCards +
      '}';
  }
}
