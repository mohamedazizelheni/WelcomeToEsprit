package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.RoomRepository;

import java.util.List;

@Service
public class RoomService implements IRoomService{
    @Autowired
    RoomRepository roomRepository;
    @Override
    public Room createNewRoom(Room room)
    {
        return roomRepository.save(room);
    }
    @Override
    public Room updateRoom(Room room)
    {
        return roomRepository.save(room);
    }
    @Override
    public List<Room> getRooms(){ return (List<Room>) roomRepository.findAll();}

    @Override
    public Room getRoomById(int id) {
        return roomRepository.findById(id).orElse(null);
    }
    @Override
    public void deleteRoom(int id){roomRepository.deleteById(id);}
}
