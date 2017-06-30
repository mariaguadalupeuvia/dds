package DDSTpAnual;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.archivo.impl.EmpresaArchivoImpl;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Periodo;

public class EmpresaArchivoImplTest {
	EmpresaArchivoImpl empresaArchivo;
	List<Periodo> periodos = new ArrayList<Periodo>();
	
	@Before
	public void init(){
		empresaArchivo = new EmpresaArchivoImpl();
		periodos = new ArrayList<Periodo>();
		periodos.add(new Periodo("2016"));
		periodos.add(new Periodo("2017"));
		periodos.add(new Periodo("2015"));
	}
	
	@Test
	public void testObtenerPeriodos() throws ArchivoException{
		
		Assert.assertEquals(periodos, empresaArchivo.obtenerPeriodos("FaceCopia"));

	}

}
