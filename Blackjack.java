public class Blackjack {
    private Deck deck;
    private Hand hand;

    public Blackjack() {
        deck = new Deck();
        hand = new Hand();
        deck.shuffle();

        // 最初の2枚を引く
        hand.addCard(deck.draw());
        hand.addCard(deck.draw());
    }

    /**
     * 手札の合計値を計算
     */
    public int handValue() {
        int value = 0;
        int aces = 0;

        for (int i = 0; i < hand.getCardCount(); i++) {
            Card card = hand.getCard(i);
            switch (card.getRank()) {
                case TWO:
                    value += 2;
                    break;
                case THREE:
                    value += 3;
                    break;
                case FOUR:
                    value += 4;
                    break;
                case FIVE:
                    value += 5;
                    break;
                case SIX:
                    value += 6;
                    break;
                case SEVEN:
                    value += 7;
                    break;
                case EIGHT:
                    value += 8;
                    break;
                case NINE:
                    value += 9;
                    break;
                case TEN:
                case JACK:
                case QUEEN:
                case KING:
                    value += 10;
                    break;
                case ACE:
                    aces++;
                    value += 1;
                    break;
            }
        }

        // エースの１か１０計算。現時点では使わない。
        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }

        return value;
    }

    /**
     * 次のドローで手札の合計が21になる確率
     */
    public double probabilityOf21() {
        // 手札の合計を21にするために必要なカードの値
        int neededValue = 21 - handValue();
        // neededValue に合致するカードの数
        int favorableOutcomes = 0;
        for (Card.Rank rank : Card.Rank.values()) {
            if (valueOf(rank) == neededValue) {
                // 手札に同じカードの数字があれば減算する
                favorableOutcomes += 4 - countCardsInHand(rank);
            }
        }
        // 山札に残っているカードの総数で favorableOutcomes を割る
        return (double) favorableOutcomes / deck.remainingCards();
    }

    /**
     * 次のドローでバーストする確率
     */
    public double probabilityOfBust() {
        // バーストする前の手札の最大値
        double bustValue = 21 - handValue();
        double bustOutcomes = 0;

        for (Card.Rank rank : Card.Rank.values()) {
            if (valueOf(rank) > bustValue) {
                // 手札に同じカードの数字があれば減算する
                bustOutcomes += 4 - countCardsInHand(rank);
            }
        }

        return (double) bustOutcomes / deck.remainingCards();
    }

    private int valueOf(Card.Rank rank) {
        switch (rank) {
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            case ACE:
                return 11;
            default:
                return 0;
        }
    }

    private int countCardsInHand(Card.Rank rank) {
        int count = 0;
        for (int i = 0; i < hand.getCardCount(); i++) {
            if (hand.getCard(i).getRank() == rank) {
                count++;
            }
        }
        return count;
    }

    public void drawCard() {
        hand.addCard(deck.draw());
    }

    @Override
    public String toString() {
        return hand.toString() + " (Total: " + handValue() + ")";
    }

    public static void main(String[] args) {
        Blackjack blackjackHand = new Blackjack();
        int turnCount = 1;
        while (true) {
            System.out.println("▽現在のターン: " + turnCount++);
            System.out.println("手札: " + blackjackHand);
            System.out.println("次のドローで21になる確率: " + blackjackHand.probabilityOf21() * 100 + "%");
            System.out.println("次のドローでバーストする確率: " + blackjackHand.probabilityOfBust() * 100 + "%");

            String input = "";
            while (!"y".equalsIgnoreCase(input) && !"n".equalsIgnoreCase(input)) {
                System.out.print("カードを引きますか？ (y/n): ");
                java.util.Scanner scanner = new java.util.Scanner(System.in);
                input = scanner.nextLine();

                if (!"y".equalsIgnoreCase(input) && !"n".equalsIgnoreCase(input)) {
                    System.out.println("無効な入力です。 'y' か 'n'を入力してください。");
                }
            }
            if ("n".equalsIgnoreCase(input)) {
                break;
            }

            blackjackHand.drawCard();

            if (blackjackHand.handValue() == 21) {
                System.out.println("21になりました!");
                break;
            } else if (blackjackHand.handValue() > 21) {
                System.out.println("バーストしました!");
                break;
            }
        }
        System.out.println("最終的な手札: " + blackjackHand);
        System.out.println("終了します。");
    }
}
