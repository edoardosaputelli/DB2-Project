package it.polimi.db2.ejb;

import it.polimi.db2.Exceptions.AlreadyDoneException;
import it.polimi.db2.Exceptions.BadLanguageException;
import it.polimi.db2.Exceptions.DatabaseFailException;
import it.polimi.db2.Exceptions.NothingThatDateException;
import it.polimi.db2.entities.*;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class QuestionnaireManager {

    @PersistenceContext(unitName = "projectPersistenceUnit")
    private EntityManager em;

    private List<MarketingQuestionEntity> marketingQuestionEntityList;
    private HashMap< StatisticalQuestionEntity, List<StatQuestionAlternativesEntity> >  statisticalQuestionEntityList;

    public QuestionnaireManager () {}

    @PostConstruct
    public void init(){
        //method called right after the EJB is injected to get the list of questions for the day
        try {
            this.marketingQuestionEntityList = retrieveMarketingQuestions();
            this.statisticalQuestionEntityList = retrieveStatisticalQuestions();
        }catch (PersistenceException ex){
            ex.printStackTrace();
        }
    }


    public HashMap< StatisticalQuestionEntity, List<StatQuestionAlternativesEntity> >  getStatisticalQuestionEntityList() {
        return statisticalQuestionEntityList;
    }

    public List<MarketingQuestionEntity> getMarketingQuestionEntityList() {
        return marketingQuestionEntityList;
    }


    //to be used together with UserManager's banUser!
    //utilizes an exception to signal the presence of a forbiddenWord in one of the answers (they're passed all together)
    public void checkForOffensiveWords(HashMap<Integer, String> mapMarketingAnsQuest) throws BadLanguageException, DatabaseFailException {
        //simple for that calls the submethod for eache answer
        for (String s : mapMarketingAnsQuest.values()) {
            if( checkSingleAnswer(s) ){

                throw new BadLanguageException();
            }
        }

    }



    //sub method called by checkForOffensiveWords that checks the passed answer uploading each forbidden words alone
    //returns true if the answer contains the currently uploaded forbiddenWord
    private boolean checkSingleAnswer(String answerText) throws DatabaseFailException {


        int numOfForbidden = 0;
        ForbiddenWordsEntity currentForbidden = new ForbiddenWordsEntity();

        //i count the number of forbidden words
        try {
            long l = (long) em.createQuery("SELECT count(i) from ForbiddenWordsEntity i").getSingleResult();
            numOfForbidden = (int) l;

        }catch (PersistenceException e) {
            e.printStackTrace();

        }


        //i upload them in order and check if the answer contains them one by one

        for(int i=0; i<numOfForbidden; i++){

            try {
                currentForbidden = em.createNamedQuery("ForbiddenWordN", ForbiddenWordsEntity.class)
                        .setParameter(1, i).getSingleResult();

            }catch (PersistenceException e) {
                e.printStackTrace();
                throw new DatabaseFailException();
            }

            if (answerText.contains(currentForbidden.getForbiddenWord())) {
                return true;

            }
        }

        return false;

    }


    //method to check if the user completed/cancelled the questionnaire for today
    public void checkIfAlreadyDone(UserEntity user) throws AlreadyDoneException, DatabaseFailException, NothingThatDateException {

        boolean hasAlreadyDone = false;

        Date today = Date.valueOf(LocalDate.now());

        QuestionnaireEntity todaysQuest;

        //i get today's questionnaire
        try {
            todaysQuest = em.createNamedQuery("QuestionnaireEntity.questOfGivenDay", QuestionnaireEntity.class)
                    .setParameter("givenDay", today).getResultList().get(0);
        }catch(PersistenceException ex){

        ex.printStackTrace();
        throw new DatabaseFailException();

        }catch (Exception ex) {throw new NothingThatDateException();}


        //i check if the user already did/cancelled it
        try{

            hasAlreadyDone = !(em.createNamedQuery("QuestionnaireResponseEntity.alreadyDidIt", QuestionnaireResponseEntity.class)
                    .setParameter(1, user.getIdUser()).setParameter(2, todaysQuest).getResultList().isEmpty());


        }catch(PersistenceException ex){
            ex.printStackTrace();
            throw new DatabaseFailException();

        }


        //if yes i throw the associated exception
        if(hasAlreadyDone){

            throw new AlreadyDoneException();

        }

    }


    //retrieves today's marketing questions
    public List<MarketingQuestionEntity> retrieveMarketingQuestions(){

        try{



            Date currentDate = Date.valueOf(LocalDate.now());
            ProductEntity product = em.createNamedQuery("ProductEntity.getProductOfGivenDay", ProductEntity.class)
                    .setParameter("givenDate", currentDate , TemporalType.DATE).getSingleResult();

            List<MarketingQuestionEntity> listMQ = product.getQuestionnaire().getmList();



            return listMQ;


        }catch (PersistenceException ex){

            ex.printStackTrace();
        }


        return null;


    }

    //retrieves today's statistical questions and theire alternatives
    public HashMap< StatisticalQuestionEntity, List<StatQuestionAlternativesEntity> > retrieveStatisticalQuestions(){

        try{

            HashMap<StatisticalQuestionEntity, List<StatQuestionAlternativesEntity> > mapStatQuestAlternatives = new HashMap<>();

            List<StatisticalQuestionEntity> listOfQuestions = em.createQuery("SELECT r FROM StatisticalQuestionEntity r")
                    .getResultList();


            for(StatisticalQuestionEntity sqe : listOfQuestions){


              List<StatQuestionAlternativesEntity>  listOfAlt = em.createQuery("SELECT u FROM StatQuestionAlternativesEntity u WHERE u.statisticalQuestion.idStatisticalQuestion = ?1")
                        .setParameter(1, sqe.getIdStatisticalQuestion()).getResultList();


              mapStatQuestAlternatives.put(sqe, listOfAlt);


            }

            return mapStatQuestAlternatives;


        }catch (PersistenceException ex){

            ex.printStackTrace();
        }


        return null;

    }


    //when a user submits its questionnaire this method is called to save the asnwers and the QuestionnaireResponse in the db
    public void persistQuestionnaireAnswers( List<MarketingAnswerEntity> mAnsList, List<StatisticalAnswerEntity> sAnsList, UserEntity user) throws DatabaseFailException {

        //i create the qResponse
        QuestionnaireEntity todaysQuestionnaire = mAnsList.get(0).getmQuestion().getQuestionnaire();
        QuestionnaireResponseEntity completedTheQuestionnaire = new QuestionnaireResponseEntity(todaysQuestionnaire, user, false);

        //and then persist everything
        try {

            for(MarketingAnswerEntity answer: mAnsList) {
                em.persist(answer);

            }

            for (StatisticalAnswerEntity answer: sAnsList) {
                em.persist(answer);
            }

            em.persist(completedTheQuestionnaire);
            em.flush();

        }catch(PersistenceException e) {
            e.printStackTrace();
            throw new DatabaseFailException();
        }

    }



    //when he uses offensive words the flag for banned is set to one
    public void banUser (UserEntity user) {

        try {
            user= em.find(UserEntity.class, user.getIdUser());
            user.setFlagStatus(new Byte("00000001"));
            em.flush();

        }catch(PersistenceException e) {
            e.printStackTrace();
        }
    }

    //method to be called when the user cancels the questionnaire
    public void cancelQuestionnaire(QuestionnaireEntity todaysQuestionnaire, UserEntity user) throws DatabaseFailException {


        //i simply generate a qResponse with cancelled equal to 1 and persist it
        QuestionnaireResponseEntity cancelledTheQuestionnaire = new QuestionnaireResponseEntity(todaysQuestionnaire, user, true);

        try {

            em.persist(cancelledTheQuestionnaire);
            em.flush();

        }catch(PersistenceException ex){

            ex.printStackTrace();
            throw new DatabaseFailException();
        }







    }

    //method to set session attributes to null when the user completes the answering of the questionnaire
    public void setSessionMapsNull(HttpServletRequest request) {

        request.getSession().setAttribute("mapStatAnsQuest", null);
        request.getSession().setAttribute( "mapMarketingAnsQuest", null);

    }





}
