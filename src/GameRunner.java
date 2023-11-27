import java.util.Scanner;
public class GameRunner {
    Scanner scan = new Scanner(System.in);
    public String card;
    public String topCard;
    private String name;
    private int cardNum;
    public String deck = "";
    public GameRunner(String name) {
        this.name = name;
    }
    public void setup() {
        System.out.println("Hello, " + name + "! Welcome to UNO!");
        if ((int) (Math.random() * 2) + 1 == 1) {
            System.out.println("You will be making the first move.");
        } else {
            System.out.println("The computer will be making the first move.");
        }
        System.out.println("How many cards do you want to be in a deck? MINIMUM OF 7");
        cardNum = scan.nextInt();
        startGame();
    }
    private void startGame() {
        GameMethods player1 = new GameMethods(cardNum);
        player1.generateDeck();
        System.out.println("Your deck is: " + player1.getDeck());
        deck = player1.getDeck();
        GameMethods player2 = new GameMethods(cardNum);
        player2.generateDeck();
        System.out.println("The first card is: " + player1.startingCard());
        topCard = player1.startingCard();
        while(player1.getDeck().length()!=0 || player2.getDeck().length()!=0) {
            if(!(player1.canPutDown(topCard, deck))) {
                System.out.println("There are no cards in your deck that you can put down.");
                System.out.println("You will pick up one card.");

            }
            System.out.println("What would you like to put down?");
            scan.nextLine();
            card = scan.nextLine();
            while(!(deck.indexOf(card.substring(1))>-1)) {
                System.out.println("That card is not in your deck. What would you like to put down?");
                card = scan.nextLine();
            }

        }
    }
}
