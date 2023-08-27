package tn.esprit.welcometoesprit_hexapod_4se1.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.EventRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.InvitationRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.UserRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import com.google.zxing.common.BitMatrix;


@Service
public class InvitationService implements IInvitationService {
    @Autowired
    InvitationRepository invitationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    MailService mailService;

    @Override
    public List<User> getUserSpeakers() {
        List<User> users = invitationRepository.findUserSpeakers(Boolean.TRUE);
        return users;
    }

    @Override
    public List<User> getUsersStudents() {
        List<User> users = invitationRepository.findUsersStudents(Boolean.FALSE);
        return users;
    }

    @Override
    public Invitation inviterSpeaker(int event_id, int user_id) {
        Event event = eventRepository.findById(event_id).get();
        User user = userRepository.findById(user_id).get();
        Invitation invitation = new Invitation();
        invitation.setType("speaker");
        invitation.setEvent(event);
        invitation.setUser(user);

        try {
            mailService.sendEmail2(user.getMail(), "Invitation", String.format("Hi Mr/Mme " + user.getFirstName() + " " + user.getLastName() + ", your are invited to this event:  " + event.getName() + " at " + event.getDate()+" " ), event.getPlanning(), "planning.pdf", "application/pdf");
        } catch (Exception e) {
            System.out.println("mail not sent: " + e.getMessage());
        }
        invitationRepository.save(invitation);
        return invitation;
    }

    @Override
    public void inviterStudents(int event_id, String niveau) {
        List<User> users = invitationRepository.findUserStudents(niveau);
        Event event = eventRepository.findById(event_id).get();

        for (User student : users) {
            Invitation invitation = new Invitation();
            invitation.setType("student");
            invitation.setEvent(event);
            invitation.setUser(student);
            invitation.setPresent(false);
            try {
                mailService.sendEmail2(student.getMail(), "Invitation", String.format("Hi Mr/Mme " + student.getFirstName() + " " + student.getLastName() + ", you are invited to this event: " + event.getName() + " at " + event.getDate()+" " ), event.getPlanning(), "planning.pdf", "application/pdf");
            } catch (Exception e) {
                System.out.println("mail not sent: " + e.getMessage());
            }
            invitationRepository.save(invitation);
        }
    }


    @Override
    public List<Invitation> getInvitations(String name) {
        List<Invitation> invitations = invitationRepository.findUserStudentss(name);
        return invitations;
    }

    @Override
    public void updatePresence(int id, boolean present) {
        Invitation invite = invitationRepository.findById(id).orElse(null);
        User student = invite.getUser();
        Event event = invite.getEvent();


        if (invite != null) {
            invite.setPresent(present);
            try {
                mailService.sendCertificat(student.getMail(), "Invitation","Certificate");
            } catch (Exception e) {
                System.out.println("mail not sent: " + e.getMessage());
            }
            invitationRepository.save(invite);


        }
    }




    @Scheduled(cron = "0 0 0 * * ?")
    public void updateAchievements() {
        Date sevenDaysLater = Date.from(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC));

        System.out.println("Sending email...");
        List<Event> events = eventRepository.findAll();
        Date currentDate = new Date();
        for (Event event : events) {
            if (currentDate.before(DateUtils.addDays(sevenDaysLater,1))) {
                List<Invitation> invitations = invitationRepository.findEventId(event.getId());
                for (Invitation invitation : invitations) {
                    User user = invitation.getUser();
                    if (user != null) {
                        try {
                            mailService.sendEmail2(user.getMail(), "Invitation", String.format("Hi Mr/Mme " + user.getFirstName() + " " + user.getLastName() + ", never forget you have an event : " + event.getName() + " at " + event.getDate()+" " ), event.getPlanning(), "planning.pdf", "application/pdf");
                        } catch (Exception e) {
                            System.out.println("mail not sent: " + e.getMessage());
                        }
                    }
                }
            }
        }
    }
}
