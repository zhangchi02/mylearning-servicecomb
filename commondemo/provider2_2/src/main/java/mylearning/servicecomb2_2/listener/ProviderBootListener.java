package mylearning.servicecomb2_2.listener;

import org.apache.servicecomb.core.BootListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
@Component
public class ProviderBootListener implements BootListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProviderBootListener.class);

	public void onBootEvent(BootEvent bootEvent) {
		// if (EventType.AFTER_REGISTRY.equals(bootEvent.getEventType())) {
		// return;
		// }

		// LOGGER.info("========================================");
		// LOGGER.info("Service startup completed!");
		// LOGGER.info("========================================");
		// BootEvent中的EventType有多种
		switch (bootEvent.getEventType()) {
		case AFTER_REGISTRY: // 微服务实例注册成功
			LOGGER.info("=============================");
			LOGGER.info("Service startup completed!");
			LOGGER.info("=============================");
			break;
		case BEFORE_CLOSE: // 微服务进程即将退出
			LOGGER.info("=============================");
			LOGGER.info("JVM process is closing!");
			LOGGER.info("=============================");
			break;
		default:
		}
	}
}
