/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author gest
 */
public class QuestionDataDTO {
    private int questionID;
    private String question;
    
    public QuestionDataDTO(){}
    
    public void setQuestionID(int questionID){
        this.questionID = questionID;
    }
    public int getQuestionID(){
        return this.questionID;
    }
    
    public void setQuestion(String question){
        this.question = question;
    }
    public String getQuestion(){
        return this.question;
    }
}
