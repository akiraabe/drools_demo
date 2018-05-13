package fact;

import lombok.Data;
import lombok.ToString;

//@Data
//@ToString
public class Apple {

    public Apple(int size) {
        this.size = size;
    }

    private int size;
    private String rank;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "size=" + size +
                ", rank='" + rank + '\'' +
                '}';
    }
}
