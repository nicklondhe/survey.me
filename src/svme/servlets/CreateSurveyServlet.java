package svme.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svme.util.ConnectionUtil;

/**
 * Servlet implementation class CreateSurveyServlet
 */
@WebServlet("/CreateSurveyServlet")
public class CreateSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSurveyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Get database connection, execute query, put results back as json
		 */
		try {
			Connection conn = ConnectionUtil.getConnection();
			if (conn != null) {
				String nm = request.getParameter("name");
				String dsc = request.getParameter("desc");
				PreparedStatement pstmt = conn.prepareStatement("insert into surveyheader (name, desc, lat, long) values (?, ?, ?, ?)");
				pstmt.setString(1, nm);
				pstmt.setString(2, dsc);
				pstmt.setDouble(3, Double.valueOf(request.getParameter("latitude")));
				pstmt.setDouble(4, Double.valueOf(request.getParameter("longitude")));
				pstmt.executeUpdate();
				conn.commit();
				pstmt.close();
				
				pstmt = conn.prepareStatement("select id from surveyheader where name = ? and desc = ?");
				pstmt.setString(1, nm);
				pstmt.setString(2, dsc);
				ResultSet rs = pstmt.executeQuery();
				String id = null;
				while (rs.next()) {
					id = rs.getObject(1).toString();
				}
				rs.close();
				pstmt.close();
				conn.close();
				
				response.setContentType("application/json");
				PrintWriter writer = response.getWriter();
				writer.print("{'SurveyId': '" + id + "'}");
				writer.flush();
			} else {
				System.out.println("null conn!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
