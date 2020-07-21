package com.dovganyuk.service;

import com.dovganyuk.model.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getHotels();

    List<Hotel> getHotelsByCountry(String country);

    List<String> getAllCountries();

    void addHotel(String hotelName, String hotelCountry);

    Hotel getHotelById(Integer hotelId);
}
