package br.com.itau.desafioseguros.application.command.handler;

import br.com.itau.desafioseguros.application.command.Command;
import br.com.itau.desafioseguros.application.command.responses.CommandResponse;

public interface CommandHandler<T extends Command, R extends CommandResponse> {
    R handle(T command);
}
