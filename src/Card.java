public class Card {
  int value;
  int type;

  public Card(int value, int type) {
    this.value = value;
    this.type = type;
  }


  @Override
  public String toString() {
    return "Card{" +
      "value=" + value +
      ", type=" + type +
      '}';
  }

  // TODO card types are enums! create an enum class as HEART = 1, DIAMOND = 2...
}
