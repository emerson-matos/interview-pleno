package br.com.brainweb.interview.core.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Não foi possivel localizar o item com id " + id.toString());
    }
}
