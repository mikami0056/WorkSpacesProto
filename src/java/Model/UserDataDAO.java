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
import java.sql.Timestamp;

/**
 *
 * @author gest
 */
public class UserDataDAO {
    
    private Connection con;
    private PreparedStatement pst;
    
    public UserDataDAO(){
        con = null;
        pst = null;
    }
    
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    public UserDataDTO getUserData(UserDataDTO dto) throws SQLException, ClassNotFoundException{
        
        UserDataDTO accountDTO = null;
        String selectSql = "SELECT user_id, user_name, pass_word, mail, question_id, answer FROM user_t WHERE user_name = ? and pass_word = ?";
        System.out.println("getUserData start");
        
        try{
            
            con = dbmanager.getConnection();
            pst = con.prepareStatement(selectSql);
            pst.setString(1, dto.getUserName());
            pst.setString(2, dto.getPassWord());
            ResultSet rs = pst.executeQuery();
                        
            while(rs.next()){
                accountDTO = new UserDataDTO();
                accountDTO.setUserID(rs.getInt("user_id"));
                accountDTO.setUserName(rs.getString("user_name"));
                accountDTO.setPassWord(rs.getString("pass_word"));
                accountDTO.setMail(rs.getString("mail"));
                accountDTO.setQuestionID(rs.getInt("question_id"));
                accountDTO.setAnswer(rs.getString("answer"));
            }
            
            System.out.println("getUserData completed");
            return accountDTO;
            
        }catch(SQLException sqle){
            System.out.println(sqle.getMessage());
            throw new SQLException(sqle);
            
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /*
    @登録可否をチェックするためのメソッド
    @新規登録時に使用
    @ユーザ入力フォ−ムより受け取ったユーザIDが同一のものがいないか確認する
    */
    public boolean checkUserData(UserDataDTO dto)throws SQLException, ClassNotFoundException{
        
        String checkSql = "SELECT * FROM user_t WHERE user_name = ?";
        System.out.println("[Notice in UserDataDAO.java]\"checkUserData\" has been started[at checkUserData]");
        boolean exist = false;
        
        try{
            
            con = dbmanager.getConnection();
            pst =  con.prepareStatement(checkSql);
            pst.setString(1, dto.getUserName());
            ResultSet rs = pst.executeQuery();
            
            //同じユーザ名とメールアドレスが登録されていれば失敗, falseを返す
            exist = !rs.next();
            if(!rs.next()){
                exist = !rs.next();
                System.out.println("[Notice]name has been existing. Please fill out the form again.");
            }
            
        }catch(ClassNotFoundException | SQLException error){
            System.out.println(error.getMessage());
            error.printStackTrace();
        }finally{
            if(con != null){
                con.close();
            }
        }
        
        return exist;
    }
    
    public void insertUserData(UserDataDTO dto) throws ClassNotFoundException, SQLException{
        
        String insertSql = "INSERT INTO user_t(user_id,user_name,pass_word,mail,question_id,answer,regist_date) VALUES(?,?,?,?,?,?,?)";
        System.out.println("[Notice in UserDataDAO.java]\"insertUserData\" has been started[at checkUserData]");
        
        try{
            
            con = dbmanager.getConnection();
            pst =  con.prepareStatement(insertSql);
            pst.setInt(1, dto.getUserID());
            pst.setString(2, dto.getUserName());
            pst.setString(3, dto.getPassWord());
            pst.setString(4, dto.getMail());
            pst.setInt(5, dto.getQuestionID());
            pst.setString(6, dto.getAnswer());
            pst.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            pst.executeUpdate();
            System.out.println("inserInformation start completed");
            
        }catch(ClassNotFoundException | SQLException error){
            System.out.println(error.getMessage());
            error.printStackTrace();
        }finally{
            if(con != null){
                con.close();
            }
        }
        
    }
    
    public void updateUserData(UserDataDTO dto) throws ClassNotFoundException, SQLException{
        
        String updateSql4User = "UPDATE user_t SET user_name = ?, pass_word = ?, mail = ? WHERE user_id = ?";
        System.out.println("updateUserData start");
        
        try{
            
            con = dbmanager.getConnection();
            pst = con.prepareStatement(updateSql4User);
            pst.setString(1, dto.getUserName());
            pst.setString(2, dto.getPassWord());
            pst.setString(3, dto.getMail());
            pst.setInt(4, dto.getUserID());
            pst.executeUpdate();
            
            System.out.println("updateUserData completed");
            
        }catch(ClassNotFoundException | SQLException classError){
            System.out.println(classError.getMessage());
            classError.printStackTrace();
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public void deleteUserData(UserDataDTO dto) throws ClassNotFoundException, SQLException{
        
        String deleteSql4Evaluation = "DELETE FROM evaluation_t WHERE user_id = ?";
        String deleteSql4Comment = "DELETE FROM comment_t WHERE user_id = ?";
        String deleteSql4Picture = "DELETE FROM picture_t WHERE user_id = ?";
        String deleteSql4User = "DELETE FROM user_t WHERE user_id = ? OR user_name = ?";
        System.out.println("deleteUserData start");
        
        try{
            
            con = dbmanager.getConnection();
            pst = con.prepareStatement(deleteSql4Evaluation);
            pst.setInt(1, dto.getUserID());
            pst.executeUpdate();
            System.out.println("評価テーブルからユーザー情報を削除しました");
            
            pst = con.prepareStatement(deleteSql4Comment);
            pst.setInt(1, dto.getUserID());
            pst.executeUpdate();
            System.out.println("コメントテーブルからユーザー情報を削除しました");
            
            pst = con.prepareStatement(deleteSql4Picture);
            pst.setInt(1,dto.getUserID());
            pst.executeUpdate();
            System.out.println("写真テーブルからユーザー情報を削除しました");
            
            pst = con.prepareStatement(deleteSql4User);
            pst.setInt(1, dto.getUserID());
            pst.setString(2, dto.getUserName());
            pst.executeUpdate();
            System.out.println("ユーザーテーブルからユーザー情報を削除しました");

        }catch(ClassNotFoundException | SQLException classError){
            
            System.out.println(classError.getMessage());
            classError.printStackTrace();
            
        }finally{
            if(con != null){
                con.close();
            }
        }
        
    }
    
}
