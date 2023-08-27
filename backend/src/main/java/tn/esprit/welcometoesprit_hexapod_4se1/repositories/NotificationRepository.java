package tn.esprit.welcometoesprit_hexapod_4se1.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Comment;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Notification;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Post;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    Optional<Notification> findByReceiverAndOwningPostAndType(User receiver, Post owningPost, String type);
    List<Notification> findNotificationsByReceiver(User receiver, Pageable pageable);
    List<Notification> findNotificationsByReceiverAndIsSeenIsFalse(User receiver);
    List<Notification> findNotificationsByReceiverAndIsReadIsFalse(User receiver);
    void deleteNotificationByOwningPost(Post owningPost);
    void deleteNotificationByOwningComment(Comment owningComment);
}
