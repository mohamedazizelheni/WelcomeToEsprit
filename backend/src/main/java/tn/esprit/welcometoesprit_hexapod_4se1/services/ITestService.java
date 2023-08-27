package tn.esprit.welcometoesprit_hexapod_4se1.services;

import tn.esprit.welcometoesprit_hexapod_4se1.entities.*;

import java.util.List;

public interface ITestService {
    Test createNewTest(Test test);

    Test getTestById(int id);

    Test addOrUpdate(Test test);


    void removeTestById(int id);



    String getresultTest(int testId, int candidacyId, String rep1, String rep2, String rep3, String rep4);

    List<Test> getTest();
}
