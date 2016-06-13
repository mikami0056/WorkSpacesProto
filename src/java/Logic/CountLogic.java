/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.CountDataBeans;
import Model.CountDataDTO;
import Model.CountDataDAO;
import Model.PictureDataBeans;
import Controller.PictureDetail;

/**
 *
 * @author gest
 */
public class CountLogic {
    public CountLogic(){}
    
    public static CountLogic getInstance(){
        return new CountLogic();
    }
    
    public int countAndGetDataFromDB(CountDataBeans cdb, String param, PictureDataBeans pdb){
        
        CountDataBeans countData = cdb;
        int returnParameter = 0;

        switch(param){
            case "beautiful":
            countData.setBeautiful(countData.getBeautiful() + 1);
            updateData(countData);
            getDataFromDB(countData);
            //pdb.setBeautiful(countData.getBeautiful());
            returnParameter = countData.getBeautiful();
            break;
            
            case "cool":
            countData.setCool(countData.getCool() + 1);
            updateData(countData);
            getDataFromDB(countData);
            //pdb.setCool(countData.getCool());
            returnParameter = countData.getCool();
            break;
            
            case "stylish":
            countData.setStylish(countData.getStylish() + 1);
            updateData(countData);
            getDataFromDB(countData);
            //pdb.setStylish(countData.getStylish());
            returnParameter = countData.getStylish();
            break;
        }
        return returnParameter;
    }
    
    public void getDataFromDB(CountDataBeans countDataB){
        CountDataBeans countData = countDataB;
        CountDataDTO dto = new CountDataDTO();
        countData.CDB2CDDMapping(dto);
        
        try {
            CountDataDAO dao = new CountDataDAO();
            
            //写真の評価データが存在する場合
            if(dao.checkCountDataExist(dto)){
                dao.getCountData(dto);
                //dao.insertCountData(dto);
                //dao.updateCountData(dto);
            //存在しない場合
            }else{
                //dao.updateCountData(dto);
                dao.insertCountData(dto);
                dao.getCountData(dto);
            }
            
            dto = dao.getCountData(dto);
            countData.CDD2CDBMapping(dto);          
            
        } catch (SQLException ex) {
            Logger.getLogger(PictureDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateData(CountDataBeans countDataB){
        CountDataBeans countData = countDataB;
        CountDataDTO dto = new CountDataDTO();
        countData.CDB2CDDMapping(dto);
        
         try {
            CountDataDAO dao = new CountDataDAO();
            dao.updateCountData(dto);
            dto = dao.getCountData(dto);
            countData.CDD2CDBMapping(dto);          
            
        } catch (SQLException ex) {
            Logger.getLogger(PictureDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
