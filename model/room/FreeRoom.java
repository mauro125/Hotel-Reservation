package model.room;

import model.roomEnum.RoomType;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, Double price, RoomType roomType) {
        super(roomNumber, 0.0, roomType);
    }

    @Override
    public String toString() {
        return super.toString() + " is a free room, thank you for your visit!";
    }
}
