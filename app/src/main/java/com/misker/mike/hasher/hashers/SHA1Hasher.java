package com.misker.mike.hasher.hashers;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class SHA1Hasher {
    public String hash(String stringToHash) {
        return new String(Hex.encodeHex(DigestUtils.sha1(stringToHash)));
    }
}
