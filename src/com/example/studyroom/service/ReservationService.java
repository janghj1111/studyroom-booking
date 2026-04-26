package com.example.studyroom.service;

import com.example.studyroom.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservationService {

    public ReservationStatus reserve(Room room, Reservation reservation, MembershipGrade grade) {
        System.out.println("예약 흐름 실행");
        ReservationValidator validator = new ReservationValidator();
        PriceCalculator calculator = new PriceCalculator();
        TimeSlotGenerator timeSlotGenerator = new TimeSlotGenerator();

        System.out.println("1. 예약 가능 여부 확인");

        ReservationStatus status = validator.validate(room.getType(), reservation, grade);

        if (status != ReservationStatus.AVAILABLE) {
            return status;
        }

        int totalPrice = calculator.calculatePrice(room.getType(), grade, 2, false);
        System.out.println(totalPrice);

        System.out.println("예약 생성 완료");

        /* 질문. 인자값들을 객체로 넣는다는것은 어떻게 진행하는 것인지? */
        /*ReservationStatus validate = validator.validate(reservedSlots,
                reservation.getStartTime(),//"10:00",
                "08:30",
                "09:00",
                "20:00");

        System.out.println(validate);
        *//* 질문. ReservationStatus가 리턴형인데 어떻게 AVAILABLE이 나오는지? *//*

        try{
            if(validate.equals(ReservationStatus.AVAILABLE)){
                System.out.println("2. 가격 계산");
                int totalPrice = calculator.calculatePrice(RoomType.SMALL, MembershipGrade.NORMAL, 2, false);

                System.out.println("3. 예약 생성");
                slots = timeSlotGenerator.generateSlots(9, 18, 30);
            }

        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return slots;*/
        return ReservationStatus.AVAILABLE;
    }
}