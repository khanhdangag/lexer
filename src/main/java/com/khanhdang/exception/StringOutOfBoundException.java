package com.khanhdang.exception;

public class StringOutOfBoundException extends LexerException {
    public StringOutOfBoundException(int row, int column) {
        super(String.format("Thera a String constant out of boundary of line at row:{%d}, column:{%d}",row,column));
    }
}
