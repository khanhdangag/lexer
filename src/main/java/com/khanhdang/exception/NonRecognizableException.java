package com.khanhdang.exception;

public class NonRecognizableException extends LexerException {
    public NonRecognizableException(int row, int column, String token) {
        super(String.format("There a token non recognizable at row:{%d}, column:{%d}, token:%s",row,column,token));
    }
}
