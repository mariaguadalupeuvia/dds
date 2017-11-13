package ar.org.utn.ddstpanual.server;

import java.util.Timer;
import java.util.TimerTask;

import ar.org.utn.ddstpanual.service.JobService;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.servlet.SparkApplication;

public class Server implements SparkApplication {

  public static void main(String[] args) {
    TimerTask timerTask = new JobService();
    Timer timer = new Timer(true);
    timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);
    Spark.port(8080);
    DebugScreen.enableDebugScreen();
    Router.configure();
  }

  @Override
  public void init() {
    TimerTask timerTask = new JobService();
    Timer timer = new Timer(true);
    timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);
    Spark.port(8080);
    DebugScreen.enableDebugScreen();
    Router.configure();
  }
}
