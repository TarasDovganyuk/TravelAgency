package com.dovganyuk.dao;

import com.dovganyuk.model.Hotel;

import java.util.List;

public interface HotelDao {
    List<Hotel> getHotels();

    void addHotel(String hotelName, String hotelCountry);

    Hotel getHotelById(Integer hotelId);
}
