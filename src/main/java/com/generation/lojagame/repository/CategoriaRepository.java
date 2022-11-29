package com.generation.lojagame.repository;

import com.generation.lojagame.model.Categoria;
import com.generation.lojagame.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {
    public List<Categoria> findAllByTipoContainingIgnoreCase(@Param("tipo") String tipo);

}
