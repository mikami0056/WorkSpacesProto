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
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gest
 */
public class PictureGetLogic {
    //コンストラクタ
    public PictureGetLogic(){}
    //インスタンス取得
    public static PictureGetLogic getInstance(){
        return new PictureGetLogic();
    }
    
    public Map<Integer, PictureDataBeans> getPicturesByMethod(String method) throws SQLException, ClassNotFoundException{
        Map<Integer, PictureDataDTO> pdtoMap = null;
        PictureDataDAO pdao = new PictureDataDAO();
        
        switch(method){
            case "Rank":
            pdtoMap = pdao.getPictureDataBySearch(method);
            break;
            
            case "Date":
            pdtoMap = pdao.getPictureDataBySearch(method);
            break;
            
            case "Comment":
            pdtoMap = pdao.getPictureDataByNewComment();
            break;
        }
        
        Map<Integer, PictureDataBeans> pictures = new LinkedHashMap<>();
        if(!pdtoMap.isEmpty()){
            for(Integer pdtoID: pdtoMap.keySet()){
                PictureDataBeans picture = new PictureDataBeans();
                picture.DTO2PDBMapping(pdtoMap.get(pdtoID));
                pictures.put(picture.getPictureID(), picture);
            }
        }
        return pictures;
    }
    
    public Map<Integer, PictureDataBeans> getPicturesByTime() throws SQLException, ClassNotFoundException{
        //
        Map<Integer, PictureDataDTO> pdtoMap = PictureDataDAO.getInstance().getPictureDataByRank();
        Map<Integer, PictureDataBeans> pictures = new HashMap<>();
            
        for(Integer pdtoID: pdtoMap.keySet()){
            
            PictureDataBeans picture = new PictureDataBeans();
            picture.DTO2PDBMapping(pdtoMap.get(pdtoID));
            pictures.put(picture.getPictureID(), picture);
            
        }
        
        return pictures;
    }
    
    /*
    public List<PictureDataBeans> getPictureByEval(String eval) throws SQLException{
        
        List<PictureDataDTO> pdtoList = PictureDataDAO.getInstance().getPictureDataByEval(eval);
        List<PictureDataBeans> pictureList = new ArrayList<>();
        
        for(PictureDataDTO pictureDTO : pdtoList){
            PictureDataBeans picture = new PictureDataBeans();
            picture.DTO2PDBMapping(pictureDTO);
            pictureList.add(picture);
        }
        
        return pictureList;
    }
    */
    //写真取得
    public Map<Integer, PictureDataBeans> getPicture(UserDataBeans loginAccount, String contextPath) throws IOException, SQLException{
                
        //ユーザーの写真が保存されているディレクトリの場所のパス
        String path = "/Users/gest/NetBeansProjects/WorkSpaces/web/common/pictures/" + loginAccount.getUserID();
        //パスよりディレクトリを取得
        File directory = new File(path);
        File[] files = directory.listFiles();
        //データベース接続用のインスタンスを生成
        PictureDataDTO dto = new PictureDataDTO();
        System.out.println("A:"+dto.getUserID());
        dto.setUserID(loginAccount.getUserID());
        //データベースに接続し, 写真の情報を持ったインスタンス群を取得
        List<PictureDataDTO> pictureDataList = PictureDataDAO.getInstance().getPictureDataByUserID(dto);
        
        Map<Integer, PictureDataBeans> pictures = new HashMap<>();
        
        if(pictureDataList != null){
            for(PictureDataDTO pdto : pictureDataList){
                PictureDataBeans picture = new PictureDataBeans();
                picture.DTO2PDBMapping(pdto);
                
                //フォルダ内に存在する写真とDB内の写真を確認
                //一致するもののみ表示
                for(File file:files){
                //隠しファイルは除外
                    if(file.getName().equals(picture.getName())){
                    pictures.put(picture.getPictureID(), picture);
                    }
                }
            }
        }
        return pictures;
    
    }
    
    /*
    public Map<String, PictureDataBeans> PictureGetTest(UserDataBeans loginAccount, String contextPath){
        //写真保存用配列
        Map<String, PictureDataBeans> pictureList = new HashMap<>();
        
        //ユーザーの写真が保存されているディレクトリの場所のパス
        String path = "/Users/gest/NetBeansProjects/WorkSpacesProto/web/common/image/" + loginAccount.getUserName();
        //パスよりディレクトリを取得
        File directory = new File(path);
        File[] files = directory.listFiles();
        
        for(File file:files){
                //隠しファイルは除外
                if(!(file.getName().indexOf(".") == 0)){
                    String fileName = file.getName();
                    PictureDataBeans pdb = new PictureDataBeans();
                    pdb.setName(fileName);
                    pdb.setUserName(loginAccount.getUserName());
                    //System.out.println(this.getServletContext().getContextPath());
                    pdb.setPath(contextPath + "/common/image/" + loginAccount.getUserName() + "/" + fileName);
                    
                    pictureList.put(fileName, pdb);
                }
                
                if(file.getName().equals(picture.getName())){
                    pictureName = file.getName();
                }
                pictures.put(pictureName, picture);
                
        }
        
        return pictureList;
        
    }
    */
    public PictureDataBeans getPictureFromList(int pictureID, Map<Integer, PictureDataBeans> pictures){
        PictureDataBeans pdb = new PictureDataBeans();
        for(Integer pID : pictures.keySet()){
            if(pID == pictureID){
                pdb = pictures.get(pID);
                System.out.println("A:"+pdb.getPath());
            }
        }
        return pdb;
    }

    
}
