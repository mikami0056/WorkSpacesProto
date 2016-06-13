/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Controller.Upload;
import Model.PictureDataBeans;
import Model.PictureDataDAO;
import Model.PictureDataDTO;
import Model.UserDataBeans;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author gest
 */
public class UploadLogic {
    //コンストラクタ
    public UploadLogic(){}
    
    //インスタンス取得用
    public static UploadLogic getInstance(){
        return new UploadLogic();
        
    }
    
    //写真アップロード用
    public void uploadPicture(HttpServletRequest request, UserDataBeans loginAccount, String contextPath){
        
        
        
        //写真保存用ディレクトリ
        String path = "/Users/gest/NetBeansProjects/WorkSpaces/web/common/pictures/";
        File newDirectry = new File(path + loginAccount.getUserID());        
        
        //存在しなければ作成
        if(!newDirectry.exists()|| newDirectry == null){
            newDirectry.mkdir();
            System.out.println("ディレクトリ作成");
        }
        
        //ディレクトリの場所を取得
        String dPath = newDirectry.getPath();
        
        //専用のライブラリが必要
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        
        try{
            //写真登録画面から送られてきたデータを格納
            List<FileItem> list = sfu.parseRequest(request);
            Iterator iterator = list.iterator();
            
            String pictureName = "";
            String extension = "";
            String comment = "";
            int categoryID = 1;
            
            FileItem pictureData = null;
            
            while(iterator.hasNext()){
                FileItem item = (FileItem)iterator.next();
                
                //写真データの場合
                if(!item.isFormField()){
                    pictureData = item;
                    String itemName = item.getName();
                    extension = itemName.substring(itemName.lastIndexOf("."));
                    
                //写真データ以外(パラメータ)の場合   
                }else{
                    //System.out.println(item.getString("UTF-8"));
                    switch(item.getFieldName()){
                       
                        case "pictureName":
                            //入力された写真名を取得
                            pictureName = item.getString("UTF-8");
                            //写真名が無記入の場合, [無題+投稿日]となる
                            if(pictureName.isEmpty()){
                            Date date = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                            String strDate = sdf.format(date);
                            pictureName = "無題" + strDate;
                            }
                        break;
                        
                        case "category":
                            categoryID = Integer.parseInt(item.getString("UTF-8"));
                        break;
                        
                        case "comment":
                            if(item.getString("UTF-8").isEmpty()){
                                comment = "投稿者コメントはありません";
                            }
                            comment = item.getString("UTF-8");
                        break;    
                    }
                }
            }
            
            /*
            @サーバー内に写真を保存し, 写真用のインスタンスに情報を保存
            @ここで写真名を利用した写真IDを生成している
            */
            pictureData.write(new File(dPath + "/" + pictureName + extension));
            String pPath = contextPath + "/common/pictures/" + loginAccount.getUserID() + "/" + pictureName + extension;
            PictureDataBeans picture = new PictureDataBeans((pictureName + extension), pPath, comment, categoryID, loginAccount.getUserName());
            picture.setPictureID(picture.hashCode());
            
            //DBに写真情報を保存
            PictureDataDTO dto = new PictureDataDTO();
            picture.PDB2DTOMapping(dto, loginAccount.getUserID());
            PictureDataDAO.getInstance().setPictureData(dto);
            
            
            
        } catch (FileUploadException ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
}
