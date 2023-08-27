package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.InterviewRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class InterviewService implements IInterviewService{
    @Autowired
    InterviewRepository interviewRepository;
    @Autowired
    MailService mailService;
    @Override
    public List<String> getDays(int k) {
        List<String> days = new ArrayList<>();
        List<Date> dates = getAvailableDates(k);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-d");

        if(dates.size()!=0){
            String formattedDate = formatter.format(dates.get(0));
            days.add(formattedDate);}
        for(int i=1;i< dates.size();i++){
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(dates.get(i));
            int day1=calendar1.get(Calendar.DAY_OF_MONTH);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(dates.get(i-1));
            int day2=calendar2.get(Calendar.DAY_OF_MONTH);
            if(day1!=day2){
                String formattedDate = formatter.format(dates.get(i));
                days.add(formattedDate);
            }
        }
        return days;
    }
    @Override
    public List<String> getHours(String date,int k) throws ParseException {
        List<Date> dates = getAvailableDates(k);
        List<String> hours=new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        int i=0;
        int n=0;
        while (i<dates.size()&&n<6){
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(dates.get(i));
            int day1=calendar1.get(Calendar.DAY_OF_MONTH);

            Calendar calendar2 = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");
            Date date1=dateFormat.parse(date);
            calendar2.setTime(date1);
            int day2=calendar2.get(Calendar.DAY_OF_MONTH);

            if(day1==day2){
                String formattedDate = formatter.format(dates.get(i));
                hours.add(formattedDate);
                n++;}
            i++;
        }
        return hours;
    }
    @Override
    public Interview createNewInterview(String day, String hour,int k) throws ParseException {

        Interview interview=new Interview();

        SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm");
        Date hourDate=hourFormat.parse(hour);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");
        Date dayDate=dateFormat.parse(day);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(dayDate);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(hourDate);

        calendar1.set(Calendar.HOUR_OF_DAY, calendar2.get(Calendar.HOUR_OF_DAY));
        calendar1.set(Calendar.MINUTE, calendar2.get(Calendar.MINUTE));
        calendar1.set(Calendar.SECOND,0);
        calendar1.set(Calendar.MILLISECOND,0);
        interview.setDate(calendar1.getTime());

        Random random = new Random();
        List<Room> rooms=interviewRepository.findAvailableRooms(calendar1.getTime());
        int randomIndex2 = random.nextInt(rooms.size());
        Room room = rooms.get(randomIndex2);
        interview.setRoom(room);

        if(k==1) {
            List<User> juries = interviewRepository.findAvailableJuries(calendar1.getTime());
            int randomIndex1 = random.nextInt(juries.size());
            User jury = juries.get(randomIndex1);
            interview.setUser(jury);
            try {
                mailService.sendEmail(jury.getMail(),"ESPRIT - Interview", "Hi Mr/Mme "+jury.getFirstName()+" "+jury.getLastName()+",\n\nAs you are a member of Juries in Esprit recruitment, you are assigned to an interview at "+interview.getDate()+" in the room "+interview.getRoom().getName()+" in block "+interview.getRoom().getBlock()+".\n\nESPRIT" );
            } catch (Exception e) {
                System.out.println("mail not sent: " + e.getMessage());
            }
        }
        if(k==2) {
            List<User> evaluators = interviewRepository.findAvailableEvaluators(calendar1.getTime());
            int randomIndex1 = random.nextInt(evaluators.size());
            User evaluator = evaluators.get(randomIndex1);
            interview.setUser(evaluator);
            try {
                 mailService.sendEmail(evaluator.getMail(),"ESPRIT - Interview", "Hi Mr/Mme "+evaluator.getFirstName()+" "+evaluator.getLastName()+",\n\nAs you are a member of evaluators in Esprit admission, you are assigned to an interview at "+interview.getDate()+" in the room "+interview.getRoom().getName()+" in block "+interview.getRoom().getBlock()+".\n\nESPRIT" );
            } catch (Exception e) {
                System.out.println("mail not sent: " + e.getMessage());
            }
            }

        return interviewRepository.save(interview);
    }

    @Override
    public void deleteInterviewById(int id) {
            interviewRepository.deleteById(id);
    }

    @Override
    public List<Interview> getInterviewsByUserId(int id){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User userDetails = (User) authentication.getPrincipal();
//        int userId = userDetails.getId();
//        System.out.println("Authenticated user ID: " + userId);
        return (List<Interview>) interviewRepository.findInterviewByUserId(id);
    }
    @Override
    public Interview getInterviewById(int id) {
        return interviewRepository.findById(id).orElse(null);}
    @Override
    public Interview getInterviewByOfferCandidacyId(int id){
        return interviewRepository.getInterviewByOfferCandidacyId(id);}
    @Override
    public Interview getInterviewByAdmissionCandidacyId(int id){return interviewRepository.getInterviewByAdmissionCandidacyId(id);}
    @Override
    public Interview updateInterview(int interview_id,String day, String hour,int k) throws ParseException {
        Interview interview = this.getInterviewById(interview_id);
        User user1 = interview.getUser();
        Room room1 = interview.getRoom();
        Date date1 = interview.getDate();
        SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm");
        Date hourDate=hourFormat.parse(hour);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");
        Date dayDate=dateFormat.parse(day);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(dayDate);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(hourDate);

        calendar1.set(Calendar.HOUR_OF_DAY, calendar2.get(Calendar.HOUR_OF_DAY));
        calendar1.set(Calendar.MINUTE, calendar2.get(Calendar.MINUTE));
        calendar1.set(Calendar.SECOND,0);
        calendar1.set(Calendar.MILLISECOND,0);
        interview.setDate(calendar1.getTime());

        Random random = new Random();
        List<Room> rooms=interviewRepository.findAvailableRooms(calendar1.getTime());
        int randomIndex2 = random.nextInt(rooms.size());
        Room room = rooms.get(randomIndex2);
        interview.setRoom(room);
        if(k==1) {
            List<User> juries = interviewRepository.findAvailableJuries(calendar1.getTime());
            int randomIndex1 = random.nextInt(juries.size());
            User jury = juries.get(randomIndex1);
            interview.setUser(jury);
            if(jury.equals(user1)) {
                try {
                    mailService.sendEmail(jury.getMail(), "ESPRIT - Interview", "Hi Mr/Mme " + jury.getFirstName() + " " + jury.getLastName() + ",\n\nYour interview scheduled in " + date1 + " has been postponed to new date at " + interview.getDate() + " in the room " + interview.getRoom().getName() + " in block " + interview.getRoom().getBlock() + ".\n\nESPRIT");
                } catch (Exception e) {
                    System.out.println("mail not sent: " + e.getMessage());
                }
            }
            else {
                try {
                    mailService.sendEmail(jury.getMail(), "ESPRIT - Interview", "Hi Mr/Mme " + jury.getFirstName() + " " + jury.getLastName() + ",\n\nAs you are a member of Juries in Esprit recruitment, you are assigned to an interview at " + interview.getDate()+ " in the room " + interview.getRoom().getName() + " in block " + interview.getRoom().getBlock() + ".\n\nESPRIT");
                    mailService.sendEmail(user1.getMail(),"ESPRIT - Interview", "Hi Mr/Mme "+user1.getFirstName()+" "+user1.getLastName()+",\n\nYour interview scheduled in "+ date1 + " has been canceled.\n\nESPRIT" );
                } catch (Exception e) {
                    System.out.println("mails not sent: " + e.getMessage());
                }
            }
        }
        if(k==2) {
            List<User> evaluators = interviewRepository.findAvailableEvaluators(calendar1.getTime());
            int randomIndex1 = random.nextInt(evaluators.size());
            User evaluator = evaluators.get(randomIndex1);
            interview.setUser(evaluator);
            if(evaluator.equals(user1)) {
                try {
                    mailService.sendEmail(evaluator.getMail(), "ESPRIT - Interview", "Hi Mr/Mme " + evaluator.getFirstName() + " " + evaluator.getLastName() + ",\n\nYour interview scheduled in " + date1 + " was postponed to new date at " + interview.getDate() + " in the room " + interview.getRoom().getName() + " in block " + interview.getRoom().getBlock() + ".\n\nESPRIT");
            } catch (Exception e) {
                System.out.println("mail not sent: " + e.getMessage());
            }
            }
            else {
                try {
                    mailService.sendEmail(evaluator.getMail(), "ESPRIT - Interview", "Hi Mr/Mme " + evaluator.getFirstName() + " " + evaluator.getLastName() + ",\n\nAs you are a member of Juries in Esprit recruitment, you are assigned to an interview at " + interview.getDate()+ " in the room " + interview.getRoom().getName() + " in block " + interview.getRoom().getBlock() + ".\n\nESPRIT");
                    mailService.sendEmail(user1.getMail(),"ESPRIT - Interview", "Hi Mr/Mme "+user1.getFirstName()+" "+user1.getLastName()+",\n\nYour interview scheduled in "+ date1 + " has been canceled.\n\nESPRIT" );
                } catch (Exception e) {
                    System.out.println("mails not sent: " + e.getMessage());
                }
                }
        }

        return interviewRepository.save(interview);
    }


    public List<Date> getDates(int day) {
        List<Date> dates = new ArrayList<>();
        for(int i=0; i<6; i++){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, day);
            switch (i) {
                case 0:
                    calendar.set(Calendar.HOUR_OF_DAY, 9);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    break;
                case 1:
                    calendar.set(Calendar.HOUR_OF_DAY, 9);
                    calendar.set(Calendar.MINUTE, 30);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    break;
                case 2:
                    calendar.set(Calendar.HOUR_OF_DAY, 10);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    break;
                case 3:
                    calendar.set(Calendar.HOUR_OF_DAY, 10);
                    calendar.set(Calendar.MINUTE, 30);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    break;
                case 4:
                    calendar.set(Calendar.HOUR_OF_DAY, 11);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    break;
                case 5:
                    calendar.set(Calendar.HOUR_OF_DAY, 11);
                    calendar.set(Calendar.MINUTE, 30);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    break;

            }
            dates.add(calendar.getTime());
        }


        return dates;
    }

    public List<Date> getAvailableDates(int k) {
        List<Date> dates = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(getDates(i).get(0));
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                if(k==1){

                    for (int j = 0; j < 6; j++) {
                        if(findAvailableJuries(getDates(i).get(j)).size()>0 && findAvailableRooms(getDates(i).get(j)).size()>0) {
                            dates.add(getDates(i).get(j));
                        }
                    }
                }
                if(k==2){

                    for (int j = 0; j < 6; j++) {
                        if(findAvailableEvaluators(getDates(i).get(j)).size()>0 && findAvailableRooms(getDates(i).get(j)).size()>0) {
                            dates.add(getDates(i).get(j));
                        }
                    }
                }
            }
        }
        return dates;
    }
    public List<User> findAvailableJuries(Date date){return interviewRepository.findAvailableJuries(date);}
    public List<User> findAvailableEvaluators(Date date){return interviewRepository.findAvailableEvaluators(date);}
    public List<Room> findAvailableRooms(Date date){return interviewRepository.findAvailableRooms(date);}

}
