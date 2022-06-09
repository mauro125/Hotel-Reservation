package model.room;

import roomEnum.RoomType;

public class Room implements IRoom {
    private int roomNumber;
    private Double roomPrice;
    private RoomType roomType;

    public Room(int roomNumber, Double roomPrice, RoomType roomType) {
        super();
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int setRoomNumber(int roomNumber) {
        return this.roomNumber = roomNumber;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public Double setRoomPrice(Double roomPrice) {
        return this.roomPrice = roomPrice;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public RoomType setRoomType(RoomType roomType) {
        return this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + "\nRoom Price: " + roomPrice + "\nRoom Type: " + roomType;
    }
}