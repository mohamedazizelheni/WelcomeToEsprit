package tn.esprit.welcometoesprit_hexapod_4se1.appConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import tn.esprit.welcometoesprit_hexapod_4se1.security.JWTAuthenticationFilter;
import tn.esprit.welcometoesprit_hexapod_4se1.security.JWTAuthorizationFilter;
import tn.esprit.welcometoesprit_hexapod_4se1.security.JWTUtils;
import tn.esprit.welcometoesprit_hexapod_4se1.services.AchievementService;
import tn.esprit.welcometoesprit_hexapod_4se1.services.UserService;

import javax.json.Json;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private AchievementService achievementService;
    @Autowired
    private JWTUtils jwtUtils;



    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(jwtUtils, userService);
        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                //all modules

                .antMatchers("/**/admin", "/api/role/*", "/api/room/*","api/event/*","api/invitation/*,/api/test/*,/api/question/*,/api/socity/*").hasAuthority("ADMIN")
                //user module
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/api/user/addUser","/api/user/login","/api/faq/allfaq","/api/ads/allads","/getads/{id}","/api/faq/allfaq","/api/faq/getfaq/{id}","/api/faq/getupfaq/{idFaq}","/api/faq/getallsubject").permitAll()
                .antMatchers("/api/user/deleteUser/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(#id)")
                .antMatchers("/api/user/getUserById/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(#id)   or @userService.isJuryOrEvaluator()")

                .antMatchers("/api/user/getUserbyMail/{Mail}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(@userService.getUserByMail(#Mail).id)")
                //***recruitment module***
                //interview
                .antMatchers("/api/interview/getInterviewsByUserId/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(#id)")
                .antMatchers("/api/interview/getInterviewById/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(@interviewService.getInterviewById(#id).user.id) or (@interviewService.getInterviewById(#id).offerCandidacy != null and @userService.isCurrentUser(@interviewService.getInterviewById(#id).offerCandidacy.user.id)) or (@interviewService.getInterviewById(#id).admissionCandidacy != null and @userService.isCurrentUser(@interviewService.getInterviewById(#id).admissionCandidacy.user.id))")
                .antMatchers("/api/interview/getInterviewByAdmissionCandidacyId/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(@admissionCandidacyService.getAdmissionCandidacyById(#id).user.id) or @userService.isCurrentUser(@interviewService.getInterviewByAdmissionCandidacyId(#id).user.id)")
                .antMatchers("/api/interview/getInterviewByOfferCandidacyId/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(@offerCandidacyService.getOfferCandidacyById(#id).user.id)  or @userService.isCurrentUser(@interviewService.getInterviewByOfferCandidacyId(#id).user.id)")
                //complain
                .antMatchers("/api/complain/createNewComplain/{interview_id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(@interviewService.getInterviewById(#interview_id).admissionCandidacy?.user?.id ?: @interviewService.getInterviewById(#interview_id).offerCandidacy.user.id)")
                .antMatchers("/api/complain/getComplainById/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(@complainService.getComplainById(#id).user.id)")
                .antMatchers("/api/complain/getComplainsByIdComplainer/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(#id)")
                .antMatchers("/api/complain/deleteComplain/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(@complainService.getComplainById(#id).user.id)")
                //offerCandidacy
                .antMatchers("/api/offerCandidacy/createNewOfferCandidacy").hasAnyAuthority("PRE", "STUDENT")
                .antMatchers("/api/offerCandidacy/getOfferCandidaciesByOfferId/{id}", "/api/offerCandidacy/deleteOfferCandidacies/{id_offer}").hasAuthority("ADMIN")
                .antMatchers("/api/offerCandidacy/getOfferCandidacyById/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(@offerCandidacyService.getOfferCandidacyById(#id).user.id)  or @userService.isCurrentUser(@interviewService.getInterviewByOfferCandidacyId(#id).user.id)")
                .antMatchers("/api/offerCandidacy/getOfferCandidaciesByOfferIdAndUserId/{offerId}/{userId}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(#userId)")

                .antMatchers("/api/offerCandidacy/getOfferCandidaciesByIdCandidate/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(#id)")
                .antMatchers("/api/offerCandidacy/updateOfferCandidacy/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(@offerCandidacyService.getOfferCandidacyById(#id).user.id)or @userService.isCurrentUser(@interviewService.getInterviewByOfferCandidacyId(#id).user.id)")
                .antMatchers("/api/offerCandidacy/deleteOfferCandidacyById/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(@offerCandidacyService.getOfferCandidacyById(#id).user.id)")
                .antMatchers("/api/offerCandidacy/updateOfferCandidacyStatus").hasAnyAuthority("ADMIN")

                //Achievement
                .antMatchers("/api/achievement/archived").access("hasAuthority('ADMIN')")
                .antMatchers("/api/achievement/update/{id}")
                .access("hasAuthority('ADMIN')")
                .antMatchers("/api/achievement/getAchievementArchived").access("hasAuthority('ADMIN')")
                .antMatchers("/api/achievement/getAllAchievement").access("hasAuthority('ADMIN')")
                .antMatchers("/api/achievement/getAchievement").access("hasAuthority('ADMIN')")

                //***admission module***
                //admissionCandidacy
                .antMatchers("/api/admissionCandidacy/updateAdmissionCandidacy/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(@admissionCandidacyService.getAdmissionCandidacyById(#id).user.id)")
                .antMatchers("/api/admissionCandidacy/createNewAdmissionCandidacy/{id}").hasAnyAuthority("PRE","STUDENT")
                .antMatchers("/api/admissionCandidacy/getAdmissionCandidacyById/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(@admissionCandidacyService.getAdmissionCandidacyById(#id).user.id) or @userService.isCurrentUser(@interviewService.getInterviewByAdmissionCandidacyId(#id).user.id)")
                .antMatchers("/api/admissionCandidacy/average-test-score").access("hasAuthority('ADMIN')")
                .antMatchers("/api/admissionCandidacy/speciality-test-average").access("hasAuthority('ADMIN')")
                .antMatchers("/api/admissionCandidacy/delete-candidature/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(@admissionCandidacyService.getAdmissionCandidacyById(#id).user.id)")
                .antMatchers("/api/admissionCandidacy/getAdmissionCandidacyByIdCandidate/{id}").access("hasAuthority('ADMIN') or @userService.isCurrentUser(#id)")
                .antMatchers("/api/offerCandidacy/updateAdmissionCandidacyStatus").hasAnyAuthority("ADMIN")

                //test

                //question
                //****FAQmodule ***
                .antMatchers("/api/faq/add").access("hasAuthority('ADMIN')")
                .antMatchers("/api/faq/update/{id}").access("hasAuthority('ADMIN')")
                .antMatchers("/api/faq/delete/{id}").access("hasAuthority('ADMIN')")
                .antMatchers("/api/faq/print/{name}").access("hasAuthority('ADMIN')")
                .antMatchers("/api/faq/getallsubject").access("hasAuthority('ADMIN')")
                //****FAQ module***
                .antMatchers("/api/ads/add").access("hasAuthority('ADMIN')")
                .antMatchers("/api/ads/update/{idads}").access("hasAuthority('ADMIN')")
                .antMatchers("/api/ads/{id}").access("hasAuthority('ADMIN')")
                .antMatchers("/api/ads/statmonth/{mois}").access("hasAuthority('ADMIN')")









                .anyRequest().authenticated()
                .and()
//                .addFilterAfter(new JWTAuthenticationFilter(authenticationManager(), jwtUtils), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
