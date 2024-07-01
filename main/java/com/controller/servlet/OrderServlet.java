package com.controller.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.dao.OrderDao;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDao order=null;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement GET method to retrieve order information
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement POST method to create a new order
    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    	String userid=request.getAttribute("sub").toString();
    	JsonObject object= new JsonObject();
    	StringBuilder jsonBuffer= new StringBuilder();
    	BufferedReader jsonreader= request.getReader();
    	String line;
    	while((line=jsonreader.readLine())!=null) {
    		
    		jsonBuffer.append(line);
    	}
    	JsonParser parser=new JsonParser();
    	System.out.println(jsonBuffer);
    	object=parser.parse(jsonBuffer.toString()).getAsJsonObject();
    	System.out.println(object);
    	response.getWriter().write(object.toString());
    	
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement PUT method to update order information
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement DELETE method to delete an order
    }
}

