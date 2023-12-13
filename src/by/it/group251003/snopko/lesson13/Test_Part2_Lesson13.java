package by.it.group251003.snopko.lesson13;

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
        run("A -> B, B -> C, C -> D", true).include("A B C D");
        run("J -> K, K -> L, L -> M, M -> N, N -> O", true).include("J K L M N O");
        run("A -> B, B -> C, C -> D, D -> E, E -> F", true).include("A B C D E F");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G", true).include("A B C D E F G");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H", true).include("A B C D E F G H");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I", true).include("A B C D E F G H I");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J", true).include("A B C D E F G H I J");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K", true).include("A B C D E F G H I J K");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L", true).include("A B C D E F G H I J K L");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M", true).include("A B C D E F G H I J K L M");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N", true).include("A B C D E F G H I J K L M N");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N, N -> O", true).include("A B C D E F G H I J K L M N O");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N, N -> O, O -> P", true).include("A B C D E F G H I J K L M N O P");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N, N -> O, O -> P, P -> Q", true).include("A B C D E F G H I J K L M N O P Q");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N, N -> O, O -> P, P -> Q, Q -> R", true).include("A B C D E F G H I J K L M N O P Q R");
    }

    @Test
    public void testGraphB() {
        run("0 -> 1", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2", true).include("no").exclude("yes");
        run("0 -> 1, 1 -> 2, 2 -> 0", true).include("yes").exclude("no");
        //Дополните эти тесты СВОИМИ более сложными примерами и проверьте их работоспособность.
        //Параметр метода run - это ввод. Параметр метода include - это вывод.
        //Общее число примеров должно быть не менее 12 (сейчас их 3).
        run("X -> Y, Y -> Z, Z -> X, A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> A", true).include("yes").exclude("no");
        run("M -> N, N -> O, O -> M, P -> Q, Q -> R, R -> S, S -> T, T -> P", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> A", true).include("yes").exclude("no");
        run("1 -> 2, 2 -> 3, 3 -> 4, 4 -> 5, 5 -> 6, 6 -> 7, 7 -> 8, 8 -> 9, 9 -> A, A -> 1, 1 -> 3, 3 -> 5, 5 -> 7, 7 -> 9, 9 -> B, B -> D, D -> F, F -> H, H -> T, T -> 2, 2 -> 4, 4 -> 6, 6 -> 8, 8 -> A, A -> C, C -> E, E -> G, G -> U, U -> Z", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> A, M -> N, N -> O, O -> P, P -> Q, Q -> R, R -> S, S -> T, T -> U, U -> V, V -> W, W -> X, X -> M", true).include("yes").exclude("no");
        run("1 -> 2, 2 -> 3, 3 -> 1, 4 -> 5, 5 -> 6, 6 -> 4, 7 -> 8, 8 -> 9, 9 -> 7", true).include("yes").exclude("no");
        run("X -> Y, Y -> Z, Z -> X, A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N, N -> O, O -> P, P -> Q, Q -> R, R -> S, S -> T, T -> U, U -> V, V -> W, W -> X", true).include("yes").exclude("no");
        run("P -> Q, Q -> R, R -> S, S -> T, T -> P, U -> V, V -> W, W -> U, X -> Y, Y -> Z, Z -> X, A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K", true).include("yes").exclude("no");
        run("1 -> 2, 2 -> 3, 3 -> 1, 1 -> 4, 4 -> 5, 5 -> 6, 6 -> 1, 7 -> 8, 8 -> 9, 9 -> 7, A -> B, B -> C, C -> A", true).include("yes").exclude("no");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> A, M -> N, N -> O, O -> P, P -> Q, Q -> R, R -> S, S -> T, T -> U, U -> V, V -> W, W -> X, X -> M", true).include("yes").exclude("no");
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
        run("1 -> 2, 2 -> 3, 3 -> 1, 4 -> 5, 5 -> 6, 6 -> 4, 7 -> 8, 8 -> 9, 9 -> 7", true).include("123\n456\n789");
        run("P -> Q, Q -> R, R -> S, S -> P, P -> T, T -> U, U -> P, V -> W, W -> X, X -> V", true).include("PQRSTU\nVWX");
        run("A -> B, B -> C, C -> A, B -> D, D -> E, E -> F, F -> D, C -> G, G -> H, H -> I, I -> C", true).include("ABCGHI\nDEF");
        run("A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N, N -> O, O -> P, P -> Q, Q -> D, R -> Z, S -> T, T -> A, U -> Z", true).include("S\nT\nA\nB\nC\nDEFGHIJKLMNOPQ\nR\nU\nZ");
        run("X -> Y, Y -> Z, Z -> W, W -> V, V -> U, U -> T, T -> S, S -> R, R -> Q, Q -> P, P -> O, O -> N, N -> M, M -> L, L -> K, K -> J, J -> I, I -> H, H -> G, G -> F, F -> E", true).include("X\nY\nZ\nW\nV\nU\nT\nS\nR\nQ\nP\nO\nN\nM\nL\nK\nJ\nI\nH\nG\nF\nE");
        run("P -> Q, Q -> R, R -> S, S -> T, T -> U, U -> V, V -> W, W -> X, X -> Z, Y -> Z, Z -> A, A -> B, B -> C, C -> D, D -> E, E -> F, F -> G, G -> H, H -> I, I -> J, J -> K, K -> L, L -> M, M -> N, N -> O, O -> Q, P -> F, Q -> G, R -> H, S -> I, T -> J, U -> K, V -> L, W -> M, X -> N, Y -> O, F -> Q, G -> R, H -> S, I -> T, J -> U, K -> H, L -> I, M -> X, N -> Y, O -> Z", true).include("P\nABCDEFGHIJKLMNOQRSTUVWXYZ");
    }


}