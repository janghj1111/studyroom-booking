package com.example.studyroom.service;

import com.example.studyroom.domain.MembershipGrade;
import com.example.studyroom.domain.RoomType;

public class PriceCalculator {
    /*
     * 리팩터링 전 상태
     * - RoomType, Grade enum 이 클래스 내부에 있었음
     * - 주말 여부를 입력으로 받지 않고 LocalDate.now() 로 판단했음
     * - "입력 -> 규칙 -> 결과" 흐름은 있었지만 책임이 한 메서드에 몰려 있었음
     *
     * 아래 레거시 메서드는 기존 흐름을 보존한 학습용 흔적이다.
     */
    @Deprecated
    public int calculatePriceLegacy(RoomType roomType, MembershipGrade membershipGrade, int hours) {
        validateInput(hours);

        int price = roomType == RoomType.SMALL ? 10_000 * hours : 18_000 * hours;

        // 기존 코드는 현재 날짜를 기준으로 주말 여부를 판별했다.
        java.time.DayOfWeek dayOfWeek = java.time.LocalDate.now().getDayOfWeek();
        if (dayOfWeek == java.time.DayOfWeek.SATURDAY || dayOfWeek == java.time.DayOfWeek.SUNDAY) {
            price = (int) (price * 1.2);
        }

        if (membershipGrade == MembershipGrade.VIP) {
            price = (int) (price * 0.9);
        }

        if (hours >= 4) {
            price = (int) (price * 0.95);
        }

        return price;
    }

    public int calculatePrice(RoomType roomType, MembershipGrade membershipGrade, int hours, boolean isWeekend) {
        validateInput(hours);

        int price = calculateBasePrice(roomType, hours);

        if (isWeekend) {
            price = applyWeekendSurcharge(price);
        }

        if (membershipGrade == MembershipGrade.VIP) {
            price = applyVipDiscount(price);
        }

        if (hours >= 4) {
            price = applyLongStayDiscount(price);
        }

        return price;
    }

    private void validateInput(int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("이용 시간은 1시간 이상이어야 합니다.");
        }
    }

    private int calculateBasePrice(RoomType roomType, int hours) {
        return roomType.getHourlyRate() * hours;
    }

    private int applyWeekendSurcharge(int price) {
        return (int) (price * 1.2);
    }

    private int applyVipDiscount(int price) {
        return (int) (price * 0.9);
    }

    private int applyLongStayDiscount(int price) {
        return (int) (price * 0.95);
    }

    /*public static void main(String[] args) {
        PriceCalculator calculator = new PriceCalculator();
        int weekdayPrice = calculator.calculatePrice(RoomType.SMALL, MembershipGrade.NORMAL, 2, false);
        int weekendVipPrice = calculator.calculatePrice(RoomType.LARGE, MembershipGrade.VIP, 6, true);

        System.out.println(weekdayPrice);
        System.out.println(weekendVipPrice);
    }*/
}
