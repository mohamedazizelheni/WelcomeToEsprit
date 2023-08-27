package tn.esprit.welcometoesprit_hexapod_4se1.entities;
import javax.persistence.*;
import javax.swing.*;
import java.io.File;
import java.io.Serializable;
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

public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String block;
    @Column(length = 10000000)
    private byte[] map;
    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private List<Interview> interviews;
}
