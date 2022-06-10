package model.room;

import roomEnum.RoomType;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        super();
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String setRoomNumber(String roomNumber) {
        return this.roomNumber = roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    public Double setRoomPrice(Double price) {
        return this.price = price;
    }

    public RoomType getRoomType() {
        return roomType;
    }



    public RoomType setRoomType(RoomType roomType) {
        return this.roomType = roomType;
    }

    public boolean isFree() {
        return true;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + "\nRoom Price: " + price + "\nRoom Type: " + roomType;
    }
}