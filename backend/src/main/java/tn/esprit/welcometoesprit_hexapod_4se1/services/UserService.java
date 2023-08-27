package tn.esprit.welcometoesprit_hexapod_4se1.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Role;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.RoleRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.UserRepository;

import java.util.*;

@Service
@AllArgsConstructor

public class UserService implements IUserService, UserDetailsService {
    UserRepository userRepository;
    RoleRepository roleRepositry;

    @Override
    public User addUserByAdmin(User user,String role) {
        Role role1=roleRepositry.findByRole(role);
        user.setRole(role1);
        return userRepository.save(user);

    }
    @Override
    public User addUserByForm(User user) {
        Role role =roleRepositry.findByRole("PRE");
        Date date=new Date();
        user.setRole(role);
        user.setCreationDate(date);
        return userRepository.save(user);
    }
    @Override
    public List<User> getallUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByLastName(String lastName) {
        List<User> users=userRepository.findByLastNameContaining(lastName);
        return users;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserbyId(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        List<User> usersbyrole=userRepository.findUserByRoleRole(role);
        return usersbyrole;
    }
    @Override
    public User getUserByMail(String mail) {
        User user = userRepository.findUserByMail(mail).orElse(null);
        return user;
    }

    @Override
    public List<User> getAllUsersOrderByFirstName() {
        List<User> users=userRepository.findAll();
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        });
        return users;
    }
    @Override
    public User updateRole(int id,String role) {
        User user=userRepository.findById(id).orElse(null);
        Role roleUser=roleRepositry.findByRole(role);
        user.setRole(roleUser);
        return userRepository.save(user);
    }

    @Override
    public User getUserByCin(String cin) {
        return userRepository.findUserByCin(cin);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByMail(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return user.get();
    }

    public boolean isCurrentUser(int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            return user.getId()==id;
        }
        return false;
    }
    public boolean isJuryOrEvaluator() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            return user.getEvaluator() || user.getJury();
        }
        return false;
    }

}
