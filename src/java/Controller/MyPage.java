/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gest
 */
public class MyPage extends HttpServlet {

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
        String view = (String)request.getAttribute("view");
        String option = request.getParameter("option");
        String destination = "";
        
        if(view != null && view.equals("mypage")){
            
            destination = "/WEB-INF/jsp/mypage.jsp";
            
        } else if(option != null){
            
            switch(option){
            
            case "Upload":  //写真投稿
            destination = option;
            request.setAttribute("view", "uploadpicture");
            break;
            
            case "Manage":  //写真管理
            destination = option;
            request.setAttribute("option", "manageMyPicture");
            break;
                
            case "MyDataUpdate":    //ユーザー情報変更画
            destination = option;
            break;
            
            case "DeleteMyData":    //ユーザー情報削除遷移
            destination = option;
            break;
            
            }
            
        } else {
            
            destination = "/WEB-INF/jsp/mypage.jsp";
            if(request.getAttribute("flag") != null){
            request.setAttribute("option", "pictureupload");
            }
            
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
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
