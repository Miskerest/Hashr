package com.misker.mike.hasher.hashers;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MD5HasherTest {
    @Test
    public void shouldCreateAnMD5Hash() throws IOException {
        InputStream inputStream = IOUtils.toInputStream("some string to hash");
        Hasher md5Hasher = new MD5Hasher();
        String hashedString = md5Hasher.hash(inputStream);
        assertThat(hashedString, is("b5ad53c085f0d402334689101351d842"));
    }
}
