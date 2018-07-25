package disruptor;

import com.lmax.disruptor.EventTranslatorTwoArg;

/**
 * Created by louis on 2018/7/20.
 */
public class MyEventInfo {

    private int value;
    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyEventInfo{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}


class MyEventInfoTranslator implements EventTranslatorTwoArg<MyEventInfo, Integer, String>{

    public void translateTo(MyEventInfo myEventInfo, long l, Integer value, String name) {
        myEventInfo.setName(name);
        myEventInfo.setValue(value);
    }
}