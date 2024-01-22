package edu.school21.application.enums;

import lombok.Getter;

@Getter
public enum Directory {
    IMPORT("/import/"),
    EXPORT("/export/");

    private final String name;

    Directory(final String name) {
        this.name = name;
    }
}
