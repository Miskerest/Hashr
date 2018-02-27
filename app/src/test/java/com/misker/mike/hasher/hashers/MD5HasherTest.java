package com.misker.mike.hasher.hashers;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MD5HasherTest {
    @Test
    public void shouldCreateAnMD5Hash() {
        MD5Hasher md5Hasher = new MD5Hasher();
        String hashedString = md5Hasher.hash("some string to hash");
        assertThat(hashedString, is("b5ad53c085f0d402334689101351d842"));
    }
}
