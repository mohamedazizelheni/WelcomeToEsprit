package tn.esprit.welcometoesprit_hexapod_4se1.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String speciality;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "offer")
    private List<OfferCandidacy> offerCandidacies;
}
