public class GameMethods {
    private int numberedCards = 80;
    private int wildCards = 4;
    private int plusTwoCards = 8;
    private int drawFourCards = 4;
    private int skipCards = 12;
    private int cardNum;
    private String deck;
    public GameMethods(int cardNum) {
        this.cardNum = cardNum;
    }
    public String generateDeck() {
        int num = 0;
        int color = 0;
        int number = 0;
        String deck = "";
        for (int i = 1; i <= cardNum; i++) {
            num = (int) (Math.random() * 108) + 1;
            color = (int) (Math.random() * 4) + 1;
            if (num >= 1 && num <= 12) {
                if (color == 1) {
                    deck = deck + " Red Skip Card,";
                } else if (color == 2) {
                    deck = deck + " Blue Skip Card,";
                } else if (color == 2) {
                    deck = deck + " Green Skip Card,";
                } else {
                    deck = deck + " Yellow Skip Card,";
                }
                skipCards = skipCards-1;
            } else if (num >= 13 && num <= 20) {
                color = (int) (Math.random() * 4) + 1;
                if (color == 1) {
                    deck = deck + "Red Draw-Two Card,";
                } else if (color == 2) {
                    deck = deck + "Blue Draw-Two Card,";
                } else if (color == 2) {
                    deck = deck + "Green Draw-Two Card,";
                } else {
                    deck = deck + "Yellow Draw-Two Card,";
                }
                plusTwoCards = plusTwoCards-1;
            } else if (num >= 21 && num <= 100) {
                color = (int) (Math.random() * 4) + 1;
                number = (int) (Math.random() * 9);
                if (color == 1) {
                    deck = deck + "Red ";
                } else if (color == 2) {
                    deck = deck + "Blue ";
                } else if (color == 3) {
                    deck = deck + "Green ";
                } else {
                    deck = deck + "Yellow ";
                }
                for (int j = 0; j <= 9; j++) {
                    if (j == number) {
                        deck = deck + number + " card, ";
                    }
                }
                numberedCards = numberedCards-1;
            } else if (num >= 101 && num <= 104) {
                deck = deck + "Wild Card, ";
                wildCards = wildCards-1;
            } else {
                deck = deck + "Draw-Four Card";
                drawFourCards = drawFourCards-1;
            }
        }
        setDeck(deck);
        return deck;
    }
    // to see if player can put down any cards
    public Boolean canPutDown(String topCard, String deck) {
        int indexOfNum;
        String color = "";
        String num = "";
        for(int i=0; i<=9; i++) {
            String number = Integer.toString(i);
            if(topCard.indexOf(number)>-1) {
                color = topCard.substring(0, topCard.indexOf(i));
                num = number;
            }
        }
        if(deck.indexOf(num)>-1) {
            return true;
        }
        if(deck.indexOf(color)>-1) {
            return true;
        }
        return false;
    }
    public String pickUp() {

    }

    private void setDeck(String deck) {
        this.deck = deck;
    }
    public String getDeck() {
        return deck;
    }
    public String startingCard() {
        int color = (int) (Math.random() * 4) + 1;
        int number = (int) (Math.random() * 9);
        String card = "";
        for(int i = 0; i<=9; i++) {
            if(i==number) {
                for(int j = 1; j<=4; j++) {
                    if(j==color) {
                        if(j==1) {
                            card = "Red " + number + " Card";
                        } else if(j==2) {
                            card = "Blue " + number + " Card";
                        } else if(j==3) {
                            card = "Green " + number + " Card";
                        } else {
                            card = "Yellow " + number + " Card";
                        }
                    }
                }
            }
        }
        return card;
    }
}