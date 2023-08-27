package tn.esprit.welcometoesprit_hexapod_4se1.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode

public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    @Temporal(TemporalType.TIME)
    private Date date;
    private int totReaction;

    @ManyToOne
    private Post post;
    @ManyToOne(cascade = CascadeType.ALL)
    private Comment comment;

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL)
    private List<Reaction> reactions;
    @ManyToOne
    private User user;
}

