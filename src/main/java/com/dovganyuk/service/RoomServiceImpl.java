package com.dovganyuk.service;

import com.dovganyuk.dao.HotelDao;
import com.dovganyuk.dao.RoomDao;
import com.dovganyuk.dao.UserDao;
import com.dovganyuk.model.Order;
import com.dovganyuk.model.Room;
import com.dovganyuk.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class RoomServiceImpl implements RoomService {
    private RoomDao roomDao;
    private UserDao userDao;
    private HotelDao hotelDao;

    @Autowired
    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setHotelDao(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    @Override
    public List<Room> getRoomsForDatesByHotelId(Integer hotelId, String startDateStr, String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        List<Room> rooms = roomDao.getRoomsByHotelId(hotelId);
        List<Room> result = new ArrayList<>();
        for (Room room : rooms) {
            List<LocalDate> bookedDates = room.getOrders().stream()
                    .map(Order::getBookDate)
                    .filter(date -> (date.isAfter(startDate) || date.isEqual(startDate))
                            && (date.isBefore(endDate) || date.isEqual(endDate)))
                    .distinct()
                    .collect(Collectors.toList());

            String bookedDatesArr = bookedDates.stream()
                    .map(localDate -> localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .collect(Collectors.joining("',' ", "['", "']"));
            room.setBookedDatesJSArray(bookedDatesArr);

            long days = DAYS.between(startDate, endDate) + 1;
            if (days != bookedDates.size()) {
                result.add(room);
            }
        }

        return result;
    }

    @Override
    public Room getRoomById(Integer roomId) {
        return roomDao.getRoomById(roomId);
    }

    @Override
    public void bookRoom(Integer roomId, String bookDateStr, String username) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate bookDate = LocalDate.parse(bookDateStr, formatter);
        roomDao.bookRoom(roomId, bookDate, userDao.findByUsername(username).getId());
    }

    @Override
    public List<RoomType> getAllRoomTypes() {
        return roomDao.getAllRoomTypes();
    }

    @Override
    public RoomType getRoomTypeById(Integer roomTypeId) {
        return roomDao.getRoomTypeById(roomTypeId);
    }

    @Override
    public void addRoom(Integer hotelId, Integer roomTypeId) {
        Room room = new Room();
        room.setHotel(hotelDao.getHotelById(hotelId));
        room.setRoomType(roomDao.getRoomTypeById(roomTypeId));
        roomDao.addRoom(room);
    }
}
