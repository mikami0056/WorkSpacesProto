package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/includedpages/recaptcha.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>WorkSpaces</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"common/css/standard.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"common/css/login.css\">\n");
      out.write("        <script src=\"https://www.google.com/recaptcha/api.js?hl=ja\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"page\">\n");
      out.write("        <header id=\"pageHead\">\n");
      out.write("        <h1>WorkSpaces</h1>\n");
      out.write("        <hr>\n");
      out.write("        </header>\n");
      out.write("        <div id=\"login\">\n");
      out.write("            <section class=\"title\">\n");
      out.write("            <p>ログイン</p>\n");
      out.write("            </section>\n");
      out.write("            <form action=\"/WorkSpacesProto/Login\" method=\"POST\">\n");
      out.write("            <p class=\"username\">ユーザー名<br>\n");
      out.write("            <input type=\"text\" name=\"username\"></p>\n");
      out.write("            <p class=\"password\">パスワード<br>\n");
      out.write("            <input type=\"password\" name=\"password\"></p>\n");
      out.write("            ");
 if("error".equals(request.getParameter("flag"))){out.print("ログインに失敗しました");} 
      out.write("\n");
      out.write("            <section id=\"g\">\n");
      out.write("            <div class=\"g-recaptcha\" data-sitekey=\"6Ldh7yATAAAAAFXQSTDIvh6WOkkP0TXRcB12KZR9\" disable=\"false\">\n");
      out.write("            </div>\n");
      out.write("            </section>\n");
      out.write("            <div class=\"buttom\" >\n");
      out.write("            <input type=\"submit\" value=\"ログイン\">\n");
      out.write("            </div>\n");
      out.write("            </form>\n");
      out.write("            <p class=\"regist\">新規登録される方は<a href=\"Registration\">こちら</a></p>\n");
      out.write("        </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("    ");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
