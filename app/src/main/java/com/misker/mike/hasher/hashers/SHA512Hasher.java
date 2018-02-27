package com.misker.mike.hasher.hashers;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

class SHA512Hasher implements Hasher {
    @Override
    public String hash(String stringToHash) {
        return new String(Hex.encodeHex(DigestUtils.sha512(stringToHash)));
    }
}
