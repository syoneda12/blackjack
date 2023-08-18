import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    // 手持ちのカードにカードを追加する
    public void addCard(Card card) {
        hand.add(card);
    }

    // 指定されたカードを手持ちから削除する
    public boolean removeCard(Card card) {
        return hand.remove(card);
    }

    // 手持ちのカードの数を取得する
    public int getCardCount() {
        return hand.size();
    }

    // 指定されたインデックスのカードを手持ちから取得する
    public Card getCard(int index) {
        return hand.get(index);
    }

    // 手持ちのカードを全て表示する
    @Override
    public String toString() {
        return hand.toString();
    }

    public int getTotalValue() {
        int total = 0;

        for (Card card : hand) {
            total += card.getRank().getValue();
        }

        return total;
    }

    // public static void main(String[] args) {
    // Deck deck = new Deck();
    // Hand playerHand = new Hand();

    // deck.shuffle();

    // // 5枚のカードをドローして、プレイヤーの手に追加する
    // for (int i = 0; i < 5; i++) {
    // Card drawnCard = deck.draw();
    // playerHand.addCard(drawnCard);
    // System.out.println("Drew: " + drawnCard);
    // }

    // System.out.println("\nPlayer's hand:");
    // System.out.println(playerHand);
    // }
}
