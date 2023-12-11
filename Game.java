import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private boolean gameState;
    private Player dealer;
    private Player player;
    Game() {
        this.deck = new Deck();
        deck.shuffle();
        player = new Player();
        dealer = new Player();
        gameState = false;
    }

    public void start() {
        gameState = true;
        System.out.println("Game start!");
        dealer.hands.add(deck.draw());
        player.hands.add(deck.draw());
        dealer.hands.add(deck.draw());
        player.hands.add(deck.draw());
        showDealerHands();
        while (gameState) {
            showPlayerCurrentHand();
            System.out.println("Do you want more card?\n");
            Scanner sc = new Scanner(System.in);
            String user_resp = sc.nextLine();
            if (user_resp.equals("y")) {
                player.hands.add(deck.draw());
                if (countHand(player) > 21) {
                    showPlayerCurrentHand();
                    System.out.println("Game over. You lose!");
                    gameReset();
                }
            } else if (user_resp.equals("n")) {
                gameState = false;
                showDealerHands();
                while (countHand(dealer) < 17) {
                    dealer.hands.add(deck.draw());
                    showDealerHands();
                }
                showGameResult();
                if (countHand(dealer) > 21 | countHand(player) > countHand(dealer)) {
                    System.out.println("You win!");
                    gameReset();
                } else if (countHand(player) == countHand(dealer)) {
                    System.out.println("Draw!!");
                    gameReset();
                } else if (countHand(player) < countHand(dealer)) {
                    System.out.println("Game over. You lose!");
                    gameReset();
                }
            } else {
                System.out.println("Please enter y/n.");
            }
        }


    }

    public void showGameResult() {
        System.out.println("Your point: " + countHand(player));
        System.out.println("Dealer point: " + countHand(dealer));
    }


    public void showPlayerCurrentHand() {
        System.out.println("Current hands: " + player.hands);
        System.out.println("Current point:" + countHand(player));
    }

    public void showDealerHands() {
        if (gameState) {
            List<Card> dealerHandsWithOutFirst = new ArrayList<>();
            dealerHandsWithOutFirst.add(null);
            dealerHandsWithOutFirst.add(dealer.hands.get(1));
            System.out.println("Dealer current hands: " + dealerHandsWithOutFirst);
        } else {
            System.out.println("Dealer current hands: " + dealer.hands);
            System.out.println("Dealer current point:" + countHand(dealer));
        }
    }

    public void gameReset() {
        while (!player.hands.isEmpty()) {
            deck.getDiscard(player.hands.remove(0));
        }
        while (!dealer.hands.isEmpty()) {
            deck.getDiscard(dealer.hands.remove(0));
        }
        deck.reset();
        gameState = false;
        System.out.println("Do you want to start another game?");
        Scanner sc = new Scanner(System.in);
        String user_resp = sc.nextLine();
        if (user_resp.equals("y")) {
            start();
        } else {
            System.out.println("Thank you!");
        }
    }

    public int countHand(Player player) {
        int count = 0;
        int AceCount = 0;
        for (Card card : player.hands) {
            if (card.number == 1) {
                AceCount += 1;
                count += 11;
            } else if (card.number >= 10) {
                count += 10;
            } else {
                count += card.number;
            }
        }
        while (count > 21 && AceCount > 0) {
            count -= 10;
            AceCount--;
        }
        return count;
    }


}
