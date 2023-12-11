public class Card {
    Suits suit;
    Integer number;
    Card(int num) {
        int indexOfSuits = num / 13;
        switch (indexOfSuits) {
            case 0:
                this.suit = Suits.SPADE;
                break;
            case 1:
                this.suit = Suits.HEART;
                break;
            case 2:
                this.suit = Suits.DIAMOND;
                break;
            case 3:
                this.suit = Suits.CLUB;
                break;
        }
        number = num % 13 + 1;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", number=" + number +
                '}';
    }
}
