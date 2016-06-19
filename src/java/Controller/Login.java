/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logic.HandleRecaptchaLogic;
import Logic.LoginLogic;
import Logic.WordCheckLogic;
import Model.UserDataBeans;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Login extends HttpServlet {

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
        //リクエストの文字コード指定及びセッションの取得(のちにFilterにする予定)
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        
        //reCAPTCHA API用パラメータを取得
        String remoteIp = request.getRemoteAddr();
        String recap = request.getParameter("g-recaptcha-response");
        
        //ログイン用パラメータを取得
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        
        //フォワード用, エラー発生時はログイン画面に戻る
        String destination = "index.jsp";
        
        try{
            
            //セッション内にアカウント情報があれば遷移
            if(session.getAttribute("loginAccount") != null){
                destination = "WorkSpaces";
                response.sendRedirect(destination);
                return;
            }
            
            //入力されたパラメータを確認
            //問題なければtrue, 入力に不備があればfalseを返す.
            boolean check = WordCheckLogic.getInstance().parameterCheck(userName, passWord);
            if(!check){
                response.sendRedirect("index.jsp?error=input");
                System.out.println("フォーム入力エラーです.");
                return;
            }
            
            //reCAPTCHA API用
            //reCAPTCHA APIの各パラメータを利用して, googleに接続
            boolean varified = HandleRecaptchaLogic.getInstance(recap, remoteIp).connection2Google();
            
            /*googleに接続し, 承認された場合
            @フォームに入力されたパラメータを利用してDBに接続. 
            @アカウントが存在する場合, その情報をインスタンスに格納し, セッションへ保存する.
            */
            if(varified){
                UserDataBeans loginAccount = LoginLogic.getInstance().loginExecute(userName, passWord);
                //アカウント有無の確認, なければログイン画面にリダイレクト
                if(loginAccount == null){
                    response.sendRedirect("index.jsp?error=notexisting");
                    System.out.println("アカウントが存在しません.");
                    return;
                }
                
                session.setAttribute("loginAccount", loginAccount);
                destination = "WorkSpaces";
                
            //承認されなかった場合    
            }else{
                
                response.sendRedirect("index.jsp?error=youwillbebot");
                System.out.println("ボットである可能性があります.");
                
            }
            
            response.sendRedirect(destination);
        
        }catch(Exception e){
            
            response.sendRedirect("index.jsp?error=contact2admin");
            System.out.println("何かしらのエラーが発生しました.");
            e.printStackTrace();
            
        }
        
        
        /*
        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
         */   
        
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
