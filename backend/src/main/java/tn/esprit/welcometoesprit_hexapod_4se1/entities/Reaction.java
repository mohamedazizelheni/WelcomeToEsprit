package tn.esprit.welcometoesprit_hexapod_4se1.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode

public class Reaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.ORDINAL)
    private TypeReaction type;
    @Temporal(TemporalType.TIME)
    private Date date;
    @JsonIgnore
    @ManyToOne
    private Comment comment;

    @ManyToOne
    private Achievement achievement;

    @ManyToOne
    private User user;
}
