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
        System.out.println("How many cards do you want to be in a deck? MINIMUM OF 7");
        cardNum = scan.nextInt();
        startGame();
    }
    public void setup(String repeat) {
        System.out.println("Welcome to another round!");
        startGame();
    }
    private void startGame() {
        GameMethods player1 = new GameMethods(cardNum);
        player1.generateCards("demo");
        deck = player1.getDeck();
        GameMethods player2 = new GameMethods(cardNum);
        player2.generateCards("demo");
        topCard = player1.startingCard();
        int a = 1;
        while ((player1.numOfCards() !=0 || player2.numOfCards() !=0)) {
            if (a % 2 == 1) {
                a = a+1;
                if (player1.canPutDown(topCard, player1.getDeck())) {
                    System.out.println("Your deck is: " + player1.getDeck());
                    System.out.println("The card/color on top of the pile is: " + topCard);
                    System.out.println("What would you like to put down?");
                    scan.nextLine();
                    card = scan.nextLine();
                    while (!(player1.getDeck().indexOf(card)>-1)) {
                        System.out.println("That card is not in your deck. What would you like to put down?");
                        card = scan.nextLine();
                    }
                    player1.removeCard(card);
                    topCard = card;
                    player1.updateCardNum(-1);
                    if (topCard.indexOf("Draw-4") > -1) {
                        System.out.println("The computer has to draw four cards!");
                        player2.pickUp(4);
                        player2.updateCardNum(4);
                        System.out.println("What color do you want it to be?");
                        String color = scan.nextLine();
                        topCard = color + " Draw-4 Card";
                    } else if (topCard.indexOf("Draw-2") > -1) {
                        System.out.println("The computer has to draw two cards!");
                        player2.pickUp(2);
                        player2.updateCardNum(2);
                    } else if (topCard.indexOf("Skip") > -1) {
                        System.out.println("The computer's turn will be skipped.");
                        a=a+1;
                    } else if (topCard.indexOf("Wild") > -1) {
                        System.out.println("You put down a wild card!\nWhat color do you want to choose?");
                        String color = scan.nextLine();
                        topCard = color;
                    }
                } else {
                    System.out.println("There are no cards in your deck that you can put down.");
                    System.out.println("You will pick up one card.");
                    player1.pickUp(1);
                    player1.updateCardNum(1);
                    System.out.println("Your new deck is " + player1.getDeck());
                }
            } else {
                a = a+1;
                if (player2.canPutDown(topCard, player2.getDeck())) {
                    String choice = player2.chooseCard(topCard);
                    topCard = choice;
                    System.out.println("The computer put down a " + topCard + "!");
                    player2.removeCard(topCard);
                    player2.updateCardNum(-1);
                    if (topCard.indexOf("Draw-4") > -1) {
                        System.out.println("You have to draw four!");
                        player1.pickUp(4);
                        player1.updateCardNum(4);
                        int color = (int) (Math.random()*4)+1;
                        if(color==1) {
                            topCard = "Red Draw-4 Card";
                        } else if(color==2) {
                            topCard = "Blue Draw-4 Card";
                        } else if(color==3) {
                            topCard = "Yellow Draw-4 Card";
                        } else if(color==4) {
                            topCard = "Green Draw-4 Card";
                        }
                    } else if (topCard.indexOf("Draw-2") > -1) {
                        System.out.println("You have to draw two cards!");
                        player1.pickUp(2);
                        player1.updateCardNum(2);
                    } else if (topCard.indexOf("Skip") > -1) {
                        System.out.println("Your turn will be skipped.");
                        a=a+1;
                    } else if (topCard.indexOf("Wild") > -1) {
                        int color = (int) (Math.random() * 4) + 1;
                        String colorr = "";
                        if (color == 1) {
                            colorr = "Red";
                        } else if (color == 2) {
                            colorr = "Blue";
                        } else if (color == 3) {
                            colorr = "Green";
                        } else {
                            colorr = "Yellow";
                        }
                        System.out.println("The computer put down a wild card! The color is " + colorr);
                        topCard = colorr;
                    }
                } else {
                    System.out.println("The computer does not have any cards to put down. It will draw one.");
                    player2.pickUp(1);
                    player2.updateCardNum(1);

                }
            }
        }
        if(player1.numOfCards()==0) {
            System.out.println("Congratulations! You won!");
        } else {
            System.out.println("The computer won.");
        }
        System.out.println("Want to play again? Enter y/n");
        String ans = scan.nextLine();
        if(ans.equals("y")) {
            setup("repeat");
        }
    }
}
