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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gest
 */
public class CommentDataDAO {
    public CommentDataDAO(){}
    
    public static CommentDataDAO getInstance(){
        return new CommentDataDAO();
    }
    
    public void insertCommentData(CommentDataDTO dto) throws SQLException{
        
        Connection con = null;
        PreparedStatement pst = null;
        String insertSql = "INSERT INTO comment_t VALUES(?,?,?,?,?)";
        System.out.println("insertCommenttData start");
        
        try{
            
            con = dbmanager.getConnection();
            pst = con.prepareStatement(insertSql);
            pst.setInt(1, dto.getCommentID());
            pst.setString(2, dto.getComment());
            pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pst.setInt(4, dto.getPictureID());
            pst.setString(5, dto.getUserName());
            pst.executeUpdate();
            
            System.out.println("insertCommentData completed");
            
        }catch(ClassNotFoundException cnfeError){
            
            System.out.println("エラーが発生しました / " + cnfeError.getMessage());
            cnfeError.printStackTrace();
            
        }catch(SQLException sqlError){
            
            System.out.println("エラーが発生しました / " + sqlError.getMessage());
            sqlError.printStackTrace();
            
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public List<CommentDataBeans> getCommentData(int pictureID) throws SQLException{
        
        Connection con = null;
        PreparedStatement pst = null;
        String selectSql = "SELECT * FROM comment_t WHERE picture_id = ? LIMIT 10";
        List<CommentDataBeans> commentList = new ArrayList<CommentDataBeans>();
        System.out.println("getCommenttData start");
        
        try{
            
            con = dbmanager.getConnection();
            pst = con.prepareStatement(selectSql);
            pst.setInt(1, pictureID);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                CommentDataDTO dto = new CommentDataDTO();
                dto.setCommentID(rs.getInt("comment_id"));
                dto.setComment(rs.getString("comment"));
                dto.setCommentDate(rs.getDate("comment_date"));
                dto.setPictureID(rs.getInt("picture_id"));
                dto.setUserName(rs.getString("user_name"));
                
                CommentDataBeans cdb = new CommentDataBeans();
                cdb.CDD2CDBMapping(dto);
                commentList.add(cdb);
                
            }
            
            System.out.println("insertCommentData completed");
            
        }catch(ClassNotFoundException cnfeError){
            
            System.out.println("エラーが発生しました / " + cnfeError.getMessage());
            cnfeError.printStackTrace();
            
        }catch(SQLException sqlError){
            
            System.out.println("エラーが発生しました / " + sqlError.getMessage());
            sqlError.printStackTrace();
            
        }finally{
            if(con != null){
                con.close();
            }
        }
        return commentList;
    }
}
