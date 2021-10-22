package com.alkemy.ChallengeWarmUP.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.ChallengeWarmUP.DTO.CategoriaDto;
import com.alkemy.ChallengeWarmUP.entity.CategoriaEntity;

@Component
public class CategoriaMapper {
	public CategoriaDto categoriaEntity2Dto(CategoriaEntity categoria) {
		CategoriaDto cat = new CategoriaDto();
		cat.setId(categoria.getId());
		cat.setNombre(categoria.getNombre());
		return cat;
	}
	
	public List<CategoriaDto> categoriaEntity2DtoList(List<CategoriaEntity> categorias){
		List<CategoriaDto> cat = new ArrayList<>();
		for (CategoriaEntity categoriaEntity : categorias) {
			cat.add(this.categoriaEntity2Dto(categoriaEntity));
		}
		return cat;
	}
}
