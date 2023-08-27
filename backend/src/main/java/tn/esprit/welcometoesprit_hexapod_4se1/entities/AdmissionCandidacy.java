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

public class AdmissionCandidacy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    private Boolean bac;
    @Column(length = 10000000)
    private byte[] docBacDiploma;
    private Float bacMoy;
    @Column(length = 10000000)
    private byte[] docBacReleve;
    private String bacYear;
    private String bacEstablishment;
    private String bacGovernorate;
    private Float moy1;
    @Column(length = 10000000)
    private byte[] docReleve1;
    private Float moy2;
    @Column(length = 10000000)
    private byte[] docReleve2;
    private Float moy3;
    @Column(length = 10000000)
    private byte[] docReleve3;
    private Float moy4;
    @Column(length = 10000000)
    private byte[] docReleve4;
    private Boolean diploma;
    @Column(length = 10000000)
    private byte[] docDiploma;
    private String level;
    private String speciality;
    private Float testScore;
    private Float interviewScore;
    private String status;
    @ManyToMany
    private List<Test> tests;
    @OneToOne
    @JsonIgnore
    private Interview interview;
    @ManyToOne
    private User user;
}
