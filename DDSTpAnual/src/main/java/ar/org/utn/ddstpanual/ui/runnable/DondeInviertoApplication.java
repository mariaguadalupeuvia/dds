package ar.org.utn.ddstpanual.ui.runnable;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.ApplicationContext;

import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.service.impl.IndicadorServiceImpl;
import ar.org.utn.ddstpanual.ui.AbmIndicadoresWindow;

public class DondeInviertoApplication extends Application {

  public static void main(String[] args) {
    new DondeInviertoApplication().start();
  }

  @Override
  protected Window<?> createMainWindow() {
    ApplicationContext.getInstance().configureSingleton(IndicadorService.class,
        new IndicadorServiceImpl());
    return new AbmIndicadoresWindow(this);
  }
}
