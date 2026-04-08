package com.example.studyroom;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ReservationValidator {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /*
     * 리팩터링 전 상태
     * - resValidate 메서드가 있었지만 실제 판정 로직은 비어 있었음
     * - 문자열 반환을 하려는 방향은 보였지만 결과 타입이 정리되지 않았음
     * - 종료 시간만 받고 시작 시간은 받지 않아 운영 시간 검증이 불완전했음
     *
     * 아래 메서드는 기존 시그니처를 살려 둔 호환용 메서드다.
     */
    @Deprecated
    public String resValidate(List<String> list, String reqRes, String now, String EndTime) {
        ReservationValidationResult result = validate(list, reqRes, now, "09:00", EndTime);
        return result.name();
    }

    public ReservationValidationResult validate(
            List<String> reservedSlots,
            String requestedSlot,
            String now,
            String businessStartTime,
            String businessEndTime
    ) {
        LocalTime requestTime = parseTime(requestedSlot);
        LocalTime currentTime = parseTime(now);
        LocalTime startTime = parseTime(businessStartTime);
        LocalTime endTime = parseTime(businessEndTime);

        if (reservedSlots.contains(requestedSlot)) {
            return ReservationValidationResult.DUPLICATE_SLOT;
        }

        if (requestTime.isBefore(startTime) || !requestTime.isBefore(endTime)) {
            return ReservationValidationResult.OUT_OF_BUSINESS_HOURS;
        }

        if (!requestTime.isAfter(currentTime)) {
            return ReservationValidationResult.PAST_TIME;
        }

        return ReservationValidationResult.AVAILABLE;
    }

    private LocalTime parseTime(String time) {
        return LocalTime.parse(time, TIME_FORMATTER);
    }

    public static void main(String[] args) {
        ReservationValidator validator = new ReservationValidator();
        List<String> reservedSlots = Arrays.asList("09:00", "09:30");

        ReservationValidationResult result = validator.validate(
                reservedSlots,
                "10:00",
                "08:30",
                "09:00",
                "20:00"
        );

        System.out.println(result);
    }
}
