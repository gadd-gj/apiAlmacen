package org.uv.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uv.excepcion.RecursoNoEncontrado;
import org.uv.modelo.Producto;
import org.uv.repositorio.RepositorioProducto;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/uv")
public class ControladorProducto {

    @Autowired
    private RepositorioProducto repositorioProducto;

    @GetMapping("/productos")
    public List<Producto> showAll() {
        return repositorioProducto.findAll();
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> searchById(@PathVariable(value = "id") Long id) throws RecursoNoEncontrado {

        Producto producto = repositorioProducto.findById(id).orElseThrow(
                () -> new RecursoNoEncontrado("No se encontró el id : " + id)
        );

        return ResponseEntity.ok().body(producto);
    }

    @PostMapping("/productos")
    public Producto create(@Valid @RequestBody Producto producto) {
        return repositorioProducto.save(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Producto updateProducto)
            throws RecursoNoEncontrado {

        Producto producto = repositorioProducto.findById(id).orElseThrow(
                () -> new RecursoNoEncontrado("No se encontró el id : " + id)
        );

        producto.setNombre(updateProducto.getNombre());
        producto.setDescripcion(updateProducto.getDescripcion());

        producto.setCantidad(updateProducto.getCantidad());

        final Producto pro = repositorioProducto.save(producto);
        return ResponseEntity.ok(pro);

    }

    @DeleteMapping("/productos/{id}")
    public Map<String,Boolean> delete (@PathVariable(value = "id") Long id) throws RecursoNoEncontrado {

        Producto producto = repositorioProducto.findById(id).orElseThrow(
                () -> new RecursoNoEncontrado("No se encontró el id : " + id)
        );

        repositorioProducto.delete(producto);
        Map<String,Boolean> respuesta = new HashMap<>();
        respuesta.put("delete", Boolean.TRUE);
        return respuesta;
    }


}
