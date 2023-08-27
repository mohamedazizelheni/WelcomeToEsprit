package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.util.List;

public interface IRoleService {
    Role addRole(Role role);

    Role updateRole(Role role);

    List<Role> getallRoles();
    int deleteRole(int id);
}
