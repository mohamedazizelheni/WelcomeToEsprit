package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.io.File;
import java.util.List;

public interface IRoomService {

    Room createNewRoom(Room room);

    Room updateRoom(Room room);

    List<Room> getRooms();

    Room getRoomById(int id);

    void deleteRoom(int id);
}
