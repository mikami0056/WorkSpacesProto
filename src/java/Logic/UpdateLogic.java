/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Model.PictureDataBeans;
import Model.PictureDataDAO;
import Model.PictureDataDTO;
import Model.UserDataBeans;
import Model.UserDataDAO;
import Model.UserDataDTO;
import java.io.File;
import java.sql.SQLException;

/**
 *
 * @author gest
 */
public class UpdateLogic {
    public UpdateLogic(){}
    
    public static UpdateLogic getInstance(){
        return new UpdateLogic();
    }
    
    /*
    @ユーザー情報を更新するためのメソッド
    @
    */
    public void updateUserData(UserDataBeans loginAccount) throws ClassNotFoundException, SQLException{
        UserDataDTO dto = new UserDataDTO();
        loginAccount.UDB2DTOMapping(dto);
        //ユーザー情報を更新
        UserDataDAO.getInstance().updateUserData(dto);
        //写真情報を更新
        PictureDataDAO.getInstance().updatePictureData(dto);
        /*
        //写真保存用ディレクトリの名前を更新
        String basePath = "/Users/gest/NetBeansProjects/WorkSpaces/web/common/pictures/";
        String oldPath = basePath + oldName;
        String newPath = basePath + loginAccount.getUserName();
        File oldDirectory = new File(oldPath);
        if(oldDirectory.exists() && oldDirectory.isDirectory()){
            File newDirectory = new File(newPath);
            oldDirectory.renameTo(newDirectory);
        }
        */
    }
        
    /*
    @データベース内の写真情報を更新するメソッド
    @更新される情報は
    */
    public void updatePictureData(PictureDataBeans picture4Update, UserDataBeans loginAccount, String oldName) throws SQLException, ClassNotFoundException{
        
        PictureDataDTO dto = new PictureDataDTO();
        String path = "/Users/gest/NetBeansProjects/WorkSpaces/web/common/pictures/";
        picture4Update.PDB2DTOMapping(dto, loginAccount.getUserID());
        File oldFile = new File(path + loginAccount.getUserID() + "/" + oldName);
        
        if(oldFile.exists()){
            System.out.println(dto.getPicturePath());
            File newFile = new File(path + loginAccount.getUserID() + "/" + dto.getPictureName());
            oldFile.renameTo(newFile);
            PictureDataDAO.getInstance().updatePictureData(dto);
        } 
    }
    
}
