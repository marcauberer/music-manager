package bartype;

public class BarType {

    long id;
    private int beatCount; // e.g. for 6/8: 6
    private int beatValue; // e.g. for 6/8: 8

    public BarType(long id, int beatCount, int beatValue) {
        this.id = id;
        this.beatCount = beatCount;
        this.beatValue = beatValue;
    }

    public long getId() {
        return id;
    }

    public int getBeatCount() {
        return beatCount;
    }

    public int getBeatValue() {
        return beatValue;
    }
}