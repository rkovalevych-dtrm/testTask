package utils;

import org.apache.commons.text.RandomStringGenerator;

public class RandStrings {
    private static RandomStringGenerator generator = new RandomStringGenerator.Builder()
            .withinRange('a', 'z')
            .build();

    public static String appendRandom(String base, int maxLength) {
        int charactersAppend = maxLength - base.length() - 1;
        String randomLetters = generator.generate(charactersAppend);
        return base+randomLetters;
    }

    public static String getRandom(int length) {
        String randomLetters = generator.generate(length);
        return randomLetters;
    }

    public static String getRandomInts(int length) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .build();
        String randomInt = generator.generate(length);
        return randomInt;
    }
}
