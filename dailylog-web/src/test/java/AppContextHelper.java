

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContextHelper {
	private static AppContextHelper instance = null;
	private static Logger log = LoggerFactory.getLogger(AppContextHelper.class);
	private ClassPathXmlApplicationContext ctx;

	private AppContextHelper() {
		ctx = new ClassPathXmlApplicationContext("classpath*:*_config.xml");
	}

	public static void destroy() {
		instance = null;
	}

	private static void init() {
		if (null == instance) {

			synchronized (AppContextHelper.class) {
				if (instance == null) {
					long start = System.currentTimeMillis();

					log.info("Initializing Spring context...");

					try {
						instance = new AppContextHelper();
						log.info("Initialized in {} ms.", (System.currentTimeMillis() - start));
					} catch (Exception e) {
						log.error(null, e);
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static AppContextHelper getInstance() {
		if (null == instance)
			init();
		return instance;
	}

	public Object getBean(String name) {
		return ctx.getBean(name);
	}

	public <T> T getBean(Class<T> clazz) {
		return ctx.getBean(clazz);
	}
}
