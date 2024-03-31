package br.com.itau.desafioseguros.adapters.entrypoint.api.controller;

import br.com.itau.desafioseguros.adapters.entrypoint.api.request.AddInsuranceProductRequest;
import br.com.itau.desafioseguros.adapters.entrypoint.api.response.AddInsuranceProductResponse;
import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.command.handlers.CommandHandler;
import br.com.itau.desafioseguros.application.command.responses.AddInsuranceProductCommandResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/insurance/products")
public class InsuranceProductController {

    private final CommandHandler<AddInsuranceProductCommand, AddInsuranceProductCommandResponse> commandHandler;

    public InsuranceProductController(CommandHandler<AddInsuranceProductCommand, AddInsuranceProductCommandResponse> commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public ResponseEntity<AddInsuranceProductResponse> add(@RequestBody AddInsuranceProductRequest request,
                                                           UriComponentsBuilder uriBuilder) {
        AddInsuranceProductCommand command = new AddInsuranceProductCommand(request.getName(),
                request.getCategory(),
                request.getBasePrice());

        AddInsuranceProductCommandResponse responseModel = commandHandler.handle(command);

        URI uri = uriBuilder.path("/insurance/products/{id}").buildAndExpand(responseModel.getId()).toUri();

        AddInsuranceProductResponse response = new AddInsuranceProductResponse(responseModel.getId(),
                responseModel.getName(),
                responseModel.getCategory(),
                responseModel.getBasePrice(),
                responseModel.getTariffedPrice());

        return ResponseEntity.created(uri).body(response);
    }

}
