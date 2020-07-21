package com.dovganyuk.controller;

import com.dovganyuk.model.Hotel;
import com.dovganyuk.model.Room;
import com.dovganyuk.service.HotelService;
import com.dovganyuk.service.RoomService;
import com.dovganyuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class HotelController {
    private HotelService hotelService;
    private RoomService roomService;
    private UserService userService;

    @Autowired
    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/", "/findHotel"})
    public String getCountries(Model model) {
        List<String> countries = hotelService.getAllCountries();
        model.addAttribute("countries", countries);
        return "findHotel";
    }

    @PostMapping(value = "/findHotel")
    public String findHotel(Model model, @RequestParam String selectCountry) {
        List<Hotel> hotels = hotelService.getHotelsByCountry(selectCountry);
        model.addAttribute("hotels", hotels);
        model.addAttribute("currDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return "hotels";
    }

    @PostMapping(value = "/rooms")
    public String getRooms(Model model, @RequestParam Integer hotelId,
                           @RequestParam String startDate, @RequestParam String endDate) {
        List<Room> rooms = roomService.getRoomsForDatesByHotelId(hotelId, startDate, endDate);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("rooms", rooms);
        return "rooms";
    }

    @PostMapping(value = "/book_room")
    public String bookRoom(Model model, @RequestParam Integer roomId, @RequestParam String bookDate,
                           Authentication authentication) {
        roomService.bookRoom(roomId, bookDate, authentication.getName());

        Room room = roomService.getRoomById(roomId);
        model.addAttribute("titleMsg", "Book confirmation");
        model.addAttribute("infoMsg", "Booked successfully!");
        String mainMsg = room.getRoomType().getType() +
                " room in hotel " + room.getHotel().getName() + "(" + room.getHotel().getCountry() +
                ") booked successfully on " + bookDate;
        model.addAttribute("mainMsg", mainMsg);
        return "confirm";
    }

    @GetMapping(value = "/addHotel")
    public String addHotel(Model model) {
        return "addHotel";
    }

    @PostMapping(value = "/addHotel")
    public String addHotel(Model model, @RequestParam String hotelName, @RequestParam String hotelCountry) {
        hotelService.addHotel(hotelName, hotelCountry);

        model.addAttribute("titleMsg", "Hotel addition confirmation");
        model.addAttribute("infoMsg", "Added successfully!");
        String mainMsg = "Hotel " + hotelName + "(" + hotelCountry + ") created successfully";
        model.addAttribute("mainMsg", mainMsg);
        return "confirm";
    }

    @GetMapping(value = "/addRoom")
    public String addRoom(Model model) {
        model.addAttribute("hotels", hotelService.getHotels());
        model.addAttribute("roomTypes", roomService.getAllRoomTypes());

        return "addRoom";
    }

    @PostMapping(value = "/addRoom")
    public String addRoom(Model model, @RequestParam Integer selectHotel, @RequestParam Integer selectRoomType) {
        roomService.addRoom(selectHotel, selectRoomType);
        model.addAttribute("titleMsg", "Room addition confirmation");
        model.addAttribute("infoMsg", "Added successfully!");
        String mainMsg = "Room " + roomService.getRoomTypeById(selectRoomType).getType() + " added to hotel " +
                hotelService.getHotelById(selectHotel).getName() + " successfully";
        model.addAttribute("mainMsg", mainMsg);
        return "confirm";
    }

    @GetMapping(value = "/users")
    public String viewUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "users";
    }

    @PostMapping(value = "/view_orders")
    public String viewOrders(Model model, @RequestParam Integer userId) {
        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("orders", userService.getOrdersByUserId(userId));

        return "orders";
    }
}
