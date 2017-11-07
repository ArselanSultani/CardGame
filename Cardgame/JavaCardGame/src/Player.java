import java.util.*;

public class Player {
    private int id;
    private List<Card> hand;

    public Player(int id){
        this.id = id;
        hand = new ArrayList<Card>();
    }

    public void addCard(Card n) {
        hand.add(n);
        sortHand();
    }

    public int returnId () {
        return id;
    }

    public boolean isEmpty () {
        return hand.isEmpty();
    }

    public void printCard() {
        System.out.println("For player: " + id);
        hand.forEach(h -> System.out.println(h.rank +" of " + h.type));
        System.out.println("\n\n");
    }

    void sortHand(){
        //Java 8

        Collections.sort(hand, (Card c1, Card c2) ->
                Integer.valueOf(c1.value).compareTo(c2.value)
        );

        /*
       Java 7 and earlier version
       Collections.sort(hand, new Comparator<Card>() {
           @Override
           public int compare(Card o1, Card o2) {
               return Integer.valueOf(o1.value).compareTo(Integer.valueOf(o2.value));
           }
       });
       */
    }



    int got2 () {
        int found2 = -1;
        for (int i = 0 ;i < hand.size(); i++ ) {
            if (hand.get(i).value == 2)
                found2 = i;
        }
        return found2;
    }


    public int playHand (int boardValue ) {

        Card found = null;
        for (int i = 0 ; i < hand.size(); i++ ) {
            if (hand.get(i).value >= boardValue) {
                found = hand.get(i);
                hand.remove(i);
                if (returnId() == 1) {
                    //System.out.print("-----------");

                }
                //System.out.println("id: " + returnId() + " plays: " + found.type + " - " + found.rank + " - val: " + found
                //.value);
                return found.value;
            }
        }

        if(found == null) {
            int found2 = got2();
            if (found2 != -1) {
                Card foundThe2 = hand.get(found2);
                hand.remove(found2);
                return foundThe2.value;
            }
        }

        return 0;
    }


}
