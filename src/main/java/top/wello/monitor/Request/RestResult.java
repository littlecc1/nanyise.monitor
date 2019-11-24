package top.wello.monitor.Request;

public class RestResult<T> {

    private T data;
    private boolean success;
    private int errorCode;
    private String errorMsg;

    public static final int ERROR_LOGIN_NULL_CODE = 101;
    public static final int ERROR_LOGIN_ERROR_CODE = 102;
    public static final int ERROR_INVAILD_SESSION = 103;
    public static final int ERROR_LOGIN_FAILED = 100;

    public static final int ERROR_SUGGEST_ERROR = 201;
    public static final int ERROR_WXRUN_ERROR = 210;

    public static final int ERROR_EXCEPTION = 301;
    public static final int ERROR_INVALID_PARAM = 302;
//    public static fina

    public RestResult(T data) {
        this.data = data;
        this.success = true;
    }

    public RestResult(T data, boolean success, int errorCode, String errorMsg) {
        this.data = data;
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static <T> RestResult<T> success(T data) {
        return new RestResult<T>(data);
    }

    public static <T> RestResult<T> fail(int errorCode, String errorMsg) {
        return new RestResult<T>(null, false, errorCode, errorMsg);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "data=" + data +
                ", success=" + success +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
