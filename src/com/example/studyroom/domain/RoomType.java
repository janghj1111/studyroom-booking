package com.example.studyroom.domain;

public enum RoomType {
    SMALL(10_000),
    LARGE(18_000);

    private final int hourlyRate;

    RoomType(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }
}
