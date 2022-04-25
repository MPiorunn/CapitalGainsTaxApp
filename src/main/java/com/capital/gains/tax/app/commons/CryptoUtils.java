package com.capital.gains.tax.app.commons;

import org.apache.commons.codec.digest.DigestUtils;

public class CryptoUtils {

    public static String hash(String input) {
        return DigestUtils.sha256Hex(input);
    }
}
