package com.alkemy.ChallengeWarmUP.service;

import java.util.List;

import com.alkemy.ChallengeWarmUP.dto.CategoriaDto;
import com.alkemy.ChallengeWarmUP.entity.CategoriaEntity;

public interface CategoriaService {
	public CategoriaDto saveCategoria(CategoriaEntity entity);
	public List<CategoriaDto> getAll();
	public CategoriaEntity getById(Long id);
}
