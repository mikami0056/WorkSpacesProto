/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logic.PictureGetLogic;
import Model.PictureDataBeans;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gest
 */
public class WorkSpaces extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            HttpSession session = request.getSession();
            
            //ホーム画面からユーザーの行動内容をパラメータとして取得し, 各担当のコントローラに遷移.
            //パラメータがなければホーム画面に遷移.
            request.setCharacterEncoding("UTF-8");
            String option = request.getParameter("option");
            if(option == null){
                option = "";
            }
            
            String destination = "";
            switch(option){
                
                case "MyPage"://ユーザー管理画面遷移用
                    destination = option;
                    response.sendRedirect(destination);
                    break;
                    
                case "Logout"://ログアウト画面遷移用
                    destination = option;
                    response.sendRedirect(destination);
                    break;
                    
                case "Contact"://お問い合わせ画面遷移用
                    destination = option;
                    response.sendRedirect(destination);
                    break;
                    
                default: //基本画面(ホーム)遷移用
                    PictureGetLogic pgl = new PictureGetLogic();
                    destination = "/WEB-INF/jsp/workspaces.jsp";
                    if(request.getAttribute("registComplete") != null){
                        request.setAttribute("flag", "welcome");
                    }

                    Map<Integer, PictureDataBeans> pictureByRank = pgl.getPicturesByMethod("Rank");
                    if(!pictureByRank.isEmpty()){
                        session.setAttribute("pictureByRank", pictureByRank);
                    }else{
                        session.setAttribute("Rank", "empty");
                    }
                    
                    Map<Integer, PictureDataBeans> pictureByDate = pgl.getPicturesByMethod("Date");
                    if(!pictureByDate.isEmpty()){
                    session.setAttribute("pictureByDate", pictureByDate);
                    }else{
                    session.setAttribute("Date", "empty");
                    }
                    /*
                    Map<Integer, PictureDataBeans> pictureByComment = pgl.getPicturesByMethod("Comment");
                    if(!pictureByComment.isEmpty()){
                    session.setAttribute("picturesByComment", pictureByComment);
                    }else{
                    session.setAttribute("Comment", "empty");
                    }
                    */
                    RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
                    dispatcher.forward(request, response);
                    
            }
            
            /*
            } catch (SQLException ex) {
            Logger.getLogger(WorkSpaces.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(WorkSpaces.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
        } catch (SQLException ex) {
            Logger.getLogger(WorkSpaces.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WorkSpaces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
