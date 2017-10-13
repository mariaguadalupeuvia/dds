package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

import Repositorio.RepositorioMetodologias;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Observable
@Entity
@Table(name = "METODOLOGIA")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Metodologia {

	@Id
	@GeneratedValue
	private int id;

	private String nombre;
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "metodologia_id")
	private List<Condicion> condiciones;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "metodologia_id")
	@OrderColumn(name = "prioridad")
	private List<Orden> ordenes;

	public Metodologia(String nombre, List<Condicion> condiciones, List<Orden> ordenes) {
		this.nombre = nombre;
		this.condiciones = condiciones;
		this.ordenes = ordenes;
	}

	public void guardarMetodologia(Metodologia metodologia) {
		RepositorioMetodologias.instancia.guardarMetodologia(metodologia);
	}

	public List<Empresa> ejecutarMetodologia(List<Empresa> empresas, Periodo periodo) {

		empresas = empresas.stream().filter(e -> cumpleCondiciones(e, periodo)).collect(Collectors.toList());

		Collections.sort(empresas, (e1, e2) -> {
			int comp = 0;
			try {
				comp = compararEmpresas(e1, e2, ordenes, periodo);
			} catch (Exception e3) {
				e3.getMessage();
			}
			return comp;
		});

		return empresas;
	}

	private Boolean cumpleCondiciones(Empresa empresa, Periodo periodo) {
		return condiciones.stream().allMatch(c -> c.cumpleCondicion(empresa, periodo));
	}

	private int compararEmpresas(Empresa e1, Empresa e2, List<Orden> ordenes, Periodo per) throws ServiceException {
		int flag = 0;

		for (Orden orden : ordenes) {
			Indicador indicador = orden.getIndicador();

			Double valorE1 =indicador.ejecutarIndicador(per.getFecha(), e1);
			Double valorE2 =indicador.ejecutarIndicador(per.getFecha(), e2);
			
			if (orden.getTipoOrden().equals("Ascendente")) {
				flag = Double.compare(valorE1, valorE2);
			}

			if (orden.getTipoOrden().equals("Descendente")) {
				flag = Double.compare(valorE2, valorE1);
			}

			if (flag != 0) {
				break;
			}
		}
		return flag;
	}

	public String toJson() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("\"nombre\" : ");
		builder.append("\"" + nombre + "\"");
		builder.append(",");
		builder.append("\"condiciones\" : [");
		if (condiciones.size() > 0) {
			for (Condicion condicion : condiciones) {
				builder.append(condicion.toJson());
				builder.append(",");
			}
			builder.deleteCharAt(builder.length() - 1);
		}
		builder.append("\"ordenes\" : [");
		if (ordenes.size() > 0) {
			for (Orden orden : ordenes) {
				builder.append(orden.toJson());
				builder.append(",");
			}
			builder.deleteCharAt(builder.length() - 1);
		}
		builder.append("]");
		builder.append("}");
		return builder.toString();
	}
}
