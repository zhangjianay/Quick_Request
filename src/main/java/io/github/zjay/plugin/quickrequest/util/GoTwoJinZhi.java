package io.github.zjay.plugin.quickrequest.util;


import java.util.Random;

public class GoTwoJinZhi {

    public static String getStatementList = "1100111 1100000 1100101 1110100 1010011 1110100 1100001 1110100 1100101 1101101 1100101 1101110 1110100 1001100 1101001 1110011 1110100 1100000";
    public static String getLeftHandExprList = "1100111 1100000 1100101 1110100 1001100 1100101 1100110 1110100 1001000 1100001 1101110 1100100 1000101 1111000 1110000 1110010 1001100 1101001 1110011 1110100 1100000";
    public static String getChildren = "1100111 1100000 1100101 1110100 1000011 1101000 1101001 1101100 1100100 1110010 1100101 1101110 1100000";
    public static String UNARY_EXPR = "1010101 1100000 1001110 1000001 1010010 1011001 1011111 1000101 1011000 1010000 1010010 1100000";
    public static String resolve = "1110010 1100000 1100101 1110011 1101111 1101100 1110110 1100101 1100000";
    public static String getGoTypeInner = "1100111 1100000 1100101 1110100 1000111 1101111 1010100 1111001 1110000 1100101 1001001 1101110 1101110 1100101 1110010 1100000";
    public static String contextlessResolve = "1100011 1100000 1101111 1101110 1110100 1100101 1111000 1110100 1101100 1100101 1110011 1110011 1010010 1100101 1110011 1101111 1101100 1110110 1100101 1100000";
    public static String getSpecType = "1100111 1100000 1100101 1110100 1010011 1110000 1100101 1100011 1010100 1111001 1110000 1100101 1100000";
    public static String getType = "1100111 1100000 1100101 1110100 1010100 1111001 1110000 1100101 1100000";
    public static String getFieldDeclarationList = "1100111 1100000 1100101 1110100 1000110 1101001 1100101 1101100 1100100 1000100 1100101 1100011 1101100 1100001 1110010 1100001 1110100 1101001 1101111 1101110 1001100 1101001 1110011 1110100 1100000";
    public static String getFieldDefinitionList = "1100111 1100000 1100101 1110100 1000110 1101001 1100101 1101100 1100100 1000100 1100101 1100110 1101001 1101110 1101001 1110100 1101001 1101111 1101110 1001100 1101001 1110011 1110100 1100000";
    public static String MAP_TYPE = "1001101 1100000 1000001 1010000 1011111 1010100 1011001 1010000 1000101 1100000";
    public static String ARRAY_OR_SLICE_TYPE = "1000001 1100000 1010010 1010010 1000001 1011001 1011111 1001111 1010010 1011111 1010011 1001100 1001001 1000011 1000101 1011111 1010100 1011001 1010000 1000101 1100000";
    public static String getIdentifier = "1100111 1100000 1100101 1110100 1001001 1100100 1100101 1101110 1110100 1101001 1100110 1101001 1100101 1110010 1100000";
    public static String getAnonymousFieldDefinition = "1100111 1100000 1100101 1110100 1000001 1101110 1101111 1101110 1111001 1101101 1101111 1110101 1110011 1000110 1101001 1100101 1101100 1100100 1000100 1100101 1100110 1101001 1101110 1101001 1110100 1101001 1101111 1101110 1100000";

    public static String getTag = "1100111 1100000 1100101 1110100 1010100 1100001 1100111 1100000";
    public static String getValue = "1100111 1100000 1100101 1110100 1010110 1100001 1101100 1110101 1100101 1100000";
    public static String json = "1101010 1100000 1110011 1101111 1101110";
    public static String getName = "1100111 1100000 1100101 1110100 1001110 1100001 1101101 1100101 1100000";
    public static String getLength = "1100111 1100000 1100101 1110100 1001100 1100101 1101110 1100111 1110100 1101000 1100000";
    public static String getKeyType = "1100111 1100000 1100101 1110100 1001011 1100101 1111001 1010100 1111001 1110000 1100101 1100000";
    public static String getValueType = "1100111 1100000 1100101 1110100 1010110 1100001 1101100 1110101 1100101 1010100 1111001 1110000 1100101 1100000";

    public static String CALL_EXPR = "1000011 1100000 1000001 1100000 1001100 1001100 1011111 1000101 1011000 1010000 1010010";
    public static String getArgumentList = "1100111 1100101 1110100 1100000 1000001 1110010 1100111 1110101 1101101 1100101 1101110 1100000 1110100 1001100 1101001 1110011 1110100";
    public static String getLparen = "1100111 1100101 1110100 1001100 1110000 1100001 1100000 1110010 1100101 1100000 1101110";
    public static String FUNCTION_LIT = "1000110 1010101 1001110 1000011 1010100 1001001 1100000 1001111 1001110 1011111 1001100 1001001 1100000 1010100";
    public static String REFERENCE_EXPRESSION = "1010010 1000101 1000110 1100000 1000101 1010010 1000101 1001110 1000011 1000101 1011111 1000101 1011000 1100000 1010000 1010010 1000101 1010011 1100000 1010011 1001001 1001111 1001110";
    public static String getBlock = "1100111 1100101 1110100 1000010 1101100 1101111 1100011 1100000 1101011 1100000";
    public static String STRING_LITERAL = "1010011 1100000 1010100 1010010 1001001 1001110 1000111 1100000 1011111 1001100 1001001 1010100 1000101 1100000 1010010 1000001 1001100";

    public static String GIN_Default = "11001111000001 11010110000001 11011111000000 1100000101110 11100000000100 11110000000101 11011000000110 11001100000001 11100000110101 11011110000000 11100000110100 1011100000000 1010011000001";

    public static String Engine = "1011000001010 11000001000101 11011110000010 11000001100111 11000001101001 11110000001110 11100000100101";

    public static String GIN_ENGINE = "1010110000010 11011000000111 11000001101001 11110000001110 1100000101110 11100000000101 11011100000110 11011000000111 11011100000001 11011000001110 11001011000001";

//    public static void main(String[] args) {
//        String str = "route";
//        Random random = new Random();
//        char[] strChar=str.toCharArray();
//        String result="";
//        for(int i=0;i<strChar.length;i++){
//            String binaryString = Integer.toBinaryString(strChar[i]);
//            int randomNumber = random.nextInt(7);
//            String prefix = binaryString.substring(0, randomNumber);
//            String suffix = binaryString.substring(randomNumber);
//            binaryString = prefix + "1100000" + suffix;
//            result+=binaryString+" ";
//        }
//        System.out.println(result);
//    }
}
