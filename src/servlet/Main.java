package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mutter;
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());

        ServletContext application = this.getServletContext();
        @SuppressWarnings("unchecked")
        List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

        if (mutterList == null) {
            mutterList = new ArrayList<>();
            application.setAttribute("mutterList", mutterList);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            response.sendRedirect("/docoTsubu");
        } else {
            //go to top (Login page)
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);

        String text = request.getParameter("text");

        if (text != null && text.length() > 0) {
            ServletContext application = this.getServletContext();
            List<Mutter> mutterListApplicationScope = (List<Mutter>) application.getAttribute("mutterList");
            List<Mutter> mutterList = mutterListApplicationScope;
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("loginUser");
            Mutter mutter = new Mutter(user.getUserName(), text);
            PostMutterLogic.execute(mutter, mutterList);

            application.setAttribute("mutterList", mutterList);
        } else {
            request.setAttribute("errorMsg", "You have no message in your tweet");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        dispatcher.forward(request, response);
    }

}
