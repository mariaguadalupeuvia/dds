package ar.org.utn.ddstpanual.ui.runnable;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.ApplicationContext;

import ar.org.utn.ddstpanual.service.EmpresaService;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;
import ar.org.utn.ddstpanual.service.impl.IndicadorServiceImpl;
import ar.org.utn.ddstpanual.ui.MenuPrincipalWindow;

public class DondeInviertoApplication extends Application {

  public static void main(final String[] args) {
    new DondeInviertoApplication().start();
  }

  @Override
  protected Window<?> createMainWindow() {
    ApplicationContext.getInstance().configureSingleton(IndicadorService.class, new IndicadorServiceImpl());
    ApplicationContext.getInstance().configureSingleton(EmpresaService.class, new EmpresaServiceImpl());
    return new MenuPrincipalWindow(this);
  }
}
