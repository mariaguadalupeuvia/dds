package ar.org.utn.ddstpanual.controllerOld;

import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

import ar.org.utn.ddstpanual.db.MetodologiaDb;
import ar.org.utn.ddstpanual.db.impl.MetodologiaDbImpl;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.service.EmpresaService;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Observable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MetodologiasController implements WithGlobalEntityManager {

	private MetodologiaDb metododologiaDb = new MetodologiaDbImpl();
	EmpresaService empresaService = new EmpresaServiceImpl();
	List<Empresa> empresas;
	List<Metodologia> metodologias;
	Metodologia metodologiaCheckbox;
	Periodo periodoCheckbox;
	List<Periodo> periodos;
	List<Empresa> empresasResultado;

	String error;

	public void obtenerEmpresas() {
		error = "";
		try {
			empresas = getEmpresaService().obtenerEmpresas();
		} catch (final ServiceException e) {
			error = "Se produjo un error al obtener las empresas.";
		}
	}

	public void obtenerMetodologia(String nombre) {
		try {
			metodologiaCheckbox = metododologiaDb.obtenerMetodologia(nombre);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}

	public void obtenerMetodologias() {
		try {
			metodologias = metododologiaDb.obtenerMetodologias();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> obtenerPeriodos() {
		try {
			periodos = entityManager().createQuery("from Periodo", Periodo.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void ejecutarMetodologia() {
		error = "";
		try {
			empresasResultado = metodologiaCheckbox.ejecutarMetodologia(empresas, periodoCheckbox);
			if (empresasResultado.isEmpty())
				error = "No se encuentran empresas que cumplan estas condiciones \n para el año "
						+ periodoCheckbox.getFecha();
		} catch (final NullPointerException n) {
			error = "Debe completar todos los campos";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inicializarVariables() {
		error = "";
		obtenerEmpresas();
		obtenerMetodologias();
		obtenerPeriodos();
	}
}
