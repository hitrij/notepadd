package notepadd;

public abstract class Record {
    public static  int count = 0;

    private int id;

    public Record() {
        count++;
        id = count;
    }
    public int getId() {
        return id;
    }
}
