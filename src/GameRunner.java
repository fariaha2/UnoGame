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
        player1.generateCards(cardNum);
        deck = player1.getDeck();
        GameMethods player2 = new GameMethods(cardNum);
        player2.generateCards(cardNum);
        topCard = player1.startingCard();
        int a = 1;
        while ((player1.numOfCards() !=0 && player2.numOfCards() !=0)) {
            // if num is odd, it is the player's turn
            if (a % 2 == 1) {
                a = a+1;
                if (player1.canPutDown(topCard, player1.getDeck())) {
                    System.out.println("Your deck is: " + player1.getDeck());
                    System.out.println("The card/color on top of the pile is: " + topCard);
                    System.out.println("What would you like to put down?");
                    Scanner scan = new Scanner(System.in);
                    card = scan.nextLine();
                    while (!(player1.getDeck().indexOf(card)>-1)) {
                        System.out.println("That card is not in your deck. What would you like to put down?");
                        card = scan.nextLine();
                    }
                    // removes card from the player's deck, reduces cardNum by 1, sets topCard to the new card
                    player1.removeCard(card);
                    topCard = card;
                    player1.updateCardNum(-1);
                    if(player1.numOfCards()==1) {
                        System.out.println("UNO! You have one card left!");
                    }
                    // various outcomes based on the card put down
                    if (topCard.indexOf("Draw-4") > -1) {
                        System.out.println("The computer has to draw four cards!");
                        player2.pickUp(4);
                        player2.updateCardNum(4);
                        System.out.println("What color do you want it to be?");
                        String color = scan.nextLine();
                        topCard = color;
                    } else if (topCard.indexOf("Draw-2") > -1) {
                        System.out.println("The computer has to draw two cards!");
                        player2.pickUp(2);
                        player2.updateCardNum(2);
                    } else if (topCard.indexOf("Skip") > -1) {
                        System.out.println("The computer's turn will be skipped.");
                        // adds 1 so the turn will be skipped
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
                    // removes card from the computer's deck, reduces cardNum by 1, sets topCard to the new card
                    player2.removeCard(topCard);
                    player2.updateCardNum(-1);
                    if(player2.numOfCards()==1) {
                        System.out.println("UNO! The computer has one card left!");
                    }
                    // various outcomes based on the card put down
                    if (topCard.indexOf("Draw-4") > -1) {
                        System.out.println("You have to draw four!");
                        player1.pickUp(4);
                        player1.updateCardNum(4);
                        int color = (int) (Math.random()*4)+1;
                        if(color==1) {
                            topCard = "Red";
                        } else if(color==2) {
                            topCard = "Blue";
                        } else if(color==3) {
                            topCard = "Yellow";
                        } else if(color==4) {
                            topCard = "Green";
                        }
                    } else if (topCard.indexOf("Draw-2") > -1) {
                        System.out.println("You have to draw two cards!");
                        player1.pickUp(2);
                        player1.updateCardNum(2);
                    } else if (topCard.indexOf("Skip") > -1) {
                        System.out.println("Your turn will be skipped.");
                        // adds 1 so the turn will be skipped
                        a=a+1;
                    } else if (topCard.indexOf("Wild") > -1) {
                        int color = (int) (Math.random() * 4) + 1;
                        String chosenColor = "";
                        if (color == 1) {
                            chosenColor = "Red";
                        } else if (color == 2) {
                            chosenColor = "Blue";
                        } else if (color == 3) {
                            chosenColor = "Green";
                        } else {
                            chosenColor = "Yellow";
                        }
                        System.out.println("The color is " + chosenColor);
                        topCard = chosenColor;
                    }
                } else {
                    System.out.println("The computer does not have any cards to put down. It will draw one.");
                    player2.pickUp(1);
                    player2.updateCardNum(1);

                }
            }
        }
        if(player1.numOfCards()==0) {
            System.out.println("UNO OUT! Congratulations, you won!");
        } else {
            System.out.println("The computer won. Good luck next time!");
        }
        System.out.println("Want to play again? Enter y/n");
        scan.nextLine();
        String ans = scan.nextLine();
        if(ans.equals("y")) {
            setup("repeat");
        } else {
            System.out.println("Thank you for playing UNO! Have a nice day!");
        }
    }
}
