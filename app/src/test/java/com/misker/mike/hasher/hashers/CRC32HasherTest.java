package com.misker.mike.hasher.hashers;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CRC32HasherTest {
    @Test
    public void shouldCreateCRC32Hash() throws IOException {
        InputStream inputStream = IOUtils.toInputStream("some string to hash");
        Hasher crc32Hasher = new CRC32Hasher();
        String hashedString = crc32Hasher.hash(inputStream);
        assertThat(hashedString, is("6beb4e6e"));
    }
}
