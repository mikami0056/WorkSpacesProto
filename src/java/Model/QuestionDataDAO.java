/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DB.dbmanager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gest
 */
public class QuestionDataDAO {
    
    public QuestionDataDAO(){}
    
    public static QuestionDataDAO getInstance(){
        return new QuestionDataDAO();
    }
    
    public List<QuestionDataDTO> getQuestions() throws SQLException{
        
        List<QuestionDataDTO> dtoList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        String sql = "SELECT * FROM question_t";
        System.out.println("getQuestion start");
        
        try{
            
            con = dbmanager.getConnection();
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                QuestionDataDTO dto = new QuestionDataDTO();
                dto.setQuestionID(rs.getInt("question_id"));
                dto.setQuestion(rs.getString("question"));
                dtoList.add(dto);
            }
            
        }catch(ClassNotFoundException | SQLException classError){
            
            System.out.println(classError.getMessage());
            classError.printStackTrace();
            
        }finally{
            if(con != null){
                con.close();
            }
        }
        
        return dtoList;
    }
}
