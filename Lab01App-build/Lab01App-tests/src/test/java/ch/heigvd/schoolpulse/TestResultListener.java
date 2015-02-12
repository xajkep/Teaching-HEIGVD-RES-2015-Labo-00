package ch.heigvd.schoolpulse;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/**
 *
 * @author Olivier Liechti
 */
public class TestResultListener extends RunListener {

  private static final Logger LOG = Logger.getLogger(TestResultListener.class.getName());
  private WebTarget target;
  
  
  public TestResultListener() {
    Client client = ClientBuilder.newClient().register(JacksonFeature.class);
    target = client.target("http://iflux.herokuapp.com/").path("events");

  }

  @Override
  public void testStarted(Description description) throws Exception {
    LOG.log(Level.INFO, "Test started: {0}", description.getClassName());
    LOG.log(Level.INFO, System.getProperty("schoolPulseUserId"));
    Event[] payload = new Event[1];
    Event e1 = new Event();
    e1.setSource("RES");
    e1.setTimestamp(new Date());
    e1.setType("io.iflux.schoolpulse.test");
    e1.set("who", "olivier");
    e1.set("pulseId", System.getProperty("schoolPulseUserId"));
    payload[0] = e1;
    Response response = target.request().post(Entity.json(payload));
  }

  @Override
  public void testRunStarted(Description description) throws Exception {
    LOG.log(Level.INFO, "Test run started: {0}", description.getClassName());
  }

  @Override
  public void testRunFinished(Result result) throws Exception {
    LOG.log(Level.INFO, "{0} tests have been run.", result.getRunCount());
    LOG.log(Level.INFO, "{0} failures have been reported", result.getFailureCount());
    for (Failure failure : result.getFailures()) {
      LOG.log(Level.INFO, "{0}, {1}", new Object[]{failure.getDescription(), failure.getMessage()});
    }
  }

}
