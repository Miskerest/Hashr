package com.misker.mike.hasher.hashers;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SHA256HasherTest {

    @Test
    public void shouldCreateSHA256Hash() {
        Hasher sha256Hasher = new SHA256Hasher();
        String hashedString = sha256Hasher.hash("some string to hash");
        assertThat(hashedString, is("ea83a45637a9af470a994d2c9722273ef07d47aec0660a1d10afe6e9586801ac"));
    }
}
