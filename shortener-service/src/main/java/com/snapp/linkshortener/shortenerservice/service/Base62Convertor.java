package com.snapp.linkshortener.shortenerservice.service;

import org.springframework.stereotype.Service;

@Service
public class Base62Convertor {

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final char[] allowedCharacters = allowedString.toCharArray();
    private final int base = allowedCharacters.length;

    public String encode(long input){
        StringBuilder encodedString = new StringBuilder();
        if(input == 0)
            return String.valueOf(allowedCharacters[0]);
        while (input > 0) {
            encodedString.append(allowedCharacters[(int) (input % base)]);
            input = input / base;
        }
        return encodedString.reverse().toString();
    }

    public long decode(String input) {
        char[] characters = input.toCharArray();
        final int length = characters.length;
        int decoded = 0;
        //counter is used to avoid reversing input string
        int counter = 1;
        for (char character : characters) {
            decoded += allowedString.indexOf(character) * Math.pow(base, length - counter);
            counter++;
        }
        return decoded;
    }
}
