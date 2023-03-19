package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserDt
 */
@WebServlet("/UserDt")
public class UserDt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();

		// URLからGETパラメータとしてIDを受け取る
		String id = request.getParameter("id");

		// TODO  未実装：idを引数にして、idに紐づくユーザ情報を取得する
		User user = userDao.findById(id);
		// TODO  未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード
		request.setAttribute("userD", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDt.jsp");
		dispatcher.forward(request, response);
	}


}
