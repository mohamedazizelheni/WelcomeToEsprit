package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.util.Date;
import java.util.List;

public interface IEventService {
    Event ajouterEvent(Event event);

    List<Event> getEvents();

    Event updateEvent(Event updateEvent);

    List<Event> rechercheEvent(Date date);

    Event getEventById(int id);

    void deleteById(int event_id);
}
