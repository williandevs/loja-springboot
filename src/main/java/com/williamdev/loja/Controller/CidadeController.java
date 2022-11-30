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

import com.williamdev.loja.model.Cidade;
import com.williamdev.loja.repositorios.CidadeRepositorio;
import com.williamdev.loja.repositorios.EstadoRepositorio;


@Controller
public class CidadeController {
    
    @Autowired
    private CidadeRepositorio cidadeRepositorio;

	@Autowired
	private  EstadoRepositorio estadoRepositorio;


    @GetMapping("/adminitrativo/cidades/cadastrar")
	public ModelAndView cadastrar(Cidade cidade){
		ModelAndView mv = new ModelAndView("/adminitrativo/cidades/cadastro");
		mv.addObject("cidade", cidade);
		mv.addObject("listaEstado", estadoRepositorio.findAll());
		return mv;

	}


	@GetMapping("adminitrativo/cidades/listar")
	public ModelAndView listar(){
		ModelAndView mv = new ModelAndView("adminitrativo/cidades/lista");
		mv.addObject("listaCidade", cidadeRepositorio.findAll());
		return mv;
	}




	@GetMapping("/adminitrativo/cidades/editar{id}")
		public ModelAndView editar (@PathVariable("id") Long id){
			Optional<Cidade> cidade = cidadeRepositorio.findById(id);
			return cadastrar(cidade.get());
		}
    
	@GetMapping("/adminitrativo/cidades/remover{id}")
		public ModelAndView remover (@PathVariable("id") Long id){
			Optional<Cidade> cidade = cidadeRepositorio.findById(id);
			cidadeRepositorio.delete(cidade.get());
			return listar();
		}
    


	@PostMapping("adminitrativo/cidades/salvar")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result ){
		if(result.hasErrors()){
			return cadastrar(cidade);
		}
		cidadeRepositorio.saveAndFlush(cidade);
		return cadastrar(new Cidade());

	}

}
