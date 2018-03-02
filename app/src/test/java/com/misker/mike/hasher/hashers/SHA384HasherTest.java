package com.misker.mike.hasher.hashers;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SHA384HasherTest {
    @Test
    public void shouldCreateSHA384Hash() throws IOException {
        InputStream inputStream = IOUtils.toInputStream("some string to hash");
        Hasher sha384Hasher = new SHA384Hasher();
        String hashedString = sha384Hasher.hash(inputStream);
        assertThat(hashedString, is("d32d6343ac065ff185abd04a2f54d1f825d51f4c9af1ee181fd6e7b1042f577fe19f2817c39ddeda52cdc31c0cd0195d"));
    }
}
