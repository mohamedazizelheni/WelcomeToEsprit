package tn.esprit.welcometoesprit_hexapod_4se1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode

public class User implements Serializable, UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int id;
    private String cin;
    private String firstName;
    private String lastName;
    private String gender;
    @Column(length = 10000000)
    private byte[] image;
    private String image0;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String address;
    private String phone;
    @Column(unique = true)
    private String mail;
    private String password;
    private String Department;
    @Temporal(TemporalType.DATE)
    private Date HiringDate;
    @Column(columnDefinition = "boolean default false")
    private Boolean evaluator = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean jury = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean speaker = false;

    private String bacSection;
    private String educationLevel;
    private String speciality;
    private String classe;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @ManyToOne
    private Role role;

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @JsonIgnore
    @Transient
    private List<GrantedAuthority> authorities;
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities = Collections.singletonList(new SimpleGrantedAuthority(role.getRole()));
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return mail;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

}
