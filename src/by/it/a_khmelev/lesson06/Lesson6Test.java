package by.it.a_khmelev.lesson06;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class Lesson6Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataA.txt");
        A_LIS instance = new A_LIS();
        int result=instance.getSeqSize(stream);
        boolean ok=(result==3);
        assertTrue("A failed", ok);
    }


    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataB.txt");
        B_LongDivComSubSeq instance=new B_LongDivComSubSeq();
        int result=instance.getDivSeqSize(stream);
        boolean ok=(result==3);
        assertTrue("B failed", ok);
    }

    @Test(timeout = 1000)
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result=instance.getNotUpSeqSize(stream);
        boolean ok=(result==4);
        assertTrue("C failed", ok);
    }

}
