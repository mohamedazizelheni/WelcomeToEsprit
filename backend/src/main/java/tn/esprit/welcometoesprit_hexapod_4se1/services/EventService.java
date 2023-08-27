package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.AchievementRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.EventRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.InvitationRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class EventService implements IEventService{
    AchievementRepository achievementRepository;
    @Autowired
    InvitationRepository invitationRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    MailService mailService;
    @Override
    public Event ajouterEvent(Event event) {

        return eventRepository.save(event);
    }
    @Override
    public List<Event> getEvents(){
        List<Event> events=eventRepository.findAll();
        return events;
    }
    @Override
    public Event updateEvent(Event updateEvent) {
        Event event = eventRepository.findById(updateEvent.getId()).get();
        event.setName(updateEvent.getName());
        event.setDate(updateEvent.getDate());
        event.setSpace(updateEvent.getSpace());
        event.setPlanning(updateEvent.getPlanning());


        // Retrieve all the invitations related to the updated event
        List<Invitation> invitations = invitationRepository.findByEventId(event.getId());
        for (Invitation invitation : invitations) {
            User user = invitation.getUser();
            if (user != null) {
                try {
                    mailService.sendEmail2(user.getMail(), "Invitation", String.format("Hi Mr/Mme " + user.getFirstName() + " " + user.getLastName() + ", we updated this event: " + event.getName() + " at " + event.getDate()+ " " ), event.getPlanning(), "planning.pdf", "application/pdf");
                } catch (Exception e) {
                    System.out.println("mail not sent: " + e.getMessage());
                }
            }
        }
        return eventRepository.save(event);
    }

    @Override
    public List<Event> rechercheEvent(Date date){
        List<Event> events= eventRepository.findByDate(date);
        if (date.equals("date")) {
            events.sort(Comparator.comparing(Event::getDate).reversed());
        } else  {
            events.sort(Comparator.comparing(Event::getDate).reversed());
        }

        return eventRepository.findByDate(date);
    }
    @Override
    public Event getEventById(int id){
        return eventRepository.findById(id).orElse(null);
    }

    @Override
//    public void deleteById(int event_id) {
//        Event event = eventRepository.findById(event_id).get();
//        eventRepository.deleteById(event_id);
//        List<Invitation> invitations = invitationRepository.findByEventId(event_id);
//        for (Invitation invitation : invitations) {
//            User user = invitation.getUser();
//            if (user != null) {
//                  try{
//                      mailService.sendEmail(user.getMail(), "Invitation", String.format("Hi Mr/Mme " + user.getFirstName() + " " + user.getLastName() + ", we annuled this event: " + event.getName() + " at " + event.getDate()));
//                  }
//                  catch (Exception e) {
//                      System.out.println("mail not sent: " + e.getMessage());
//                  }
//            }
//        }
//    }

    public void deleteById(int event_id) {
        Event event = eventRepository.findById(event_id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        // Send email to all the users who have been invited to this event
        List<Invitation> invitations = invitationRepository.findByEventId(event_id);
        for (Invitation invitation : invitations) {
            User user = invitation.getUser();
            if (user != null) {
                try {
                    mailService.sendEmail2(user.getMail(),
                            "Invitation",
                            String.format("Hi Mr/Mme %s %s, we cancelled this event: %s at %s",
                                    user.getFirstName(),
                                    user.getLastName(),
                                    event.getName(),
                                    event.getDate()+" " ), event.getPlanning(), "planning.pdf", "application/pdf");
                } catch (Exception e) {

                }
            }
        }

        // Delete the event from the database
        eventRepository.deleteById(event_id);
    }








}
