package it.indra.SigecAPI.migration.controller;

import java.time.LocalTime;

import org.springframework.web.util.HtmlUtils;

public class main {


	public static void main(String[] args) {

//		LocalTime timeDa = LocalTime.parse("22:00");	//inizia	
//		LocalTime timeA = LocalTime.parse("01:00");		//fine
//
//		//		if((timeDa.isBefore(LocalTime.parse("23:59"))|| timeDa.equals(LocalTime.parse("23:59")))
//		//				&&(timeA.equals(LocalTime.parse("00:00")) || timeA.isAfter(LocalTime.parse("00:00")))
//		//				)
//		if( timeA.isBefore(timeDa)) {	
//			//	timeDa.isAfter(timeA)
//			System.out.println("la data inizio è successiva alla data fine");
//		}else {
//			System.out.println("la data inizio è precedente alla data fine");
//		}
//
		
//		String pathcopy= "578659|true";
//		String[] parts = pathcopy.split("\\|");
//		String allegatoOldString=parts[0];
//		boolean bool=Boolean.parseBoolean(parts[1]);
//		System.out.println(allegatoOldString);
//		System.out.println(bool);
		
		String x="cionedfkdkddkd - “";
		HtmlUtils.htmlEscape(x);
		System.out.println(HtmlUtils.htmlEscape(x));
		
	
	}
}
