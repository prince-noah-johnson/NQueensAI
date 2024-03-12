package com.nqueens;

import java.io.*;
import java.util.Scanner;

public class Reader {
    private Reader() {
    }

    public static Input getInput(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner reader = new Scanner(file);

        // Number of dimension from the input
        int rows = 0;
        while(reader.hasNextLine()) {
            reader.nextLine();
            rows++;
        }

        return new Input(rows);
    }
}