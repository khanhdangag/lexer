package com.khanhdang.token;


import java.util.HashMap;

public class PASCToken extends Token {

    static {
        tokenDistionary = new HashMap<>();
        tokenDistionary.put("COMMENT", "((\\(\\\\*.*?\\\\*\\)).*)");
        tokenDistionary.put("EOF", "\\z");
        tokenDistionary.put("SEMI", ";");
        tokenDistionary.put("COLON", ":");
        tokenDistionary.put("COMMMA", ",");
        tokenDistionary.put("DOT", "\\.");
        tokenDistionary.put("LPARE", "\\(");
        tokenDistionary.put("RPARE", "\\)");
        tokenDistionary.put("LT", "<");
        tokenDistionary.put("RT", ">");
        tokenDistionary.put("EQ", "=");
        tokenDistionary.put("MINUS", "-");
        tokenDistionary.put("PLUS", "\\+");
        tokenDistionary.put("TIME", "\\*");
        tokenDistionary.put("DOTDOT", "\\.\\.");
        tokenDistionary.put("COLEQ", ":=");
        tokenDistionary.put("LE", "<=");
        tokenDistionary.put("GE", ">=");
        tokenDistionary.put("NE", "<>");
        tokenDistionary.put("IDENTIFIER", "(?i)([a-zA-Z]+)([a-zA-Z0-9])*");
        tokenDistionary.put("ICONST", "[0-9]+");
        tokenDistionary.put("FCONST","[0-9]+\\.[0-9]+");
        tokenDistionary.put("CCONST", "'[a-zA-Z0-9!@#\\$%\\^&\\*\\(\\)-\\+\\?]'");
        tokenDistionary.put("SCONST", "'[a-zA-Z0-9!@#\\$%\\^&\\*\\(\\)-\\+\\?\\ ]+'");
        tokenDistionary.put("AND", "and");
        tokenDistionary.put("ARRAY", "array");
        tokenDistionary.put("BEGIN", "begin");
        tokenDistionary.put("CONST", "constant");
        tokenDistionary.put("DIV", "div");
        tokenDistionary.put("DOWNTO", "downto");
        tokenDistionary.put("ELSE", "else");
        tokenDistionary.put("ELSIF", "elsif");
        tokenDistionary.put("END", "end");
        tokenDistionary.put("ENDIF", "endif");
        tokenDistionary.put("ENDLOOP", "endloop");
        tokenDistionary.put("ENDREC", "endrec");
        tokenDistionary.put("EXIT", "exit");
        tokenDistionary.put("FOR", "for");
        tokenDistionary.put("FORWARD", "forward");
        tokenDistionary.put("FUNCTION", "function");
        tokenDistionary.put("IF", "if");
        tokenDistionary.put("IS", "is");
        tokenDistionary.put("LOOP", "loop");
        tokenDistionary.put("NOT", "not");
        tokenDistionary.put("OF", "of");
        tokenDistionary.put("OR", "or");
        tokenDistionary.put("PROCEDURE", "procedure");
        tokenDistionary.put("PROGRAM", "program");
        tokenDistionary.put("RECORD", "record");
        tokenDistionary.put("REPEAT", "repeat");
        tokenDistionary.put("RETURN", "return");
        tokenDistionary.put("THEN", "then");
        tokenDistionary.put("TO", "to");
        tokenDistionary.put("TYPE", "type");
        tokenDistionary.put("UNTIL", "until");
        tokenDistionary.put("VAR", "var");
        tokenDistionary.put("WHILE", "while");
        tokenDistionary.put("NEWLINE", "\n");
        tokenDistionary.put("TAB", "\t");
    }


    public PASCToken(int row, int col, int beginIndex, String tokenType, String tokenString) {
        super(row,col, beginIndex, tokenType, tokenString);
    }
    public PASCToken(){
        super();
    }


    @Override
    public void setId(int i) {
        switch (this.tokenType) {
            case "ICONST":
                this.id = Integer.valueOf(this.tokenString) + this.hashCode();
                break;
            case "CCONST":
                this.id = Character.getNumericValue(this.tokenString.charAt(0) + +this.hashCode());
                break;
            case "SCONST":
                this.id = i + this.hashCode();
                break;
            case "IDENTIFIER":
                this.id = i + this.hashCode();
                break;
            case "FCONST":
                this.id = (int) (Float.valueOf(this.tokenString) + this.hashCode());
                break;
            default:
                this.id = this.hashCode();
                break;
        }


    }

    public static void main(String[] args){
        if ("'a + b'".matches(PASCToken.tokenDistionary.get("SCONST"))) System.out.println("Yu");
    }


}