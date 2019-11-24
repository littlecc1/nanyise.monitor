package top.wello.monitor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    Logger logger = LoggerFactory.getLogger(ConfigService.class);

    private String defaultConfigPath = "https://healthmeal.oss-cn-beijing.aliyuncs.com/config/config";
    private String lastConfig = "{}";
    private long lastFetchTime = 0;
    private long minInterval = 1000 * 60;

    public String getConfig() {
        long now = System.currentTimeMillis();
        if (now - lastFetchTime < minInterval) {
            logger.info("no need to fetch config");
            return lastConfig;
        }
        lastFetchTime = now;
        String ret = NetworkService.get(defaultConfigPath);
        if (ret != null) {
            if (!ret.equals(lastConfig)) {
                logger.info("config changed: " + ret);
            }
            lastConfig = ret;
        }
        return lastConfig;
    }
}
