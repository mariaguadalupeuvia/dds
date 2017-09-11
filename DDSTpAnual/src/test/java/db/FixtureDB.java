package db;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Filtro;
import ar.org.utn.ddstpanual.model.metodologia.FiltroCreciente;
import ar.org.utn.ddstpanual.model.metodologia.FiltroDecreciente;
import ar.org.utn.ddstpanual.model.metodologia.FiltroIgual;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMayor;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMayorIgual;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMenor;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMenorIgual;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.Orden;

public class FixtureDB extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
  public FixtureDB() {
    try {
        crearFiltros();
        crearEmpresas();
        crearIndicadoresMetodologia();
    } catch (Exception e) {
       e.printStackTrace();
    }
  }

private void crearEmpresas()
  {
	  //empresa 1
		List<Periodo> periodos1 =
		        Arrays.asList( new Periodo("2015", 20000), new Periodo("2016", 25000), new Periodo("2017", 50000));
		List<Periodo> periodos2 =
		    Arrays.asList( new Periodo("2015", 5000), new Periodo("2016", 10000), new Periodo("2017", 30000));
		List<Periodo> periodos3 =
		    Arrays.asList( new Periodo("2015", 100000), new Periodo("2016", 10000), new Periodo("2017", 20000));
		
		List<Cuenta> cuentas = Arrays.asList(new Cuenta("activoCorriente", periodos1), new Cuenta("pasivoTotal", periodos2), new Cuenta("patrimonioNeto", periodos3));
		Empresa facebook = new Empresa("Facebook", cuentas);
		
		//empresa 2
		List<Periodo> periodos4 =
		        Arrays.asList( new Periodo("2015", 30000), new Periodo("2016", 60000), new Periodo("2017", 100000));
		List<Periodo> periodos5 =
		    Arrays.asList( new Periodo("2015", 10000), new Periodo("2016", 20000), new Periodo("2017", 80000));
		List<Periodo> periodos6 =
		    Arrays.asList( new Periodo("2015", 20000), new Periodo("2016", 40000), new Periodo("2017", 20000));
		
		List<Cuenta> cuentas2 = Arrays.asList(new Cuenta("activoCorriente", periodos4), new Cuenta("pasivoTotal", periodos5), new Cuenta("patrimonioNeto", periodos6));
		Empresa twitter = new Empresa("twitter", cuentas2);
		
		//empresa 3
		List<Periodo> periodos7 =
		        Arrays.asList( new Periodo("2015", 200), new Periodo("2016", 1000));
		List<Periodo> periodos8 =
		    Arrays.asList( new Periodo("2015", 100), new Periodo("2016", 500));
		List<Periodo> periodos9 =
		    Arrays.asList( new Periodo("2015",100), new Periodo("2016", 500));
		
		List<Cuenta> cuentas3 = Arrays.asList(new Cuenta("activoCorriente", periodos7), new Cuenta("pasivoTotal", periodos8), new Cuenta("patrimonioNeto", periodos9));
		Empresa ebay = new Empresa("ebay", cuentas3);
			        
		//empresa 4       
		List<Periodo> periodos10 =
		        Arrays.asList( new Periodo("2017", 100000));
		List<Periodo> periodos11 =
		    Arrays.asList( new Periodo("2017", 20000));
		
		List<Cuenta> cuentas4 = Arrays.asList(new Cuenta("activoCorriente", periodos10), new Cuenta("patrimonioNeto", periodos11));
		Empresa yahoo = new Empresa("yahoo", cuentas4);
				        
		//empresa 5	        
		List<Periodo> periodos13 =
		        Arrays.asList( new Periodo("2016", 180000), new Periodo("2017", 10000));
		List<Periodo> periodos14 =
		    Arrays.asList( new Periodo("2016", 8000), new Periodo("2017", 10000));
		List<Periodo> periodos15 =
		    Arrays.asList( new Periodo("2016", 100000), new Periodo("2017", 100000));
		
		List<Cuenta> cuentas5 = Arrays.asList(new Cuenta("activoCorriente", periodos13), new Cuenta("pasivoTotal", periodos14), new Cuenta("patrimonioNeto", periodos15));
		Empresa google = new Empresa("google", cuentas5);
		
	    withTransaction(() -> {
	    
	        entityManager().persist(facebook);
	        entityManager().persist(twitter);
	        entityManager().persist(ebay);
	        entityManager().persist(yahoo);
	        entityManager().persist(google);
	       
	    });
  }

	private void crearFiltros() {
	    
		List<Filtro> filtros = new ArrayList<>();
	    filtros.add(new FiltroCreciente());
	    filtros.add(new FiltroDecreciente());
	    filtros.add(new FiltroIgual());
	    //mayor y menor los agrega con la metodologia
	    filtros.add(new FiltroMayorIgual());
	    filtros.add(new FiltroMenorIgual());
	    withTransaction(() -> {
	        filtros.stream().forEach((f -> entityManager().persist(f)));
	      });
	}

	private void crearIndicadoresMetodologia() {

	    Indicador deuda = new Indicador("deuda", "[patrimonioNeto]-[pasivoTotal]");
	    Indicador roe = new Indicador("roe", "[activoCorriente]/[pasivoTotal]");
	    Metodologia metodologia;
	    metodologia = new Metodologia("BUFFET", Arrays.asList(new Condicion(roe, new FiltroMayor() ,1),new Condicion(deuda, new FiltroMenor(),50000)), Arrays.asList(new Orden(deuda,"Ascendente"),new Orden(roe,"Descendente")));
	    entityManager().persist(metodologia);
	   
	    withTransaction(() -> {
	      entityManager().persist(metodologia);
	    });
	}
}
