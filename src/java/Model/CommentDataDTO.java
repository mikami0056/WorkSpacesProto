/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author gest
 */
public class CommentDataDTO {
    //各フィールド
    private int commentID;
    private int pictureID;
    private String userName;
    private String comment;
    private Date commentDate;
    
    //コンストラクタ
    public CommentDataDTO(){}
    
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
    public String getUserName(){
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
}
