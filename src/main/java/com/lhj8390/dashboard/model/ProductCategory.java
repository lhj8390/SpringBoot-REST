package com.lhj8390.dashboard.model;


public enum ProductCategory {
    ELECTRONIC("전자기기"),
    BOOK("책"),
    FASHION("패션")
    ;

    private final String category;

    ProductCategory(String category) {
        this.category = category;
    }

    public String getValue() {
        return category;
    }
}

