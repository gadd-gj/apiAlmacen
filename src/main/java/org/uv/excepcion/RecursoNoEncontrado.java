package org.uv.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNoEncontrado extends Exception {

    private static final long serialVersionUID = 1L;

    public RecursoNoEncontrado(String mensaje) {
        super(mensaje);
    }



}