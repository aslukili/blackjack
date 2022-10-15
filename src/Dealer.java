import java.util.ArrayList;

public class Dealer {

  ArrayList<Card> dealerCards;

  public Dealer(ArrayList<Card> dealerCards) {
    this.dealerCards = dealerCards;
  }




  @Override
  public String toString() {
    return "Dealer{" +
      "dealerCards=" + dealerCards +
      '}';
  }
}
