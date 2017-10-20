package ar.org.utn.ddstpanual.db;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Indicador;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndicadorDb implements WithGlobalEntityManager, TransactionalOps {

	public void guardarIndicador(Indicador indicador) throws DbException {
		try {
			withTransaction(() -> {
				entityManager().persist(indicador);
			});
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DbException(e.getMessage());
		}
	}

	public void eliminarIndicador(Indicador indicador) throws DbException {
		// TODO Auto-generated method stub

	}

	public List<Indicador> obtenerIndicadores() throws DbException {
		try {
			return entityManager().createQuery("from Indicador", Indicador.class).getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<Indicador> obtenerIndicadoresPorUsuario(int usuario_id) throws DbException {
		try {
			return entityManager().createQuery(
					"from " + Indicador.class.getName() + " i WHERE i.usuario_id IN  (" + usuario_id + ", 0)",
					Indicador.class).getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public boolean exists(Indicador indicador) throws DbException {
		return ((obtenerFormula(indicador.getNombre()).equals(indicador.getFormula()))
				|| (obtenerNombre(indicador.getFormula()).equals(indicador.getNombre())));
	}

	public String obtenerFormula(String nombre) throws DbException {
		try {
			return entityManager().createQuery("from Indicador i WHERE i.nombre LIKE '" + nombre + "'", Indicador.class)
					.setMaxResults(1).getSingleResult().getFormula();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "";
	}

	public String obtenerNombre(String formula) throws DbException {
		try {
			return entityManager()
					.createQuery("from Indicador i WHERE i.formula LIKE '" + formula + "'", Indicador.class)
					.setMaxResults(1).getSingleResult().getNombre();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "";
	}

	public Indicador obtenerIndicador(String nombreIndicador) throws DbException {
		try {
			return entityManager().find(Indicador.class, obtenerId(nombreIndicador));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DbException(e.getMessage());
		}

	}

	private int obtenerId(String nombreIndicador) {
		return entityManager()
				.createQuery("from " + Indicador.class.getName() + " i where i.nombre = '" + nombreIndicador + "'",
						Indicador.class)
				.getSingleResult().getId();
	}

}
