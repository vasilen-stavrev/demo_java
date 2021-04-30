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
 * Servlet implementation class Search
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 		Търсене на лице по име и визуализиране на списък с намерени резултати – 
	 * 		търсенето по име не трябва да зависи от начина на изписване в базата данни на името – Case Insensitive search; 
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//
		response.setContentType( "application/json" );
    	response.setCharacterEncoding( "UTF-8" );
		
    	//
		String term = request.getParameter( "term" );
		
		JSONObject result = new JSONObject();
		
		if( term.length() == 0) {
			result.put( "result", 1 );
			result.put( "error", "Please enter something to search." );
    	}
    	else {
    		try {
	    		DataBase database = new DataBase();
	    		database.init();
	    		
	    		result.put( "result", 0 );
	    		result.put( "data",  database.search( term ));
	    		
	    		database.exit();
    		}
    		catch( Exception ex ) {
    			ex.printStackTrace();
    			
    			result.put( "result", 2 );
    			result.put( "error", ex.getMessage() );
    		}
    	}
		
    	//
		response.getWriter().println( result.toString() );
	}
}
