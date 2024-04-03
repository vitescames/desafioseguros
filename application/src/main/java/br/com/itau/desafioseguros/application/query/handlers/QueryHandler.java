package br.com.itau.desafioseguros.application.query.handlers;

import br.com.itau.desafioseguros.application.query.Query;
import br.com.itau.desafioseguros.application.query.responses.QueryResponse;

import java.util.List;

public interface QueryHandler<T extends Query, R extends QueryResponse>{
    List<R> handle(T query);
}
