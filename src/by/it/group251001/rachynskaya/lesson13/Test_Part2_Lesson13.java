package by.it.group251001.rachynskaya.lesson13;

import by.it.HomeWork;
import org.junit.Test;

@SuppressWarnings("NewClassNamingConvention")
public class Test_Part2_Lesson13 extends HomeWork {

    @Test
    public void testGraphA() {
        run("0 -> 1", true).include("0 1");
        run("0 -> 1, 1 -> 2", true).include("0 1 2");
        run("0 -> 2, 1 -> 2, 0 -> 1", true).include("0 1 2");
        run("0 -> 2, 1 -> 3, 2 -> 3, 0 -> 1", true).include("0 1 2 3");
        run("1 -> 3, 2 -> 3, 2 -> 3, 0 -> 1, 0 -> 2", true).include("0 1 2 3");
        run("0 -> 1, 0 -> 2, 0 -> 2, 1 -> 3, 1 -> 3, 2 -> 3", true).include("0 1 2 3");
        run("A -> B, A -> C, B -> D, C -> D", true).include("A B C D");
        run("A -> B, A -> C, B -> D, C -> D, A -> D", true).include("A B C D");
        run("A -> B, B -> C, C -> D", true).include("A B C D");
        run("0 -> 1, 1 -> 3, 1 -> 2, 1 -> 5, 2 -> 3, 2 -> 4, 3 -> 4, 4 -> 5", true).include("0 1 2 3 4 5");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G", true).include("A B C D E F G");
        run("0 -> 1, 0 -> 3, 1 -> 2, 1 -> 3, 2 -> 3, 2 -> 4, 2 -> 5, 3 -> 4, 4 -> 5", true).include("0 1 2 3 4 5");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H", true).include("A B C D E F G H");
        run("0 -> 1, 0 -> 3, 1 -> 2, 1 -> 3, 2 -> 3, 2 -> 4, 2 -> 5, 3 -> 4, 4 -> 5, 5 -> 6", true).include("0 1 2 3 4 5 6");
        run("A -> B, B -> C, C -> D, D -> E", true).include("A B C D E");
        run("A -> B, B -> C", true).include("A B C");
        run("K -> L, K -> M, K -> M, L -> N, L -> N, M -> N", true).include("K L M N");
        run("1 -> 2, 2 -> 3, 3 -> 4", true).include("1 2 3 4");
        run("1 -> 2, 3 -> 4, 2 -> 3, 3 -> 5", true).include("1 2 3 4 5");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I", true).include("A B C D E F G H I");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 20 (сейчас их 8).
    }

    @Test
    public void testGraphB() {
        run("0 -> 1", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 3, 2 -> 3, 0 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 3, 2 -> 3, 0 -> 2, 3 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 3, 1 -> 2, 1 -> 5, 2 -> 3, 2 -> 4, 3 -> 4, 4 -> 5, 4 -> 1", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 3, 1 -> 2, 1 -> 5, 2 -> 3, 2 -> 4, 3 -> 4, 4 -> 5, 5 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 5, 5 -> 3, 2 -> 3", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 3, 2 -> 3, 0 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 3, 2 -> 3, 0 -> 2, 3 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 3, 2 -> 3, 0 -> 2, 3 -> 4, 2 -> 4", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 3", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 3, 2 -> 3, 0 -> 2, 1 -> 5, 3 -> 6, 5 -> 6, 6 -> 2", true).include("yes").exclude("no");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 12 (сейчас их 3).
    }

    @Test
    public void testGraphC() {
        run("1 -> 2, 2 -> 3, 3 -> 1", true)
                .include("123");
        run("1 -> 2, 2 -> 3, 3 -> 1, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 4", true)
                .include("123\n456");
        run("0 -> 1, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 3", true)
                .include("012\n345");
        run("1 -> 2, 2 -> 3, 3 -> 1, 4 -> 5, 5 -> 6, 6 -> 4, 7 -> 8, 8 -> 9, 9 -> 7", true)
                .include("123\n456\n789");
        run("P -> Q, Q -> R, R -> S, S -> P, P -> T, T -> U, U -> P, V -> W, W -> X, X -> V", true)
                .include("PQRSTU\nVWX");
        run("C -> B, C -> I, I -> A, A -> D, D -> I, D -> B, B -> H, H -> D, D -> E, H -> E, E -> G, A -> F, G -> F, F -> K, K -> G", true)
                .include("C\nABDHI\nE\nFGK");
        run("I -> A, A -> D, D -> I, D -> B, B -> H, H -> D, A -> F, G -> F, F -> K, K -> G", true)
                .include("ABDHI\nFGK");
        run("Q -> R, R -> U, U -> Q, V -> W, W -> X, X -> V", true)
                .include("QRU\nVWX");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 8 (сейчас их 2).
    }


}