package com.java1234.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java1234.dao.StudentDao;

import com.java1234.model.PageBean;
import com.java1234.model.Student;
import com.java1234.util.DbUtil;
import com.java1234.util.JsonUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentListServlet extends HttpServlet{
	
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
		String stuNo=req.getParameter("stuNo");
		String stuName=req.getParameter("stuName");
		String sex=req.getParameter("sex");
		String bbirthday=req.getParameter("bbirthday");
		String ebirthday=req.getParameter("ebirthday");
		String gradeId=req.getParameter("gradeId");
		  
		String page=req.getParameter("page");
		String rows=req.getParameter("rows");
		
		Student student=new Student();
		if(stuNo!=null) {
			student.setStuNo(stuNo);
			student.setStuName(stuName);
			student.setSex(sex);
			if(StringUtil.isNotEmpty(gradeId)){
				student.setGradeId(Integer.parseInt(gradeId));
				
			}
			
			
		}
	
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try {
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray  jsonArray=JsonUtil.formatRsToJsonArray(studentDao.studentList(con, pageBean, student, bbirthday, ebirthday)) ;
			int total=studentDao.studentCount(con, student, bbirthday, ebirthday);
			result.put("rows",jsonArray);
			result.put("total", total);
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
