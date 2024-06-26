package br.com.itau.desafioseguros.entrypoint.api.response;

public class BaseResponse<T> {

    private final T data;

    public BaseResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

}
