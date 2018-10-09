package com.java1234.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java1234.dao.GradeDao;
import com.java1234.dao.StudentDao;
import com.java1234.model.Grade;
import com.java1234.model.PageBean;
import com.java1234.util.DbUtil;
import com.java1234.util.JsonUtil;
import com.java1234.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GradeDeleteServlet extends HttpServlet{
	
	DbUtil dbUtil=new DbUtil();
	
	GradeDao gradeDao=new GradeDao();
	
	StudentDao studentDao=new StudentDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String delIds=req.getParameter("delIds");
		Connection con=null;
		try {
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			String str[]=delIds.split(",");
			for(int i=0;i<str.length;i++) {
				boolean f=studentDao.getStudentByGradeId(con, str[i]);
				if(f) {
					result.put("errorIndex", i);
					result.put("errorMsg", "班级下面有学生不能删除");
					ResponseUtil.write(resp, result);
					return ;
				}
			}
			int delNums=gradeDao.gradeDelete(con, delIds);
		    if(delNums>0) {
		    	result.put("success", "true");
		    	result.put("delNums", delNums);
		    }else {
		    	result.put("errorMsg", "删除失败！");
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
