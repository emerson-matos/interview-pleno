package br.com.brainweb.interview.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Não foi possível localizar o item com id " + id.toString());
    }
}
