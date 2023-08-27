package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Role;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IRoleService;

import java.util.List;

@RestController
@RequestMapping("api/role")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")

public class RoleRestController {

    IRoleService iRoleService;

    @PostMapping("/add")
    public Role addRole(@RequestBody Role role){

        return iRoleService.addRole(role);
    }
    @GetMapping("/getallRoles")
    public List<Role> getallRole(){
        return iRoleService.getallRoles();
    }
    @DeleteMapping("/deleteRole/{idRole}")
    public ResponseEntity<String> deleteRole(@PathVariable ("idRole")int idRole){
        int i= iRoleService.deleteRole(idRole);
        if (i==0){ return ResponseEntity.ok("Role successfully deleted!");}
        else if (i==1){ return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Role can not be deleted, a user has that role!");}
        else {return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Role can not be deleted, "+i+" users have that role!");}
    }

    @PutMapping("/updateRole")
    public Role updateRole(@RequestBody Role role){return iRoleService.updateRole(role);}



}
