import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Game game = new Game();

    game.createCards();
    System.out.println(game.cards);

//    ArrayList<Card> shuffledCards = game.shuffleGameCards(game.cards);
//    System.out.println(shuffledCards);


    System.out.println("Welcome to YouCode Casino!");
    System.out.println("Let's play some BlackJack!");

    game.createBlackJack();
    // give the dealer 1 card:
    game.dealerDrawN();
    // give two cards to player
    game.playerDrawN();
    game.playerDrawN();
    //give another card to the dealer
    game.dealerDrawN();

    System.out.println("Your cards: ");
    System.out.println(game.player.playerCards);

    System.out.println("What to do? \n 1- stand\n 2- hit");
    int choice = scanner.nextInt();

    switch (choice){
      case 1:
        // TODO show result!
        break;
      case 2:
        System.out.println("you chose to hit");
        game.playerDrawN();
        break;
      default:
        System.out.println("invalid choice! try again");
    }

//    System.out.println(game.totalCards(game.dealer.dealerCards));
//    System.out.println(game.totalCards(game.player.playerCards));


//    System.out.println(game.drawNCards(shuffledCards, 5));
  }
}
