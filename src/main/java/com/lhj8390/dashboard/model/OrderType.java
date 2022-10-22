package com.lhj8390.dashboard.model;

public enum OrderType {
    WAIT("주문대기"),
    PROCESSING("진행중"),
    REJECTED("주문취소"),
    COMPLETED("주문완료")
    ;

    private final String state;

    OrderType(String state) {
        this.state = state;
    }

    public String getValue() {
        return state;
    }
}
