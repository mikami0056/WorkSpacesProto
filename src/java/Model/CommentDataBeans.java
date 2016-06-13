/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author gest
 */
public class CommentDataBeans implements Serializable{
    
    //各フィールド
    private int commentID;
    private int pictureID;
    private String userName;
    private String comment;
    private Date commentDate;
    
    //コンストラクタ
    public CommentDataBeans(){}
    
    public void setCommentID(int commentID){
        this.commentID = commentID;
    }
    public int getCommentID(){
        return this.commentID;
    }
    
    public void setPictureID(int pictureID){
        this.pictureID = pictureID;
    }
    public int getPictureID(){
        return this.pictureID;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserID(){
        return this.userName;
    }
    
    public void setComment(String comment){
        this.comment = comment;
    }
    public String getComment(){
        return this.comment;
    }
    
    public void setCommentDate(Date commentDate){
        this.commentDate = commentDate;
    }
    public Date getCommentDate(){
        return this.commentDate;
    }
    
    public void CDB2CDDMapping(CommentDataDTO dto){
        dto.setCommentID(this.commentID);
        dto.setPictureID(this.pictureID);
        dto.setUserName(this.userName);
        dto.setComment(this.comment);
        dto.setCommentDate(this.commentDate);
    }
    
    public void CDD2CDBMapping(CommentDataDTO dto){
        this.commentID = dto.getCommentID();
        this.pictureID = dto.getPictureID();
        this.userName = dto.getUserName();
        this.comment = dto.getComment();
        this.commentDate = dto.getCommentDate();
    }
}
