package com.grandprix.gpline.mm.utils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Log {

    public static void debug(String msg) {
        log.debug(msg);
    }

    public static void info(String msg) {
        log.info(msg);
    }

    public static void warn(String msg) {
        log.warn(msg);
    }

    public static void error(String msg) {
        log.error(msg);
    }

    public static void debug(String msg, Throwable exception) {
        log.debug(msg, exception);
    }

    public static void info(String msg, Throwable exception) {
        log.info(msg, exception);
    }

    public static void warn(String msg, Throwable exception) {
        log.warn(msg, exception);
    }

    public static void error(String msg, Throwable exception) {
        log.error(msg, exception);
    }

}
