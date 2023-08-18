public class CardCombinationStatistics {

    public static void main(String[] args) {
        displayCombinations();
    }

    public static void displayCombinations() {
        System.out.println("+-----------------+-----------------+-------+--------------+--------+");
        System.out.println("| Card 1          | Card 2          | Value | 21 Prob. %   | Bust % |");
        System.out.println("+-----------------+-----------------+-------+--------------+--------+");

        for (Card.Rank rank1 : Card.Rank.values()) {
            for (Card.Rank rank2 : Card.Rank.values()) {
                Deck deck = new Deck();
                // Remove cards from the deck for this combination
                deck.removeCard(null, rank1); // Suit is not important for this calculation
                deck.removeCard(null, rank2);
                Hand hand = new Hand(); // Using the same deck in the hand
                hand.addCard(new Card(null, rank1)); // Suit doesn't matter for this
                hand.addCard(new Card(null, rank2));

                double prob21 = probabilityOf21(hand, deck);
                double bustProb = probabilityOfBust(hand, deck);

                System.out.printf("| %-15s | %-15s | %5d | %11.2f | %6.2f |\n", rank1, rank2, hand.getTotalValue(),
                        prob21 * 100, bustProb * 100);
            }
        }

        System.out.println("+-----------------+-----------------+-------+--------------+--------+");
    }

    /**
     * 次のドローで手札の合計が21になる確率
     */
    public static double probabilityOf21(Hand hand, Deck deck) {
        // 手札の合計を21にするために必要なカードの値
        int neededValue = 21 - hand.getTotalValue();
        // neededValue に合致するカードの数
        int favorableOutcomes = 0;
        for (Card.Rank rank : Card.Rank.values()) {
            if (rank.getValue() == neededValue) {
                // 手札に同じカードの数字があれば減算する
                favorableOutcomes += 4 - countCardsInHand(rank, hand);
            }
        }
        // favorableOutcomes を山札に残っているカード総数で 割る
        return (double) favorableOutcomes / deck.remainingCards();
    }

    /**
     * 次のドローでバーストする確率
     */
    public static double probabilityOfBust(Hand hand, Deck deck) {
        // バーストする前の手札の最大値
        double bustValue = 21 - hand.getTotalValue();
        double bustOutcomes = 0;

        for (Card.Rank rank : Card.Rank.values()) {
            if (rank.getValue() > bustValue) {
                // 手札に同じカードの数字があれば減算する
                bustOutcomes += 4 - countCardsInHand(rank, hand);
            }
        }

        return (double) bustOutcomes / deck.remainingCards();
    }

    private static int countCardsInHand(Card.Rank rank, Hand hand) {
        int count = 0;
        for (int i = 0; i < hand.getCardCount(); i++) {
            if (hand.getCard(i).getRank() == rank) {
                count++;
            }
        }
        return count;
    }
}
