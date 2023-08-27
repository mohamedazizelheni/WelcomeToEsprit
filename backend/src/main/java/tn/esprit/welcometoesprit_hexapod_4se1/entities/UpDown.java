package tn.esprit.welcometoesprit_hexapod_4se1.entities;
import java.io.Serializable;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class  UpDown implements Serializable{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    UpDownId upDownId ;
    int value;
}
