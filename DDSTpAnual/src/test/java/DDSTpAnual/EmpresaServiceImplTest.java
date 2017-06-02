package DDSTpAnual;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;

public class EmpresaServiceImplTest {
	EmpresaServiceImpl service = null;

	@Before
	public void init() {
		service = new EmpresaServiceImpl();
	}

	public String iniciarArchivo(String nombreArchivo) {
		String path = System.getProperty("user.dir");
		String rutaArchivo = path + "\\src\\main\\resources\\test\\" + nombreArchivo;
		return rutaArchivo;
	}

	// Test sobre la carga del archivo
	@Test
	public void testSubirExcel() throws ServiceException {
		service.subirExcel(iniciarArchivo("Carga1.xlsx"));
	}

	@Test(expected = NullPointerException.class)
	public void testSubirArchivoInexistente() throws ServiceException {
		service.subirExcel(iniciarArchivo("Caso1"));
	}

	@Test(expected = NotOfficeXmlFileException.class)
	public void testSubirArchivoNoExcel() throws ServiceException {
		service.subirExcel(iniciarArchivo("CargaCSV.csv"));
	}

	// Test sobre los datos del archivo
	@After
	public void finish() {
	}

}
