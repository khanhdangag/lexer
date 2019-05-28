package com.khanhdang.exception;

public class NonCloseCommentException extends LexerException {

    public NonCloseCommentException(int row, int column){
        super(String.format("There are a comment not closed at row:{%d},column:{%d}", row, column));
    }
}
