package br.com.itau.desafioseguros.adapters.entrypoint.api.response;

public class BaseResponse<T> {

    private T data;

    public BaseResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
