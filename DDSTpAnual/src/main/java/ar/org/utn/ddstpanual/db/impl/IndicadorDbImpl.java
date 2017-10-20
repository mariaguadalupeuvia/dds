package ar.org.utn.ddstpanual.db.impl;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.org.utn.ddstpanual.db.IndicadorDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Indicador;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndicadorDbImpl implements IndicadorDb, WithGlobalEntityManager, TransactionalOps {

	@Override
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

	@Override
	public void eliminarIndicador(Indicador indicador) throws DbException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Indicador> obtenerIndicadores() throws DbException {
		try {
			return entityManager().createQuery("from " + Indicador.class.getName(), Indicador.class).getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public boolean exists(Indicador indicador) throws DbException {
		return (obtenerFormula(indicador.getNombre()).equals(indicador.getFormula()))
				|| (obtenerNombre(indicador.getFormula()).equals(indicador.getNombre()));
	}

	@Override
	public String obtenerFormula(String nombre) throws DbException {
		try {
			return entityManager().createQuery("from Indicador i WHERE i.nombre LIKE '" + nombre + "'", Indicador.class)
					.setMaxResults(1).getSingleResult().getFormula();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public String obtenerNombre(String formula) throws DbException {
		try {
			return entityManager().createQuery("from Indicador i WHERE i.formula LIKE '" + formula + "'", Indicador.class)
					.setMaxResults(1).getSingleResult().getNombre();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new DbException(e.getMessage());
		}
	}

	@Override
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
