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
      game.play();
        System.out.println("\n\n \t what to do now? \n 1- play again \n 2- exit");
      choice = scanner.nextInt();
      if (choice == 1) game.play();
      if (choice == 2) return;
//      else System.out.println("something went wrong!");
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
