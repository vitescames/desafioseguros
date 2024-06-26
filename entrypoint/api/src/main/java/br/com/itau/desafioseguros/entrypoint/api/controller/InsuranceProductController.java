package br.com.itau.desafioseguros.entrypoint.api.controller;

import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.command.handler.CommandHandler;
import br.com.itau.desafioseguros.application.command.responses.AddInsuranceProductCommandResponse;
import br.com.itau.desafioseguros.application.query.GetAllInsuranceProductsQuery;
import br.com.itau.desafioseguros.application.query.handlers.QueryHandler;
import br.com.itau.desafioseguros.application.query.responses.GetInsuranceProductQueryResponse;
import br.com.itau.desafioseguros.entrypoint.api.request.AddInsuranceProductRequest;
import br.com.itau.desafioseguros.entrypoint.api.response.BaseResponse;
import br.com.itau.desafioseguros.entrypoint.api.response.GetInsuranceProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/insurance/products")
public class InsuranceProductController {

    private final CommandHandler<AddInsuranceProductCommand, AddInsuranceProductCommandResponse> addInsuranceProductCommandHandler;

    private final QueryHandler<GetAllInsuranceProductsQuery, GetInsuranceProductQueryResponse> getAllInsuranceProductsQuery;

    public InsuranceProductController(CommandHandler<AddInsuranceProductCommand, AddInsuranceProductCommandResponse> addInsuranceProductCommandHandler,
                                      QueryHandler<GetAllInsuranceProductsQuery, GetInsuranceProductQueryResponse> getAllInsuranceProductsQuery) {
        this.addInsuranceProductCommandHandler = addInsuranceProductCommandHandler;
        this.getAllInsuranceProductsQuery = getAllInsuranceProductsQuery;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<GetInsuranceProductResponse>> add(@RequestBody AddInsuranceProductRequest request,
                                                                         UriComponentsBuilder uriBuilder) {
        AddInsuranceProductCommand command = new AddInsuranceProductCommand(request.getName(),
                request.getCategory(),
                request.getBasePrice());

        AddInsuranceProductCommandResponse responseModel = addInsuranceProductCommandHandler.handle(command);

        URI uri = uriBuilder.path("/insurance/products/{id}").buildAndExpand(responseModel.getId()).toUri();

        GetInsuranceProductResponse response = new GetInsuranceProductResponse(responseModel.getId(),
                responseModel.getName(),
                responseModel.getCategory(),
                responseModel.getBasePrice(),
                responseModel.getTariffedPrice());

        return ResponseEntity.created(uri).body(new BaseResponse<>(response));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<GetInsuranceProductResponse>>> get() {
        List<GetInsuranceProductQueryResponse> responseList = getAllInsuranceProductsQuery.handle(new GetAllInsuranceProductsQuery());

        List<GetInsuranceProductResponse> response = responseList.stream().map(product -> new GetInsuranceProductResponse(product.getId(),
                product.getName(),
                product.getCategory(),
                product.getBasePrice(),
                product.getTariffedPrice())).toList();

        return ResponseEntity.ok().body(new BaseResponse<>(response));
    }

}
