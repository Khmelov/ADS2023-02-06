package by.it.group251001.zhidkov.lesson13;
import by.it.HomeWork;
import org.junit.Test;
@SuppressWarnings("NewClassNamingConvention")
public class Test_Part2_Lesson13Test extends HomeWork {
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
        run("5 -> 3, 5 -> 1, 5 -> 4, 4 -> 1, 4 -> 0, 3 -> 2, 1 -> 0", true).include("5 3 2 4 1 0");
        run("5 -> 3, 5 -> 1, 5 -> 4, 4 -> 1, 4 -> 0, 3 -> 2, 1 -> 0, 2 -> 0", true).include("5 3 2 4 1 0");
        run("0 -> 1, 2 -> 5, 3 -> 1, 3 -> 7, 4 -> 7, 4 -> 8, 4 -> 6, 6 -> 5", true).include("0 2 3 1 4 6 5 7 8");
        run("0 -> 2, 0 -> 4, 0 -> 3, 0 -> 5, 0 -> 1, 2 -> 5, 1 -> 2, 1 -> 3, 1 -> 4, 1 -> 5, 3 -> 4, 3 -> 5, 4 -> 5", true).include("0 1 2 3 4 5");
        run("1 -> 4, 1 -> 3, 1 -> 2, 0 -> 1, 0 -> 2, 0 -> 3, 0 -> 4, 2 -> 3, 2 -> 4, 3 -> 4", true).include("0 1 2 3 4");
        run("1 -> 2, 1 -> 3, 2 -> 3, 0 -> 2, 0 -> 1", true).include("0 1 2 3");
        run("0 -> 1, 1 -> 2", true).include("0 1 2");
        run("1 -> 2, 0 -> 2, 0 -> 1", true).include("0 1 2");
        run("3 -> 4, 1 -> 3, 1 -> 2, 1 -> 4, 2 -> 4, 2 -> 3, 0 -> 4, 0 -> 2, 0 -> 1, 0 -> 3", true).include("0 1 2 3 4");
        run("0 -> 2, 0 -> 1, 0 -> 4, 2 -> 4, 2 -> 3, 3 -> 4, 1 -> 4, 1 -> 3", true).include("0 1 2 3 4");
        run("1 -> 2, 0 -> 2", true).include("0 1 2");
        run("1 -> 2, 0 -> 4, 0 -> 1, 0 -> 3", true).include("0 1 2 3 4");
        run("0 -> 2, 0 -> 1", true).include("0 1 2");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 20 (сейчас их 8).
    }

    @Test
    public void testGraphB() {
        run("0 -> 1", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> A", true).include("yes").exclude("no");
        run("3 -> 1, 3 -> 4, 4 -> 2, 4 -> 5, 1 -> 2, 2 -> 5, 0 -> 1, 5 -> 1", true).include("yes").exclude("no");
        run("5 -> 2, 4 -> 2, 3 -> 4, 3 -> 5, 2 -> 1, 1 -> 0", true).include("no").exclude("yes");
        run("5 -> 2, 4 -> 2, 3 -> 4, 3 -> 5, 2 -> 1, 1 -> 0, 0 -> 2", true).include("yes").exclude("no");
        run("5 -> 2, 4 -> 2, 3 -> 4, 3 -> 5, 2 -> 1, 1 -> 0, 0 -> 2, 2 -> 4", true).include("yes").exclude("no");
        run("A -> C, B -> C, C -> D, C -> E", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 4, 4 -> 3, 3 -> 3, 2 -> 5", true).include("yes").exclude("no");
        run("0 -> 1, 0 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 0 -> 2, 0 -> 3, 1 -> 2, 3 -> 2, 1 -> 4, 4 -> 2, 3 -> 6, 2 -> 5, 4 -> 5, 5 -> 6, 6 -> 7, 4 -> 7, 5 -> 7", true).include("no").exclude("yes");



        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 12 (сейчас их 3).
    }

    @Test
    public void testGraphC() {
        run("1 -> 2, 2 -> 3, 3 -> 1, 2 -> 4, 4 -> 5, 5 -> 6, 6 -> 4", true).include("123\n456");
        run("C -> B, C -> I, I -> A, A -> D, D -> I, D -> B, B -> H, H -> D, D -> E, H -> E, E -> G, A -> F, G -> F, F -> K, K -> G", true).include("C\nABDHI\nE\nFGK");
        run("A -> B, B -> C, C -> A, C -> D, D -> E, F -> E, F -> G", true).include("F\nG\nABC\nD\nE");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 1, 3 -> 5", true).include("0\n1234\n5");
        run("0 -> 1, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 4, 4 -> 0, 3 -> 5", true).include("01234\n5");
        run("0 -> 1, 1 -> 0, 2 -> 0, 1 -> 3", true).include("2\n01\n3");
        run("0 -> 1, 1 -> 0, 2 -> 0, 1 -> 3, 3 -> 4, 4 -> 2", true).include("01234");
        run("A -> B, B -> C, C -> D, D -> E, E -> C, E -> B, E -> F, A -> G", true).include("A\nG\nBCDE\nF");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 8 (сейчас их 2).
    }
}
