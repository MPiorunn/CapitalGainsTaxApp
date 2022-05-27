package pl.piorun.capital.billing.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class CryptoUtils {

    public static String hash(String input) {
        return DigestUtils.sha256Hex(input);
    }
}
