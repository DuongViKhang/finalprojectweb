package com.laptrinhweb.Service.Implementation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class CheckLogic {
	
	public static boolean checkStringAlphabet(String input) {
		String regex = ".*[@#$%^&*()`\\-_+={}|~\\\\/<>,.;:'?!].*";
        
        return !Pattern.matches(regex, input);
    }
	
	public static boolean checkStringNumber(String input) {
        for (char c : input.toCharArray()) {
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }

}
