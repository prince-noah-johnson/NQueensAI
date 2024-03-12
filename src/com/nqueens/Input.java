package com.nqueens;

public class Input {
      private int target;

    public Input(int target) {
        this.target = target;
    }

    public int getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "Input{" +
                        " target=" + target +
                '}';
    }
}