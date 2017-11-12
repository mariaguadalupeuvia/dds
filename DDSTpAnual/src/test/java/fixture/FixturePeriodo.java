package fixture;

import ar.org.utn.ddstpanual.model.Periodo;

public class FixturePeriodo {
  public FixturePeriodo(){
    Periodo periodoNulo = new Periodo();
    Periodo periodoValorCero = new Periodo("2017");
    Periodo periodo2013 = new Periodo("2013", 3000);
    Periodo periodo2014 = new Periodo("2014", 4000);   
  }
}
