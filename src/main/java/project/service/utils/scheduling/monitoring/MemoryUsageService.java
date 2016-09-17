package project.service.utils.scheduling.monitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Green-L on 17.09.2016.
 */
public class MemoryUsageService {

    private static Logger log = LoggerFactory.getLogger(MemoryUsageService.class.getName());

    private final static int MB = 1024*1024;
    private final static Runtime RUNTIME = Runtime.getRuntime();
    private static StringBuilder message = new StringBuilder();

    public static void logMemoryUsage() {
        message.setLength(0);
        message.append("Used Memory:" + (RUNTIME.totalMemory() - RUNTIME.freeMemory()) / MB);
        message.append("\tFree Memory:" + RUNTIME.freeMemory() / MB);
        message.append("\tTotal Memory:" + RUNTIME.totalMemory() / MB);
        message.append("\tMax Memory:" + RUNTIME.maxMemory() / MB);
        log.info(message.toString());
    }
}
