package com.misker.mike.hasher.hashers;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SHA256HasherTest {

    @Test
    public void shouldCreateSHA256Hash() throws IOException {
        InputStream inputStream = IOUtils.toInputStream("some string to hash");
        Hasher sha256Hasher = new SHA256Hasher();
        String hashedString = sha256Hasher.hash(inputStream);
        assertThat(hashedString, is("ea83a45637a9af470a994d2c9722273ef07d47aec0660a1d10afe6e9586801ac"));
    }
}
