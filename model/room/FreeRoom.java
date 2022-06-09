package model.room;

import roomEnum.RoomType;

public class FreeRoom extends Room {
    public boolean isFree;
    public FreeRoom(int roomNumber, Double roomPrice, RoomType roomType, boolean isFree) {
        super(roomNumber, roomPrice, roomType);
        this.isFree = isFree;
    }

    public boolean getIsFree() {
        return isFree;
    }

    public boolean setIsFree(boolean isFree) {
        return this.isFree = isFree;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRoom Status: " + isFree;
    }
}
