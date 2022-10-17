import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Game {
  Dealer dealer;
  Player player;
  private ArrayList<Card> cards;
  public Game() {
    this.cards = new ArrayList<>();
  }

  // TODO create play() method and move everything from Main method to it


  /**
   * first method to be invoked to create game cards
   */
  public void createCards() {
    //enhanced for loop to generate cards:
    for (Suit cardSuit : Suit.values()){
      for (Value cardValue : Value.values()){
        // add  cards to our deck
        this.cards.add(new Card(cardSuit, cardValue));
      }
    }
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


  // TODO:
  //  public ArrayList<Card> discardCards() {}


  /**
   * game initializer
   */
  public void createBlackJack() {
    this.dealer = new Dealer(new ArrayList<>());
    this.player = new Player(new ArrayList<>());
    this.cards = shuffleGameCards(this.cards);
//    System.out.println(this.dealer);
//    System.out.println(this.player);
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

  //
  public void dealerPlays() {
    while (totalCards(dealer.dealerCards) <= 17){
      dealerDrawN();
    }
  }

  public void gameState() {
    System.out.println("Your cards: ");
    System.out.println(this.player.playerCards);
    System.out.println("Your hand value: " + totalCards(player.playerCards));
    System.out.println("Dealer cards: ");
    System.out.println(this.dealer.dealerCards);
    System.out.println("Dealer hand's value: "+ totalCards(dealer.dealerCards));
  }

  public void playerPlays(){
    Scanner scanner = new Scanner(System.in);

    while (true){
      if (totalCards(player.playerCards) > 21){
        System.out.println("Your total cards value equals more than 21! you lost");
        return;
      }
      if (totalCards(player.playerCards) == 21){
        System.out.println("you won by a blackjack");
        return;
      }
      System.out.println("Your hand: " + player.playerCards);
      System.out.println("your hand value equals to: " + totalCards(player.playerCards));
      System.out.println("Dealer hand: " + dealer.dealerCards.get(0) + " [Card]");
      // what the player wants to do:
      System.out.println("What to do? \n 1- stand\n 2- hit");
      int choice = scanner.nextInt();
      switch (choice) {
        case 1:
          dealerPlays();
          return;
        case 2:
          System.out.println("you chose to hit");
          playerDrawN();
          System.out.println("Your got a: " + player.playerCards.get(player.playerCards.size() - 1));

//            gameState();
          break;
        default:
          System.out.println("invalid choice! try again");
      }
    }

  }

  /**
   *
   * @param cardsToBeCounted an arraylist of dealer's or player's hand
   * @return value of the given hand cards
   */
  public int totalCards(ArrayList<Card> cardsToBeCounted){
    int totalValue = 0;
    int aces = 0;

    for (Card aCard : cardsToBeCounted) {
      switch (aCard.getValue()){
        case TWO: totalValue += 2; break;
        case THREE: totalValue += 3; break;
        case FOUR: totalValue += 4; break;
        case FIVE: totalValue += 5; break;
        case SEX: totalValue += 6; break;
        case SEVEN: totalValue += 7; break;
        case EIGHT: totalValue += 8; break;
        case NINE: totalValue += 9; break;
        case TEN:
        case JACK:
        case QUEEN:
        case KING:
          totalValue += 10; break;
        case ACE: aces += 1; break;
      }// end of switch
    }// end of foreach loop
    for (int i = 0; i < aces; i++) {
      if (totalValue > 10){
        totalValue += 1;
      }else totalValue += 11;
    }
    return totalValue;
  }


  public void play() {
    createCards();
    createBlackJack();
    //play
    playerDrawN();
    // give the dealer 1 card:
    dealerDrawN();
    // give another card to the player
    playerDrawN();
    //give another card to the dealer
    dealerDrawN();
    // let the player play
    playerPlays();

    // whois the winner
    gameState();
//        System.out.println("here");

    if (totalCards(player.playerCards) > 21){
      System.out.println("you lost!");
    }
    if (totalCards(dealer.dealerCards) == totalCards(player.playerCards) && totalCards(dealer.dealerCards) <= 21){
      System.out.println("you got the same hand value as the dealer");
    }
    if (totalCards(dealer.dealerCards) > 21 && totalCards(player.playerCards) < 21){
      System.out.println("you won this game!");
    }
    if (totalCards(dealer.dealerCards) > totalCards(player.playerCards) && totalCards(dealer.dealerCards) <= 21){
      System.out.println("dealer won this game!");
    }
    if (totalCards(player.playerCards) > totalCards(dealer.dealerCards) && totalCards(player.playerCards) <= 21){
      System.out.println("you won");
    }
  }
}
