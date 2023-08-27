package tn.esprit.welcometoesprit_hexapod_4se1.restControllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.LoginRequest;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Role;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.RoleRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.security.JWTUtils;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IRoleService;
import tn.esprit.welcometoesprit_hexapod_4se1.services.IUserService;
import tn.esprit.welcometoesprit_hexapod_4se1.services.TokenBlacklistService;
import tn.esprit.welcometoesprit_hexapod_4se1.services.UserService;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tn.esprit.welcometoesprit_hexapod_4se1.security.SecurityConstants.TOKEN_HEADER;
import static tn.esprit.welcometoesprit_hexapod_4se1.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("api/user")
@SecurityRequirement(name = "bearerAuth")

public class UserRestController {
    @Autowired
    IUserService iUserService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    TokenBlacklistService tokenBlacklistService;
//    @PostMapping("/login")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {
//        return ResponseEntity.ok("Login successful");
//
//    }
@Autowired
private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.getMail(),
                loginRequest.getPassword()
        );
        authentication = authenticationManager.authenticate(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", token);
        return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        String authorizationHeader = request.getHeader(TOKEN_HEADER);
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            token = authorizationHeader.substring(7);
            tokenBlacklistService.addToBlacklist(token);

        }
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Logout successful");
        return ResponseEntity.ok(responseBody);
    }


    @PostMapping(value = "/addUser", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addUserByForm(@RequestParam String cin,
                              @RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String gender,
                              @RequestParam MultipartFile image,
                              @RequestParam String image0,
                              @RequestParam Date birthDate,
                              @RequestParam String address,
                              @RequestParam String phone,
                              @RequestParam String mail,
                              @RequestParam String password,
                              @RequestParam String bacSection,
                              @RequestParam String educationLevel,
                              @RequestParam String speciality) throws IOException {
        User user=new User();
        user.setMail(mail);
        user.setPassword(password);
        user.setAddress(address);
        user.setCin(cin);
        user.setBacSection(bacSection);
        user.setEducationLevel(educationLevel);
        user.setGender(gender);
        user.setImage(image.getBytes());
        user.setImage0(image0);
        user.setBirthDate(birthDate);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setSpeciality(speciality);
        return ResponseEntity.ok(iUserService.addUserByForm(user));

    }
    @PostMapping("/addUser/admin")
    public ResponseEntity<?> addUserByAdmin(@RequestBody User user,@RequestParam String role){
        return ResponseEntity.ok(iUserService.addUserByAdmin(user,role));
    }

    @GetMapping("/getAllUsers/admin")
    public List<User> getallUser(){

        return iUserService.getallUsers();
    }

    @GetMapping("/getUsersByLastName/{Name}/admin")
    public List<User> getUsersByLastName(@PathVariable("Name")String LastName){

        return iUserService.getUsersByLastName(LastName);
    }
    @GetMapping("/getAllUsersOrderByFirstName/admin")
    public List<User> getAllUsersOrderByFirstName(){return iUserService.getAllUsersOrderByFirstName();}
    @GetMapping("/getUserbyMail/{Mail}")
    public User getUserByMail(@PathVariable("Mail")String Mail){

        return iUserService.getUserByMail(Mail);
    }
    @GetMapping("/getUsersbyRole/{Role}/admin")
    public List<User> getUsersByRole(@PathVariable("Role")String Role){

        return iUserService.getUsersByRole(Role);
    }

    @PutMapping(value = "/updateUser", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updadeUser(@RequestParam int id,
                                        @RequestParam (required = false) String cin,
                                        @RequestParam (required = false) String firstName,
                                        @RequestParam (required = false) String lastName,
                                        @RequestParam (required = false) String gender,
                                        @RequestParam (required = false) MultipartFile image,
                                        @RequestParam (required = false) String  image0,
                                        @RequestParam (required = false) Date birthDate,

                                        @RequestParam (required = false) String address,
                                        @RequestParam (required = false) String phone,
                                        @RequestParam (required = false) String mail,
                                        @RequestParam (required = false) String password,
                                        @RequestParam (required = false) String Department,
                                        @RequestParam (required = false) Date HiringDate,
                                        @RequestParam (required = false) Boolean evaluator,
                                        @RequestParam (required = false) Boolean jury,
                                        @RequestParam (required = false) Boolean speaker,
                                        @RequestParam (required = false) String bacSection,
                                        @RequestParam (required = false) String educationLevel,
                                        @RequestParam (required = false) String speciality,
                                        @RequestParam (required = false) String classe,
                                        @RequestParam (required = false) String role) throws IOException {

        User user=iUserService.getUserbyId(id);
        if(cin!=null)
            user.setCin(cin);
        if(firstName!=null)
            user.setFirstName(firstName);
        if(lastName!=null)
            user.setLastName(lastName);
        if (gender!=null)
            user.setGender(gender);
        if(image!=null)
            user.setImage(image.getBytes());
        if(image0!=null)
            user.setImage0(image0);
        if(birthDate!=null)
            user.setBirthDate(birthDate);
        if(address!=null)
            user.setAddress(address);
        if (phone!=null)
            user.setPhone(phone);
        if (mail!=null)
            user.setMail(mail);
        if(password!=null)
            user.setPassword(password);
        if (Department!=null)
            user.setDepartment(Department);

            user.setHiringDate(HiringDate);
        if(evaluator!=null)
            user.setEvaluator(evaluator);
        if(jury!=null)
            user.setJury(jury);
        if(speaker!=null)
            user.setSpeaker(speaker);
        if (bacSection!=null)
            user.setBacSection(bacSection);
        if(educationLevel!=null)
            user.setEducationLevel(educationLevel);
        if(speciality!=null)
            user.setSpeciality(speciality);

            user.setClasse(classe);
        if(role!=null){
            Role role1=roleRepository.findByRole(role);
            System.out.println(role);
            user.setRole(role1);
        }

        // Check if the current user is authorized to delete the specified user
        if (userService.isCurrentUser(id) || SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return ResponseEntity.ok(iUserService.updateUser(user));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable ("id")int id, Authentication authentication) {
        String username = authentication.getName();
        int userId = ((User)authentication.getPrincipal()).getId();
        System.out.println("User ID: " + userId);
        System.out.println("Username: " + username);
        iUserService.deleteUser(id);
    }
    @GetMapping("/getUserById/{id}")
    public User getUser(@PathVariable("id")int id){
        return iUserService.getUserbyId(id);
    }


    @PutMapping("/updateUserRole/{id}/admin")
    public void updadeUserRole(@RequestParam String role, @PathVariable("id")int id){
        iUserService.updateRole(id,role);
    }

    @GetMapping("/getUserbyCin/{Cin}/admin")
    public User getUserByCin(@PathVariable("Cin")String Cin){

        return iUserService.getUserByCin(Cin);
    }

}
