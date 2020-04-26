package org.zdjecia.model.tag;

public enum TagEnum {
    TAG1("TAG1"),
    TAG2("TAG2"),
    TAG3("TAG3");

    String tagName;

    TagEnum(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}
