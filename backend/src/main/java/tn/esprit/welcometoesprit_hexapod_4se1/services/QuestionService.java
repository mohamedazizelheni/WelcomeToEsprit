package tn.esprit.welcometoesprit_hexapod_4se1.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.QuestionRepository;

import java.util.List;

@Service
public class QuestionService implements IQuestionService{
    @Autowired
    QuestionRepository questionRepository;
    @Override
    public Question createNewQuestion(Question question){
        return questionRepository.save(question);
    }
    @Override
    public String getResultQuestion(String reponse,int id) {
        Question question=questionRepository.findById(id).get();
        Question question1 = questionRepository.findQuestionByFalse1(reponse);
        Question question2 = questionRepository.findQuestionByFalse2(reponse);
        Question question3 = questionRepository.findQuestionByFalse3(reponse);
        Question question4 = questionRepository.findQuestionByTrue1(reponse);
        if (question1 == question) {
            return "votre reponse incorrecte";
        } else if (question2 == question) {
            return "votre reponse incorrecte";
        } else if (question3 == question) {
            return "votre reponse incorrecte";
        } else if (question4 == question) {
            return "votre reponse correcte";
        } else return "Remplir le question";

    }
    @Override
    public void removeQuestionById(int id) {
        Question question = questionRepository.findById(id).orElse(null);
        if (question.getTests().size() == 0) {
            questionRepository.deleteById(id);
        }
    }
    @Override
    public List<Question> getQuestion(){
        List<Question>questions =questionRepository.findAll();
        return questions;
    }
    @Override
    public Question getQuestionById(int id){
        return questionRepository.findById(id).orElse(null);
    }
}
