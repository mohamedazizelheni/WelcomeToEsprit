package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.AdmissionCandidacyRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.QuestionRepository;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.TestRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class TestService implements ITestService {

    @Autowired
    TestRepository testRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AdmissionCandidacyRepository admissionCandidacyRepository;
    @Autowired
    AdmissionCandidacyService admissionCandidacyService;

    QuestionService questionService;

    @Override
    public Test createNewTest(Test test) {
        List<Question> questions = questionRepository.findAllByType(test.getType());
        test.setQuestions(new ArrayList<Question>());
        if (questions.size() > 3) {
            Random random = new Random();
            int i, n = 0;
            boolean found = false;
            while (n < 4) {
                i = random.nextInt(questions.size());
                int j = 0;
                while (j < test.getQuestions().size() && !found) {
                    if (questions.get(i) == test.getQuestions().get(j)) {
                        found = true;
                    }
                    j++;
                }
                if (!found) {
                    test.getQuestions().add(questions.get(i));
                    n++;
                }
                found = false;
            }
            return testRepository.save(test);
        }
        return test;
    }

    @Override
    public Test getTestById(int id) {
        return testRepository.findById(id).get();
    }

    @Override
    public Test addOrUpdate(Test test) {
        return testRepository.save(test);

    }

    @Override
    @Transactional
    public void removeTestById(int id) {

        Test test = testRepository.findById(id).orElse(null);
        if (test.getAdmissionCandidacies().size() == 0) {
            testRepository.deleteById(id);

        }
     /*   if(test.getAdmissionCandidacies().{
            testRepository.deleteById(id);
        }*/


    }


    @Override
    public String getresultTest(int testId, int candidacyId, String rep1, String rep2, String rep3, String rep4) {
        AtomicReference<Float> resultFinal = new AtomicReference<>((float) 0);
        Test test = testRepository.findById(testId).get();
        List<Question> questions = test.getQuestions();
//        List<Integer> idQuests = test.getQuestions().stream().map(Question::getId).collect(Collectors.toList());

        AdmissionCandidacy candidacy = admissionCandidacyRepository.findById(candidacyId).get();

//        questions.forEach(question -> {
//            int idQuess = question.getId();
//            Question question1 = questionRepository.findById(idQuess).get();
//            System.out.println(question.getId());
//            if (question.getTrue1().equals(rep1) || question1.getTrue1().equals(rep2) || question1.getTrue1().equals(rep3) || question1.getTrue1().equals(rep4)) {
//                resultFinal.updateAndGet(v -> v + 1);
//            }
//        });
        if(questions.get(0).getTrue1().equals(rep1))
            resultFinal.updateAndGet(v -> v + 1);
        if(questions.get(1).getTrue1().equals(rep2))
            resultFinal.updateAndGet(v -> v + 1);
        if(questions.get(2).getTrue1().equals(rep3))
            resultFinal.updateAndGet(v -> v + 1);
        if(questions.get(3).getTrue1().equals(rep4))
            resultFinal.updateAndGet(v -> v + 1);

        float score = resultFinal.get() * 5;
        admissionCandidacyService.updateTestScore(candidacy.getId(), testId, score);

        candidacy.setTestScore(score);
        admissionCandidacyRepository.save(candidacy);

        return String.valueOf(score);
    }

    @Override
    public List<Test> getTest() {
        List<Test> tests = testRepository.findAll();
        return tests;
    }
}








