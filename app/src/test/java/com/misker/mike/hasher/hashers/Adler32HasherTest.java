package com.misker.mike.hasher.hashers;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Adler32HasherTest {
    @Test
    public void shouldCreateCRC32Hash() {
        Hasher adler32Hasher = new Adler32Hasher();
        String hashedString = adler32Hasher.hash("some string to hash");
        assertThat(hashedString, is("49420733"));
    }
}
