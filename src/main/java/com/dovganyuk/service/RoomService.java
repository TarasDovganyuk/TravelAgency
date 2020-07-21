package com.dovganyuk.service;

import com.dovganyuk.model.Room;
import com.dovganyuk.model.RoomType;

import java.util.List;

public interface RoomService {
    List<Room> getRoomsForDatesByHotelId(Integer hotelId, String startDateStr, String endDateStr);

    Room getRoomById(Integer roomId);

    void bookRoom(Integer roomId, String bookDateStr, String username);

    List<RoomType> getAllRoomTypes();

    RoomType getRoomTypeById(Integer roomTypeId);

    void addRoom(Integer hotelId, Integer roomTypeId);
}
