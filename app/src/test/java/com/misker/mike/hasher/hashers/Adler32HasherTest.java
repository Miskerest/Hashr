package com.misker.mike.hasher.hashers;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Adler32HasherTest {
    @Test
    public void shouldCreateCRC32Hash() throws IOException {
        InputStream inputStream = IOUtils.toInputStream("some string to hash");
        Hasher adler32Hasher = new Adler32Hasher();
        String hashedString = adler32Hasher.hash(inputStream);
        assertThat(hashedString, is("49420733"));
    }
}
