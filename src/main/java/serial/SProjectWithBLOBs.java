package serial;

import java.io.Serializable;

/**
 * Created by louis on 2018/8/28.
 */
public class SProjectWithBLOBs extends SProject implements Serializable {
    //private static final long serialVersionUID = 7382193346717471169L;
    private static final long serialVersionUID = 2L;


    String strOther1;
    Integer nOther1;


    public String getStrOther1() {
        return strOther1;
    }

    public void setStrOther1(String strOther1) {
        this.strOther1 = strOther1;
    }

    public Integer getnOther1() {
        return nOther1;
    }

    public void setnOther1(Integer nOther1) {
        this.nOther1 = nOther1;
    }


    @Override
    public String toString() {
        String str = super.toString();
        String str2 = "SProjectWithBLOBs{" +
                "strOther1='" + strOther1 + '\'' +
                ", nOther1=" + nOther1 +
                '}';
        return str+"\r\n"+str2;
    }
}
