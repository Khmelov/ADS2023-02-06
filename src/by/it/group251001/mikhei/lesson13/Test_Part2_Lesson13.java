package by.it.group251001.mikhei.lesson13;

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
        run("A -> B, A -> C, A -> D, B -> E, B -> F, C -> G, D -> H, D -> I, E -> J, F -> J, G -> K, H -> L",true).include("A B C D E F G H I J K L");
        run("A -> B, B -> C, C -> D", true).include("A B C D");
        run("A -> B, B -> C, C -> D, D -> E, E -> F", true).include("A B C D E F");
        run("I -> J, K -> L, J -> K, K -> M", true).include("I J K L M");
        run("A -> B, B -> C, C -> D, C -> E, E -> F, E -> G, G -> H", true).include("A B C D E F G H");
        run("A -> B, B -> C, C -> D, C -> E, E -> F, E -> G, G -> H", true).include("A B C D E F G H");
        run("0 -> 1, 1 -> 3, 1 -> 2, 1 -> 5, 2 -> 3, 2 -> 4, 3 -> 4, 4 -> 5", true).include("0 1 2 3 4 5");
        run("5 -> 4, 5 -> 2, 4 -> 3, 4 -> 2, 3 -> 2, 3 -> 1, 3 -> 0, 2 -> 1, 1 -> 0", true).include("5 4 3 2 1 0");
        run("A -> B, B -> D, D -> F, A -> C, C -> E, E -> F", true).include("A B C D E F");
        run("B -> A, B -> C, A -> D, C -> D", true).include("B A C D");
        run("B -> A, C -> A, B -> C, A -> D, C -> D", true).include("B C A D");
        run("5 -> 1, 1 -> 3, 5 -> 4, 4 -> 9, 9 -> 3", true).include("5 1 4 9 3");
        run("9 -> 5, 9 -> 3, 5 -> 1, 5 -> 2, 3 -> 4, 1 -> 4, 2 -> 4", true).include("9 3 5 1 2 4");
        run("A -> D, A -> X, A -> K, D -> X, X -> K, D -> Z, X -> Y, K -> Y, Y -> Z", true).include("A D X K Y Z");
        run("Z -> L, Z -> N, Z -> E, L -> A, N -> L, N -> A, E -> A", true).include("Z E N L A");
        run("1 -> 2, 2 -> 3, 3 -> 20, 2 -> 4, 4 -> 20, 1 -> 11, 11 -> 5, 5 -> 4", true).include("1 11 2 3 5 4 20");
        run("1 -> 2, 2 -> 3, 3 -> 20, 2 -> 4, 4 -> 20, 1 -> 9, 9 -> 5, 5 -> 4", true).include("1 2 3 9 5 4 20");
        run("9 -> 1, 1 -> 6, 6 -> 7, 9 -> 8, 8 -> 2, 2 -> 7, 9 -> 3, 3 -> 5, 5 -> 7", true).include("9 1 3 5 6 8 2 7");
    }

    @Test
    public void testGraphB() {
        run("0 -> 1", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");

        run("1 -> 1, 1 -> 0", true).include("yes").exclude("no");
        run("1 -> 2, 3 -> 4", true).include("no").exclude("yes");
        run("5 -> 1, 1 -> 3, 5 -> 4, 4 -> 9, 9 -> 3", true).include("no").exclude("yes");
        run("1 -> 2, 2 -> 3, 3 -> 4, 1 -> 5, 5 -> 6, 6 -> 7", true).include("no").exclude("yes");
        run("1 -> 2, 2 -> 3, 3 -> 4, 1 -> 5, 5 -> 6, 6 -> 7, 7 -> 6", true).include("yes").exclude("no");
        run("Z -> L, Z -> N, Z -> E, L -> A, N -> L, N -> A, E -> A", true).include("no").exclude("yes");
        run("Z -> L, Z -> N, Z -> E, L -> A, N -> L, N -> A, E -> A, A -> Z", true).include("yes").exclude("no");
        run("9 -> 1, 1 -> 6, 6 -> 7, 9 -> 8, 8 -> 2, 2 -> 7, 9 -> 3, 3 -> 5, 5 -> 7", true).include("no").exclude("yes");
        run("9 -> 1, 1 -> 6, 6 -> 7, 9 -> 8, 8 -> 2, 2 -> 7, 9 -> 3, 3 -> 5, 5 -> 7, 2 -> 1, 6 -> 8", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 3, 2 -> 3, 0 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 3, 2 -> 3, 0 -> 2, 3 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 3, 1 -> 2, 1 -> 5, 2 -> 3, 2 -> 4, 3 -> 4, 4 -> 5", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 3, 1 -> 2, 1 -> 5, 2 -> 3, 2 -> 4, 3 -> 4, 4 -> 5, 4 -> 1", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 3, 1 -> 2, 1 -> 5, 2 -> 3, 2 -> 4, 3 -> 4, 4 -> 5, 5 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 0 -> 3, 1 -> 2, 1 -> 3, 2 -> 3, 2 -> 4, 2 -> 5, 3 -> 4, 4 -> 5", true).include("no").exclude("yes");
    }

    @Test
    public void testGraphC() {
        run("1->2, 2->3, 3->1, 3->4, 4->5, 5->6, 6->4", true)
                .include("123\n456");
        run("C->B, C->I, I->A, A->D, D->I, D->B, B->H, H->D, D->E, H->E, E->G, A->F, G->F, F->K, K->G", true)
                .include("C\nABDHI\nE\nFGK");

        run("0->1, 1->2, 2->0, 2->3, 3->4, 4->5, 5->3", true)
                .include("012\n345");
        run("0->1, 1->2, 2->0, 2->3, 3->4, 4->5, 5->3, 5->6, 6->7, 7->6", true)
                .include("012\n345\n67");
        run("0->1, 1->2, 2->0, 2->3, 3->4, 4->5, 5->3, 5->6, 6->7, 7->8, 8->6", true)
                .include("012\n345\n678");
        run("0->1, 1->2, 2->0, 2->3, 3->4, 4->5, 5->6, 6->7, 7->3, 7->8, 8->7", true)
                .include("012\n345678");
        run("A->C, C->B, B->A, D->E", true)
                .include("D\nE\nABC");
        run("A->C, C->B, B->A, D->E, E->F, F->D", true)
                .include("DEF\nABC");
        run("A->C, C->B, B->A, D->E, E->F, F->D, A->F", true)
                .include("ABC\nDEF");
        run("A->D, A->X, A->K, D->X, X->K, D->Z, Y->X, K->Y, Y->Z", true)
                .include("A\nD\nKXY\nZ");
        run("L->Z, Z->N, Z->E, L->A, N->L, N->A, E->A", true)
                .include("LNZ\nE\nA");
        run("A->B, B->C, C->D, D->A, D->E, E->F, F->E", true)
                .include("ABCD\nEF");

    }


}