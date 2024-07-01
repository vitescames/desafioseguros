package br.com.itau.desafioseguros.application.command.handlers;

import br.com.itau.desafioseguros.application.command.responses.CommandResponse;
import br.com.itau.desafioseguros.application.command.Command;

public interface CommandHandler<T extends Command, R extends CommandResponse> {
    R handle(T command);
}
