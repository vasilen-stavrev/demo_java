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
 * Servlet implementation class Create
 */
@WebServlet("/create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Create() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 		Създаване на запис за лице в базата данни – ограниченията да бъдат дефинирани на ниво web услуга, 
	 * 		а наличието на валидационни проблеми да се визуализира с подходящи съобщения. 
	 * 		Да се изведе информиращо съобщение след успешното създаване на запис;
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Create 
		response.setContentType( "application/json" );
    	response.setCharacterEncoding( "UTF-8" );
		
    	//
    	JSONObject result = new JSONObject();
    	try {
	    	DataBase database = new DataBase();
	    	database.init();
	    	
	    	// Parameters for create
	    	database.create(
	    			request.getParameter( "full_name" ), 
	    			request.getParameter( "pin" ), 
	    			request.getParameter( "email" ), 
	    			request.getParameter( "address" ));
	    	
	    	database.exit();
	    	result.put( "result", 0 );
    	}
    	catch( Exception ex ) {
    		ex.printStackTrace();
    			
    		result.put( "result", 1 );
    		result.put( "error", ex.getMessage() );
    	}
    	
    	// output
		response.getWriter().println( result.toString() );
	}

}
