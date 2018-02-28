package com.misker.mike.hasher.hashers;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SHA1HasherTest {
    @Test
    public void shouldCreateSHA1Hash() {
        SHA1Hasher sha1Hasher = new SHA1Hasher();
        String hashedString = sha1Hasher.hash("some string to hash");
        assertThat(hashedString, is("473cc856cae3bd89e43ff9f62963d6f38372ccbd"));
    }
}
