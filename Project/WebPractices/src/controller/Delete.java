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
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
//		ユーザーdaoのインスタンスを生成
		String id = request.getParameter("id");
//		？
		User user = userDao.findById(id);
//		userDao.でそこにあるfindByIdの引数上で取得したIdをセット
		request.setAttribute("userC", user);
//		変数userに入れた情報をusercというキーでセットしてjspで使える
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/delete.jsp");
		dispatcher.forward(request, response);
//		その情報をフォワードしてjspで利用
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String UserId = request.getParameter("id");
		UserDao userDao = new UserDao();
		userDao.delete(UserId);

		response.sendRedirect("UserList");
	}
//	dogetとdopostどちらが先か
//	どこからid情報を受け取っているか

}
