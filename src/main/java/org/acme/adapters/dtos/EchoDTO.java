package org.acme.adapters.dtos;

public class EchoDTO {

    private String uuid;
    private String word;
    private String tag;

    public EchoDTO(String uuid, String word, String tag) {
        this.uuid = uuid;
        this.word = word;
        this.tag  = tag;
    }

    public EchoDTO() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}