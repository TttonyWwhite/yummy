package pers.illiant.yummy.util;

import pers.illiant.yummy.enums.ResultCode;

public class ResultUtils {

    public static Result success(Object data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    public static Result success(){
        return new Result(ResultCode.SUCCESS);
    }

    public static Result error(int code, String message) {
        Result result = new Result();
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    public static Result error(ResultCode resultCode) {
        return new Result(resultCode);
    }
}
