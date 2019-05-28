package com.khanhdang.exception;

import com.khanhdang.parser.Expression;

public class ParserException  extends Exception {
    public ParserException(String messages){
        super(messages);
    }
}
