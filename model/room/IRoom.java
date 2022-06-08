package model.room;

import roomEnum.RoomType;

public interface IRoom {
    public int getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
}
