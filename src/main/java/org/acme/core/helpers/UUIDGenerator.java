package org.acme.core.helpers;

import java.util.UUID;

public class UUIDGenerator {

    private static final UUIDGenerator INSTANCE;

    static {
        INSTANCE = new UUIDGenerator();
    }

    private UUIDGenerator() {}

    public static UUIDGenerator getInstance() {
        return INSTANCE;
    }

    public UUID generate() {
        return UUID.randomUUID();
    }
    public String string() {
        return generate().toString();
    }
}
