package serial;

import java.io.Serializable;

/**
 * Created by louis on 2018/8/28.
 */
public class SerialObj implements Serializable{

    static final long serialVersionUID = 1L;

    String name;
    Integer age;

    Integer v1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public Integer getV1() {
        return v1;
    }

    public void setV1(Integer v1) {
        this.v1 = v1;
    }


    @Override
    public String toString() {
        return "SerialObj{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", v1=" + v1 +
                '}';
    }
}
