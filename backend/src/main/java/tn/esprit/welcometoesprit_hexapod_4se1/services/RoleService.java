package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleService implements IRoleService{
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role addRole(Role role){

        return roleRepository.save(role);
    }
    @Override
    public Role updateRole(Role role){

        return roleRepository.save(role);
    }
    @Override
    public List<Role> getallRoles() {
        return roleRepository.findAll();
    }
    @Override
    public int deleteRole(int id) {
        Role role = roleRepository.findById(id).get();
        List<User> users = role.getUsers();
        int i=0;
        if (users==null || users.size()==0) {
            roleRepository.deleteById(id);
        }
        else {
            i=users.size();
        }
        return i;
    }
}
