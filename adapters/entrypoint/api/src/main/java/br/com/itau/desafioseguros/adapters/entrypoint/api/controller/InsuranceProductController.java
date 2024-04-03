package br.com.itau.desafioseguros.adapters.entrypoint.api.controller;

import br.com.itau.desafioseguros.adapters.entrypoint.api.request.AddInsuranceProductRequest;
import br.com.itau.desafioseguros.adapters.entrypoint.api.response.BaseResponse;
import br.com.itau.desafioseguros.adapters.entrypoint.api.response.GetInsuranceProductResponse;
import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.command.handlers.CommandHandler;
import br.com.itau.desafioseguros.application.command.responses.AddInsuranceProductCommandResponse;
import br.com.itau.desafioseguros.application.query.GetAllInsuranceProductQuery;
import br.com.itau.desafioseguros.application.query.handlers.QueryHandler;
import br.com.itau.desafioseguros.application.query.responses.GetInsuranceProductQueryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/insurance/products")
public class InsuranceProductController {

    private final CommandHandler<AddInsuranceProductCommand, AddInsuranceProductCommandResponse> commandHandler;

    private final QueryHandler<GetAllInsuranceProductQuery, GetInsuranceProductQueryResponse> queryHandler;

    public InsuranceProductController(CommandHandler<AddInsuranceProductCommand, AddInsuranceProductCommandResponse> commandHandler,
                                      QueryHandler<GetAllInsuranceProductQuery, GetInsuranceProductQueryResponse> queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<GetInsuranceProductResponse>> add(@RequestBody AddInsuranceProductRequest request,
                                                           UriComponentsBuilder uriBuilder) {
        AddInsuranceProductCommand command = new AddInsuranceProductCommand(request.getName(),
                request.getCategory(),
                request.getBasePrice());

        AddInsuranceProductCommandResponse responseModel = commandHandler.handle(command);

        URI uri = uriBuilder.path("/insurance/products/{id}").buildAndExpand(responseModel.getId()).toUri();

        GetInsuranceProductResponse response = new GetInsuranceProductResponse(responseModel.getId(),
                responseModel.getName(),
                responseModel.getCategory(),
                responseModel.getBasePrice(),
                responseModel.getTariffedPrice());

        return ResponseEntity.created(uri).body(new BaseResponse<>(List.of(response)));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<GetInsuranceProductResponse>> get() {
        List<GetInsuranceProductQueryResponse> responseList = queryHandler.handle(new GetAllInsuranceProductQuery());

        List<GetInsuranceProductResponse> response = responseList.stream().map(product -> new GetInsuranceProductResponse(product.getId(),
                product.getName(),
                product.getCategory(),
                product.getBasePrice(),
                product.getTariffedPrice())).toList();

        return ResponseEntity.ok().body(new BaseResponse<>(response));
    }

}
