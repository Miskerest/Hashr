package com.misker.mike.hasher.hashers;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CRC32HasherTest {
    @Test
    public void shouldCreateCRC32Hash() {
        Hasher crc32Hasher = new CRC32Hasher();
        String hashedString = crc32Hasher.hash("some string to hash");
        assertThat(hashedString, is("6beb4e6e"));
    }
}
