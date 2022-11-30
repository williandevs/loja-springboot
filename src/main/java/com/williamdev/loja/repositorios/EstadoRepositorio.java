package com.williamdev.loja.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.williamdev.loja.model.Estado;


public interface EstadoRepositorio extends JpaRepository<Estado, Long> {

}