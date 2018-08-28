package serial;

/**
 * Created by louis on 2018/8/28.
 */
public class SProject extends BaseModel {

    protected  static final long serialVersionUID = 2L;

    String s1;
    Integer p1;
    Integer p2;
    Integer p3;
    Integer p4;

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public Integer getP1() {
        return p1;
    }

    public void setP1(Integer p1) {
        this.p1 = p1;
    }


    public Integer getP2() {
        return p2;
    }

    public void setP2(Integer p2) {
        this.p2 = p2;
    }


    public Integer getP3() {
        return p3;
    }

    public void setP3(Integer p3) {
        this.p3 = p3;
    }


    public Integer getP4() {
        return p4;
    }

    public void setP4(Integer p4) {
        this.p4 = p4;
    }


    @Override
    public String toString() {
        return "SProject{" +
                "s1='" + s1 + '\'' +
                ", p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                ", p4=" + p4 +
                '}';
    }
}
