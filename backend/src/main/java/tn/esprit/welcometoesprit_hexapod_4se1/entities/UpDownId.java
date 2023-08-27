package tn.esprit.welcometoesprit_hexapod_4se1.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpDownId implements Serializable {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    tn.esprit.welcometoesprit_hexapod_4se1.entities.User User;
    @ManyToOne(fetch = FetchType.EAGER)
    FAQ faq;
}
