package br.com.itau.desafioseguros.adapters.entrypoint.api.response;

import java.util.List;

public class BaseResponse<T> {

    private List<T> data;

    public BaseResponse(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
