package tn.esprit.welcometoesprit_hexapod_4se1.entities;

import lombok.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.categorieFAQ;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode

public class FAQ implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.ORDINAL)
    private categorieFAQ subject ;
    private String question;
    private String answer;
    @Temporal(TemporalType.DATE)
    private Date adddate ;
    @ManyToOne
    private User user;


}
