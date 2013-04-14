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
 * Servlet implementation class GetAvailableSurveysServlet
 */
@WebServlet("/GetAvailableSurveysServlet")
public class GetAvailableSurveysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAvailableSurveysServlet() {
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
				PreparedStatement pstmt = conn.prepareStatement("select id, name, desc from surveyheader");
				ResultSet rs = pstmt.executeQuery();
				StringBuilder jsonBldr = new StringBuilder("{'survey':[");
				boolean isFirst = true;
				while (rs.next()) {
					if (!isFirst)
						jsonBldr.append(",");
					else
						isFirst = false;
					
					jsonBldr.append("{");
					jsonBldr.append("'name': '").append(rs.getObject(2)).append("',");
					jsonBldr.append("'SurveyID': '").append(rs.getObject(1)).append("',");
					jsonBldr.append("'desc': '").append(rs.getObject(3)).append("'");
					jsonBldr.append("}");
					
				}
				
				jsonBldr.append("]}");
				rs.close();
				pstmt.close();
				conn.close();
				
				response.setContentType("application/json");
				PrintWriter writer = response.getWriter();
				writer.print(jsonBldr.toString());
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
