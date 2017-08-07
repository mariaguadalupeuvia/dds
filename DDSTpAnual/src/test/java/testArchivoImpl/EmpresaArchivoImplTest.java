package testArchivoImpl;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.archivo.impl.EmpresaArchivoImpl;
import ar.org.utn.ddstpanual.model.Periodo;

public class EmpresaArchivoImplTest {
  EmpresaArchivoImpl empresaArchivo;
  List<Periodo> periodos = new ArrayList<Periodo>();

  @Before
  public void init() {
    empresaArchivo = new EmpresaArchivoImpl();
    periodos = new ArrayList<Periodo>();
    periodos.add(new Periodo("2016"));
    periodos.add(new Periodo("2017"));
    periodos.add(new Periodo("2015"));
  }
}
