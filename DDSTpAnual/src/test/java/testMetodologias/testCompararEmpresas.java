package testMetodologias;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Orden;

public class testCompararEmpresas 
{
	MetodologiaMock metodologia = null;
	 
	 @Test
	 public void testOrdenAscendenteOk() throws ServiceException, DbException
	 {
		 metodologia = new MetodologiaMock();
		 Indicador deuda = new Indicador("deuda", "[pasivoTotal]");
		 List<Cuenta> cuentasEmpresa1 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 200))));
		 Empresa empresa1 = new Empresa("E1", cuentasEmpresa1);
		 List<Cuenta> cuentasEmpresa2 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 100))));
         Empresa empresa2 = new Empresa("E2", cuentasEmpresa2);
		 metodologia.setOrdenes(Arrays.asList(new Orden(deuda,"Ascendente")));

		 assertTrue(metodologia.compararEmpresas(empresa1, empresa2, "2017") == 1);  
	 }
	 
	 @Test
	 public void testOrdenAscendenteInvertido() throws ServiceException, DbException
	 {
		 metodologia = new MetodologiaMock();
		 Indicador deuda = new Indicador("deuda", "[pasivoTotal]");
		 List<Cuenta> cuentasEmpresa1 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 100))));
		 Empresa empresa1 = new Empresa("E1", cuentasEmpresa1);
		 List<Cuenta> cuentasEmpresa2 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 200))));
         Empresa empresa2 = new Empresa("E2", cuentasEmpresa2);
		 metodologia.setOrdenes(Arrays.asList(new Orden(deuda,"Ascendente")));

		 assertTrue(metodologia.compararEmpresas(empresa1, empresa2, "2017") == -1);  
	 }
	 
	 @Test
	 public void testOrdenDescendenteOk() throws ServiceException, DbException
	 {
		 metodologia = new MetodologiaMock();
		 Indicador deuda = new Indicador("deuda", "[pasivoTotal]");
		 List<Cuenta> cuentasEmpresa1 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 100))));
		 Empresa empresa1 = new Empresa("E1", cuentasEmpresa1);
		 List<Cuenta> cuentasEmpresa2 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 200))));
         Empresa empresa2 = new Empresa("E2", cuentasEmpresa2);
		 metodologia.setOrdenes(Arrays.asList(new Orden(deuda,"Descendente")));

		 assertTrue(metodologia.compararEmpresas(empresa1, empresa2, "2017") == 1); 
	 }
	 
	 @Test
	 public void testOrdenDescendenteInvertido() throws ServiceException, DbException
	 {
		 metodologia = new MetodologiaMock();
		 Indicador deuda = new Indicador("deuda", "[pasivoTotal]");
		 List<Cuenta> cuentasEmpresa1 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 200))));
		 Empresa empresa1 = new Empresa("E1", cuentasEmpresa1);
		 List<Cuenta> cuentasEmpresa2 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 100))));
         Empresa empresa2 = new Empresa("E2", cuentasEmpresa2);
		 metodologia.setOrdenes(Arrays.asList(new Orden(deuda,"Descendente")));

		 assertTrue(metodologia.compararEmpresas(empresa1, empresa2, "2017") == -1); 
	 }
	 
	 @Test
	 public void testOrdenMismoValor() throws ServiceException, DbException
	 {
		 metodologia = new MetodologiaMock();
		 Indicador deuda = new Indicador("deuda", "[pasivoTotal]");
		 List<Cuenta> cuentasEmpresa1 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 100))));
		 Empresa empresa1 = new Empresa("E1", cuentasEmpresa1);
		 List<Cuenta> cuentasEmpresa2 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 100))));
         Empresa empresa2 = new Empresa("E2", cuentasEmpresa2);
		 metodologia.setOrdenes(Arrays.asList(new Orden(deuda,"Ascendente")));

		 assertTrue(metodologia.compararEmpresas(empresa1, empresa2, "2017") == 0); 
	 }
	 
	 @Test
	 public void testDosOrdenesOk() throws ServiceException, DbException
	 {
		 metodologia = new MetodologiaMock();
		 Indicador deuda = new Indicador("deuda", "[pasivoTotal]");
		 Indicador roe = new Indicador("roe", "[pasivoTotal] - 50");
		 List<Cuenta> cuentasEmpresa1 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 200))));
		 Empresa empresa1 = new Empresa("E1", cuentasEmpresa1);
		 List<Cuenta> cuentasEmpresa2 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 100))));
         Empresa empresa2 = new Empresa("E2", cuentasEmpresa2);
		 metodologia.setOrdenes(Arrays.asList(new Orden(deuda,"Ascendente"),new Orden(roe,"Ascendente")));

		 assertTrue(metodologia.compararEmpresas(empresa1, empresa2, "2017") == 1); 
	 }
	 
	 @Test
	 public void testDosOrdenesInvertidosOk() throws ServiceException, DbException
	 {
		 metodologia = new MetodologiaMock();
		 Indicador deuda = new Indicador("deuda", "[pasivoTotal]");
		 Indicador roe = new Indicador("roe", "[pasivoTotal] - 50");
		 List<Cuenta> cuentasEmpresa1 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 200))));
		 Empresa empresa1 = new Empresa("E1", cuentasEmpresa1);
		 List<Cuenta> cuentasEmpresa2 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 100))));
         Empresa empresa2 = new Empresa("E2", cuentasEmpresa2);
		 metodologia.setOrdenes(Arrays.asList(new Orden(deuda,"Descendente"),new Orden(roe,"Ascendente")));

		 assertTrue(metodologia.compararEmpresas(empresa1, empresa2, "2017") == -1); 
	 }
	 
	 @Test (expected = NullPointerException.class) 
	 public void testSinOrden() throws ServiceException, DbException
	 {
		 metodologia = new MetodologiaMock();
		 List<Cuenta> cuentasEmpresa1 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 200))));
		 Empresa empresa1 = new Empresa("E1", cuentasEmpresa1);
		 List<Cuenta> cuentasEmpresa2 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 100))));
         Empresa empresa2 = new Empresa("E2", cuentasEmpresa2);
         
		 metodologia.compararEmpresas(empresa1, empresa2, "2017"); 
	 }

	 @Test
	 public void testIndicadorOrdenSinValorCalculable() throws ServiceException, DbException //ver esto 
	 {
		 metodologia = new MetodologiaMock();
		 Indicador deuda = new Indicador("deuda", "[pasivoTotal]");
		 List<Cuenta> cuentasEmpresa1 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 100))));
		 Empresa empresa1 = new Empresa("E1", cuentasEmpresa1);
		 List<Cuenta> cuentasEmpresa2 = Arrays.asList(new Cuenta("activo", Arrays.asList(new Periodo("2017", 200))));
         Empresa empresa2 = new Empresa("E2", cuentasEmpresa2);
		 metodologia.setOrdenes(Arrays.asList(new Orden(deuda,"Ascendente")));

		 assertTrue(metodologia.compararEmpresas(empresa1, empresa2, "2017") == 1); 
	 }
//	 @Test
//	 public void testIndicadorOrdenValorInfinito() throws ServiceException, DbException
//	 {
//		 metodologia = new MetodologiaMock();
//		 Indicador deuda = new Indicador("deuda", "[pasivoTotal]/[activo]");
//		 List<Cuenta> cuentasEmpresa1 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 100))));
//		 Empresa empresa1 = new Empresa("E1", cuentasEmpresa1);
//		 List<Cuenta> cuentasEmpresa2 = Arrays.asList(new Cuenta("pasivoTotal", Arrays.asList(new Periodo("2017", 100))), new Cuenta("activo", Arrays.asList(new Periodo("2017", 100))));
//         Empresa empresa2 = new Empresa("E2", cuentasEmpresa2);
//		 metodologia.setOrdenes(Arrays.asList(new Orden(deuda,"Ascendente")));
//
//		 assertTrue(metodologia.compararEmpresas(empresa1, empresa2, "2017") == 1); 
//	 }

}

