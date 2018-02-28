package com.misker.mike.hasher.hashers;

class HasherFactory {

    public static Hasher createHasher(String hasherType) {
        Hasher hasher;
        switch (hasherType) {
            case "MD5":
                hasher = new MD5Hasher();
                break;
            case "SHA1":
                hasher = new SHA1Hasher();
                break;
            case "SHA256":
                hasher = new SHA256Hasher();
                break;
            case "SHA384":
                hasher = new SHA384Hasher();
                break;
            case "Adler32":
                hasher = new Adler32Hasher();
                break;
            case "CRC32b":
                hasher = new CRC32Hasher();
                break;

            default:
                hasher = new SHA512Hasher();
        }
        return hasher;
    }
}
