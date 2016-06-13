/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Model.PictureDataBeans;
import Model.PictureDataDTO;
import Model.PictureDataDAO;
import Model.UserDataBeans;
import Model.UserDataDAO;
import Model.UserDataDTO;
import java.io.File;
import java.sql.SQLException;

/**
 *
 * @author gest
 */
public class DeleteLogic {
    
    public DeleteLogic(){}
    
    public static DeleteLogic getInstance(){
        return new DeleteLogic();
    }
    
    public void deleteUserData(UserDataBeans loginAccount) throws ClassNotFoundException, SQLException{
        
        UserDataDTO dto = new UserDataDTO();
        loginAccount.UDB2DTOMapping(dto);
        UserDataDAO.getInstance().deleteUserData(dto);
        
    }
    
    public void deletePictureData (PictureDataBeans picture4Delete, UserDataBeans loginAccount) throws ClassNotFoundException, SQLException{
        
        PictureDataDTO dto = new PictureDataDTO();
        picture4Delete.PDB2DTOMapping(dto, loginAccount.getUserID());
        PictureDataDAO.getInstance().deletePictureData(dto);
        String path = "/Users/gest/NetBeansProjects/WorkSpaces/web/common/image/";
        File file4Delete = new File(path + loginAccount.getUserID() + "/" + picture4Delete.getName());
        
        if(file4Delete.exists()){
            file4Delete.delete();
        }
        
    }
    
}
