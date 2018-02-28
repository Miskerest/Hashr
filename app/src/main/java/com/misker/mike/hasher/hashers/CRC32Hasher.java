package com.misker.mike.hasher.hashers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

class CRC32Hasher implements Hasher {
    @Override
    public String hash(String stringToHash) {
        byte[] buffer = new byte[128];
        long checksum = -1;
        try (InputStream inputStream = new ByteArrayInputStream(stringToHash.getBytes());
             CheckedInputStream cis = new CheckedInputStream(inputStream, new CRC32())) {
            //noinspection StatementWithEmptyBody
            while (cis.read(buffer) >= 0) ;
            checksum = cis.getChecksum().getValue();
        } catch (IOException ioe) {
            throw new RuntimeException("There was a problem reading the CheckedInputStream for the CRC32 algorithm", ioe);
        }
        return Long.toHexString(checksum);
    }
}
