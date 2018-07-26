package cglib.beancopier;

/**
 * Created by louis on 2018/7/26.
 */
public class Family {
    private People people;
    private int others;


    public Family(People people, int others) {
        this.people = people;
        this.others = others;
    }


    public Family() {
    }


    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "Family{" +
                "people=" + people +
                ", others=" + others +
                '}';
    }
}
