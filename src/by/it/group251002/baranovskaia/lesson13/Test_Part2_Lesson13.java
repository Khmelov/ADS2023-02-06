package by.it.group251002.baranovskaia.lesson13;

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
        run("A -> B, B -> C, C -> D, C -> E, E -> F, E -> G, G -> H", true).include("A B C D E F G H");
        run("0 -> 1, 1 -> 3, 1 -> 2, 1 -> 5, 2 -> 3, 2 -> 4, 3 -> 4, 4 -> 5", true).include("0 1 2 3 4 5");
        run("0 -> 1, 0 -> 3, 1 -> 2, 1 -> 3, 2 -> 3, 2 -> 4, 2 -> 5, 3 -> 4, 4 -> 5", true).include("0 1 2 3 4 5");
        run("A -> D, B -> F, A -> B, B -> D, A -> F, F -> H", true).include("A B D F H");
        run("E -> F, B -> F, A -> B, B -> D, B -> C, C -> E, D -> E", true).include("A B C D E F");
        run("A -> B", true).include("A B");
        run("C -> D, B -> C, A -> D, A -> B", true).include("A B C D");
        run("3 -> 4, 3 -> 6, 0 -> 6, 0 -> 1, 1 -> 2, 2 -> 3, 2 -> 4, 3 -> 4, 4 -> 6, 1 -> 5, 5 -> 6", true).include("0 1 2 3 4 5 6");
        run("0 -> 1, 1 -> 6, 0 -> 2, 2 -> 3, 3 -> 4, 4 -> 6, 5 -> 6, 0 -> 5", true).include("0 1 2 3 4 5 6");
        run("5 -> 6, 4 -> 5, 3 -> 4, 2 -> 3, 1 -> 2, 0 -> 1", true).include("0 1 2 3 4 5 6");
        run("D -> E, D -> F, E -> F", true).include("D E F");
        run("D -> E, D -> F, E -> F, A -> B, B -> C, C -> D", true).include("A B C D E F");
    }

    @Test
    public void testGraphB() {
        run("0 -> 1", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 0, 4 -> 2, 4 -> 0, 2 -> 4", true).include("yes").exclude("no");
        run("4 -> 3, 3 -> 4, 3 -> 0, 0 -> 3, 0 -> 4", true).include("yes").exclude("no");
        run("0 -> 1, 1 -> 2, 2 -> 4, 4 -> 2, 2 -> 0", true).include("yes").exclude("no");
        run("5 -> 6, 4 -> 5, 3 -> 4, 2 -> 3, 1 -> 2, 0 -> 1", true).include("no").exclude("yes");
        run("A -> E, B -> C, C -> D, C -> A, C -> E, E -> C", true).include("yes").exclude("no");
        run("D -> E, D -> F, E -> F", true).include("no").exclude("yes");
        run("A -> C, B -> D, E -> F, C -> D, D -> F, F -> B, F -> A, F -> C", true).include("yes").exclude("no");
        run("0 -> 1, 0 -> 2, 0 -> 2, 1 -> 3, 1 -> 3, 2 -> 3", true).include("no").exclude("yes");
        run("3 -> 4, 3 -> 6, 0 -> 6, 0 -> 1, 1 -> 2, 2 -> 3, 2 -> 4, 3 -> 4, 4 -> 6, 1 -> 5, 5 -> 6", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 6, 0 -> 2, 2 -> 3, 3 -> 4, 4 -> 6, 5 -> 6, 0 -> 5", true).include("no").exclude("yes");
    }

    @Test
    public void testGraphC() {
        run("1 -> 2, 2 -> 3, 3 -> 1, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 4", true)
                .include("123\n456");
        run("C -> B, C -> I, I -> A, A -> D, D -> I, D -> B, B -> H, H -> D, D -> E, H -> E, E -> G, A -> F, G -> F, F -> K, K -> G", true)
                .include("C\nABDHI\nE\nFGK");
        run("0 -> 5, 5 -> 0, 0 -> 2, 2 -> 3, 3 -> 4, 4 -> 3, 3 -> 6", true)
                .include("05\n2\n34\n6");
        run("0 -> 1, 1 -> 2, 2 -> 0, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 3, 5 -> 6, 6 -> 7, 7 -> 6", true)
                .include("012\n345\n67");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> E, F -> A, D -> L", true)
                .include("ABCDEF\nL");
        run("A -> B, B -> C, C -> D, D -> E, E -> F", true)
                .include("A\nB\nC\nD\nE\nF");
        run("A -> B, B -> C, C -> A, A -> C, C -> B, B -> D, D -> C, C -> A", true)
                .include("ABCD");
        run("A -> B, A -> C, B -> C, C -> D, D -> E, E -> B, B -> G, B -> F", true)
                .include("A\nBCDE\nF\nG");
    }

}