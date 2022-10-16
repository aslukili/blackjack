import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Game game = new Game();

//    System.out.println(game.cards);

    System.out.println("Welcome to YouCode Casino!");
    System.out.println("what to do? \n 1- play a blackjack game \n 2- exit");

    int choice = scanner.nextInt();



    switch (choice){
      case 1:
        game.createCards();
        game.createBlackJack();
        // give two cards to player
        game.playerDrawN();
        // give the dealer 1 card:
        game.dealerDrawN();
        // give another card to the player
        game.playerDrawN();
        //give another card to the dealer
        game.dealerDrawN();
        // show player's hand
        game.gameState();


        game.playerPlays();

        game.dealerPlays();
        // whois the winner
        game.gameState();
        if (game.totalCards(game.dealer.dealerCards) > game.totalCards(game.player.playerCards) && game.totalCards(game.dealer.dealerCards) < 21){
          System.out.println("dealer won this game!");
        } else {
          System.out.println("you won");
        }

        break;
      case 2:
        break;
      default:
        System.out.println("invalid choice!");
        main(null);
    }


//    System.out.println(game.totalCards(game.dealer.dealerCards));
//    System.out.println(game.totalCards(game.player.playerCards));

//    System.out.println(game.drawNCards(shuffledCards, 5));
  }
}
