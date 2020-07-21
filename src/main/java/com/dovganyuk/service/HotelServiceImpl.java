package com.dovganyuk.service;

import com.dovganyuk.dao.HotelDao;
import com.dovganyuk.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {
    private HotelDao hotelDao;

    @Autowired
    public void setHotelDao(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelDao.getHotels();
    }

    @Override
    public List<Hotel> getHotelsByCountry(String country) {
        return hotelDao.getHotels().stream()
                .filter(hotel -> hotel.getCountry().equals(country))
                .sorted(Comparator.comparing(Hotel::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllCountries() {
        return hotelDao.getHotels().stream()
                .map(Hotel::getCountry)
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    @Override
    public void addHotel(String hotelName, String hotelCountry) {
        hotelDao.addHotel(hotelName, hotelCountry);
    }

    @Override
    public Hotel getHotelById(Integer hotelId) {
        return hotelDao.getHotelById(hotelId);
    }
}
