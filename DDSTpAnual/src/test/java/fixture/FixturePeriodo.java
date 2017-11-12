package fixture;

import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.model.Periodo;

public class FixturePeriodo {
  public Periodo periodoValorNulo;
  public Periodo periodo2013;
  public Periodo periodo2014;
  public Periodo periodoSetFecha;
  public Periodo periodoSetValor;
  public List<Periodo> periodosA;
  public List<Periodo> periodosB;
  
  public FixturePeriodo(){
    periodoValorNulo = new Periodo();
    periodo2013 = new Periodo("2013", 3000);
    periodo2014 = new Periodo("2014", 4000); 
    periodoSetFecha = new Periodo("2012", 5000);
    periodoSetValor = new Periodo("2011", 5000);
    
    periodosA = new ArrayList<Periodo>();
    periodosA.add(new Periodo("2013", 1000));
    periodosA.add(new Periodo("2014", 2000));
    periodosA.add(new Periodo("2015", 3000));
    periodosA.add(new Periodo("2016", 4000));
    periodosA.add(new Periodo("2017", 5000));
    
    periodosB = new ArrayList<Periodo>();
    periodosB.add(new Periodo("2013", 1500));
    periodosB.add(new Periodo("2014", 2500));
    periodosB.add(new Periodo("2015", 3500));
    periodosB.add(new Periodo("2016", 4000));
    periodosB.add(new Periodo("2017", 5500));
    
  }
}
