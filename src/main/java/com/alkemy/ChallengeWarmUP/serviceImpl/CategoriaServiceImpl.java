package com.alkemy.ChallengeWarmUP.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ChallengeWarmUP.dto.CategoriaDto;
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
	@Transactional
	public CategoriaDto saveCategoria(CategoriaEntity entity) {
		return catMap.categoriaEntity2Dto(catRepo.save(entity));
	}

	@Override
	@Transactional(readOnly = true)
	public List<CategoriaDto> getAll() {
		return catMap.categoriaEntity2DtoList(catRepo.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public CategoriaEntity getById(Long id) {
		return catRepo.getById(id);
	}

}
