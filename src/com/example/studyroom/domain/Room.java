package com.example.studyroom.domain;

public class Room {

    long id;
    RoomType type;
    int capacity;

    // 이 클래스의 생성자
    public Room(long id, RoomType type, int capacity) {
        this.id = id;
        this.type = type;
        this.capacity = capacity;
    }

    public RoomType getType(){
        return type;
    }
}
