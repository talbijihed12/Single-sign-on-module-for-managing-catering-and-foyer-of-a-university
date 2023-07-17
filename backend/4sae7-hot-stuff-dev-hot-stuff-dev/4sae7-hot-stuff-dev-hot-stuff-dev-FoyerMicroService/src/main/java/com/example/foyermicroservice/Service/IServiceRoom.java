package com.example.foyermicroservice.Service;

import com.example.foyermicroservice.Entity.Room;
import com.example.foyermicroservice.Entity.Sexe;
import com.example.foyermicroservice.Entity.Types;

import java.util.List;

public interface IServiceRoom {
    public Room ajouterRoom(Room room);
    public void affecterRoomBlock( long idR , long idB);
    public void AjouterRoometAffecterToBlock (Room room, long idB);
    public void desaffecterRoomBlock(long idR) ;
    public List<Room> getRooms();
    public void deleteRoom( long idR) ;
    public Room updateRoom( Room room , long idR);
    List<Room> listeRoomDispo();
    List<Room> Roomvide();
    List<Room> listeRoomParTypeAndSexe(Types types, Sexe sexe);
    public Room BookRoom(long idR ) ;
    public Room UNBookRoom(long idR ) ;
    public Room BookAllRoom(long idR ) ;
    public Room UnBookAllRoom(long idR ) ;
    public Room RoomUnderConstruction (long idR);

    List<Room> listeRoomParTypeAndSexe1();
    List<Room> listeRoomParTypeAndSexeF();
    List<Room> listeRoomParTypeAndSexeFR();
    List<Room> listeRoomParTypeAndSexeFRT();
    List<Room> listeRoomParTypeAndSexeMRS();
    List<Room> listeRoomParTypeAndSexeMRT();
    List<Room> listeRoomParTypeAndSexeMixtNR();
    List<Room> listeRoomParTypeAndSexeMixtRT();
    public void updateCleanningRoom(Room room, boolean cleaningRoom) ;
    public void CleanningRoom( Types type,Sexe sexe,Integer Number);
    public int NumberVideRoom(Types types,Sexe sexe);
    public List<Room> RoomEmpty(Types types,Sexe sexe) ;

}
