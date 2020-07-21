package com.dovganyuk.dao;

import com.dovganyuk.model.Room;
import com.dovganyuk.model.RoomType;

import java.time.LocalDate;
import java.util.List;

public interface RoomDao {
    List<Room> getRoomsByHotelId(Integer hotelId);

    Room getRoomById(Integer roomId);

    void bookRoom(Integer roomId, LocalDate bookDate, Integer userId);

    List<RoomType> getAllRoomTypes();

    RoomType getRoomTypeById(Integer roomTypeId);

    void addRoom(Room room);
}
