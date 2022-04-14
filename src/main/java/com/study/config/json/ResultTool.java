package com.study.config.json;

/**
 * 返回体构造工具
 *
 * @author Cyanogen
 * @date 2022-04-13 21:05:33
 */
public class ResultTool {
    public static JsonResult success() {
        return new JsonResult(true);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult(true, data);
    }

    public static JsonResult fail() {
        return new JsonResult(false);
    }

    public static JsonResult fail(ResultCode resultEnum) {
        return new JsonResult(false, resultEnum);
    }

    public static JsonResult fail(ResultCode resultEnum, String msg) {
        JsonResult jsonResult = new JsonResult(false, resultEnum);
        jsonResult.setMsg(msg);
        return jsonResult;
    }
}
