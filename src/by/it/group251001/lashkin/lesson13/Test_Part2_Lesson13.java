package by.it.group251001.lashkin.lesson13;

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
        run("A -> B, B -> C", true).include("A B C");
        run("A -> B, B -> C, C -> A", true).include("A B C");
        run("A -> B, A -> C, B -> D, C -> D, D -> E", true).include("A B C D E");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G", true).include("A B C D E F G");
        run("A -> B, A -> C, B -> D, C -> D", true).include("A B C D");
        run("A -> B, B -> C, C -> A", true).include("A B C");
        run("A -> B, B -> C, C -> D, D -> B", true).include("A B C D");
        run("A -> B, A -> C, B -> D, C -> D, D -> A", true).include("A B C D");
        run("A -> B, A -> C, B -> D, C -> D, A -> E, D -> E", true).include("A B C D E");
        run("A -> B, A -> C, B -> D, C -> D", true).include("A B C D");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> A", true).include("A B C D E F");
        run("A -> B, A -> C, B -> D, C -> D, D -> E, E -> F, F -> G, G -> A", true).include("A B C D E F G");

    }

    @Test
    public void testGraphB() {
        run("0 -> 1", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 0, 0 -> 4, 4 -> 5, 5 -> 6, 6 -> 0", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E", true).include("no").exclude("yes");
        run("A -> B, B -> C, C -> D, D -> A", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> A", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> A, A -> F, F -> G, G -> H, H -> I, I -> J, J -> A", true).include("yes").exclude("no");
    }

    @Test
    public void testGraphC() {
        run("1->2, 2->3, 3->1, 3->4, 4->5, 5->6, 6->4", true)
                .include("123\n456");
        run("C->B, C->I, I->A, A->D, D->I, D->B, B->H, H->D, D->E, H->E, E->G, A->F, G->F, F->K, K->G", true)
                .include("C\nABDHI\nE\nFGK");
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