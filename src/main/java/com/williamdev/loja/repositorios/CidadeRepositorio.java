package com.williamdev.loja.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.williamdev.loja.model.Cidade;


public interface CidadeRepositorio extends JpaRepository<Cidade, Long> {

}