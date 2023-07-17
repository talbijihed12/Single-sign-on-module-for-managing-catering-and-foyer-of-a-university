package com.example.reservationmicroservice.RoomMicroService;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "FOEYRMICROSERVICE",url = "http://localhost:8090")
public interface  RoomService {
    @GetMapping("/RoomVide")
    List<RoomDTO> readItems();
    @PutMapping("/BookRoom/{idR}")
    RoomDTO BookRoom(@PathVariable("idR") Long id);
    @PutMapping("/BookRoom/{idR}")
    RoomDTO BookRoomRf(@PathVariable("idR") Long id);
    @PutMapping("/UnBookRoom/{idR}")
    RoomDTO UnbookRoom(@PathVariable("idR") Long idR);
    @PutMapping("/UnBookRoom/{idR}")
    RoomDTO retrieveRoomById(@PathVariable("idR") Long idR);
}
