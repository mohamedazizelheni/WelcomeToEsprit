package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.entities.Comment;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Notification;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Post;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;

import java.util.List;

public interface InotificationService {
    Notification getNotificationById(Long notificationId);
    Notification getNotificationByReceiverAndOwningPostAndType(User receiver, Post owningPost, String type);
    void sendNotification(User receiver, User sender, Post owningPost, Comment owningComment, String type);
    void removeNotification(User receiver, Post owningPost, String type);
    List<Notification> getNotificationsForAuthUserPaginate(Integer page, Integer size);
    void markAllSeen();
    void markAllRead();
    void deleteNotification(User receiver, Post owningPost, String type);
    void deleteNotificationByOwningPost(Post owningPost);
    void deleteNotificationByOwningComment(Comment owningComment);
}
