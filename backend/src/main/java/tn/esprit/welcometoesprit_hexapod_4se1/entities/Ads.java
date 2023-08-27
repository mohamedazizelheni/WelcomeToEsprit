package tn.esprit.welcometoesprit_hexapod_4se1.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode

public class  Ads implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String socity ;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate ;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date startDate ;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate ;
    @Temporal(TemporalType.DATE)
    private Date adddate ;
    @Enumerated(EnumType.ORDINAL)
    private   Categorieads categorieads  ;
    @Enumerated(EnumType.ORDINAL)
    private Typeads typeads;
    private String content ;
    private int finalNbViews ;
    private Float cost ;
    private int requestedNbViews;

    @ManyToOne
    private User user;
    @ManyToOne
    private  Socity socityy ;


}
