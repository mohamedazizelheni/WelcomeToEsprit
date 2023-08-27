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

public class OfferCandidacy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(length = 10000000)
    private byte[] docDiploma;
    private String establishment;
    @Column(length = 10000000)
    private byte[] docCV;
    @Column(length = 10000000)
    private byte[] docLetter;
    private Float score;
    private String status;
    @ManyToOne
    private Offer offer;
    @JsonIgnore
    @OneToOne
    private Interview interview;
    @ManyToOne
    private User user;
}
