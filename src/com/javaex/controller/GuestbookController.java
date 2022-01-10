package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
	
    public GuestbookController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		GuestbookDao guestbookDao = new GuestbookDao();
		List<GuestbookVo> getList = guestbookDao.gbList();
		//System.out.println(getList);
		
		String act = request.getParameter("action");
		
		//다오의 겟리스트를 g리스트에 넣는다
		request.setAttribute("gList", getList);
		
		///////////////////////////////////////
		//add, addList, delete, deleteForm
		///////////////////////////////////////
		
		if ("addList".equals(act)) {
			System.out.println("action=addList");

			//어트리뷰트 영역에 getList를 gList라는 별명으로 넣어준다
			request.setAttribute("gList", getList);
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);

		} else if ("add".equals(act)) {
			System.out.println("action=add");

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");

			GuestbookVo vo = new GuestbookVo(name, password, content);
			int count = guestbookDao.insert(vo);

			System.out.println(count + "건 등록되었습니다");
			response.sendRedirect("/guestbook2/gbc?action=addList");

		} else if ("deleteForm".equals(act)) {
			System.out.println("action=deleteForm");

			String stringNo = request.getParameter("no");
			request.setAttribute("stringNo", stringNo);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);

		} else if ("delete".equals(act)) {
			System.out.println("action=delete");

			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");

			GuestbookVo vo = new GuestbookVo();
			vo.setNo(no);
			vo.setPassword(password);
			int count = guestbookDao.delete(vo);

			System.out.println(count + "건 삭제되었습니다.");
			response.sendRedirect("/guestbook2/gbc?action=addList");

		} else {
			System.out.println("파라미터 없음");
		}

		//System.out.println("GuestbookController");
		
		
		
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
