package com.alkemy.ChallengeWarmUP.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ChallengeWarmUP.DTO.CategoriaDto;
import com.alkemy.ChallengeWarmUP.entity.CategoriaEntity;
import com.alkemy.ChallengeWarmUP.mapper.CategoriaMapper;
import com.alkemy.ChallengeWarmUP.repository.CategoriaRepository;
import com.alkemy.ChallengeWarmUP.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private CategoriaMapper catMap;
	
	@Override
	public CategoriaDto saveCategoria(CategoriaEntity entity) {
		return catMap.categoriaEntity2Dto(catRepo.save(entity));
	}

	@Override
	public List<CategoriaDto> getAll() {
		return catMap.categoriaEntity2DtoList(catRepo.findAll());
	}

	@Override
	public CategoriaEntity getById(Long id) {
		return catRepo.getById(id);
	}

}
