package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.util.List;

public interface IQuestionService {
    Question createNewQuestion(Question question);
    String getResultQuestion(String reponse,int id);

    void removeQuestionById(int id);

    List<Question> getQuestion();

    Question getQuestionById(int id);
}
