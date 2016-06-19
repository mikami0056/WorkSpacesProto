/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UserDataBeans;
import Logic.RegistrationLogic;
import Model.QuestionDataBeans;
import Model.QuestionDataDAO;
import Model.QuestionDataDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class Registration extends HttpServlet {

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
        
        try {
            HttpSession session = request.getSession();
            if(session.getAttribute("questions") == null){
            //質問格納用リスト
            List<QuestionDataBeans> questions = new ArrayList<>();
            //質問をDBから取得
            List<QuestionDataDTO> dtoList = QuestionDataDAO.getInstance().getQuestions();
            //質問情報を持つインスタンスをリストに格納
            for(QuestionDataDTO dto : dtoList){
                QuestionDataBeans question = new QuestionDataBeans();
                question.QDD2QDBMapping(dto);
                questions.add(question);
            }
            //セッションに格納
            session.setAttribute("questions", questions);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registrationform.jsp");
            dispatcher.forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        request.setCharacterEncoding("UTF-8");
        String operation = request.getParameter("operation");
        
        switch(operation){
            
            case "REGIST":
                String destination = "";
                String userName = request.getParameter("username");
                String passWord = request.getParameter("password");
                String mail = request.getParameter("mail");
                int questionID = Integer.parseInt(request.getParameter("questionID"));
                String answer = request.getParameter("answer");
                
                UserDataBeans registUser = new UserDataBeans(userName, passWord, mail, questionID, answer);
                registUser.setUserID(registUser.hashCode());
                System.out.println(registUser.hashCode());
                HttpSession session = request.getSession();
                
                try {
                    boolean status = RegistrationLogic.getInstance().RegistrationExecute(registUser);
                    if(status){
                        destination = "WorkSpaces";
                        session.setAttribute("loginAccount", registUser);
                        request.setAttribute("registComplete", "registComplete");
                        
                    }else{
                        destination = "/WEB-INF/jsp/registrationform.jsp";
                        session.setAttribute("registUser", registUser);
                    }
                    
                    RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
                    dispatcher.forward(request, response);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            break;
            
            default:
            doGet(request, response);
            
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
