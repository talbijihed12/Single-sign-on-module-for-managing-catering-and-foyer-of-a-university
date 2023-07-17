package com.example.foyermicroservice.Repository;

import com.example.foyermicroservice.Entity.Room;
import com.example.foyermicroservice.Entity.Sexe;
import com.example.foyermicroservice.Entity.Types;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

@Query(" select r from Room r where r.placesOcc < r.nbrPlace  ")
List<Room> listeRoomDispo();
@Query("select r from Room  r where r.placesOcc = 0")
List<Room> Roomvide();
    @Query("select r from Room  r where r.nbrPlace-r.placesOcc > 0")
    List<Room> RoomDispo();
List<Room> findByBlock_TypeAndBlock_Sexe(Types types , Sexe sexe);

    List<Room> findRoomsByCleaningRoom(boolean cleaningRoom);
    Room findRoomsByBlock_TypeAndBlock_SexeAndAndNumber(Types type,Sexe sexe,Integer Number);

    List<Room>findRoomsByBlock_TypeAndBlock_Sexe(Types types,Sexe sexe);

    Integer countByBlock_TypeAndAndBlock_SexeAndPlacesOcc(Types types,Sexe sexe, Integer PlacesOcc);
    List<Room> findRoomsByBlock_TypeAndAndBlock_SexeAndAndPlacesOcc(Types types,Sexe sexe, Integer PlacesOcc);
  

}
