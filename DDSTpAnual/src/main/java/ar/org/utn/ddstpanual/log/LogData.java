package ar.org.utn.ddstpanual.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date; 

public class LogData{
	private static FileWriter fWriter;
    private static String fileName;
    private static PrintWriter  pWriter;
   
    // Set
    public static void setFileStream() throws IOException{
        String path = System.getProperty("user.dir");
    	String ruta = path + "\\logs"; // Ruta de la carpeta a loguea - archivo de configuracion
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
        fileName = ruta + "\\LogTP_" + formatoFecha.format(fechaActual) + ".log";
        fWriter = new FileWriter(fileName, true);
        pWriter = new PrintWriter(fWriter);
    }
    
	public static void close() {
		pWriter.close();
        try {
			fWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void EscribirLogException(Exception ex) 
    {
		try {
			setFileStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Date fechaActual = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        pWriter.println("--------------------------------------------------------------------------------");
        pWriter.println(formatoFecha.format(fechaActual) + " - EXCEPTION");
        pWriter.println("Tipo: " + ex.getCause()); 
        pWriter.println("Mensaje: " + ex.getMessage());
        pWriter.println("StackTrace: " + ex.getStackTrace());
        pWriter.println("--------------------------------------------------------------------------------");
        
        close();
    }

    public static void EscribirLogText(String texto) {
    	try {
			setFileStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	Date fechaActual = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
        pWriter.println("--------------------------------------------------------------------------------");
        pWriter.println(formatoFecha.format(fechaActual) + ":");
        pWriter.println(texto);
        pWriter.println("--------------------------------------------------------------------------------");
        
        close();
    }
}
