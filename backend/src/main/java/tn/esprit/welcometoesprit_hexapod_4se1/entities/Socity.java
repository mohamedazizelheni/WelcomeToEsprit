package tn.esprit.welcometoesprit_hexapod_4se1.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Socity  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name ;
    private String URLfacbook ;
    private String  Email ;
    private String URLlinkedin ;
    private int numerotelephonique ;
    private String Logo ;
    @OneToMany(mappedBy = "socity")
    private List<Ads> ads;
    @OneToMany(mappedBy = "socity")
    private List<Channel> channels;
}
