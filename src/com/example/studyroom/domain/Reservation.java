package com.example.studyroom.domain;

public class Reservation {

    Room room;
    String startTime;
    String endTime;

    public Reservation(Room room, String startTime, String endTime) {
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // 질문. return this.room이 아니고 그냥 room인지?
    public Room getRoom(){
        return room;
    }

    public String getStartTime(){
        return startTime;
    }
    public String getEndTime(){
        return endTime;
    }
}
