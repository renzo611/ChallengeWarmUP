package com.alkemy.ChallengeWarmUP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ChallengeWarmUP.DTO.CategoriaDto;
import com.alkemy.ChallengeWarmUP.entity.CategoriaEntity;
import com.alkemy.ChallengeWarmUP.serviceImpl.CategoriaServiceImpl;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaServiceImpl catService;
	
	@PostMapping
	public ResponseEntity<?> saveCategoria(@RequestBody CategoriaEntity categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(catService.saveCategoria(categoria));
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaDto>> getAll(){
		return ResponseEntity.ok(catService.getAll());
	}
}
