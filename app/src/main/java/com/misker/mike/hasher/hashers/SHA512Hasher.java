package com.misker.mike.hasher.hashers;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;

class SHA512Hasher implements Hasher {
    @Override
    public String hash(InputStream inputStream) throws IOException {
        return new String(Hex.encodeHex(DigestUtils.sha512(inputStream)));
    }
}
