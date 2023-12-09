public class GameMethods {
    private int cardNum;
    private String deck;
    public GameMethods(int cardNum) {
        this.cardNum = cardNum;
    }
    /* generates a certain number of cards based on the parameter
    probabilities are based on actual UNO decks
     */
    public String generateCards(int numOfCards) {
        int num = 0;
        int color = 0;
        int number = 0;
        String deck = "";
        for (int i = 1; i < numOfCards+1; i++) {
            num = (int) (Math.random() * 108) + 1;
            color = (int) (Math.random() * 4) + 1;
            if (num >= 1 && num <= 12) {
                if (color == 1) {
                    deck = deck + "Red Skip Card,";
                } else if (color == 2) {
                    deck = deck + "Blue Skip Card,";
                } else if (color == 3) {
                    deck = deck + "Green Skip Card,";
                } else {
                    deck = deck + "Yellow Skip Card,";
                }
            } else if (num >= 13 && num <= 20) {
                color = (int) (Math.random() * 4) + 1;
                if (color == 1) {
                    deck = deck + "Red Draw-2 Card,";
                } else if (color == 2) {
                    deck = deck + "Blue Draw-2 Card,";
                } else if (color == 3) {
                    deck = deck + "Green Draw-2 Card,";
                } else {
                    deck = deck + "Yellow Draw-2 Card,";
                }
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
                        deck = deck + number + " Card,";
                    }
                }
            } else if (num >= 101 && num <= 104) {
                deck = deck + "Wild Card,";
            } else {
                deck = deck + "Draw-4 Card,";
            }
        }
        setDeck(deck);
        return deck;
    }
    // checks if the user has any cards that can be put down
    public Boolean canPutDown(String topCard, String deck) {
        String color = "null";
        String num = "null";
        /* if the length is 6, this means there is only a color on top
        this only occurs if the previous card was a wild card or a draw-4 card
         */
        if(topCard.length()<=6) {
            color = topCard;
        } else {
            for (int i = 0; i <= 9; i++) {
                String number = Integer.toString(i); // https://stackoverflow.com/questions/5071040/java-convert-integer-to-string
                if (topCard.indexOf(number) != -1) {
                    color = topCard.substring(0, topCard.indexOf(number) - 1);
                    num = number;
                }
            }
        }
        if(topCard.contains("Draw-2")) {
            num="null";
        } else if(topCard.contains("Skip")) {
            color = topCard.substring(0, topCard.indexOf(" S"));
            num="null";
        }
        if(deck.indexOf(num)>-1 || deck.indexOf(color)>-1) {
            if(num.equals("2")) {
                if(deck.indexOf("Draw-2")>-1) {
                    if(deck.substring(0, deck.indexOf("Draw-2")).indexOf("2")>-1) {
                        return true;
                    } else if(deck.substring(deck.indexOf("-2")+2).indexOf("2")>-1) {
                        return true;
                    } else {
                        return false;
                    }
                }

            }
            return true;
        } else if(deck.indexOf("Wild Card")>-1){
            return true;
        } else if(deck.indexOf("Draw-4 Card")>-1) {
            return true;
        } else {
            return false;
        }
    }
    // starting card can only be a numbered card
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
    // chooses card for computer to put down based on color and number
    public String chooseCard(String card) {
        String color = "null";
        String num = "null";
        String chosenCard = "";
        if (card.length() <= 6) {
            color = card;
            if (deck.indexOf(color) > -1) {
                chosenCard = deck.substring(deck.indexOf(color.substring(0, 1)));
                chosenCard = chosenCard.substring(0, chosenCard.indexOf("d,") + 1);
            }
        } else {
            for (int i = 0; i <= 9; i++) {
                String number = Integer.toString(i); // https://stackoverflow.com/questions/5071040/java-convert-integer-to-string
                if (card.indexOf(number) != -1) {
                    color = card.substring(0, card.indexOf(number) - 1);
                    num = number;
                }
            }
            if(card.indexOf("Skip")>-1) {
                color = card.substring(0, card.indexOf(" S"));
                num = "null";
            } else if(card.indexOf("Draw-2")>-1) {
                num = "null";
                color = card.substring(0, card.indexOf(" D"));
            }
            /* if color is found, it finds a card based on the color
            if number is found, it finds a card based on the number
            if neither are found, it checks for a wild card
            if there is no wild card, there has to be a Draw-4 Card, since this code isn't executed unless canPutDown is true
             */
            if (deck.indexOf(color) > -1) {
                chosenCard = deck.substring(deck.indexOf(color.substring(0, 1)));
                if (chosenCard.indexOf("d,") == -1) {
                    chosenCard.substring(0, chosenCard.indexOf("d"));
                } else {
                    chosenCard = chosenCard.substring(0, chosenCard.indexOf("d,") + 1);
                }
            } else if(deck.indexOf(num)>-1){
                chosenCard = deck.substring(0, deck.indexOf(num) + 1) + " Card";
                while ((chosenCard.substring(0, chosenCard.indexOf("d"))).indexOf(num) == -1) {
                    chosenCard = chosenCard.substring(chosenCard.indexOf("d") + 2);
                }
            } else if(deck.indexOf("Wild Card")>-1) {
                chosenCard = "Wild Card";
            } else {
                chosenCard = "Draw-4 Card";
            }
        }
        return chosenCard;
    }
    public void removeCard(String card) {
        int idx = deck.indexOf(card);
        int cardLength = card.length();
        if(deck.length()-idx>=10 && deck.length()-idx<=20) {
            deck = deck.substring(0, idx);
        } else {
            deck = deck.substring(0, idx) + deck.substring(idx+cardLength+1);
        }
    }
    public void pickUp(int num) {
        deck = deck + generateCards(num);
    }

    private void setDeck(String deck) {
        this.deck = deck;
    }
    public String getDeck() {
        return deck;
    }
    public int numOfCards() {
        return cardNum;
    }
    public void updateCardNum(int num) {
        cardNum= cardNum+num;
    }

}