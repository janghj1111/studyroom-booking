package com.example.studyroom.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotGenerator {
    private static final int MIN_HOUR = 0;
    private static final int MAX_HOUR = 24;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /*
     * 리팩터링 전 상태
     * - 메서드 이름이 generateSlot 하나로 되어 있었음
     * - 입력 검증과 슬롯 생성이 한 메서드에 섞여 있었음
     * - main 에서 실패 케이스(10, 9, 30)를 바로 호출하고 있었음
     *
     * 이전 코드 형태를 학습용으로 남겨 둔다.
     */
    @Deprecated
    public List<String> generateSlotLegacy(int StartTime, int EndTime, int intervalMinutes) {
        if (StartTime >= EndTime) {
            throw new IllegalArgumentException("시작 시간은 종료 시간보다 빨라야 합니다.");
        }
        if (intervalMinutes <= 0) {
            throw new IllegalArgumentException("슬롯 간격은 0보다 커야 합니다.");
        }
        if (60 % intervalMinutes != 0) {
            throw new IllegalArgumentException("슬롯 간격은 60을 나눌 수 있는 값이어야 합니다.");
        }

        List<String> list = new ArrayList<>();
        for (int hour = StartTime; hour < EndTime; hour++) {
            for (int min = 0; min < 60; min += intervalMinutes) {
                String slot = String.format("%02d:%02d", hour, min);
                list.add(slot);
            }
        }

        return list;
    }

    public List<String> generateSlots(int startHour, int endHour, int intervalMinutes) {
        validateInputs(startHour, endHour, intervalMinutes);

        List<String> slots = new ArrayList<>();
        LocalTime current = LocalTime.of(startHour, 0);
        LocalTime end = LocalTime.of(endHour, 0);

        while (current.isBefore(end)) {
            slots.add(current.format(TIME_FORMATTER));
            current = current.plusMinutes(intervalMinutes);
        }

        return slots;
    }

    private void validateInputs(int startHour, int endHour, int intervalMinutes) {
        if (startHour < MIN_HOUR || endHour > MAX_HOUR) {
            throw new IllegalArgumentException("운영 시간은 0시부터 24시 사이여야 합니다.");
        }

        if (startHour >= endHour) {
            throw new IllegalArgumentException("시작 시간은 종료 시간보다 빨라야 합니다.");
        }

        if (intervalMinutes <= 0) {
            throw new IllegalArgumentException("슬롯 간격은 0보다 커야 합니다.");
        }

        if (60 % intervalMinutes != 0) {
            throw new IllegalArgumentException("슬롯 간격은 60을 나눌 수 있는 값이어야 합니다.");
        }
    }

    /*public static void main(String[] args) {
        TimeSlotGenerator generator = new TimeSlotGenerator();
        List<String> slots = generator.generateSlots(9, 18, 30);
        System.out.println(slots);
    }*/
}
