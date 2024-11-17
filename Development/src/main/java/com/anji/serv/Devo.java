package com.anji.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;


@WebServlet("/demo")
public class Devo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String QUERY="insert into content(name,phone,email,password) values (?,?,?,?)";
    private Connection conn=null;
    private String un="jdbc:mysql://localhost:3306/anji";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Devo() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name=req.getParameter("name");
		long ph=Long.parseLong(req.getParameter("phone"));
		String email=req.getParameter("email");
		String pd=req.getParameter("pwd");
		String cpw=req.getParameter("cpwd");
		PrintWriter pw=resp.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(un,"root","1489");
//			pw.println("Your Details are::::");
//			pw.println("Your name: "+name+"\nyour PhoneNumber: "+ph+"\nYour Email: "+email+"\nYour Pwd: "+pd);
			PreparedStatement ps=conn.prepareStatement(QUERY);
			ps.setString(1, name);
			ps.setLong(2, ph);
			ps.setString(3, email);
			ps.setString(4,pd);
			int rs=ps.executeUpdate();
			HttpSession s=req.getSession();
			s.setAttribute("name", name);
			if(rs!=0) {
				resp.sendRedirect("success.jsp");
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		if(!pd.equals(cpw)) {
//			resp.sendRedirect("error.html");
//		}
//		PrintWriter pw=resp.getWriter();
//		pw.println("Hello Welcome "+name);
		
	}

}
