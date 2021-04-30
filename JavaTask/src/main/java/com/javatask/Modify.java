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
 * Servlet implementation class Modify
 */
@WebServlet("/modify")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 		Редактиране на съществуващ запис – след изпълнение на операция търсене, избран запис да се отвори 
	 *		за редактиране, и нанесените изменения да се обработят със съответна операция за промяна – 
	 *		валидационните грешки да се обработят от операцията аналогично на операцията по създаване на запис.  
	 *		Да се изведе информиращо съобщение след успешната промяна на запис;
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Modify 
		response.setContentType( "application/json" );
		response.setCharacterEncoding( "UTF-8" );
		
		//
		JSONObject result = new JSONObject();
		try {
			long id = Long.parseLong(request.getParameter( "id" ));
			
			//
			DataBase database = new DataBase();
			database.init();
			    	
			// Parameters for modify
			database.modify(id,
				request.getParameter( "full_name" ), 
				request.getParameter( "pin" ), 
				request.getParameter( "email" ), 
				request.getParameter( "address" )
			);
			
			// Load modifications
			JSONObject json = database.get( id );
			database.exit();
			
			if( json != null ) {
				result.put( "result", 0 );
				result.put( "data", json );
			}
			else {
				result.put( "result", 1 );
			    result.put( "error", "No record found with id " + id);
			}
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
