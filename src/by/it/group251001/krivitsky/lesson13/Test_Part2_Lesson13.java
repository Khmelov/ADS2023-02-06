package by.it.group251001.krivitsky.lesson13;

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
        ///////
        run("A -> C, C -> B, B -> D", true).include("A C B D");
        run("A -> C, A -> B, B -> D, C -> D", true).include("A B C D");
        run("1 -> 2, 3 -> 2, 0 -> 1, 1 -> 3", true).include("0 1 3 2");
        run("M -> N, M -> O, N -> P, O -> P", true).include("M N O P");
        run("P -> Q, P -> R, Q -> S, R -> S", true).include("P Q R S");
        run("A -> B, B -> C, C -> D, D -> E, E -> F", true).include("A B C D E F");
        run("A -> B, B -> C, C -> D, A -> D, D -> E, E -> F", true).include("A B C D E F");
        run("1 -> 2, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6", true).include("1 2 3 4 5 6");
        run("A -> B, B -> C, C -> D, A -> D, D -> E, E -> F", true).include("A B C D E F");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G", true).include("A B C D E F G");
        run("1 -> 2, 1 -> 3, 2 -> 4, 3 -> 4, 4 -> 5", true).include("1 2 3 4 5");
        run("A -> B, B -> C, A -> C, B -> D, D -> C", true).include("A B D C");
        run("X -> Y, Y -> Z, X -> Z, W -> X, W -> Z", true).include("W X Y Z");
        run("M -> N, M -> O, N -> P, O -> P, P -> Q, Q -> R", true).include("M N O P Q R");
    }

    @Test
    public void testGraphB() {
        run("0 -> 1", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 3, 4 -> 2, 2 -> 0, 3 -> 4", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 3, 4 -> 2, 0 -> 2, 3 -> 4", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 0, 3 -> 4, 4 -> 5, 5 -> 3", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 0, 4 -> 5, 5 -> 6, 6 -> 4", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 0, 3 -> 4, 4 -> 5, 5 -> 3", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 0, 3 -> 4, 4 -> 5, 5 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 4, 3 -> 4, 4 -> 5, 5 -> 0, 0 -> 6, 6 -> 7, 7 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 0, 0 -> 6, 6 -> 7, 7 -> 0", true).include("yes").exclude("no");
    }

    @Test
    public void testGraphC() {
        run("1 -> 2, 2 -> 3, 3 -> 1, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 4", true)
                .include("123\n456");
        run("C -> B, C -> I, I -> A, A -> D, D -> I, D -> B, B -> H, H -> D, D -> E, H -> E, E -> G, A -> F, G -> F, F -> K, K -> G", true)
                .include("C\nABDHI\nE\nFGK");
        run("0 -> 1, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 3", true)
                .include("012\n345");
        run("0 -> 1, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 3, 5 -> 6, 6 -> 7, 7 -> 6", true)
                .include("012\n345\n67");
        run("0 -> 1, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 3, 5 -> 6, 6 -> 7, 7 -> 8, 8 -> 6", true)
                .include("012\n345\n678");
        run("0 -> 1, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 7, 7 -> 3, 7 -> 8, 8 -> 7", true)
                .include("012\n345678");
    }


}