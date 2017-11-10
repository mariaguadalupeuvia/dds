package ar.org.utn.ddstpanual.server;

import java.util.Timer;
import java.util.TimerTask;

import ar.org.utn.ddstpanual.service.JobService;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
  public static void main(String[] args) {
    TimerTask timerTask = new JobService();
    Timer timer = new Timer(true);
    timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);

    Spark.port(8080);
    DebugScreen.enableDebugScreen();
    Router.configure();
  }

}
