package com.pairuan.website.common.result;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

/**
 * 通用返回结果类
 * @param <T> 返回数据的类型
 */
@Data
@ResponseBody
public class R<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 成功返回结果
     * @param data 返回的数据
     */
    public static <T> R<T> success(T data) {
        R<T> r = new R<>();
        r.setCode(200);
        r.setMessage("success");
        r.setData(data);
        return r;
    }

    /**
     * 失败返回结果
     * @param message 错误信息
     */
    public static <T> R<T> error(String message) {
        R<T> r = new R<>();
        r.setCode(500);
        r.setMessage(message);
        return r;
    }

    /**
     * 自定义状态码和错误信息的返回结果
     * @param code 状态码
     * @param message 错误信息
     */
    public static <T> R<T> error(Integer code, String message) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }
} 