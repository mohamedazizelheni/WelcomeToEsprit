package tn.esprit.welcometoesprit_hexapod_4se1.entities;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Channel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String typeChannel;
    private String link;
    @ManyToOne
    private Socity socity;
}
