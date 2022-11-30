package com.williamdev.loja.Controller;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.williamdev.loja.model.Funcionario;
import com.williamdev.loja.repositorios.FuncionarioRepositorio;

@Controller
public class FuncionarioControle {
	
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;


	@GetMapping("/adminitrativo/funcionarios/cadastrar")
	public ModelAndView cadastrar(Funcionario funcionario){
		ModelAndView mv = new ModelAndView("/adminitrativo/funcionarios/cadastro");
		mv.addObject("funcionario", funcionario);
		return mv;

	}


	@GetMapping("adminitrativo/funcionarios/listar")
	public ModelAndView listar(){
		ModelAndView mv = new ModelAndView("adminitrativo/funcionarios/lista");
		mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
		return mv;
	}




	@GetMapping("/adminitrativo/funcionarios/editar{id}")
		public ModelAndView editar (@PathVariable("id") Long id){
			Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);
			return cadastrar(funcionario.get());
		}
    
	@GetMapping("/adminitrativo/funcionarios/remover{id}")
		public ModelAndView remover (@PathVariable("id") Long id){
			Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);
			funcionarioRepositorio.delete(funcionario.get());
			return listar();
		}
    


	@PostMapping("adminitrativo/funcionarios/salvar")
	public ModelAndView salvar(@Valid Funcionario funcionario, BindingResult result ){
		if(result.hasErrors()){
			return cadastrar(funcionario);
		}
		funcionarioRepositorio.saveAndFlush(funcionario);
		return cadastrar(new Funcionario());

	}



}