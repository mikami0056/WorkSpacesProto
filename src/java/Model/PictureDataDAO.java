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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gest
 */
public class PictureDataDAO {
    public PictureDataDAO(){}
    
    public static PictureDataDAO getInstance(){
        return new PictureDataDAO();
    }
    
    /*
    @投稿された写真情報を格納するメソッッド.
    @主に写真投稿画面で使用する.
    */
    public void setPictureData(PictureDataDTO dto) throws ClassNotFoundException, SQLException{
        
        Connection con = null;
        PreparedStatement pst = null;
        String selectSql = "INSERT INTO picture_t VALUES(?,?,?,?,?,?,?,?,?)";
        System.out.println("setPictueData start");
        
        try{
            
            con = dbmanager.getConnection();
            pst = con.prepareStatement(selectSql);
            pst.setInt(1, dto.getPictureID());
            pst.setString(2, dto.getPictureName());
            pst.setString(3, dto.getPicturePath());
            pst.setString(4, dto.getOwnerComment());
            pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pst.setInt(6, dto.getCategoryID());
            pst.setInt(7, dto.getUserID());
            pst.setString(8, dto.getUserName());
            pst.setInt(9, dto.getSum());
            
            pst.executeUpdate();

            System.out.println("setPictureData completed");

        }catch(SQLException sqlError){
            
            System.out.println(sqlError.getMessage());
            throw new SQLException(sqlError);
            
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /*
    @ユーザーIDをもとに写真情報を取得するメソッド.
    @主にマイページでの写真管理画面で使用する.
    */
    public List<PictureDataDTO> getPictureDataByUserID(PictureDataDTO dto) throws SQLException{
        
        List<PictureDataDTO> pdtoList = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement pst = null;
        System.out.println(dto.getUserID());
        String selectSql = "SELECT * FROM picture_t WHERE user_id = ?";
        System.out.println("getPictueData start");
        
        try{
            
            con = dbmanager.getConnection();
            pst = con.prepareStatement(selectSql);
            pst.setInt(1, dto.getUserID());
            ResultSet rs = pst.executeQuery();//nullを返さないので注意
            
            while(rs.next()){
                System.out.println("写真情報をセットします.");
                PictureDataDTO pdto = new PictureDataDTO();
                pdto.setPictureID(rs.getInt("picture_id"));
                pdto.setPictureName(rs.getString("picture_name"));
                pdto.setPicturePath(rs.getString("picture_path"));
                pdto.setOwnerComment(rs.getString("user_comment"));
                pdto.setSubmitDate(rs.getDate("submit_date"));
                pdto.setCategoryID(rs.getInt("category_id"));
                pdto.setUserID(rs.getInt("user_id"));
                pdto.setUserName(rs.getString("user_name"));
                pdtoList.add(pdto);
                
            }

            System.out.println("getPictureData completed");
            
            
        }catch(SQLException sqle){
            System.out.println(sqle.getMessage());
            throw new SQLException(sqle);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PictureDataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(con != null){
                con.close();
            }
        }
        return pdtoList;
    }
    
    public Map<Integer, PictureDataDTO> getPictureDataByRank() throws SQLException, ClassNotFoundException{
        
        Connection con = null;
        PreparedStatement pst = null;
        Map<Integer, PictureDataDTO> pdtoMap = new HashMap<Integer, PictureDataDTO>();
        String selectSqlFromPicture = "SELECT * FROM picture_t ORDER BY sum LIMIT 10";
        System.out.println("getPictureDataByEval start");
        
        try{
            
            con = dbmanager.getConnection();
            pst = con.prepareStatement(selectSqlFromPicture);
            ResultSet rs = pst.executeQuery();//nullを返さないので注意
            
            while(rs.next()){
                
                //取得した写真情報をDTOインスタンスに格納
                PictureDataDTO dto = new PictureDataDTO();
                dto.setPictureID(rs.getInt("picture_id"));
                dto.setPictureName(rs.getString("picture_name"));
                dto.setPicturePath(rs.getString("picture_path"));
                dto.setOwnerComment(rs.getString("user_comment"));
                dto.setSubmitDate(rs.getDate("submit_date"));
                dto.setUserID(rs.getInt("user_id"));
                dto.setUserName(rs.getString("user_name"));
                dto.setCategoryID(rs.getInt("category_id"));
               
                pdtoMap.put(dto.getPictureID(), dto);
                
            }
            
        }catch(ClassNotFoundException cnfError){ 
            
            System.out.println(cnfError.getMessage());
            cnfError.printStackTrace();
            throw new ClassNotFoundException();
            
        } catch(SQLException sqlError){
            
            System.out.println(sqlError.getMessage());
            sqlError.printStackTrace();
            throw new SQLException();
            
        }finally{
            if(con != null){
                con.close();
            }
        }
        
        return pdtoMap;
    }
    
    public Map<Integer, PictureDataDTO> getPictureDataBySearch(String method) throws ClassNotFoundException, SQLException{
        
        String mainSearch = "";
        String subSearch = "";
        String mainTable = "";
        String subTable = "";
        String sql = "";
        switch(method){
            
            case "Rank":
            mainSearch = "sum";
            mainTable = "evaluation_t";
            subSearch = "picture_id";
            subTable = "picture_t";
            sql = "SELECT * FROM " + subTable + " WHERE " + subSearch + " IN ( SELECT " + subSearch + " FROM " + mainTable +") ORDER BY " + mainSearch + " DESC LIMIT 3 ";
            break;
            
            case "Date":
            mainSearch = "submit_date";
            mainTable = "picture_t";
            sql = "SELECT * FROM " + mainTable + " ORDER BY " + mainSearch + " DESC LIMIT 3";
            break;
            
        }
        
        Connection con = null;
        PreparedStatement pst = null;
        Map<Integer, PictureDataDTO> pdtoMap = new LinkedHashMap<>();
        //String selectSqlFromPicture = "SELECT * FROM picture_t ORDER BY " + mainSearch + " DESC LIMIT 3";
        System.out.println("getPictureDataBySearch start");
        
        try{
            
            con = dbmanager.getConnection();
            //pst = con.prepareStatement(selectSqlFromPicture);
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();//nullを返さないので注意
            
            while(rs.next()){
                
                //取得した写真情報をDTOインスタンスに格納
                PictureDataDTO dto = new PictureDataDTO();
                dto.setPictureID(rs.getInt("picture_id"));
                dto.setPictureName(rs.getString("picture_name"));
                dto.setPicturePath(rs.getString("picture_path"));
                dto.setOwnerComment(rs.getString("user_comment"));
                dto.setSubmitDate(rs.getDate("submit_date"));
                dto.setUserID(rs.getInt("user_id"));
                dto.setUserName(rs.getString("user_name"));
                dto.setCategoryID(rs.getInt("category_id"));
                dto.setSum(rs.getInt("sum"));
                
                pdtoMap.put(dto.getPictureID(), dto);
                
            }
            
        }catch(ClassNotFoundException cnfError){ 
            
            System.out.println(cnfError.getMessage());
            cnfError.printStackTrace();
            throw new ClassNotFoundException();
            
        } catch(SQLException sqlError){
            
            System.out.println(sqlError.getMessage());
            sqlError.printStackTrace();
            throw new SQLException();
            
        }finally{
            if(con != null){
                con.close();
            }
        }
        return pdtoMap;
        
    }
    
    public Map<Integer, PictureDataDTO> getPictureDataByNewComment() throws ClassNotFoundException, SQLException{
        
        Connection con = null;
        PreparedStatement pst = null;
        Map<Integer, PictureDataDTO> pdtoMap = new HashMap<Integer, PictureDataDTO>();
        String selectSqlFromComment = "SELECT * FROM comment_t ORDER BY comment_date LIMIT 10";
        String selectSqlFromPicture = "SELECT * FROM user_t WHERE user_id = ?";
        System.out.println("getPictureDataByNewComment start");
        
        try{
            
            con = dbmanager.getConnection();
            pst = con.prepareStatement(selectSqlFromComment);
            ResultSet rsMain = pst.executeQuery();
            
            while(rsMain.next()){
                int userID = rsMain.getInt("user_id");
                pst = con.prepareStatement(selectSqlFromPicture);
                pst.setInt(1, userID);
                ResultSet rsSub = pst.executeQuery();
                
                while(rsSub.next()){
                    
                    //取得した写真情報をDTOインスタンスに格納
                    PictureDataDTO dto = new PictureDataDTO();
                    dto.setPictureID(rsSub.getInt("picture_id"));
                    dto.setPictureName(rsSub.getString("picture_name"));
                    dto.setPicturePath(rsSub.getString("picture_path"));
                    dto.setOwnerComment(rsSub.getString("user_comment"));
                    dto.setSubmitDate(rsSub.getDate("submit_date"));
                    dto.setUserID(rsSub.getInt("user_id"));
                    dto.setUserName(rsSub.getString("user_name"));
                    dto.setCategoryID(rsSub.getInt("category_id"));
                    
                    pdtoMap.put(dto.getPictureID(), dto);
                
                }
            }
            
        }catch(ClassNotFoundException cnfError){ 
            
            System.out.println(cnfError.getMessage());
            cnfError.printStackTrace();
            throw new ClassNotFoundException();
            
        } catch(SQLException sqlError){
            
            System.out.println(sqlError.getMessage());
            sqlError.printStackTrace();
            throw new SQLException();
            
        }finally{
            if(con != null){
                con.close();
            }
        }
        return pdtoMap;
    }
    
    /*
    public List<PictureDataDTO> getPictureDataByEval(String eval) throws SQLException{
        
        Connection con = null;
        PreparedStatement pst = null;
        List<PictureDataDTO> pdtoList = new ArrayList<>();
        String selectSql = "SELECT * FROM picture_t ORDER BY submit_date LIMIT 10";
        System.out.println("getPictureDataByEval start");
        
        try{
            
            con = dbmanager.getConnection();
            pst = con.prepareStatement(selectSql);
            pst.setString(1, eval);
            ResultSet rs = pst.executeQuery();//nullを返さないので注意
            
            while(rs.next()){
                
                PictureDataDTO pdto = new PictureDataDTO();
                pdto.setPictureID(rs.getInt("pictureID"));
                pdto.setPictureName(rs.getString("pictureName"));
                pdto.setPicturePath(rs.getString("picturePath"));
                pdto.setOwnerComment(rs.getString("ownerComment"));
                pdto.setSubmitDate(rs.getDate("submitDate"));
                pdto.setCategoryID(rs.getInt("categoryID"));
                pdto.setUserID(rs.getInt("userID"));
                pdto.setUserName(rs.getString("userName"));
                pdtoList.add(pdto);
                
            }

            System.out.println("getPictureDataByEval completed");
            
        }catch(SQLException sqle){
            System.out.println(sqle.getMessage());
            throw new SQLException(sqle);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PictureDataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(con != null){
                con.close();
            }
        }
        return pdtoList;
    }
    
    /*
    @更新された写真情報を格納するメソッド
    @
    */
    public void updatePictureData(Object dtoObj) throws SQLException, ClassNotFoundException{
        
        Connection con = null;
        PreparedStatement pst = null;
        
        if(dtoObj instanceof PictureDataDTO){
            
            PictureDataDTO dto = (PictureDataDTO)dtoObj;
            String updateSql = "UPDATE picture_t SET picture_name = ?, picture_path = ?, user_comment = ?, category_id = ? WHERE user_id = ?";
            System.out.println("updatePictureData start");
        
        try{
            
            con = dbmanager.getConnection();
            pst = con.prepareStatement(updateSql);
            pst.setString(1, dto.getPictureName());
            pst.setString(2, dto.getPicturePath());
            pst.setString(3, dto.getOwnerComment());
            pst.setInt(4, dto.getCategoryID());
            pst.setInt(5, dto.getUserID());
            pst.executeUpdate();
        
        }catch(ClassNotFoundException classError){
            
            System.out.println(classError.getMessage());
            classError.printStackTrace();
            throw new ClassNotFoundException(classError.getMessage());
            
        }catch(SQLException sqlError){
            
            System.out.println(sqlError.getMessage());
            sqlError.printStackTrace();
            throw new SQLException(sqlError.getMessage());
            
        }finally{
            if(con != null){
                con.close();
            }
        }
            
        }else if(dtoObj instanceof UserDataDTO){
            UserDataDTO dto = (UserDataDTO)dtoObj;
            String updateSql = "UPDATE picture_t SET user_name = ? WHERE user_id = ?";
            
            try{
                
                con = dbmanager.getConnection();
                pst = con.prepareStatement(updateSql);
                pst.setString(1, dto.getUserName());
                pst.setInt(2, dto.getUserID());
                pst.executeUpdate();
                
            }catch(ClassNotFoundException classError){
            
            System.out.println(classError.getMessage());
            classError.printStackTrace();
            throw new ClassNotFoundException(classError.getMessage());
            
            }catch(SQLException sqlError){
            
            System.out.println(sqlError.getMessage());
            sqlError.printStackTrace();
            throw new SQLException(sqlError.getMessage());
            
            }finally{
                if(con != null){
                    con.close();
                }
            }
        }
    }
    
    public void deletePictureData(PictureDataDTO dto) throws ClassNotFoundException, SQLException{
        
        Connection con = null;
        PreparedStatement pst = null;
        String deleteSql4Evaluation = "DELETE FROM evaluation_t WHERE picture_id = ?";
        String deleteSql4Comment = "DELETE FROM comment_t WHERE picture_id = ?";
        String deleteSql4Picture = "DELETE FROM picture_t WHERE picture_id = ? OR picture_name = ?";
        System.out.println("deletePictureData start");
        
        try{
            
            con = dbmanager.getConnection();
            pst = con.prepareStatement(deleteSql4Evaluation);
            pst.setInt(1, dto.getPictureID());
            pst.executeUpdate();
            System.out.println("評価テーブルから写真情報を削除しました");
            
            pst = con.prepareStatement(deleteSql4Comment);
            pst.setInt(1, dto.getPictureID());
            pst.executeUpdate();
            System.out.println("コメントテーブルから写真情報を削除しました");
            
            pst = con.prepareStatement(deleteSql4Picture);
            pst.setInt(1,dto.getPictureID());
            pst.setString(2, dto.getPictureName());
            pst.executeUpdate();
            System.out.println("写真テーブルから写真情報を削除しました");

        }catch(ClassNotFoundException classError){
            
            System.out.println(classError.getMessage());
            classError.printStackTrace();
            throw new ClassNotFoundException(classError.getMessage());
            
        }catch(SQLException sqlError){
            
            System.out.println(sqlError.getMessage());
            sqlError.printStackTrace();
            throw new SQLException(sqlError.getMessage());
            
        }finally{
            
            if(con != null){
                con.close();
            }
            
        }
        
    }
    
}
