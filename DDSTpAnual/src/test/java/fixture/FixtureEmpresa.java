package fixture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;

public class FixtureEmpresa {
  public Empresa empresaX;
  public Empresa empresaY;
  public Empresa empresaZ;
  public Empresa empresaSinPeriodos;

  public FixtureEmpresa() {
    FixtureCuenta fixtureC = new FixtureCuenta();
    FixturePeriodo fixtureP = new FixturePeriodo();

    List<Cuenta> cuentasX = new ArrayList<Cuenta>();
    cuentasX
        .add(fixtureC.activoCorriente.BuilderCuenta(Arrays.asList(fixtureP.periodo2015.BuilderPeriodo(1000), fixtureP.periodo2016.BuilderPeriodo(2000), fixtureP.periodo2017.BuilderPeriodo(3000))));
    cuentasX
        .add(fixtureC.pasivoCorriente.BuilderCuenta(Arrays.asList(fixtureP.periodo2015.BuilderPeriodo(1500), fixtureP.periodo2016.BuilderPeriodo(2500), fixtureP.periodo2017.BuilderPeriodo(3500))));
    cuentasX.add(fixtureC.pasivoNoCorriente.BuilderCuenta(Arrays.asList(fixtureP.periodo2015.BuilderPeriodo(500), fixtureP.periodo2016.BuilderPeriodo(500), fixtureP.periodo2017.BuilderPeriodo(500))));
    cuentasX
        .add(fixtureC.activoNoCorriente.BuilderCuenta(Arrays.asList(fixtureP.periodo2015.BuilderPeriodo(3000), fixtureP.periodo2016.BuilderPeriodo(5000), fixtureP.periodo2017.BuilderPeriodo(4000))));
    empresaX = new Empresa("EmpresaX", cuentasX);

    List<Cuenta> cuentasY = new ArrayList<Cuenta>();
    cuentasY
        .add(fixtureC.activoCorriente.BuilderCuenta(Arrays.asList(fixtureP.periodo2015.BuilderPeriodo(1000), fixtureP.periodo2016.BuilderPeriodo(2000), fixtureP.periodo2017.BuilderPeriodo(3000))));
    cuentasY.add(fixtureC.ventas.BuilderCuenta(Arrays.asList(fixtureP.periodo2015.BuilderPeriodo(9000), fixtureP.periodo2016.BuilderPeriodo(10000), fixtureP.periodo2017.BuilderPeriodo(8000))));
    cuentasY.add(fixtureC.beneficio.BuilderCuenta(Arrays.asList(fixtureP.periodo2015.BuilderPeriodo(5000), fixtureP.periodo2016.BuilderPeriodo(3000), fixtureP.periodo2017.BuilderPeriodo(4500))));
    cuentasY.add(fixtureC.activoNoCorriente.BuilderCuenta(Arrays.asList(fixtureP.periodo2015.BuilderPeriodo(300), fixtureP.periodo2016.BuilderPeriodo(500), fixtureP.periodo2017.BuilderPeriodo(400))));
    empresaY = new Empresa("EmpresaY", cuentasY);

    empresaZ = new Empresa("EmpresaZ");

  }

}
