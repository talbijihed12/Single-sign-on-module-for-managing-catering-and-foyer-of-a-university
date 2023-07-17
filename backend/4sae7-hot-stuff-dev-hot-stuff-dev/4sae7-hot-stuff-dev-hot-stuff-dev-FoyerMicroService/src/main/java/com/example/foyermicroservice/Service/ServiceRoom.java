package com.example.foyermicroservice.Service;

import com.example.foyermicroservice.Entity.Block;
import com.example.foyermicroservice.Entity.Room;
import com.example.foyermicroservice.Entity.Sexe;
import com.example.foyermicroservice.Entity.Types;
import com.example.foyermicroservice.Repository.BlockRepository;
import com.example.foyermicroservice.Repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceRoom implements IServiceRoom{
    private RoomRepository roomRepo ;
    private BlockRepository blockRepo ;
    @Override
    public Room ajouterRoom(Room room){
        return roomRepo.save(room);
    }
@Override
public void affecterRoomBlock( long idR , long idB){
        Block block = blockRepo.findById(idB).orElse(null);
        Room room1 = roomRepo.findById(idR).orElse(null);
        room1.setBlock(block);
        roomRepo.save(room1);
}
    @Override
    public void AjouterRoometAffecterToBlock (Room room, long idB){

        Block block = blockRepo.findById(idB).orElse(null);
        room.setBlock(block);
        room.setCleaningRoom(false);
        roomRepo.save(room);
    }

    @Override
    public void desaffecterRoomBlock(long idR){
        Room room = roomRepo.findById(idR).orElse(null);
        room.setBlock(null);
        roomRepo.save(room);


    }
    @Override
    public List<Room> getRooms(){
        return roomRepo.findAll();
    }
    public void deleteRoom( long idR){
        Room room = roomRepo.getReferenceById(idR);
        roomRepo.delete(room);
    }
    public Room updateRoom( Room room, long idR){
        Room room1 = roomRepo.findById(idR).orElse(null);
        room1.setNumber(room.getNumber());
        room1.setFloor(room.getFloor());
        room1.setNbrPlace(room.getNbrPlace());
        room1.setPlacesOcc(room.getPlacesOcc());
        room1.setCleaningRoom(room.getCleaningRoom());
        roomRepo.save(room1);
        return room1 ;


    }
    @Override
    public List<Room> listeRoomDispo(){
        return roomRepo.listeRoomDispo();
    }
    @Override
    public List<Room> Roomvide(){
        return roomRepo.Roomvide();
    }
    public List<Room> RoomDispoBloc(){
        return roomRepo.Roomvide();
    }
    @Override
    public List<Room> listeRoomParTypeAndSexe(Types types , Sexe sexe){
        return roomRepo.findByBlock_TypeAndBlock_Sexe(types, sexe);
    }

    @Override
    public int NumberVideRoom(Types types,Sexe sexe){
        return roomRepo.countByBlock_TypeAndAndBlock_SexeAndPlacesOcc(types, sexe, 0);
    }
    @Override
    public List<Room> RoomEmpty(Types types,Sexe sexe){
        return roomRepo.findRoomsByBlock_TypeAndAndBlock_SexeAndAndPlacesOcc(types,sexe,0);
    }
    @Override
    public Room BookRoom(long idR ) {
        Room room = roomRepo.findById(idR).orElse(null);
        if (room.getPlacesOcc()!=null){
            room.setPlacesOcc(room.getPlacesOcc()+1);
            roomRepo.save(room);

        }
        return room;


    }
    @Override
    public Room UNBookRoom(long idR ) {
        Room room = roomRepo.findById(idR).orElse(null);
        if (room.getPlacesOcc()!=null){
            room.setPlacesOcc(room.getPlacesOcc()-1);
            roomRepo.save(room);

        }
        return room;


    }
    @Override
    public Room BookAllRoom(long idR ) {
        Room room = roomRepo.findById(idR).get();
        if(room.getPlacesOcc()!=room.getNbrPlace()){
            room.setPlacesOcc(room.getNbrPlace());
            roomRepo.save(room);
        }
        return room ;
    }
    @Override
    public Room UnBookAllRoom(long idR ) {
        Room room = roomRepo.findById(idR).orElse(null);

            room.setPlacesOcc(0);
            roomRepo.save(room);

        return room;

    }
    @Override
    public Room RoomUnderConstruction (long idR){
        Room room = roomRepo.findById(idR).orElse(null);
        room.setFloor(room.getFloor()+ " en panne");
        room.setNbrPlace(0);
        room.setPlacesOcc(0);

        roomRepo.save(room);
        return room;
    }

    @Override
    public List<Room> listeRoomParTypeAndSexe1(){
        return roomRepo.findRoomsByBlock_TypeAndBlock_Sexe(Types.NONRESIDENT,Sexe.MALE);
    }
    @Override
    public List<Room> listeRoomParTypeAndSexeMRS(){
        return roomRepo.findRoomsByBlock_TypeAndBlock_Sexe(Types.RESIDANT_Student,Sexe.MALE);
    }
    @Override
    public List<Room> listeRoomParTypeAndSexeMRT(){
        return roomRepo.findRoomsByBlock_TypeAndBlock_Sexe(Types.RESIDANT_Teacher,Sexe.MALE);
    }
    @Override
    public List<Room> listeRoomParTypeAndSexeF(){
        return roomRepo.findRoomsByBlock_TypeAndBlock_Sexe(Types.NONRESIDENT,Sexe.FEMALE);
    }
    @Override
    public List<Room> listeRoomParTypeAndSexeFR(){
        return roomRepo.findRoomsByBlock_TypeAndBlock_Sexe(Types.RESIDANT_Student,Sexe.FEMALE);
    }
    @Override
    public List<Room> listeRoomParTypeAndSexeFRT(){
        return roomRepo.findRoomsByBlock_TypeAndBlock_Sexe(Types.RESIDANT_Teacher,Sexe.FEMALE);
    }

    @Override
    public  List<Room> listeRoomParTypeAndSexeMixtNR(){
        return roomRepo.findRoomsByBlock_TypeAndBlock_Sexe(Types.NONRESIDENT,Sexe.MIXTE);
    }
    @Override
    public List<Room> listeRoomParTypeAndSexeMixtRT(){
        return roomRepo.findRoomsByBlock_TypeAndBlock_Sexe(Types.RESIDANT_Teacher,Sexe.MIXTE);
    }
@Override
@Scheduled(cron = "0 0 0 * * SUN") // Exécuter chaque dimanche à minuit
public void updateCleanningRoom(Room room, boolean cleaningRoom){
        List<Room> rooms= roomRepo.findRoomsByCleaningRoom(true);
        for(Room room1 : rooms){
            room1.setCleaningRoom(false);
            roomRepo.save(room1);
        }
}
@Override
public void CleanningRoom(Types type,Sexe sexe,Integer Number){
        Room room1 = roomRepo.findRoomsByBlock_TypeAndBlock_SexeAndAndNumber(type, sexe, Number);

            room1.setCleaningRoom(true);
            roomRepo.save(room1);


        }
}




