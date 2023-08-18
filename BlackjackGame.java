import java.util.Scanner;

public class BlackjackGame {

    private Player player1;
    private Dealer dealer;
    private Deck deck;

    public BlackjackGame() {
        deck = new Deck();
        deck.shuffle();
        dealer = new Dealer();
        player1 = new Player();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        // ゲームの初期セットアップ
        // プレイヤーに2枚、ディーラーに2枚のカードを配る
        player1.addCard(deck.draw());
        dealer.addCard(deck.draw());
        player1.addCard(deck.draw());
        dealer.addCard(deck.draw());

        // プレイヤーのターン
        while (true) {
            System.out.println("あなたの手札: " + player1);
            System.out.println("次のドローで21になる確率: "
                    + CardCombinationStatistics.probabilityOf21(player1.getHand(), deck) * 100 + "%");
            System.out.println("次のドローでバーストする確率: "
                    + CardCombinationStatistics.probabilityOfBust(player1.getHand(), deck) * 100 + "%");
            System.out.println("カードを引きますか？ (Enter 'hit' or 'stand')");
            String choice = scanner.nextLine().toLowerCase();

            if ("hit".equals(choice)) {
                player1.addCard(deck.draw());
                if (player1.getTotalValue() > 21) {
                    System.out.println("バーストしました！あなたの負け！");
                    System.out.println("あなたの手札: " + player1);
                    System.out.println("ディーラーの手札: " + dealer);
                    return;
                }
            } else if ("stand".equals(choice)) {
                break;
            } else {
                System.out.println("無効な入力です。hit か standを入力してください。");
            }
        }

        // ディーラーのターン. ディーラーの手札が17未満の場合はカードを引き続ける
        while (dealer.getTotalValue() < 17) {
            dealer.addCard(deck.draw());
        }
        System.out.println("ディーラーの手札: " + dealer);

        // 勝敗の判定
        if (dealer.getTotalValue() > 21 || player1.getTotalValue() > dealer.getTotalValue()) {
            System.out.println("あなたの勝ち！");
        } else if (dealer.getTotalValue() == player1.getTotalValue()) {
            System.out.println("ドロー！");
        } else {
            System.out.println("あなたの負け！");
        }
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.play();
    }
}
