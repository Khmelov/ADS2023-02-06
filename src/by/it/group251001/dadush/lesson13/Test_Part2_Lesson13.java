package by.it.group251001.dadush.lesson13;

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
        run("E -> G, D -> F, B -> D, A -> D, A -> B, C -> G, B -> E", true).include("A B C D E F G");
        run("A -> B, B -> C, C -> D, C -> E, E -> F, E -> G, G -> H", true).include("A B C D E F G H");
        run("0 -> 1, 1 -> 3, 1 -> 2, 1 -> 5, 2 -> 3, 2 -> 4, 3 -> 4, 4 -> 5", true).include("0 1 2 3 4 5");
        run("5 -> 4, 5 -> 2, 4 -> 3, 4 -> 2, 3 -> 2, 3 -> 1, 3 -> 0, 2 -> 1, 1 -> 0", true).include("5 4 3 2 1 0");
        run("I -> J, K -> L, J -> K, K -> M", true).include("I J K L M");
        run("A -> B, B -> D, D -> F, A -> C, C -> E, E -> F", true).include("A B C D E F");
        run("B -> A, B -> C, A -> D, C -> D", true).include("B A C D");
        run("B -> A, C -> A, B -> C, A -> D, C -> D", true).include("B C A D");
        run("5 -> 1, 1 -> 3, 5 -> 4, 4 -> 9, 9 -> 3", true).include("5 1 4 9 3");
        run("1 -> 2, 2 -> 3, 3 -> 20, 2 -> 4, 4 -> 20, 1 -> 11, 11 -> 5, 5 -> 4", true).include("1 11 2 3 5 4 20");
        run("1 -> 2, 2 -> 3, 3 -> 20, 2 -> 4, 4 -> 20, 1 -> 9, 9 -> 5, 5 -> 4", true).include("1 2 3 9 5 4 20");
        run("9 -> 1, 1 -> 6, 6 -> 7, 9 -> 8, 8 -> 2, 2 -> 7, 9 -> 3, 3 -> 5, 5 -> 7", true).include("9 1 3 5 6 8 2 7");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("has cycles");
        run("A -> B, B -> C", true).include("A B C");
        run("A -> B, A -> C, B -> D, C -> D, D -> E", true).include("A B C D E");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G", true).include("A B C D E F G");
        run("A -> B, A -> C, B -> D, C -> D", true).include("A B C D");
        run("A -> B, B -> C, C -> A", true).include("has cycles");
        run("A -> B, B -> C, C -> D, D -> B", true).include("has cycles");
        run("A -> B, A -> C, B -> D, C -> D, D -> A", true).include("has cycles");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 20 (сейчас их 8).
    }

    @Test
    public void testGraphB() {
        run("0 -> 1", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");
        run("A -> B, E -> B, E -> F, F -> A, A -> E, B -> F", true).include("yes").exclude("no");
        run("Z -> L, Z -> N, Z -> E, L -> A, N -> L, N -> A, E -> A", true).include("no").exclude("yes");
        run("Z -> L, Z -> N, Z -> E, L -> A, N -> L, N -> A, E -> A, A -> Z", true).include("yes").exclude("no");
        run("9 -> 1, 1 -> 6, 6 -> 7, 9 -> 8, 8 -> 2, 2 -> 7, 9 -> 3, 3 -> 5, 5 -> 7", true).include("no").exclude("yes");
        run("9 -> 1, 1 -> 6, 6 -> 7, 9 -> 8, 8 -> 2, 2 -> 7, 9 -> 3, 3 -> 5, 5 -> 7, 2 -> 1, 6 -> 8", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 0, 0 -> 4, 4 -> 5, 5 -> 6, 6 -> 0", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E", true).include("no").exclude("yes");
        run("A -> B, B -> C, C -> D, D -> A", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> A", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> A, A -> F, F -> G, G -> H, H -> I, I -> J, J -> A", true).include("yes").exclude("no");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 12 (сейчас их 3).
    }

    @Test
    public void testGraphC() {
        run("1->2, 2->3, 3->1, 3->4, 4->5, 5->6, 6->4", true)
                .include("123\n456");
        run("C->B, C->I, I->A, A->D, D->I, D->B, B->H, H->D, D->E, H->E, E->G, A->F, G->F, F->K, K->G", true)
                .include("C\nABDHI\nE\nFGK");
        run("A->C, C->B, B->A, D->E, E->F, F->D, A->F", true)
                .include("ABC\nDEF");
        run("A->D, A->X, A->K, D->X, X->K, D->Z, Y->X, K->Y, Y->Z", true)
                .include("A\nD\nKXY\nZ");
        run("L->Z, Z->N, Z->E, L->A, N->L, N->A, E->A", true)
                .include("LNZ\nE\nA");
        run("A->B, B->C, C->D, D->A, D->E, E->F, F->E", true)
                .include("ABCD\nEF");
        run("1 -> 2, 2 -> 3, 3 -> 1, 4 -> 5, 5 -> 6, 6 -> 4, 7 -> 8, 8 -> 9, 9 -> 7", true)
                .include("789\n456\n123");
        run("P -> Q, Q -> R, R -> S, S -> P, P -> T, T -> U, U -> P, V -> W, W -> X, X -> V", true)
                .include("VWX\nPQRSTU");
        run("I -> A, A -> D, D -> I, D -> B, B -> H, H -> D, A -> F, G -> F, F -> K, K -> G", true)
                .include("ABDHI\nFGK");
        run("Q -> R, R -> U, U -> Q, V -> W, W -> X, X -> V", true)
                .include("VWX\nQRU");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 8 (сейчас их 2).
    }


}