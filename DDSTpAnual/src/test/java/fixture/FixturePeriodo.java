package fixture;

import ar.org.utn.ddstpanual.model.Periodo;

public class FixturePeriodo {
  public Periodo periodoValorNulo;
  public Periodo periodo2012;
  public Periodo periodo2013;
  public Periodo periodo2014;
  public Periodo periodo2015;
  public Periodo periodo2016;
  public Periodo periodo2017;
  public Periodo periodoSetFecha;
  public Periodo periodoSetValor;

  public FixturePeriodo() {
    periodoValorNulo = new Periodo();

    periodo2012 = new Periodo("2012", 2000);
    periodo2013 = new Periodo("2013", 3000);
    periodo2014 = new Periodo("2014", 4000);
    periodo2015 = new Periodo("2015", 5000);
    periodo2016 = new Periodo("2016", 6000);
    periodo2017 = new Periodo("2017", 7000);

    periodoSetFecha = new Periodo("2012", 5000);
    periodoSetValor = new Periodo("2011", 5000);

  }
}
