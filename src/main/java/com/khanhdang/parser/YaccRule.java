package com.khanhdang.parser;

public class YaccRule {
    static final String prog = "program identifier;  block";
    static final String block = "Begin + expression; + End. ";
    static final String expression  = "expression; + statement";
    static final String statement = "   identifier";

}