
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author HILDAGARD MSUYA
 */
public class Tourists extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        
            String _username=request.getParameter("name");
            String _password=request.getParameter("password");
            try{
                if(_username!=null){
                    Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tourist","root","");
            String query="select * from login where username=? and password=?";
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setString(1,_username);
            pst.setString(2,_password);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                    response.sendRedirect("homepage.html");
                }
            else{
                out.println("Login failed! please try again");
            }
                }
            }catch(IOException | ClassNotFoundException | SQLException ex){
                out.println("Exception :"+ex.getMessage());
            }
        }
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
