package mylearning.servicecomb.metrics;

import org.apache.servicecomb.foundation.metrics.MetricsBootstrapConfig;
import org.apache.servicecomb.foundation.metrics.MetricsInitializer;
//import org.apache.servicecomb.foundation.metrics.registry.GlobalRegistry;

import com.google.common.eventbus.EventBus;
import com.netflix.spectator.api.Registry;
import com.netflix.spectator.api.Spectator;
import com.netflix.spectator.jvm.Jmx;

public class JvmMetersInitializer  {
//  implements MetricsInitializer
  private Registry registry;

//  @Override
//  public void init(GlobalRegistry globalRegistry, EventBus eventBus, MetricsBootstrapConfig config) {
//    registry = globalRegistry.getDefaultRegistry();
//    Jmx.registerStandardMXBeans(registry);
//    
//  }
}
