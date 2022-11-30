package com.williamdev.loja.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.williamdev.loja.model.Funcionario;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {

}