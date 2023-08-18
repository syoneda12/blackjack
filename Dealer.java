public class Dealer {
    private Hand hand = new Hand();

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getTotalValue() {
        return hand.getTotalValue();
    }

    @Override
    public String toString() {
        return hand.toString() + " (Total: " + hand.getTotalValue() + ")";
    }
}
