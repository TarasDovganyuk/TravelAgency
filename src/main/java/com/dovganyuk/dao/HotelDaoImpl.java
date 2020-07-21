package com.dovganyuk.dao;

import com.dovganyuk.model.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class HotelDaoImpl implements HotelDao {
    private static final Logger logger = LoggerFactory.getLogger(HotelDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Hotel> getHotels() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(" from Hotel").list();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void addHotel(String hotelName, String hotelCountry) {
        try (Session session = sessionFactory.openSession()) {
            Hotel hotel = new Hotel();
            hotel.setName(hotelName);
            hotel.setCountry(hotelCountry);
            session.save(hotel);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public Hotel getHotelById(Integer hotelId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Hotel.class, hotelId);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }
}
