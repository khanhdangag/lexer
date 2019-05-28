package com.khanhdang.lexer;


import com.khanhdang.exception.NonCloseCommentException;
import com.khanhdang.exception.NonRecognizableException;
import com.khanhdang.token.PASCToken;
import com.khanhdang.token.Token;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lexer {
    private static transient final Logger LOGGER = Logger.getLogger(PASCToken.class.getName());
    private int row = 1;
    private int col = 1;
    private Deque<Token> tokenStack;

    public Lexer() {
        this.tokenStack = new ArrayDeque<>();
    }

    public Deque<Token> getTokenStack() {
        return tokenStack;
    }

    public void lexer(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            StringBuilder sourceBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                sourceBuilder.append(scanner.nextLine()).append("\n");
            }

            tokenize(sourceBuilder.toString());
        } catch (NonRecognizableException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    private void tokenize(String source) {
        int position = 0;
        int index = 0;
        String[] arrString = source.split("");
        Arrays.stream(arrString).forEach(System.out::println);
        Optional<Token> token = Optional.empty();
        while (position != source.length()) {
            try {
                token = seperateToken(arrString, position);
            } catch (NonRecognizableException e) {
                throw e;
            }

            if (token.isPresent() && token.get().getTokenString() != null) {
                if (!token.get().getTokenType().equals("COMMENT") && !token.get().getTokenType().equals("NEWLINE") && !token.get().getTokenType().equals("TAB")) {
                    token.get().setId(index);

                    tokenStack.addLast(token.get());
                    index++;
                }
                position += token.get().getTokenString().length();
            } else {
                position++;
            }
        }

        tokenStack.stream().forEach(e -> LOGGER.log(Level.INFO, e.getTokenString()));
    }

    private Optional<Token> seperateToken(String[] arrString, int position) throws NonRecognizableException, NonCloseCommentException {

        StringBuilder tokenBuilder = new StringBuilder();
        if ("(".equals(arrString[position]) && "*".equals(arrString[position + 1])) {
            int i = position + 2;

            while (i < arrString.length - 2 && !(("*".equals(arrString[i]) && ")".equals(arrString[i + 1])))) {
                tokenBuilder.append(arrString[i]);
                i++;
                if (i >= arrString.length - 1) {
                    throw new NonCloseCommentException(row, position);
                }
            }

            tokenBuilder.insert(0, "(*");
            tokenBuilder.append("*)");
        }else if("/".equals(arrString[position]) && "*".equals(arrString[position + 1])){
            int i = position + 2;

            while (i < arrString.length - 2 && !(("*".equals(arrString[i]) && "/".equals(arrString[i + 1])))) {
                tokenBuilder.append(arrString[i]);
                i++;
                if (i >= arrString.length - 1) {
                    throw new NonCloseCommentException(row, position);
                }
            }

            tokenBuilder.insert(0, "/*");
            tokenBuilder.append("*/");
        }
        else if ("'".equals(arrString[position])) {
            int i = position + 1;
            while (i < arrString.length && !"'".equals(arrString[i])) {
                tokenBuilder.append(arrString[i]);
                i++;
            }
            tokenBuilder.insert(0, "'");
            tokenBuilder.append("'");
        } else if (arrString[position].matches(";") || arrString[position].matches(",") || arrString[position].matches("\n") || arrString[position].matches("\t") ){
            tokenBuilder.append(arrString[position]);
        }
        else {
            int flag = 0;
            int i = position;
            while (i < arrString.length) {
                if (!arrString[i].matches(" +") && !arrString[i].matches(",") && !arrString[i].matches(";") && !arrString[i].matches("\n") && !arrString[i].matches("\\.")) {
                    tokenBuilder.append(arrString[i]);
                    i++;
                }
                else {
                    if (flag == 1) {
                        break;
                    }
                    flag = 1;
                }


            }
        }
        String tokenString = tokenBuilder + "";
        col += tokenString.length();
        String tokenType = PASCToken.getDistionary().entrySet().stream()
                .filter(entry -> tokenString.matches(entry.getValue()))
                .map(Map.Entry::getKey).findFirst().orElse("");
        if ("NEWLINE".equals(tokenType)) {
            row++;
            col = 0;
            return Optional.of(new PASCToken(row, col , position, tokenType, tokenString));
        }
        if (tokenType.isEmpty()) {
            throw new NonRecognizableException(row, col, tokenString);
        }
        if (!tokenString.isEmpty()) {
            PASCToken token = new PASCToken(row, position+1/row, position /row, tokenType, tokenString);
            return Optional.of(token);
        }

        return Optional.empty();
    }

    public void writeFile(String output){
        Path path = Paths.get(output);
        StringBuilder sb = new StringBuilder();
        tokenStack.stream().forEach((e)->{
            int endIndex =e.getBeginIndex() + e.getTokenString().length();
            sb.append(e.getTokenString() + " " + e.getTokenType() + " " + e.getRow() + " " +  e.getCol() +" " +e.getBeginIndex() + " " + endIndex+"\n");
        });

        byte[] str = sb.toString().getBytes();
        try {
            Files.write(path,str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
