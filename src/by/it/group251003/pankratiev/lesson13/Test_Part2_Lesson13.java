package by.it.group251003.pankratiev.lesson13;

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
        run("10 -> 11, 11 -> 12, 11 -> 13, 11 -> 13, 11 -> 14, 0 -> 1, 1 -> 2, 1 -> 3, 1 -> 4",
                true).include("0 1 2 3 4 10 11 12 13 14");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4", true).include("0 1 2 3 4");
        run("A -> B, C -> D, B -> E, D -> F, E -> G, F -> H, G -> I, H -> J, I -> K, " +
                "J -> L, K -> M, L -> N", true).include("A B C D E F G H I J K M L N ");
        run("M -> O, N -> P, O -> Q, P -> R, Q -> S, R -> T, S -> U, T -> V, U -> W, " +
                "V -> X, W -> Y, X -> Z, Y -> A, Z -> C", true).include("M N O P Q R S T U V W X Y A Z C");
        run("9 -> 10, 1 -> 2, 2 -> 3, 3 -> 4, 13 -> 14, 6 -> 7, 7 -> 8, 8 -> 9, 14 -> 15, 15 -> 16, " +
                "10 -> 11, 11 -> 12, 12 -> 13, 4 -> 5, 5 -> 6", true).
                include("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16");
        run("X -> Y, Y -> Z, A -> B, B -> C, C -> D", true).include("A B C D X Y Z ");
        run("N -> O, O -> P, P -> Q, Q -> R, R -> S, S -> T, T -> U, U -> V, V -> W, W -> X",
                true).include("N O P Q R S T U V W X");
        run("T -> P, U -> V, Y -> Z, Z -> X, A -> B, B -> C, C -> D, D -> E, E -> F", true).
                include("A B C D E F T P U V Y Z X");
        run("19 -> 20, 20 -> 1, 1 -> 3, 3 -> 5, 5 -> 7, 7 -> 9, 9 -> 11, 11 -> 13, 13 -> 15, " +
                "15 -> 17, 2 -> 4, 4 -> 6", true).include("19 2 20 1 3 4 6 5 7 9 11 13 15 17");
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
        run("A -> B, B -> C, C -> A", true).include("yes").exclude("no");
        run("1 -> 2, 2 -> 3, 3 -> 4, 4 -> 1", true).include("yes").exclude("no");
        run("X -> Y, Y -> Z, Z -> X, A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, " +
                "G -> H, H -> A", true).include("yes").exclude("no");
        run("M -> N, N -> O, O -> M, P -> Q, Q -> R, R -> S, S -> T, T -> P", true).
                include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, " +
                "K -> A", true).include("yes").exclude("no");
        run("1 -> 2, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 7, 7 -> 8, 8 -> 9, 9 -> 10, 10 -> 1, " +
                "1 -> 3, 3 -> 5, 5 -> 7, 7 -> 9, 9 -> 11, 11 -> 13, 13 -> 15, 15 -> 17, 17 -> 19, " +
                "19 -> 2, 2 -> 4, 4 -> 6, 6 -> 8, 8 -> 10, 10 -> 12, 12 -> 14, 14 -> 16, 16 -> 18, " +
                "18 -> 20", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, " +
                "K -> L, L -> A, M -> N, N -> O, O -> P, P -> Q, Q -> R, R -> S, S -> T, T -> U, " +
                "U -> V, V -> W, W -> X, X -> M", true).include("yes").exclude("no");
        run("1 -> 2, 2 -> 3, 3 -> 1, 4 -> 5, 5 -> 6, 6 -> 4, 7 -> 8, 8 -> 9, 9 -> 7",
                true).include("yes").exclude("no");
        run("X -> Y, Y -> Z, Z -> X, A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, " +
                "H -> I, I -> J, J -> K, K -> L, L -> M, M -> N, N -> O, O -> P, P -> Q, Q -> R, " +
                "R -> S, S -> T, T -> U, U -> V, V -> W, W -> X", true).include("yes").exclude("no");
        run("P -> Q, Q -> R, R -> S, S -> T, T -> P, U -> V, V -> W, W -> U, X -> Y, Y -> Z, " +
                "Z -> X, A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, " +
                "J -> K", true).include("yes").exclude("no");
        run("1 -> 2, 2 -> 3, 3 -> 1, 1 -> 4, 4 -> 5, 5 -> 6, 6 -> 1, 7 -> 8, 8 -> 9, 9 -> 7, " +
                "10 -> 11, 11 -> 12, 12 -> 10", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, " +
                "K -> L, L -> A, M -> N, N -> O, O -> P, P -> Q, Q -> R, R -> S, S -> T, T -> U, " +
                "U -> V, V -> W, W -> X, X -> M", true).include("yes").exclude("no");
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
        run("A->B, B->C, C->A, B->D, D->E, E->F, F->D", true).include("ABC\nDEF");
        run("X->Y, Y->Z, Z->X, X->W, W->V, V->U, U->W", true).include("XYZ\nUVW");
        run("1->2, 2->3, 3->1, 4->5, 5->6, 6->4, 7->8, 8->9, 9->7", true).
                include("123\n456\n789");
        run("P->Q, Q->R, R->S, S->P, P->T, T->U, U->P, V->W, W->X, X->V", true).
                include("PQRSTU\nVWX");
        run("A->B, B->C, C->A, B->D, D->E, E->F, F->D, C->G, G->H, H->I, I->C", true).
                include("ABCGHI\nDEF");
        run("A->B, B->C, C->D, D->E, E->F, F->G, G->H, H->I, I->J, J->K, K->L, L->M, M->N, " +
                "N->O, O->P, P->Q, Q->D, R->Z, S->T, T->A, U->Z", true).
                include("S\nT\nA\nB\nC\nDEFGHIJKLMNOPQ\nR\nU\nZ");
        run("X->Y, Y->Z, Z->W, W->V, V->U, U->T, T->S, S->R, R->Q, Q->P, P->O, O->N, N->M, M->L, " +
                "L->K, K->J, J->I, I->H, H->G, G->F, F->E", true).
                include("X\nY\nZ\nW\nV\nU\nT\nS\nR\nQ\nP\nO\nN\nM\nL\nK\nJ\nI\nH\nG\nF\nE");
        run("1->2, 2->3, 3->4, 4->5, 5->6, 6->7, 7->8, 8->9, 9->10, 10->11, 11->12, 12->13, 13->14, " +
                "14->15, 15->1, 16->1, 17->18, 18->19, 19->20, 20->1", true).
                include("16\n17\n18\n19\n20\n110111213141523456789");
        run("P->Q, Q->R, R->S, S->T, T->U, U->V, V->W, W->X, X->Z, Y->Z, Z->A, A->B, B->C, C->D, D->E, " +
                "E->F, F->G, G->H, H->I, I->J, J->K, K->L, L->M, M->N, N->O, O->Q, P->F, Q->G, R->H, S->I, T->J, " +
                "U->K, V->L, W->M, X->N, Y->O, F->Q, G->R, H->S, I->T, J->U, K->H, L->I, M->X, " +
                "N->Y, O->Z", true).include("P\nABCDEFGHIJKLMNOQRSTUVWXYZ");
    }


}