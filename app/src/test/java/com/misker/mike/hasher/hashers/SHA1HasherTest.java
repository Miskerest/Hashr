package com.misker.mike.hasher.hashers;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SHA1HasherTest {
    @Test
    public void shouldCreateSHA1Hash() throws IOException {
        InputStream inputStream = IOUtils.toInputStream("some string to hash");
        Hasher sha1Hasher = new SHA1Hasher();
        String hashedString = sha1Hasher.hash(inputStream);
        assertThat(hashedString, is("473cc856cae3bd89e43ff9f62963d6f38372ccbd"));
    }
}
