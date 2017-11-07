import javafx.print.PageLayout;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Created by Arsa on 08.07.2017.
 */
public class MainGame {

    Card [] cardStack;
    List<Player> players;
    int playerToPlay = 0;

    public MainGame () {
        cardStack = new Card[52];
        players = new ArrayList<Player>();
    }

    public void init () {

        for( int i = 0;i < 4; i++) {
            players.add(new Player(i+1));
        }

        createCardStack();
        stockTheCardStack();
        dealTheCards();



        theGameStart();
    }

    int startRound (List<Player> roundPlayer, int playerToPlay, int boardValue) {

        while (true) {
            if (players.size() == 1) {
                //Game won
                System.out.println("Player " + players.get(0).returnId() + " has lost");
                System.out.println("-----------------------------------------------");
                return 0;
            }
            if (roundPlayer.size() == 1) {
                //Round won
                return -1;
            }
            if (playerToPlay >= (roundPlayer.size()-1))
                playerToPlay = 0;
            else
                playerToPlay++;



            if (roundPlayer.get(playerToPlay).isEmpty()) {

                //A player has no longer any cards left to play, does not lose the game
                System.out.println("Player " + roundPlayer.get(playerToPlay).returnId() + " has finished its cards");
                int foundId = findThePlayerFromList(roundPlayer.get(playerToPlay).returnId());
                roundPlayer.remove(playerToPlay);
                players.remove(foundId);
                startRound(roundPlayer, playerToPlay, boardValue);
            }




            int returnCardValue = roundPlayer.get(playerToPlay).playHand(boardValue);
            if (returnCardValue == 0) {
                //player passes so the players card situation get updated
                int findTheId = findThePlayerFromList(roundPlayer.get(playerToPlay).returnId());
                System.out.println("---------si bf: " + players.size());
                players.set(findTheId, roundPlayer.get(playerToPlay));
                System.out.println("---------af: " + players.size());
                roundPlayer.remove(playerToPlay);

            } else {
                boardValue = returnCardValue;
            }

            try {
                Thread.sleep(100);
            } catch (java.lang.InterruptedException ie) {
                System.out.println(ie);
            }

        }

    }

    void theGameStart() {
        List<Player> roundPlayer = players;
        int boardValue = 3;

        int returnAlt = startRound(roundPlayer, playerToPlay, boardValue);
        System.out.println("************Return value: " + returnAlt + "*************    ");
        while (returnAlt != 0) {
            if (returnAlt == -1) {
                for(int i = 0 ;i< players.size(); i++ ) {
                    roundPlayer.get(i).printCard();
                }
                theGameStart();
            }
        }


    }

    int findThePlayerFromList (int id) {

        for (int i = 0; i < players.size(); i++ ){
            if (players.get(i).returnId() == id){
                return i;
            }
        }

        return 0;
    }

    void dealTheCards() {
        for (int i = 0; i < cardStack.length; i++ ) {
            players.get(i % players.size()).addCard(cardStack[i]);
        }
    }

    void createCardStack() {
        String [] types = {"clubs", "diamond", "spade", "hearts"};
        String [] ranks = {"2","3","4","5","6","7","8", "9", "10", "jack", "queen", "king", "ace"};
        int curPosStack = 0;

        for (int i = 0 ; i < types.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                cardStack[curPosStack] = new Card(types[i], ranks[j], j+1,"fileToPath");
                curPosStack++;
            }
        }
    }

    public void stockTheCardStack(){
        Random random = new Random();
        random.nextInt();
        for (int j = 0; j<10;j++){
            for ( int i  = 0; i < cardStack.length; i++ ) {
                int nextRandGen = i + random.nextInt(cardStack.length - i);
                Card cardHelper = cardStack[i];
                cardStack[i] = cardStack[nextRandGen];
                cardStack[nextRandGen] = cardHelper;
            }
        }
    }

}
