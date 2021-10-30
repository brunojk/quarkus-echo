package org.acme.core.domains;

public class Echo {

    private String uuid;
    private String word;
    private String tag;

    public Echo(String uuid, String word, String tag) {
        this.uuid = uuid;
        this.word = word;
        this.tag = tag;
    }

    public String getWord() {
        return word;
    }

    public Echo setWord(String word) {
        this.word = word;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public Echo setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public Echo setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
}
