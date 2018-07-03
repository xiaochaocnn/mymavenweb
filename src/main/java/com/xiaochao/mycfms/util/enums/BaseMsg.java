package com.xiaochao.mycfms.util.enums;

/**
 * 消息码接口
 * 
 * @author CT
 *
 */
public interface BaseMsg {
    /**
     * @Title: getId
     * @Description 获得消息码
     * @return
     * @author CT
     */
    public String getCode();

    /**
     * @Title: getDesc
     * @Description 获得消息文本
     * @return
     * @author CT
     */
    public String getDesc();
}
