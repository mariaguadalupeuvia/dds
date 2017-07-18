package ar.org.utn.ddstpanual.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogData {
  private static FileWriter fWriter;
  private static String fileName;
  private static PrintWriter pWriter;

  // Set
  public static void setFileStream() throws IOException {
    final String path = System.getProperty("user.dir");
    final String ruta = path + "\\logs"; // Ruta de la carpeta a loguea - archivo de configuracion
    final Date fechaActual = new Date();
    final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
    fileName = ruta + "\\LogTP_" + formatoFecha.format(fechaActual) + ".log";
    fWriter = new FileWriter(fileName, true);
    pWriter = new PrintWriter(fWriter);
  }

  public static void close() {
    pWriter.close();
    try {
      fWriter.close();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public static void EscribirLogException(final Exception ex) {
    try {
      setFileStream();
    } catch (final IOException e) {
      e.printStackTrace();
    }
    final Date fechaActual = new Date();
    final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    pWriter.println("--------------------------------------------------------------------------------");
    pWriter.println(formatoFecha.format(fechaActual) + " - EXCEPTION");
    pWriter.println("Tipo: " + ex.getCause());
    pWriter.println("Mensaje: " + ex.getMessage());
    pWriter.println("StackTrace: " + ex.getStackTrace());
    pWriter.println("--------------------------------------------------------------------------------");

    close();
  }

  public static void EscribirLogText(final String texto) {
    try {
      setFileStream();
    } catch (final IOException e) {
      e.printStackTrace();
    }

    final Date fechaActual = new Date();
    final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    pWriter.println("--------------------------------------------------------------------------------");
    pWriter.println(formatoFecha.format(fechaActual) + ":");
    pWriter.println(texto);
    pWriter.println("--------------------------------------------------------------------------------");

    close();
  }
}
