package by.it.group251003.Trukhan.lesson08;

import by.it.group251003.Trukhan.lesson08.A_Knapsack;
import by.it.group251003.Trukhan.lesson08.B_Knapsack;
import by.it.group251003.Trukhan.lesson08.C_Stairs;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class Lesson8Test {
    @Test
    public void A() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataA.txt");
        by.it.group251003.Trukhan.lesson08.A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        assertEquals("A failed", res, 14);
    }

    @Test
    public void B() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataB.txt");
        by.it.group251003.Trukhan.lesson08.B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        assertEquals("B failed", res, 9);
    }

    @Test
    public void C() throws Exception {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataC.txt");
        by.it.group251003.Trukhan.lesson08.C_Stairs instance = new C_Stairs();
        int res=instance.getMaxSum(stream);
        assertEquals("C failed", res, 3);
    }

}
