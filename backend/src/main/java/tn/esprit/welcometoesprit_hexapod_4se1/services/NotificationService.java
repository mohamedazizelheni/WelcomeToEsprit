package tn.esprit.welcometoesprit_hexapod_4se1.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.Exception.NotificationNotFoundException;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Comment;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Notification;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Post;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.NotificationRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService implements InotificationService{
private final NotificationRepository notificationRepository;
private final UserService userService;

    @Override
    public Notification getNotificationById(Long notificationId) {
        return notificationRepository.findById(notificationId).orElseThrow(NotificationNotFoundException::new);
    }

    @Override
    public Notification getNotificationByReceiverAndOwningPostAndType(User receiver, Post owningPost, String type) {
        return notificationRepository.findByReceiverAndOwningPostAndType(receiver, owningPost, type)
                .orElseThrow(NotificationNotFoundException::new);
    }

    @Override
    public void sendNotification(User receiver, User sender, Post owningPost, Comment owningComment, String type) {
    }

    @Override
    public void removeNotification(User receiver, Post owningPost, String type) {

    }

    @Override
    public List<Notification> getNotificationsForAuthUserPaginate(Integer page, Integer size) {
        return null;
    }

    @Override
    public void markAllSeen() {

    }

    @Override
    public void markAllRead() {

    }

    @Override
    public void deleteNotification(User receiver, Post owningPost, String type) {

    }

    @Override
    public void deleteNotificationByOwningPost(Post owningPost) {

    }

    @Override
    public void deleteNotificationByOwningComment(Comment owningComment) {

    }
}
