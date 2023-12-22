package by.it.group251003.kukhotskovolets.lesson13;

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
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 20 (сейчас их 8).
        run("0 -> 1, 1 -> 2, 1 -> 3, 1 -> 4", true).include("0 1 2 3 4");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4", true).include("0 1 2 3 4");
        run("M -> O, N -> P, O -> Q, P -> R, Q -> S, R -> T, S -> U, T -> V, U -> W, " +
                "V -> X, W -> Y, X -> Z, Y -> A, Z -> C", true).include("M N O P Q R S T U V W X Y A Z C");
        run("9 -> 10, 1 -> 2, 2 -> 3, 3 -> 4, 13 -> 14, 6 -> 7, 7 -> 8, 8 -> 9, 14 -> 15, 15 -> 16, " +
                "10 -> 11, 11 -> 12, 12 -> 13, 4 -> 5, 5 -> 6", true).
                include("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16");
        run("X -> Y, Y -> Z, A -> B, B -> C, C -> D", true).include("A B C D X Y Z");
        run("N -> O, O -> P, P -> Q, Q -> R, R -> S, S -> T, T -> U, U -> V, V -> W, W -> X",
                true).include("N O P Q R S T U V W X");
        run("T -> P, U -> V, Y -> Z, Z -> X, A -> B, B -> C, C -> D, D -> E, E -> F", true).
                include("A B C D E F T P U V Y Z X");
        run("19 -> 20, 20 -> 1, 1 -> 3, 3 -> 5, 5 -> 7, 7 -> 9, 9 -> 11, 11 -> 13, 13 -> 15, " +
                "15 -> 17, 2 -> 4, 4 -> 6", true).include("19 2 20 1 3 4 5 6 7 9 11 13 15 17");
        run("G -> H, H -> E, I -> J, J -> K, K -> L, L -> A, M -> N, N -> O, O -> P, P -> A",
                true).include("G H E I J K L M N O P A");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, 1 -> 2, " +
                "2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 7, 7 -> 8, 8 -> 9, 9 -> 10, 10 -> 11", true).
                include("1 2 3 4 5 6 7 8 9 10 11 A B C D E F G H I J K");
        run("O -> P, P -> Q, Q -> R, R -> S, S -> T, T -> U, U -> V, V -> W, A -> B, B -> C, C -> D, " +
                        "D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, 1 -> 2, 2 -> 3",
                true).include("1 2 3 A B C D E F G H I J O P Q R S T U V W");
        run("W -> X, A -> B, B -> C, C -> D, D -> E, E -> F, F -> G", true).
                include("A B C D E F G W X");
        run("P -> Q, Q -> R, R -> S, S -> T, T -> U, U -> V, V -> W, W -> X, " +
                "A -> B", true).include("A B P Q R S T U V W X");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, " +
                "I -> J, J -> K", true).include("A B C D E F G H I J K");
        run("B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, " +
                "J -> K, K -> L", true).include("B C D E F G H I J K L");
        run("2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 7, 7 -> 8, 8 -> 9, 9 -> 10, " +
                "10 -> 11, 11 -> 12, X -> Y, Y -> Z, Z -> W, W -> V, V -> U, U -> T, " +
                "T -> S, S -> R, R -> Q, Q -> P, P -> O", true).
                include("2 3 4 5 6 7 8 9 10 11 12 X Y Z W V U T S R Q P O");
    }

    @Test
    public void testGraphB() {
        run("0 -> 1", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 12 (сейчас их 3).
        run("A -> B", true)
                .include("no").exclude("yes");
        run("A -> B, B -> C", true)
                .include("no").exclude("yes");
        run("A -> B, B -> C, C -> A", true)
                .include("yes").exclude("no");
        run("X -> Y, Y -> Z, Z -> X", true)
                .include("yes").exclude("no");
        run("P -> Q, Q -> R, R -> S, S -> P", true)
                .include("yes").exclude("no");
        run("M -> N, N -> O, O -> P, P -> Q, Q -> M", true)
                .include("yes").exclude("no");
        run("U -> V, V -> W, W -> X, X -> Y, Y -> Z, Z -> U", true)
                .include("yes").exclude("no");
        run("1 -> 2, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 1", true)
                .include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> A", true)
                .include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> A, G -> I", true)
                .include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> A, G -> I, I -> J", true)
                .include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> A, G -> I, I -> J, J -> A", true)
                .include("yes").exclude("no");
        run("X -> Y, Y -> Z, Z -> X, Z -> W", true)
                .include("yes").exclude("no");
        run("1 -> 2, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 7, 7 -> 1", true)
                .include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> A", true)
                .include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> A", true)
                .include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> A", true)
                .include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N, N -> A", true)
                .include("yes").exclude("no");


    }

    @Test
    public void testGraphC() {
        run("1->2, 2->3, 3->1, 3->4, 4->5, 5->6, 6->4", true)
                .include("123\n456");
        run("C->B, C->I, I->A, A->D, D->I, D->B, B->H, H->D, D->E, H->E, E->G, A->F, G->F, F->K, K->G", true)
                .include("C\nABDHI\nE\nFGK");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 8 (сейчас их 2).
        run("A->B, B->C, C->A", true).include("ABC");
        run("A->B, B->C, C->A, D->E, E->F, F->D", true).include("DEF\nABC");
        run("A->B, B->C, C->A, C->D, D->E, E->F, F->D", true).include("ABC\nDEF");
        run("X->Y, Y->Z, Z->X, Z->W, W->V, V->U, U->W", true).include("XYZ\nUVW");
        run("P->Q, Q->R, R->P, R->S, S->T, T->U, U->S", true).include("PQR\nSTU");
        run("M->N, N->O, O->M, O->P, P->Q, Q->R, R->P", true).include("MNO\nPQR");
        run("K->L, L->M, M->K, M->N, N->O, O->P, P->O", true).include("KLM\nN\nOP");
        run("G->H, H->I, I->G, I->J, J->K, K->L, L->J", true).include("GHI\nJKL");
        run("A->B, B->C, C->A, B->D, D->E, E->F, F->D", true).include("ABC\nDEF");
        run("X->Y, Y->Z, Z->X, X->W, W->V, V->U, U->W", true).include("XYZ\nUVW");
    }
}