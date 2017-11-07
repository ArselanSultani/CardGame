/**
 * Created by Arsa on 08.07.2017.
 */
public class Card {
    public String type, rank;
    public int value;
    public String picPath;

    public Card (String type, String rank, int value, String picPath) {
        this.type = type;
        this.rank = rank;
        this.value = value;
        this.picPath = picPath;
    }

    public String returnType () {
        return type;
    }

    public String returnRank () {
        return rank;
    }

    public int retunrValue () {
        return value;
    }
}
