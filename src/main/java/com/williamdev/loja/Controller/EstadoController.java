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

import com.williamdev.loja.model.Estado;
import com.williamdev.loja.repositorios.EstadoRepositorio;

@Controller
public class EstadoController {
    
    @Autowired
    private EstadoRepositorio estadoRepositorio;


    @GetMapping("/adminitrativo/estados/cadastrar")
	public ModelAndView cadastrar(Estado estado){
		ModelAndView mv = new ModelAndView("/adminitrativo/estados/cadastro");
		mv.addObject("estado", estado);
		return mv;

	}


	@GetMapping("adminitrativo/estados/listar")
	public ModelAndView listar(){
		ModelAndView mv = new ModelAndView("adminitrativo/estados/lista");
		mv.addObject("listaEstado", estadoRepositorio.findAll());
		return mv;
	}




	@GetMapping("/adminitrativo/estados/editar{id}")
		public ModelAndView editar (@PathVariable("id") Long id){
			Optional<Estado> estado = estadoRepositorio.findById(id);
			return cadastrar(estado.get());
		}
    
	@GetMapping("/adminitrativo/estados/remover{id}")
		public ModelAndView remover (@PathVariable("id") Long id){
			Optional<Estado> estado = estadoRepositorio.findById(id);
			estadoRepositorio.delete(estado.get());
			return listar();
		}
    


	@PostMapping("adminitrativo/estados/salvar")
	public ModelAndView salvar(@Valid Estado estado, BindingResult result ){
		if(result.hasErrors()){
			return cadastrar(estado);
		}
		estadoRepositorio.saveAndFlush(estado);
		return cadastrar(new Estado());

	}

}
