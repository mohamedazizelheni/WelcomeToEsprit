package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.util.List;

public interface IUserService {
    User addUserByAdmin(User user,String role);
    User addUserByForm(User user);
    List<User> getallUsers();
    List<User> getUsersByLastName(String LastName);
    List<User> getAllUsersOrderByFirstName();
    User updateUser(User u);
    void deleteUser(int id);
    User getUserbyId(int id);
    User getUserByMail(String Mail);
    List<User> getUsersByRole(String Role);
    User updateRole(int id, String role);
    User getUserByCin(String cin);
}
