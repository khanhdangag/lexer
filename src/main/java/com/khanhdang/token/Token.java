package com.khanhdang.token;

import java.util.Map;

public abstract class Token {

    static Map<String, String> tokenDistionary;
    int id;
    private int beginIndex;
    private int length;
    private int col;
    private int row;

    String tokenType;
    String tokenString;

    Token(int row, int col, int beginIndex, int length, String tokenType, String tokenString) {
        this.beginIndex = beginIndex;
        this.length = length;
        this.tokenType = tokenType;
        this.tokenString = tokenString;
        this.row = row;
        this.col = col;
    }
    Token(){}

    Token(int row, int col, int beginIndex, String tokenType, String tokenString){
        this.row = row;
        this.col = col;
        this.beginIndex = beginIndex;
        this.tokenType = tokenType;
        this.tokenString = tokenString;
    }



    public static Map<String, String> getDistionary() {
        return tokenDistionary;
    }

    public void setId(int i){

    }

    public int getId(){
        return this.id;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public int getLength (){
        return length;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getTokenString() {
        return tokenString;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
