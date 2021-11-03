package org.acme.core.domains;

public class Echo {

    private final String uuid;
    private final String word;
    private final String tag;

    public Echo(String uuid, String word, String tag) {
        this.uuid = uuid;
        this.word = word;
        this.tag = tag;
    }

    public String getWord() {
        return word;
    }

    public String getTag() {
        return tag;
    }

    public String getUUID() {
        return uuid;
    }
}
