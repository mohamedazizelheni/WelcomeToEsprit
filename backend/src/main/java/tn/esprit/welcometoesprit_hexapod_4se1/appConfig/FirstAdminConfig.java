package tn.esprit.welcometoesprit_hexapod_4se1.appConfig;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Role;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.User;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.RoleRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.UserRepository;

import java.util.List;


@Configuration
public class FirstAdminConfig {
    @Bean
    public CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            Role adminRole = roleRepository.findByRole("ADMIN");
            Role preRole = roleRepository.findByRole("PRE");
            List<User> admins = userRepository.findUserByRoleRole("ADMIN");

            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setRole("ADMIN");
                roleRepository.save(adminRole);
                System.out.println("ADMIN role created");
            }

            if (preRole == null) {
                preRole = new Role();
                preRole.setRole("PRE");
                roleRepository.save(preRole);
                System.out.println("PRE role created");
            }

            if(admins==null||admins.size()==0)
            {
                User adminUser = new User();
                adminUser.setMail("admin");
                adminUser.setPassword("admin");
                adminUser.setRole(adminRole);
                userRepository.save(adminUser);
                System.out.println("First adminUser created");

            }
        };
    }

}
