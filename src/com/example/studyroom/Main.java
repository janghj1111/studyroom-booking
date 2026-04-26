package com.example.studyroom;

import com.example.studyroom.domain.MembershipGrade;
import com.example.studyroom.domain.Reservation;
import com.example.studyroom.domain.Room;
import com.example.studyroom.domain.RoomType;
import com.example.studyroom.service.ReservationService;

public class Main {

    // 역할의 책임을 질 main 서비스
    public static void main(String[] args) {
        ReservationService reservationService = new ReservationService();

        // 인자값으로 들어갈 객체 만들기
        // 배웠음 : 그 동안에 vo의 lombok을 통해 get set 방식으로만 해왔어서 new 안에 값을 넣어서 객체로 만드는 것을 몰랐었음. => 이것 조차 기본 생성자가 필요하다는 것.

        Room room = new Room(1L, RoomType.SMALL, 4); // 어느 방을 예약할지
        Reservation reservation = new Reservation(room, "09:00", "10:00"); // 예약을 원하는 시간대

        reservationService.reserve(room, reservation, MembershipGrade.NORMAL);
    }
}
