package com.nqueens.Testing;


import com.nqueens.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestClass {
    @Test
    public void testCSP_NQueensWithSolution() {
        int x = 4;
        Main.init(); // Change file accordingly
        Assertions.assertTrue(Main.CSP_NQueens());
    }


    @Test
    public void testCSP_NQueensWithNoSolution() {
        int x = 3;
        Main.init(); // Change file accordingly
        Assertions.assertTrue(Main.CSP_NQueens());
    }
}