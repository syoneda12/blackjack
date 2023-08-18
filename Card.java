public class Card {
    // スートの列挙型
    public enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS;

        @Override
        public String toString() {
            switch (this) {
                case SPADES:
                    return "スペード";
                case HEARTS:
                    return "ハート";
                case DIAMONDS:
                    return "ダイヤ";
                case CLUBS:
                    return "クローバー";
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    // カードの数字の列挙型
    public enum Rank {
        ACE(1), // エースは1扱い
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        // 表示用
        public String toString() {
            switch (this) {
                case ACE:
                    return "A";
                case TWO:
                    return "2";
                case THREE:
                    return "3";
                case FOUR:
                    return "4";
                case FIVE:
                    return "5";
                case SIX:
                    return "6";
                case SEVEN:
                    return "7";
                case EIGHT:
                    return "8";
                case NINE:
                    return "9";
                case TEN:
                    return "10";
                case JACK:
                    return "J";
                case QUEEN:
                    return "Q";
                case KING:
                    return "K";
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return suit + "の" + rank;
    }

    public int rankValueOf() {
        switch (this.rank) {
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
                return 11; // In the game of Blackjack, an Ace can also be 1, but initially, we value it as
                           // 11.
            default:
                throw new IllegalArgumentException("Invalid card rank");
        }
    }

    // public static void main(String[] args) {
    // Card card = new Card(Suit.HEARTS, Rank.QUEEN);
    // System.out.println(card); // 出力: QUEEN of HEARTS
    // }
}
