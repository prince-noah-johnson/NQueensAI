package com.nqueens;
import java.util.ArrayList;

public class Main {

    public static int N;
    public static int[] X;
    public static ArrayList<int[]> validSolution;

    public static int[][] board;
    public static int[] RV;

    //Initializing the file
    public static void init() {

        try{

            // Getting the input file. Insert the file path accordingly.
            String filePath = "/home/princenoahjohnson/Desktop/GW/Artificial Intelligence_CSCI_6511_DE1/Projects/Project 2/src/com/nqueens/n-queens" +
                    ".txt";
            // N Value
            N = Reader.getInput(filePath).getTarget();
            // current state of queen placements
            X = new int[N];
            //representing the chessboard
            board = new int[N][N];
            RV = new int[N];

            for (int i = 0; i < N; i++) {
                X[i] = -1;
                RV[i] = N;
            }

        }
        catch (Exception ex) {
            // Handle exceptions
            System.out.println("An exception occurred: " + ex.getMessage());
        }

    }

    //Function for printing the solution
    public static void printSolution(int[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print((x[i] + 1) + "  ");
        }
        System.out.println("");
    }

    //Prints the current state of board
    public static void printBoard() {
        updateBoard();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    System.out.print("Q ");
                } else if (board[i][j] == -1) {
                    System.out.print("X ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println("");
        }
    }

    // Updates the threat matrix F based on the current queen placements
    public static void updateBoard() {
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            if (X[i] >= 0) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = -1;
                    board[j][X[i]] = -1;
                }

                board[i][X[i]] = 1;

                // Check constraint for diagonally top-left
                for (int j = i - 1, k = X[i] - 1; j >= 0 && k >= 0; j--, k--) {
                    board[j][k] = -1;
                }
                // Check constraint diagonally top-right
                for (int j = i - 1, k = X[i] + 1; j >= 0 && k < N; j--, k++) {
                    board[j][k] = -1;
                }
                // Check constraint diagonally bottom-left
                for (int j = i + 1, k = X[i] - 1; j < N && k >= 0; j++, k--) {
                    board[j][k] = -1;
                }
                // Check constraint diagonally bottom-right
                for (int j = i + 1, k = X[i] + 1; j < N && k < N; j++, k++) {
                    board[j][k] = -1;
                }
            }
        }
    }

    // Checks if the current queen placement violates any constraints
    public static boolean AC3() {
        updateBoard();
        boolean isValid = false;
        for (int i = 0; i < N; i++) {
            isValid = false;
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0 || board[i][j] == 1) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                return false;
            }
        }

        return true;
    }

    public static void newRowValue() {
        RV = new int[N];
        for (int i = 0; i < N; i++) {
            RV[i] = N;
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1 || board[i][j] == -1) {
                    RV[i]--;
                }
            }
        }
    }

    // Selects the next row to place the queen based on the MRV heuristic.
    public static int selectRowMRV() {
        updateBoard();
        newRowValue();
        int minV = N + 1;
        int minR = 0;
        for (int i = 0; i < N; i++) {
            if (RV[i] != 0 && RV[i] < minV) {
                minV = RV[i];
                minR = i;
            }
        }
        return minR;
    }

    // CSP algorithm for NQueens problem
    public static boolean CSP_NQueens() {
        boolean noMoreRows = true;
        boolean flag = false;
        boolean forward = false;

        for (int i = 0; i < N; i++) {
            if (X[i] == -1) {
                noMoreRows = false;
                break;
            }
        }

        if (noMoreRows) {
            int[] temp = new int[N];
            System.arraycopy(X, 0, temp, 0, X.length);
            printSolution(temp);
            //printBoard();
            validSolution.add(temp);
            return true;
        }

        int i = selectRowMRV();
        for (int j = 0; j < N ; j++) {
            if(board[i][j] != 0){
                continue;
            }

            X[i] = j;
            flag = AC3();
            if (!flag) {
                X[i] = -1;
                updateBoard();
                continue;
            }
           forward = CSP_NQueens();
            if(forward){
                return true;
            }

            X[i] = -1;
            updateBoard();


        }

        return false;
    }

    public static void main(String[] args) {
        init();
        System.out.println("");

        X = new int[N];
        for (int i = 0; i < N; i++) {
            X[i] = -1;
        }

        validSolution = new ArrayList<>();
        CSP_NQueens();
        if(validSolution.size() == 0){
            System.out.println("No Solution");
        }

    }
}