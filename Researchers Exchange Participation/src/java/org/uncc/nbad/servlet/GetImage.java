/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uncc.nbad.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rohit
 */
@WebServlet("/getImage")
public class GetImage extends HttpServlet {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int BUFFER_LENGTH = 4096;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileName = request.getParameter("file");
        
        File file = new File(System.getenv("OPENSHIFT_DATA_DIR") + fileName);
        InputStream input = new FileInputStream(file);
     
        response.setContentLength((int) file.length());
        response.setContentType(new MimetypesFileTypeMap().getContentType(file));
     
        OutputStream output = response.getOutputStream();
        byte[] bytes = new byte[BUFFER_LENGTH];
        int read = 0;
        while ((read = input.read(bytes, 0, BUFFER_LENGTH)) != -1) {
            output.write(bytes, 0, read);
            output.flush();
        }
     
        input.close();
        output.close();
        
    }
}
