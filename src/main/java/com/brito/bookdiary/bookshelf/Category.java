package com.brito.bookdiary.bookshelf;

public enum Category {
    FICTION("Fiction"),
    NON_FICTION("Non-fiction"),
    SCIENCE("Science"),
    FANTASY("Fantasy"),
    MYSTERY("Mystery"),
    HISTORY("History"),
    BIOGRAPHY("Biography"),
    ROMANCE("Romance"),
    HORROR("Horror"),
    SELF_HELP("Self-help"),
    TECHNOLOGY("Technology"),
    ART("Art");

    private final String name;

    Category(String displayName) {
        this.name = displayName;
    }

    public String getName() {
        return name;
    }
}
