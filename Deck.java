import java.util.ArrayList;
import java.util.List;

public class Deck {

    public List<Card> cards = new ArrayList<>();
    public List<Card> discard = new ArrayList<>();
    public void shuffle() {
        for (int i = cards.size() - 1; i >= 0; i--) {
            int swap_index = (int) (Math.random() * i + 1);
            swap(cards, i, swap_index);
        }
    }



    public Card draw() {
        if (cards.isEmpty()) {
            System.out.println("There is no card in the deck");
            return null;
        } else {
            return cards.remove(0);
        }
    }

    public void currentDeck() {
        System.out.println(cards);
    }

    Deck() {
        for (int i = 0; i < 52; i++) {
            cards.add(new Card(i));
        }
    }

    private void swap(List<Card> cards, int i, int j) {
        Card temp = cards.get(i);
        cards.set(i, cards.get(j));
        cards.set(j, temp);
    }

    public void getDiscard(Card card) {
        discard.add(card);
    }

    public void reset() {
        while (!discard.isEmpty()) {
            cards.add(discard.remove(0));
        }
        shuffle();
    }
}
