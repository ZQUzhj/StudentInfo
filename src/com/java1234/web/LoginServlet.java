package com.java1234.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java1234.dao.UserDao;
import com.java1234.model.User;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;

public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	UserDao userDao=new  UserDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String userName=req.getParameter("userName");
	    String password=req.getParameter("password");
	    
	    if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)) {
	    	req.setAttribute("error", "用户名或密码不能为空!");
	    	req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
	    	
	    }
	    Connection con=null;
	    try {
	    	User user=new User(userName,password);
	    	con=dbUtil.getCon();
	    	User currentUser=userDao.login(con, user);
	    	if(currentUser==null) {
	    		req.setAttribute("error", "用户名或者密码错误");
	    		req.setAttribute("userName", userName);
	    		req.setAttribute("password", password);
	    		//服务器跳转
	    		req.getRequestDispatcher("login.jsp").forward(req, resp);
	    	}else {
	    		//客户端跳转
	    		HttpSession session=req.getSession();
	    		session.setAttribute("currentUser", currentUser);
	    		resp.sendRedirect("main.jsp");
	    	}
	    			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	

}
