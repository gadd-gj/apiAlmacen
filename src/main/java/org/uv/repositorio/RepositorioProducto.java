package org.uv.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uv.modelo.Producto;

@Repository
public interface RepositorioProducto extends JpaRepository<Producto, Long> {

}
