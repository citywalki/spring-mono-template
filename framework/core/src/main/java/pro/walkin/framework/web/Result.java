package pro.walkin.framework.web;

import java.io.Serializable;

/**
 * 接口返回对象
 *
 * @param <T>
 */
@SuppressWarnings("ALL")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    static String SUCCESS_MSG = "success";

    /**
     * {@code 200 OK} (HTTP/1.0 - RFC 1945)
     */
    static Integer SUCCESS_CODE = 0;

    /**
     * {@code 500 Server Error} (HTTP/1.0 - RFC 1945)
     */
    static Integer SC_INTERNAL_SERVER_ERROR_500 = 500;


    /**
     * 返回处理消息
     */
    private String msg = SUCCESS_MSG;

    /**
     * 返回代码
     */
    private Integer status = SUCCESS_CODE;

    /**
     * 返回数据对象 data
     */
    private T data;


    public Result() {

    }

    public Result(Integer status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 请求成功消息
     *
     * @param data 结果
     * @return RPC调用结果
     */
    public static <E> Result<E> success(E data) {
        return new Result<>(SUCCESS_CODE, data, SUCCESS_MSG);
    }

    public static <T> Result<T> success() {
        return new Result<T>(SUCCESS_CODE, null, SUCCESS_MSG);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> Result<E> fail(String msg) {
        return new Result<>(SC_INTERNAL_SERVER_ERROR_500, null, msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}