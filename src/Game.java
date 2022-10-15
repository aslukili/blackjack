import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Game {
  Dealer dealer;
  Player player;
  ArrayList<Card> cards = new ArrayList<>();
  public Game() {
  }


  /**
   * first method to be invoked to create game cards
   */
  public void createCards() {
    IntStream.range(1, 5).forEach(i -> {
        IntStream.range(1, 14).forEach(j ->{
            this.cards.add(new Card(j, i));
          }
        );
      }
    );
  }

  /**
   *
   * @param cards
   * @param randomI
   * @return IthCard to be extracted + List of other cards
   */
  public List<Object> extractIthCard(ArrayList<Card> cards, int randomI){
    Card iThCard = cards.get(randomI);
    cards.remove(randomI);
    List<Object> finalCards = new ArrayList<>();
    finalCards.add(iThCard);
    finalCards.add(cards);
    return finalCards;
  }


  /**
   *
   * @param cards
   * @return IthCard to be extracted + List of other cards
   */
  public List<Object> drawACard(ArrayList<Card> cards) {
    int randomInt = (new Random().nextInt(52));
    return extractIthCard(cards, randomInt);
  }


  /**
   *
   * @param cards
   * @return shuffled cards ready to play with
   */
  public ArrayList<Card> shuffleGameCards(ArrayList<Card> cards) {
    List<Object> temp = new ArrayList<>();
    for (int i = 0; i < 51; i++) {
      temp = drawACard(cards);
      cards = (ArrayList<Card>) temp.get(1);
      cards.add((Card) temp.get(0));
    }
    return cards;
  }

  /**
   *
   * @param cards shuffled arrayList of cards
   * @param n how many cards we want
   * @return two lists, first of drawn cards (first n), and second of the rest of cards
   */
  public ArrayList<ArrayList<Card>> drawNCards(ArrayList<Card> cards, int n) {
    ArrayList<ArrayList<Card>> arrayListToReturn = new ArrayList<>();
    arrayListToReturn.add(new ArrayList<>());
    for (int i = 0; i < n; i++) {
      arrayListToReturn.get(0).add(cards.get(i));
    }
    // second part of arrayList to return
    arrayListToReturn.add(new ArrayList<>());
    for (int i = n; i < cards.size(); i++) {
      arrayListToReturn.get(1).add(cards.get(i));
    }
    return arrayListToReturn;
  }


  // TODO public ArrayList<Card> discardCards() {}


  /**
   * game initializer
   */
  public void createBlackJack() {
    this.dealer = new Dealer(new ArrayList<>());
    this.player = new Player(new ArrayList<>());
    ArrayList<Card> gameCards = shuffleGameCards(cards);
    this.cards = gameCards;
    System.out.println(dealer);
    System.out.println(player);
    System.out.println(this.cards);
  }

  public void dealerDrawN(){

    dealer.dealerCards.add(this.cards.get(0));
    this.cards.remove(0);

  }

  public void playerDrawN() {
    player.playerCards.add(this.cards.get(0));
    this.cards.remove(0);
  }

  public int totalCards(ArrayList<Card> cardsToBeCounted){

    return cardsToBeCounted.stream().mapToInt(card -> card.value).sum();
  }


}
