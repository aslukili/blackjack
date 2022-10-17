public class Card {

  private Suit suit;
  private Value value;



  public Card(Suit suit, Value value) {
    this.value = value;
    this.suit = suit;
  }

  public Suit getSuit() {
    return suit;
  }

  public Value getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Card{" +
      "value=" + value +
      ", type=" + suit +
      '}';
  }

  // TODO card types are enums! create an enum class as HEART = 1, DIAMOND = 2...
}
