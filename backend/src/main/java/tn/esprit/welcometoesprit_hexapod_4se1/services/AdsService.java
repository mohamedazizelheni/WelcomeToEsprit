package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.AdsRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.SocityRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.UserRepository;


import javax.persistence.EntityNotFoundException;
import javax.xml.crypto.Data;
import java.io.Console;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AdsService {
    @Autowired
    AdsRepository adsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SocityRepository  socityRepository;
  /*  public void ajouterads(Ads ads, int id) {
        Date curent;
        User U = userRepository.findById(id).get();
        ads.setUser(U);
     curent = new Date(System.currentTimeMillis());
        ads.setAdddate(curent);
        ads.setModifiedDate(curent);
        adsRepository.save(ads);
    }*/

    public void modifierads(Ads ads, int id) {
        Date curent ;
        curent = new Date(System.currentTimeMillis());
        Ads existingAds = adsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ads not found with id " + id));
        existingAds.setCost(ads.getCost());
        existingAds.setContent(ads.getContent());
        existingAds.setCategorieads(ads.getCategorieads());
        existingAds.setFinalNbViews(ads.getFinalNbViews());
        existingAds.setStartDate(ads.getStartDate());
        existingAds.setEndDate(ads.getEndDate());
        existingAds.setRequestedNbViews(ads.getRequestedNbViews());
        existingAds.setName(ads.getName());

        existingAds.setTypeads(ads.getTypeads());

        existingAds.setModifiedDate(curent);
        adsRepository.save(existingAds);
    }

    public void supprimerads(int id) {
        adsRepository.deleteById(id);
    }
    public Ads getAd(int id){
      return adsRepository.findById(id).get();
    }

    public List<Ads> recupererlistads() {
        return (List<Ads>) adsRepository.findAll();
    }

    public void ajouteradsswithpromo(Ads ads, Principal principal  ) {
      //  Socity S = socityRepository.findById(idsocity).get();
      //  ads.setSocityy(S);
      //  ads.setSocity(S.getName());
        int nb = 0;
        float prix = 0;
        User user = userRepository.findUserByMail(principal.getName()).orElse(null);
        Date curent;
        ads.setUser(user);
        curent = new Date(System.currentTimeMillis());
        ads.setModifiedDate(curent);
        ads.setAdddate(curent);

     /*   for (Ads A : adsRepository.findAll()) {
            if ( ads.getSocityy().getId()==A.getSocityy().getId()){
                nb = nb + 1;
                if (nb %3 == 0) {
                    prix = ads.getCost();
                    prix = (prix * 50) / 100;
                    ads.setCost(prix);      }
            }*/
            adsRepository.save(ads);
        }





    public float getstatadswithonemonth(int mois) {
        int nb = 0;
        for (Ads A : adsRepository.findAll()) {
            nb = nb + 1;
        }
        float totale = 0;
        for (Ads A : adsRepository.findAll()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(A.getAdddate());
            int month = calendar.get(Calendar.MONTH);
            if (month + 1 == mois) {
                totale += 1;
            }
        }
        return (totale / nb) * 100;
    }
   /* public Map<String, Float> getADSstatwithallmonth() {

        Map<String, Float> Adscounts = new HashMap<>();

        for (Ads  ads : adsRepository.findAll()) {
            long count = adsRepository.countAdsByAdddate_Month();
            Adscounts .put(category, (float) (count*100)/x);
        }

        return Adscounts;
    }
  /*  public Map<String,List<Map<String,Float>>> STATDECHAQUECATGBYMOTH(){
        for(Ads A : adsRepository.findAll()){

        }



    }*/
   /* public Map<String, Float> getstatadsall() {
        Categorieads[] values = Categorieads.values();
        int x = values.length;
        Map<String, Float> adsCounts = new HashMap<>();

        for (Categorieads categorieads : Categorieads.values()) {
            String category = categorieads.name();
            long count = AdsRepository.countAdsByCategorieads(categorieads);
            postCounts.put(category, (float) (count*100)/x);
        }

        return postCounts;
    }*/
   @Scheduled(fixedRate = 10000)
   public void test(){
       List<Ads> adsList = (List<Ads>) adsRepository.findAll();
       if(!adsList.isEmpty()){
           for(Ads ads :adsList){
               Calendar calendar = Calendar.getInstance();
               Calendar calendarend = Calendar.getInstance();
               calendar.setTime(new java.sql.Date(System.currentTimeMillis()));
               calendarend.setTime(ads.getEndDate());
               int dayend = calendarend.get(Calendar.DAY_OF_MONTH) ;
               int yearend = calendarend.get(Calendar.YEAR);
               int monthend = calendarend.get(Calendar.MONTH)+1;
               /***************************************/
               int day = calendar.get(Calendar.DAY_OF_MONTH) ;
               int year = calendar.get(Calendar.YEAR);
               int month = calendar.get(Calendar.MONTH)+1;
               if(ads.getRequestedNbViews()>ads.getFinalNbViews() && day>=dayend && year>=yearend && month>=monthend){
                   Calendar cal = Calendar.getInstance();
                   cal=null;
                   cal = Calendar.getInstance();
                   cal.setTime(ads.getEndDate());
                   cal.add(Calendar.DAY_OF_YEAR,6);
                   Date newDate = cal.getTime();
                   ads.setEndDate(newDate);
                   System.out.println(newDate);
                   adsRepository.save(ads);
                   System.out.println("correct");
               }
           }
       }
   }

public void increNBvu( int idADS){
    Ads ads = adsRepository.findById(idADS).orElse(null);
    Date curent ;
    curent = new Date(System.currentTimeMillis());
    Ads existingAds = adsRepository.findById(idADS).orElseThrow(() -> new EntityNotFoundException("Ads not found with id " + idADS));
    existingAds.setCost(ads.getCost());
    existingAds.setContent(ads.getContent());
    existingAds.setCategorieads(ads.getCategorieads());
    existingAds.setFinalNbViews(ads.getFinalNbViews()+1);
    existingAds.setStartDate(ads.getStartDate());
    existingAds.setEndDate(ads.getEndDate());
    existingAds.setRequestedNbViews(ads.getRequestedNbViews());
    existingAds.setName(ads.getName());

    existingAds.setTypeads(ads.getTypeads());

    existingAds.setModifiedDate(curent);
    adsRepository.save(existingAds);


}


}








