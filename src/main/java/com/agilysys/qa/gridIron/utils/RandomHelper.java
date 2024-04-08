package com.agilysys.qa.gridIron.utils;


import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

/**
 * Created by egor9 on 11/5/2015.
 */
public class RandomHelper {
    private static final char[] alphaSymbols;
    private static final char[] numericSymbols;
    private static final char[] alphaNumericSymbols;

    private static final String domainDefault = "agilysys.com";
    private static final String phoneNumberDeliminator = "-";

    static
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c = 'a'; c <= 'z'; ++c)
            stringBuilder.append(c);
        alphaSymbols = stringBuilder.toString().toCharArray();

        stringBuilder = new StringBuilder();

        for (char ch = '0'; ch <= '9'; ++ch)
            stringBuilder.append(ch);
        numericSymbols = stringBuilder.toString().toCharArray();

        for (char c = 'a'; c <= 'z'; ++c)
            stringBuilder.append(c);
        alphaNumericSymbols = stringBuilder.toString().toCharArray();

    }

    public static String getRandomAlphaString(int length)
    {
        char[] temp = new char[length];
        Random random = new Random();

        for (int i = 0; i < length; i++)
        {
            temp[i] = alphaSymbols[random.nextInt(alphaSymbols.length)];
        }

        return new String(temp);
    }

    public static String getRandomNumericString(int length)
    {
        char[] temp = new char[length];
        Random random = new Random();

        for (int i = 0; i < length; i++)
        {
            temp[i] = numericSymbols[random.nextInt(numericSymbols.length)];
        }

        return new String(temp);
    }

    public static String getRandomAlphaNumericString(int length)
    {
        char[] temp = new char[length];
        Random random = new Random();

        for (int i = 0; i < length; i++)
        {
            temp[i] = alphaNumericSymbols[random.nextInt(alphaNumericSymbols.length)];
        }

        return new String(temp);
    }
    
    /**
     * Get random Double in #.## format
     *
     * @param min
     *            minimum boundary value
     * @param max
     *            maximum boundary value
     * @return {@link Double} 
     */
    public static Double getRandomDouble(int min, int max)
    {
        DecimalFormat format = new DecimalFormat("#.##");
        return Double.valueOf(format.format(Math.random() * (max - min) + 1) + min);
    }

    public static int getRandomInt(int min, int max)
    {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static String getRandomUUID()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    
    /**
     * Get random email address
     * 
     * @return random address @ agilysys.com
     */
    public static String getRandomEmailAddress()
    {
        return getEmailAddress(RandomHelper.getRandomAlphaNumericString(10), domainDefault);
    }
    
    /**
     * Get email address
     * 
     * @param address
     *            email address (default is random alpha-numeric string)
     * @param domain
     *            email domain (default value is "agilysys.com")
     * @return email address in valid format
     */
    public static String getEmailAddress(String address, String domain)
    {
        return address + "@" + domain;
    }

    /**
     * Get a random phone number that should pass a validation on area code
     *
     * @return random phone number in format nnn-nnn-nnnn
     */
    public static String getRandomPhoneNumber()
    {
        return Integer.toString(RandomHelper.getRandomInt(200, 999)) + phoneNumberDeliminator + Integer.toString(RandomHelper.getRandomInt(110, 999)) + phoneNumberDeliminator + RandomHelper.getRandomNumericString(4);
    }
}
