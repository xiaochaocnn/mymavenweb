package com.xiaochao.mycfms.util;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.alibaba.fastjson.JSON;
import com.xiaochao.mycfms.util.domain.CoreError;
import com.xiaochao.mycfms.util.domain.CoreException;
import com.xiaochao.mycfms.util.domain.InterfaceArgs;
import com.xiaochao.mycfms.util.domain.MessageArgs;

/**
 * 日志工具类
 * 
 * @author CT
 *
 */
public class LogUtil {

    /**
     * @Title: logMessage
     * @Description 消息日志(包括错误日志)
     * @param param
     * @author CT
     */
    public static void logMessage(MessageArgs param) {
        Logger log = LoggerFactory.getLogger("message");
        String msg = formatArgs(param);
        log.logp(Level.INFO, "", "", DateUtil.getCurDateTime("yyyy-MM-dd HH:mm:ss:SSS ") + msg);
    }

    private static String formatArgs(MessageArgs param) {
        return JSON.toJSONString(param);
    }

    /**
     * @Title: logInterface
     * @Description 接口调用日志
     * @param param 参数实体
     * @author CT
     */
    public static void logInterface(InterfaceArgs param) {
        Logger log = LoggerFactory.getLogger("interface");
        String msg = param.toString();
        // Level lev = getLevel(param.getMsgCode());
        log.logp(Level.INFO, "", "", DateUtil.getCurDateTime("yyyy-MM-dd HH:mm:ss:SSS ") + msg);
    }

    /**
     * @Title: logError
     * @Description:输出throwable消息
     * @param thrown
     * @author CT
     */
    @Deprecated
    public static void logError(Throwable thrown) {
        logError(null, thrown, null, null);
    }

    /**
     * @Title: logError
     * @Description 输出自定义错误消息
     * @param message
     * @author CT
     */
    @Deprecated
    public static void logError(String message) {
        logError(message, null, null, null);
    }

    /**
     * @Title: logError
     * @Description 输出自定义错误消息
     * @param message
     * @author CT
     */
    @Deprecated
    public static void logError(String message, Object obj) {
        logError(message, null, null, obj);
    }

    /**
     * @Title: logError
     * @Description 输出自定义错误消息
     * @param message
     * @author CT
     */
    @Deprecated
    public static void logError(String message, Map<String, String> map) {
        logError(message, null, map, null);
    }

    /**
     * @Title: logError
     * @Description 输出自定义消息和thrown消息
     * @param message
     * @param thrown
     * @author CT
     */
    @Deprecated
    public static void logError(String message, Throwable thrown) {
        logError(message, thrown, null, null);
    }

    /**
     * @Title: logError
     * @Description 输出自定义消息和thrown消息
     * @param message
     * @param thrown
     * @author CT
     */
    @Deprecated
    public static void logError(String message, Throwable thrown, Map<String, String> map) {
        logError(message, thrown, map, null);
    }

    /**
     * @Title: logError
     * @Description 输出自定义消息和thrown消息
     * @param message
     * @param thrown
     * @author CT
     */
    @Deprecated
    public static void logError(String message, Throwable thrown, Map<String, String> map, Object obj) {
        String stackFlag = IDGenerator.getUID() + " ";
        Logger log = LoggerFactory.getLogger("message");
        if (null != message && !message.trim().equals("")) {
            log.log(Level.SEVERE, message.replace(":", ":" + stackFlag));
        } else {
            throw new NullPointerException();
        }

        String time = DateUtil.getCurDateTime("yyyy-MM-dd HH:mm:ss:SSS ");
        if (thrown != null) {

            StackTraceElement[] stackArray = thrown.getStackTrace();
            log.log(Level.INFO, time + stackFlag + thrown.getClass().getName());
            log.log(Level.INFO, time + stackFlag + thrown.getMessage());
            for (StackTraceElement temp : stackArray) {
                log.log(Level.INFO, time + stackFlag + temp.toString());
            }
            Throwable cause = thrown.getCause();
            if (cause != null) {
                log.log(Level.INFO, time + stackFlag + cause.getClass().getName());
                log.log(Level.INFO, time + stackFlag + cause.getMessage());
                stackArray = cause.getStackTrace();
                for (StackTraceElement temp : stackArray) {
                    log.log(Level.INFO, time + stackFlag + temp.toString());
                }
            }
        }
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            String key;
            StringBuilder sb = new StringBuilder("{");
            while (it.hasNext()) {
                key = it.next();
                sb.append(key).append("=").append(map.get(key)).append(" ");
            }
            sb.append("]");
            log.log(Level.INFO, time + stackFlag + sb.toString());
        }
        if (obj != null) {
            log.log(Level.INFO, time + stackFlag + JSON.toJSONString(obj));
        }
    }

    /**
     * @Title: logError
     * @Description 输出自定义消息和thrown消息
     * @param message
     * @param thrown
     * @author CT
     */
    public static void logError(CoreException thrown, Map<String, String> map) {
        logError(thrown, map, null);
    }

    /**
     * @Title: logError
     * @Description 输出自定义消息和thrown消息
     * @param message
     * @param thrown
     * @author CT
     */
    public static void logError(CoreException thrown, Object obj) {
        logError(thrown, null, obj);
    }

    /**
     * 输出throwable标准方法
     * 
     * @param args
     * @param thrown
     */
    public static void logError(MessageArgs args, Throwable thrown) {
        String stackFlag = IDGenerator.getUID() + " ";
        Logger log = LoggerFactory.getLogger("message");
        log.log(Level.SEVERE, args.getCode() + ":" + args.getDesc());

        String time = DateUtil.getCurDateTime("yyyy-MM-dd HH:mm:ss:SSS ");
        StackTraceElement[] stackArray = thrown.getStackTrace();
        log.log(Level.INFO, time + stackFlag + thrown.getClass().getName());
        log.log(Level.INFO, time + stackFlag + thrown.getMessage());
        for (StackTraceElement temp : stackArray) {
            log.log(Level.INFO, time + stackFlag + temp.toString());
        }
        Throwable cause = thrown.getCause();
        if (cause != null) {
            log.log(Level.INFO, time + stackFlag + cause.getClass().getName());
            log.log(Level.INFO, time + stackFlag + cause.getMessage());
            stackArray = cause.getStackTrace();
            for (StackTraceElement temp : stackArray) {
                log.log(Level.INFO, time + stackFlag + temp.toString());
            }
        }
    }

    /**
     * 输出CoreException标准方法
     * 
     * @param thrown
     * @param map
     * @param obj
     */
    public static void logError(CoreException thrown, Map<String, String> map, Object obj) {
        String stackFlag = IDGenerator.getUID() + " ";
        Logger log = LoggerFactory.getLogger("message");
        // log.log(Level.SEVERE, message.replace(":", ":" + stackFlag));
        MessageArgs args = thrown.getMsgArgs();
        log.log(Level.SEVERE, args.getCode() + ":" + stackFlag + args.getDesc());

        String time = DateUtil.getCurDateTime("yyyy-MM-dd HH:mm:ss:SSS ");
        StackTraceElement[] stackArray = thrown.getStackTrace();
        log.log(Level.INFO, time + stackFlag + thrown.getClass().getName());
        log.log(Level.INFO, time + stackFlag + thrown.getMessage());
        for (StackTraceElement temp : stackArray) {
            log.log(Level.INFO, time + stackFlag + temp.toString());
        }
        Throwable cause = thrown.getCause();
        if (cause != null) {
            log.log(Level.INFO, time + stackFlag + cause.getClass().getName());
            log.log(Level.INFO, time + stackFlag + cause.getMessage());
            stackArray = cause.getStackTrace();
            for (StackTraceElement temp : stackArray) {
                log.log(Level.INFO, time + stackFlag + temp.toString());
            }
        }
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            String key;
            StringBuilder sb = new StringBuilder("{");
            while (it.hasNext()) {
                key = it.next();
                sb.append(key).append("=").append(map.get(key)).append(" ");
            }
            sb.append("]");
            log.log(Level.INFO, time + stackFlag + sb.toString());
        }
        if (obj != null) {
            log.log(Level.INFO, time + stackFlag + JSON.toJSONString(obj));
        }
    }

    /**
     * 输出CoreError标准方法
     * 
     * @param thrown
     * @param map
     * @param obj
     */
    public static void logError(CoreError thrown, Map<String, String> map, Object obj) {
        String stackFlag = IDGenerator.getUID() + " ";
        Logger log = LoggerFactory.getLogger("message");
        MessageArgs args = thrown.getMsgArgs();
        log.log(Level.SEVERE, args.getCode() + ":" + stackFlag + args.getDesc());

        String time = DateUtil.getCurDateTime("yyyy-MM-dd HH:mm:ss:SSS ");
        StackTraceElement[] stackArray = thrown.getStackTrace();
        log.log(Level.INFO, time + stackFlag + thrown.getClass().getName());
        log.log(Level.INFO, time + stackFlag + thrown.getMessage());
        for (StackTraceElement temp : stackArray) {
            log.log(Level.INFO, time + stackFlag + temp.toString());
        }
        Throwable cause = thrown.getCause();
        if (cause != null) {
            log.log(Level.INFO, time + stackFlag + cause.getClass().getName());
            log.log(Level.INFO, time + stackFlag + cause.getMessage());
            stackArray = cause.getStackTrace();
            for (StackTraceElement temp : stackArray) {
                log.log(Level.INFO, time + stackFlag + temp.toString());
            }
        }
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            String key;
            StringBuilder sb = new StringBuilder("{");
            while (it.hasNext()) {
                key = it.next();
                sb.append(key).append("=").append(map.get(key)).append(" ");
            }
            sb.append("]");
            log.log(Level.INFO, time + stackFlag + sb.toString());
        }
        if (obj != null) {
            log.log(Level.INFO, time + stackFlag + JSON.toJSONString(obj));
        }
    }

    /**
     * 输出MessageArgs标准方法
     * 
     * @param args
     */
    public static void logError(MessageArgs args) {
        Logger log = LoggerFactory.getLogger("message");
        log.log(Level.SEVERE, args.getCode() + ":" + args.getDesc());
    }

    /**
     * @Title: logWarn
     * @Description 输出warn级别消息
     * @param message
     * @author CT
     */
    public static void logWarn(String message) {
        Logger log = LoggerFactory.getLogger("message");
        log.log(Level.WARNING, DateUtil.getCurDateTime("yyyy-MM-dd HH:mm:ss:SSS ") + message);
    }

    /**
     * @Title: logInfo
     * @Description 输出info级别消息
     * @param message
     * @author CT
     */
    public static void logInfo(String message) {
        Logger log = LoggerFactory.getLogger("message");
        log.log(Level.INFO, DateUtil.getCurDateTime("yyyy-MM-dd HH:mm:ss:SSS ") + message);
    }

    /**
     * @Title: logInfo
     * @Description 输出info级别消息
     * @param message
     * @author CT
     */
    public static void logInfo(String requestFlowID, String message) {
        Logger log = LoggerFactory.getLogger("message");
        log.log(Level.INFO, DateUtil.getCurDateTime("yyyy-MM-dd HH:mm:ss:SSS ") + "[" + requestFlowID + "]:" + message);
    }

    /**
     * @Title: logInfo
     * @Description 输出info级别消息
     * @param message
     * @author zhangweirong
     */
    public static void logInfo(String message, Map<String, String> map, Object obj) {
        String stackFlag = IDGenerator.getUID() + " ";
        Logger log = LoggerFactory.getLogger("message");
        String time = DateUtil.getCurDateTime("yyyy-MM-dd HH:mm:ss:SSS ");

        log.log(Level.INFO, time + stackFlag + message);
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            String key;
            StringBuilder sb = new StringBuilder("{");
            while (it.hasNext()) {
                key = it.next();
                sb.append(key).append("=").append(map.get(key)).append(" ");
            }
            sb.append("]");
            log.log(Level.INFO, time + stackFlag + sb.toString());
        }
        if (obj != null) {
            log.log(Level.INFO, time + stackFlag + JSON.toJSONString(obj));
        }
    }

    /**
     * @Title: logInfo
     * @Description: TODO
     * @param message
     * @param thrown
     * @author zhangweirong
     */
    public static void logInfo(String message, Throwable thrown) {
        String stackFlag = IDGenerator.getUID() + " ";
        Logger log = LoggerFactory.getLogger("message");
        String time = DateUtil.getCurDateTime("yyyy-MM-dd HH:mm:ss:SSS ");

        log.log(Level.INFO, time + stackFlag + message);

        StackTraceElement[] stackArray = thrown.getStackTrace();
        log.log(Level.INFO, time + stackFlag + thrown.getClass().getName());
        log.log(Level.INFO, time + stackFlag + thrown.getMessage());
        for (StackTraceElement temp : stackArray) {
            log.log(Level.INFO, time + stackFlag + temp.toString());
        }
        Throwable cause = thrown.getCause();
        if (cause != null) {
            log.log(Level.INFO, time + stackFlag + cause.getClass().getName());
            log.log(Level.INFO, time + stackFlag + cause.getMessage());
            stackArray = cause.getStackTrace();
            for (StackTraceElement temp : stackArray) {
                log.log(Level.INFO, time + stackFlag + temp.toString());
            }
        }
    }

    /**
     * @Title: logdebug
     * @Description 输出debug级别消息
     * @param message
     * @author CT
     */
    public static void logdebug(String message) {
        Logger log = LoggerFactory.getLogger("message");
        log.log(Level.FINE, DateUtil.getCurDateTime("yyyy-MM-dd HH:mm:ss:SSS ") + message);
    }

    /**
     * 获得日志等级
     * 
     * @param msgCode ，消息编码
     * @return
     */
    public static Level getLevel(String level) {
        char levelCha = level.charAt(level.length() - 1);
        Level lev = null;
        switch (levelCha) {
            case 'D':
                lev = Level.FINE;
                break;
            case 'I':
                lev = Level.INFO;
                break;
            case 'W':
                lev = Level.WARNING;
                break;
            case 'E':
                lev = Level.SEVERE;
                break;
            default:
                lev = Level.INFO;
        }
        return lev;
    }

    /*
     * public static void main(String[] args){
     * 
     * logMessage(new MessageArgs(BCModules.ACCOUNT_CUSTOMER, "业务号", "业务流水号", "交易号",
     * "交易流水号",AccountCustomerMsgId.BC1101000E ));
     * 
     * logMessage(new MessageArgs(BCModules.ACCOUNT_CUSTOMER,"业务号", "业务流水号", "交易号",
     * "交易流水号",AccountCustomerMsgId.BC1101000E,new Exception("error") )); }
     */
}
