package ar.org.utn.ddstpanual.model.metodologia;

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
import javax.persistence.Transient;

import ar.org.utn.ddstpanual.db.IndicadorPrecalculadoDb;
import ar.org.utn.ddstpanual.db.MetodologiaDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  int usuario_id;
  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "metodologia_id")
  private List<Condicion> condiciones;

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "metodologia_id")
  @OrderColumn(name = "prioridad")
  private List<Orden> ordenes;

  @Transient
  private static MetodologiaDb metododologiaDb = new MetodologiaDb();
  @Transient
  private static IndicadorPrecalculadoDb indicadorPrecalculadoDb = new IndicadorPrecalculadoDb();

  public Metodologia(String nombre, List<Condicion> condiciones, List<Orden> ordenes) {
    this.nombre = nombre;
    this.condiciones = condiciones;
    this.ordenes = ordenes;
  }

  public void guardarMetodologia(Metodologia metodologia) throws ServiceException {
    try {
      metododologiaDb.guardarMetodologia(metodologia);
    } catch (DbException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  public List<Empresa> ejecutarMetodologia(List<Empresa> empresas, Periodo periodo) {
    empresas = empresas.stream().filter(e -> {
      return cumpleCondiciones(e, periodo);
    }).collect(Collectors.toList());

    Collections.sort(empresas, (e1, e2) -> {
      int comp = 0;
      try {
        comp = compararEmpresas(e1, e2, periodo.getFecha());
      } catch (Exception e3) {
        e3.getMessage();
      }
      return comp;
    });

    return empresas;
  }

  private Boolean cumpleCondiciones(Empresa empresa, Periodo periodo) {
    return condiciones.stream().allMatch(c -> {
      try {
        return c.cumpleCondicion(empresa, periodo);
      } catch (DbException e) {
        return false;
      }
    });
  }

  private int compararEmpresas(Empresa e1, Empresa e2, String periodo) throws ServiceException, DbException {
    int flag = 0;

    for (Orden orden : this.ordenes) {
      Indicador indicador = orden.getIndicador();

      Double valorE1 = indicadorPrecalculadoDb.obtenerIndicadorPrecalculado(e1, indicador, periodo).getValorIndicador();
      Double valorE2 = indicadorPrecalculadoDb.obtenerIndicadorPrecalculado(e2, indicador, periodo).getValorIndicador();

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
