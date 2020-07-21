package com.dovganyuk.dao;

import com.dovganyuk.model.Order;
import com.dovganyuk.model.Room;
import com.dovganyuk.model.RoomType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class RoomDaoImpl implements RoomDao {
    private static final Logger logger = LoggerFactory.getLogger(RoomDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Room> getRoomsByHotelId(Integer hotelId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Room where hotel.id = :id")
                    .setParameter("id", hotelId).list();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Room getRoomById(Integer roomId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Room.class, roomId);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    @Override
    public void bookRoom(Integer roomId, LocalDate bookDate, Integer userId) {
        try (Session session = sessionFactory.openSession();) {
            Order order = new Order();
            order.setUserId(userId);
            order.setBookDate(bookDate);
            order.setRoom(getRoomById(roomId));
            session.save(order);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public List<RoomType> getAllRoomTypes() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from RoomType").list();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public RoomType getRoomTypeById(Integer roomTypeId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(RoomType.class, roomTypeId);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    @Override
    public void addRoom(Room room) {
        try (Session session = sessionFactory.openSession();) {
            session.save(room);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
