package com.java1234.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java1234.dao.StudentDao;
import com.java1234.model.Student;
import com.java1234.model.PageBean;
import com.java1234.util.DateUtil;
import com.java1234.util.DbUtil;
import com.java1234.util.JsonUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentSaveServlet extends HttpServlet{
	
	DbUtil dbUtil=new DbUtil();
	
	StudentDao studentDao=new StudentDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String stuNo=req.getParameter("stuNo");
		String stuName=req.getParameter("stuName");
		String sex=req.getParameter("sex");
		String birthday=req.getParameter("birthday");
		String gradeId=req.getParameter("gradeId");
		String email=req.getParameter("email");
		String stuDesc=req.getParameter("stuDesc");
		String stuId=req.getParameter("stuId");
		
		Student student=null;
		try {
			student = new Student(stuNo, stuName, sex, DateUtil.formatString(birthday, "yyyy-MM-dd") , Integer.parseInt(gradeId), email,  stuDesc);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(StringUtil.isNotEmpty(stuId)) {
			student.setStuId(Integer.parseInt(stuId));
		}
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int saveNums=0;
			JSONObject result=new JSONObject();
			
			if(StringUtil.isNotEmpty(stuId)) {
				studentDao.studentModify(con, student);
			}else {
				saveNums=studentDao.studentAdd(con, student);
			}
			
		    if(saveNums>0) {
		    	result.put("success", "true");
		    	
		    }else {
		    	result.put("success", "true");
		    	result.put("errorMsg", "É¾³ýÊ§°Ü£¡");
		    }
			ResponseUtil.write(resp, result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
