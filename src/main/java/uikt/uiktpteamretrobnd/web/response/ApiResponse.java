package uikt.uiktpteamretrobnd.web.response;

public class ApiResponse<T> {
    private Meta meta;
    private T data;

    public ApiResponse(Meta meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}