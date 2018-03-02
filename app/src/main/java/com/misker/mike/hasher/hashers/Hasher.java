package com.misker.mike.hasher.hashers;

import java.io.IOException;
import java.io.InputStream;

public interface Hasher {
    String hash(InputStream inputStream) throws IOException;
}
