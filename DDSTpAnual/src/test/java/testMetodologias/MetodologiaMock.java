package testMetodologias;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.Orden;

public class MetodologiaMock {
  Metodologia metodologia = new Metodologia();

  public int compararEmpresas(Empresa e1, Empresa e2, String periodo) throws DbException {
    int flag = 0;

    for (Orden orden : metodologia.getOrdenes()) {
      Indicador indicador = orden.getIndicador();

      Double valorE1;
      try {
        valorE1 = indicador.ejecutarIndicador(periodo, e1);
        Double valorE2 = indicador.ejecutarIndicador(periodo, e2);

        if (orden.getTipoOrden().equals("Ascendente")) {
          flag = Double.compare(valorE1, valorE2);
        }

        if (orden.getTipoOrden().equals("Descendente")) {
          flag = Double.compare(valorE2, valorE1);
        }

        if (flag != 0) {
          break;
        }

      } catch (ArbolException e) {
        e.printStackTrace();
      }

    }
    return flag;
  }

  public void setOrdenes(List<Orden> ordenes) {
    metodologia.setOrdenes(ordenes);
  }
}
