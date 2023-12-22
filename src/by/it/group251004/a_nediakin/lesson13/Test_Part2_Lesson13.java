package by.it.group251004.a_nediakin.lesson13;

import by.it.HomeWork;
import org.junit.Test;

@SuppressWarnings("NewClassNamingConvention")
public class Test_Part2_Lesson13 extends HomeWork {

    @Test
    public void testGraphA() {
        run("0 -> 1", true).include("0 1");
        run("0 -> 1, 1 -> 2", true).include("0 1 2");
        run("0 -> 2, 1 -> 2, 0 -> 1", true).include("0 1 2");
        run("0 -> 2, 1 -> 3, 2 -> 3, 0 -> 1", true).include("0 2 1 3");
        run("1 -> 3, 2 -> 3, 2 -> 3, 0 -> 1, 0 -> 2", true).include("0 2 1 3");
        run("0 -> 1, 0 -> 2, 0 -> 2, 1 -> 3, 1 -> 3, 2 -> 3", true).include("0 2 1 3");
        run("A -> B, A -> C, B -> D, C -> D", true).include("A C B D");
        run("A -> B, A -> C, B -> D, C -> D, A -> D", true).include("A C B D");
        run("0 -> 1, 1 -> 2, 3 -> 6, 4 -> 5, 5 -> 6, 4 -> 7, 8 -> 9, 9 -> 10, 9 -> 13, 10 -> 17, 13 -> 16, 12 -> 16, 16 -> 17, 15 -> 16, 15 -> 17, 8 -> 11, 11 -> 14, 11 -> 15")
                .include("12 8 11 15 14 9 13 16 10 17 4 7 5 3 6 0 1 2");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 20 (сейчас их 8).
    }

    @Test
    public void testGraphB() {
        run("0 -> 1", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 12 (сейчас их 3).
    }

    @Test
    public void testGraphC() {
        run("1 -> 2, 2 -> 3, 3 -> 1, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 4", true)
                .include("123\n456");
        run("C -> B, C -> I, I -> A, A -> D, D -> I, D -> B, B -> H, H -> D, D -> E, H -> E, E -> G, A -> F, G -> F, F -> K, K -> G", true)
                .include("C\nABDHI\nE\nFGK");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 8 (сейчас их 2).
    }


}