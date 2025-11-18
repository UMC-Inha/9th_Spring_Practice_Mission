package umc.global.util;

import java.security.SecureRandom;

public class RandomCodeGenerator {

    private static final int max = 999999999;
    private static final int min = 100000000;

    private static final SecureRandom secureRandom = new SecureRandom();

    public static int generateRandomInt() {
        return min + secureRandom.nextInt(max + 1);
    }
}
