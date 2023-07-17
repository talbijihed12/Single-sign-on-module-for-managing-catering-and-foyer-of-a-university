package com.example.subscriptionmicroservice.Service;

import com.example.subscriptionmicroservice.Entity.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(value = "FOEYRMICROSERVICE",url = "http://localhost:8090")

public interface MicroS {
    @GetMapping("/listRoomDispo")
    List<Room> readRooms();
    @PutMapping("/BookPlaceRoom/{idR}")
    Room BookRoom(@PathVariable("idR") Long id);
    @PutMapping("/UnBookPlaceRoom/{idR}")
    Room BookRoomRf(@PathVariable("idR") Long id);
}


