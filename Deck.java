import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();

        // すべてのスートとランクの組み合わせに対してカードを生成し、リストに追加する
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    // 山札をシャッフルする
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // 山札のトップからカードを1枚ドローする
    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("山札がありません！");
        }
        return cards.remove(cards.size() - 1);
    }

    // 山札の残りのカード数を返す
    public int remainingCards() {
        return cards.size();
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    // public static void main(String[] args) {
    // Deck deck = new Deck();
    // System.out.println("Original deck:");
    // System.out.println(deck);

    // deck.shuffle();
    // System.out.println("\nShuffled deck:");
    // System.out.println(deck);

    // System.out.println("\nDrawing a card:");
    // Card drawnCard = deck.draw();
    // System.out.println(drawnCard);

    // System.out.println("\nRemaining cards in the deck:");
    // System.out.println(deck.remainingCards());
    // }
}
