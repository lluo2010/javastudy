package eventbus;

/**
 * Created by louis on 2018/6/6.
 */
public class Event1 {
    private int i;
    String name;

    @Override
    public String toString() {
        return "Event1{" +
                "i=" + i +
                ", name='" + name + '\'' +
                '}';
    }

    public Event1(int i, String name) {
        this.i = i;
        this.name = name;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
