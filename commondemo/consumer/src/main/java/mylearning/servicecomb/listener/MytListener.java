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
        LOG.info("cse.darklaunch.policy.provider: {}.",
            DynamicPropertyFactory.getInstance().getStringProperty("cse.darklaunch.policy.provider", "").get());
        break;
      default:
        break;
    }
  }
}
