public class Player {
    private Hand playerHand = new Hand();;

    public void addCard(Card card) {
        playerHand.addCard(card);
    }

    public int getTotalValue() {
        return playerHand.getTotalValue();
    }

    public Hand getHand() {
        return playerHand;
    }

    @Override
    public String toString() {
        return playerHand.toString() + " (Total: " + playerHand.getTotalValue() + ")";
    }
}