package com.khanhdang;

import com.khanhdang.lexer.Lexer;
import com.khanhdang.parser.Parser;
import com.khanhdang.token.PASCToken;
import com.khanhdang.token.Token;

public class Main {
    public static void main(String[] args){
        Token token = new PASCToken();
        Lexer lexer = new Lexer();
        lexer.lexer("Test07.txt");
        lexer.writeFile("outTest70.txt");
        Parser parser = new Parser();
        parser.parse(lexer.getTokenStack());
    }
}
