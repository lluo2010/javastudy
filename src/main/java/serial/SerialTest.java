package serial;

import java.io.*;

/**
 * Created by louis on 2018/8/28.
 */
public class SerialTest {
    public static void main(String[] args) {
        //write();
        read();

    }

    private  static void write(){
        SProjectWithBLOBs sp = new SProjectWithBLOBs();
        sp.setnOther1(1);
        sp.setP1(1);
        sp.setS1("s1");
        sp.setStrOther1("s1");
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("cache2.txt"));
            out.writeObject(sp);
            out.close();
            System.out.println("write finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void read(){
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("cache2.txt"));
            SProjectWithBLOBs obj = (SProjectWithBLOBs)in.readObject();
            System.out.println("obj:"+obj);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
