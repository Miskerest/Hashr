package com.misker.mike.hasher.hashers;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;

public class SHA1Hasher implements Hasher {
    public String hash(InputStream inputStream) throws IOException {
        return new String(Hex.encodeHex(DigestUtils.sha1(inputStream)));
    }
}
