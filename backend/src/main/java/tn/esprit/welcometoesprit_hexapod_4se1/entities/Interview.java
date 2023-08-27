package tn.esprit.welcometoesprit_hexapod_4se1.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

public class Interview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToOne(mappedBy = "interview")
    private AdmissionCandidacy admissionCandidacy;
    @OneToOne(mappedBy = "interview")
    private OfferCandidacy offerCandidacy;
    @ManyToOne
    private Room room;
    @JsonIgnore
    @OneToOne(mappedBy = "interview")
    private Complain complain;
    @ManyToOne
    private User user;

}
