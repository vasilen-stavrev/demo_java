package com.javatask;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.javatask.hibernate.DataBase;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete")
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 		Изтриване на избран запис - след изпълнение на операция търсене, избран запис да се изтрие – 
	 *		услугата да визуализира въпрос за потвърждение на операцията изтриване.
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Delete 
		response.setContentType( "application/json" );
		response.setCharacterEncoding( "UTF-8" );
				
		//
		JSONObject result = new JSONObject();
		try {
			// Parameters for delete
			long id = Long.parseLong(request.getParameter( "id" ));
			
			//
			DataBase database = new DataBase();
			database.init();
			
			if( request.getParameter( "confirm" ) != null ) {
				database.delete( id );
			}
			else {
				JSONObject json = database.get( id );
				if( json != null ) {
					result.put( "confirm", "Please confirm deleting the entry: " + json.get( "full_name" ));
					result.put( "result", 0 );
				}
				else {
					result.put( "result", 0 );
				}
			}
			
			database.exit();
			
		}
		catch( Exception ex ) {
			ex.printStackTrace();
			
			result.put( "result", 2 );
			result.put( "error", ex.getMessage() );
		}
				    	
		// output
		response.getWriter().println( result.toString() );
	}
}
