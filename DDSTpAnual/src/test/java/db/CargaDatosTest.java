package db;

import java.util.List;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import ar.org.utn.ddstpanual.archivo.impl.EmpresaArchivoImpl;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Empresa;

public class CargaDatosTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	public CargaDatosTest() {
		try {
			List<Empresa> empresas = new EmpresaArchivoImpl().obtenerEmpresas();
			entityManager().getTransaction().begin();
			for (Empresa e : empresas) {
				entityManager().persist(e);
			}
			entityManager().getTransaction().commit();

		} catch (ArchivoException e) {
			// TODO Auto-generated catch block
		}
	}

	@Test
	public void testObtenerEmpresa() {
		withTransaction(() -> {
			entityManager().createQuery("from Empresa e ").getFirstResult();
		});
	}
}
