package com.autobid.util;

public class StringUtil {

    public static String decodeUnicode(String theString) {
        char aChar = 0;
        int len = theString.length();
        StringBuilder outBuffer = new StringBuilder(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if(aChar == 'u'){
                    outBuffer = switchCode(theString,x,outBuffer);
                }else{
                    switch(aChar){
                        case 't': aChar = '\t'; break;
                        case 'r': aChar = '\r'; break;
                        case 'n': aChar = '\n'; break;
                        case 'f': aChar = '\f'; break;
                        default:  outBuffer.append(aChar);
                    }
                    outBuffer.append(aChar);
                }
            }else{
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

    private static StringBuilder switchCode(String theString, int x, StringBuilder outBuffer) {
        // Read the xxxx
        int value = 0;
        for (int i = 0; i < 4; i++) {
            char aChar = theString.charAt(x++);
            switch (aChar) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    value = (value << 4) + aChar - '0';
                    break;
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                    value = (value << 4) + 10 + aChar - 'a';
                    break;
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                    value = (value << 4) + 10 + aChar - 'A';
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Malformed   \\uxxxx   encoding.");
            }

        }
        outBuffer.append((char) value);
        return outBuffer;
    }
}
