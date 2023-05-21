package it.indra.SigecAPI.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.portable.ApplicationException;

public final class CommonUtility {

	public static final String to_char = "to_char";
	
	public static final String to_date = "to_date";
	
	public static final String italianDateFormat = "dd/MM/yyyy";
	
	public static final String ukDateFormat = "yyyy-MM-dd";
	
	public static final String ukDateFormatSec= "yyyy-MM-dd HH24:MI:SS:MS";
	
	public static String getTokenFromRequest(HttpServletRequest request){
		String accessToken = null;
		if (request != null) {
			accessToken = request.getHeader("Authorization");
			if (accessToken != null && accessToken.length() > 0) {
				accessToken = accessToken.replaceAll("Bearer ", "");
			}
		}
		return accessToken;
	}
	
	public static byte[] scale(byte[] fileData, int width, int height, String extension) throws ApplicationException {
	    ByteArrayInputStream in = new ByteArrayInputStream(fileData);
	    try {
	        BufferedImage img = ImageIO.read(in);
	        if(height == 0) {
	            height = (width * img.getHeight())/ img.getWidth(); 
	        }
	        if(width == 0) {
	            width = (height * img.getWidth())/ img.getHeight();
	        }
	        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

	        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

	        ImageIO.write(imageBuff, extension, buffer);

	        return buffer.toByteArray();
	    } catch (IOException e) {
	        throw new ApplicationException("IOException in scale", null);
	    }
	}
}
