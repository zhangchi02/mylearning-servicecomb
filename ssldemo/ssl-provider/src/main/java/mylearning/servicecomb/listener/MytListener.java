package mylearning.servicecomb.listener;

import org.apache.servicecomb.core.BootListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.config.DynamicPropertyFactory;

@Component
public class MytListener implements BootListener {
  private static final Logger LOG = LoggerFactory.getLogger(MytListener.class);

  @Override
  public void onBootEvent(BootEvent event) {
    switch (event.getEventType()) {
      case BEFORE_PRODUCER_PROVIDER:
        LOG.info("cse.rest.address: {}.",
            DynamicPropertyFactory.getInstance().getStringProperty("cse.rest.address", "").get());
        LOG.info("cse.service.publishAddress: {}.",
            DynamicPropertyFactory.getInstance().getStringProperty("cse.service.publishAddress", "").get());
        LOG.info("cse.rest.publishPort: {}.",
            DynamicPropertyFactory.getInstance().getStringProperty("cse.rest.publishPort", "").get());
        LOG.info("cse.config.client.serverUri: {}.",
            DynamicPropertyFactory.getInstance().getStringProperty("cse.config.client.serverUri", "").get());
        break;
      default:
        break;
    }
  }
}
