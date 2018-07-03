package com.xiaochao.mycfms.util;

import java.util.Enumeration;
import java.util.logging.Logger;

import org.apache.logging.log4j.jul.LogManager;

public class LoggerFactory {

    private static LogManager logManager = new LogManager();

    public static Logger getLogger(String name) {
        return logManager.getLogger(name);
    }

    public static Enumeration<String> getLoggerNames() {
        return logManager.getLoggerNames();
    }
}
