package com.backendmonolitico.repository;

import com.backendmonolitico.Model.Produto;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {


}
