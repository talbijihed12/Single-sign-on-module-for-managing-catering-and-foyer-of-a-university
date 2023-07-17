package com.example.foyermicroservice.Controllers;

import com.example.foyermicroservice.Entity.Room;
import com.example.foyermicroservice.Entity.Sexe;
import com.example.foyermicroservice.Entity.Types;
import com.example.foyermicroservice.Repository.RoomRepository;
import com.example.foyermicroservice.Service.IServiceRoom;
import lombok.AllArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class RoomController {
   private RoomRepository roomRepo ;
    private IServiceRoom iServiceRoom ;
    @PostMapping("/ajouterRoom/{idB}")
    public Room ajouterRoom(@RequestBody Room room,@PathVariable("idB") Long idB){
        iServiceRoom.ajouterRoom(room);
        iServiceRoom.affecterRoomBlock( room.getIdR(), idB);
        return room ;

    }
    @PutMapping("/affecterRoomBlock/{idR}/{idB}")
    public void affecterRoomBlock(@PathVariable long idR , @PathVariable long idB){
        iServiceRoom.affecterRoomBlock( idR, idB);
    }
    /*@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @PostMapping("/AjouterRoometAffecterToBlock/{idB}")
    public void AjouterRoometAffecterToBlock (@RequestBody Room room, @PathVariable long idB){
        iServiceRoom.AjouterRoometAffecterToBlock(room,idB);

    }*/
  //  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/desaffecterRoomBlock/{idR}")
    public void desaffecterRoomBlock(@PathVariable long idR) {

        iServiceRoom.desaffecterRoomBlock(idR);
    }
   // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/Rooms")
    public List<Room> getRooms(){
        return iServiceRoom.getRooms();
    }
  // @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @DeleteMapping("/deleteRoom/{idR}")
    public void deleteRoom( @PathVariable long idR){
        iServiceRoom.deleteRoom(idR);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @PutMapping("/updateRoom/{idR}")
    public Room updateRoom(@RequestBody Room room , @PathVariable long idR){
        return iServiceRoom.updateRoom(room,idR);
    }
    @GetMapping("/RoomAvaileb")
    public List<Room> getRoom(){
        return iServiceRoom.listeRoomDispo();
    }
    @GetMapping("/RoomEmty/{types}/{sexe}")
    public List<Room> RoomEmpty (@PathVariable Types types,@PathVariable Sexe sexe){
        return iServiceRoom.Roomvide();
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/NumberRoomVide/{types}/{sexe}")
    public int NumberVideRoom(@PathVariable Types types,@PathVariable Sexe sexe){
        return iServiceRoom.NumberVideRoom(types, sexe);
    }

    @PutMapping("/BookPlaceRoom/{idR}")
    public Room BookRoom(@PathVariable long idR )
    {
        return iServiceRoom.BookRoom(idR);
    }
    @PutMapping("/UnBookPlaceRoom/{idR}")
    public Room UNBookRoom(@PathVariable long idR ){
        return iServiceRoom.UNBookRoom(idR);
    }

    @PutMapping("/BookRoom/{idR}")
    public Room BookAllRoom(@PathVariable long idR)
    {
        return  iServiceRoom.BookAllRoom(idR);
    }
    @PutMapping("/UnBookRoom/{idR}")
    public Room UnBookAllRoom(@PathVariable long idR ) {
        return iServiceRoom.UnBookAllRoom(idR);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @PutMapping("/RoomUnderConstruction/{idR}")
    public Room RoomUnderConstruction (@PathVariable long idR){
        return iServiceRoom.RoomUnderConstruction(idR);
    }
    @GetMapping("/RoomParTypeSexe/{types}/{sexe}")
    public List<Room> listeRoomParTypeAndSexe (@PathVariable("types") Types types,@PathVariable("sexe")  Sexe sexe){
        return iServiceRoom.listeRoomParTypeAndSexe(types, sexe);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/cleaningRoom/{types}/{sexe}/{Number}")
    public void CleanningRoom( Types type,  Sexe sexe, Integer Number){
        iServiceRoom.CleanningRoom(type, sexe, Number);
    }
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_FOYER')")
    @GetMapping("/statRoomsFull")
    public Map<String,Double> getRoomPercentage(){
        List<Room> rooms = roomRepo.findAll();
        Map<String,Double> percentages = new HashMap<>();
        int total = rooms.size();
        int blockA = 0,
         blockB = 0,
         blockC = 0;
        for (Room room : rooms){
            if (room.getPlacesOcc()!=0 && room.getBlock().getType()==Types.NONRESIDENT){
                blockA++;
            } else if (room.getPlacesOcc()!=0 && room.getBlock().getType()==Types.RESIDANT_Student) {
                blockB++;

            } else if (room.getPlacesOcc()!=0 && room.getBlock().getType()==Types.RESIDANT_Teacher) {
                blockC++;

            }
        }
        percentages.put("NORESIDANT %",(double)blockA / total * 100 );
        percentages.put("RESIDANT_Student %",(double)blockB / total * 100);
        percentages.put("RESIDANT_Teacher % ",(double)blockC / total * 100);
        return percentages;
    }








}
