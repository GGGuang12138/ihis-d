package com.gg.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: GG
 * @date: 2021/2/17 11:56 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static RespBean success(String message){
        return new RespBean(200,message, null);
    }

    /**
     * 成功返回结果，带数据
     * @param message
     * @param obj
     * @return
     */
    public static RespBean success(String message, Object obj){
        return new RespBean(200,message,obj);
    }

    /**
     * 失败返回结果 500
     * @param message
     * @return
     */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

    /**
     * 失败返回结果，带数据 500
     * @param message
     * @param obj
     * @return
     */
    public static RespBean error(String message, Object obj){
        return new RespBean(500, message, obj);
    }
}
