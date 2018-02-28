package com.misker.mike.hasher.hashers;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HasherFactoryTest {

    @Test
    public void shouldCreateAdler32hasher() {
        Hasher hasher = HasherFactory.createHasher("Adler32");
        assertThat(hasher.getClass().getTypeName(), is(Adler32Hasher.class.getTypeName()));
    }

    @Test
    public void shouldCreateCRC32hasher() {
        Hasher hasher = HasherFactory.createHasher("CRC32b");
        assertThat(hasher.getClass().getTypeName(), is(CRC32Hasher.class.getTypeName()));
    }

    @Test
    public void shouldCreateMD5hasher() {
        Hasher hasher = HasherFactory.createHasher("MD5");
        assertThat(hasher.getClass().getTypeName(), is(MD5Hasher.class.getTypeName()));
    }

    @Test
    public void shouldCreateSHA1hasher() {
        Hasher hasher = HasherFactory.createHasher("SHA1");
        assertThat(hasher.getClass().getTypeName(), is(SHA1Hasher.class.getTypeName()));
    }

    @Test
    public void shouldCreateSHA256hasher() {
        Hasher hasher = HasherFactory.createHasher("SHA256");
        assertThat(hasher.getClass().getTypeName(), is(SHA256Hasher.class.getTypeName()));
    }

    @Test
    public void shouldCreateSHA384hasher() {
        Hasher hasher = HasherFactory.createHasher("SHA384");
        assertThat(hasher.getClass().getTypeName(), is(SHA384Hasher.class.getTypeName()));
    }

    @Test
    public void shouldCreateSHA512hasherByDefault() {
        String hashType = RandomStringUtils.random(15);
        Hasher hasher = HasherFactory.createHasher(hashType);
        assertThat(hasher.getClass().getTypeName(), is(SHA512Hasher.class.getTypeName()));
    }
}
