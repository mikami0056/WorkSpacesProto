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
public class PictureDataDTO {
    private int pictureID;
    private String picturePath;
    private String pictureName;
    private String ownerComment;
    private String userName;
    private int categoryID;
    private int userID;
    private Date submitDate;
    private int sum;
    
    
    public PictureDataDTO(){}
    
    public void setPictureID(int pictureID){
        this.pictureID = pictureID;
    }
    public int getPictureID(){
        return this.pictureID;
    }
    
    public void setPicturePath(String path){
        this.picturePath = path;
    }
    public String getPicturePath(){
        return this.picturePath;
    }
    
    public void setPictureName(String name){
        this.pictureName = name;
    }
    public String getPictureName(){
        return this.pictureName;
    }
    
    public void setOwnerComment(String comment){
        this.ownerComment = comment;
    }
    public String getOwnerComment(){
        return this.ownerComment;
    }
    
    public void setCategoryID(int category){
        this.categoryID = category;
    }
    public int getCategoryID(){
        return this.categoryID;
    }
    
    public void setUserID(int userID){
        this.userID = userID;
    }
    public int getUserID(){
        return this.userID;
    }
    
    public void setSubmitDate(Date dateTime){
        this.submitDate = dateTime;
    }
    public Date getSubmitDate(){
        return this.submitDate;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return this.userName;
    }
    
    public void setSum(int sum){
        this.sum = sum;
    }
    public int getSum(){
        return this.sum;
    }
}
