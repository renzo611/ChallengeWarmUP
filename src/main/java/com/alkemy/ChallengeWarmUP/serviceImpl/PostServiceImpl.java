package com.alkemy.ChallengeWarmUP.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.ChallengeWarmUP.auth.service.JwtUtils;
import com.alkemy.ChallengeWarmUP.auth.service.UserDetailsCustomService;
import com.alkemy.ChallengeWarmUP.dto.PostBasicDto;
import com.alkemy.ChallengeWarmUP.dto.PostDto;
import com.alkemy.ChallengeWarmUP.dto.PostFilterDto;
import com.alkemy.ChallengeWarmUP.entity.PostEntity;
import com.alkemy.ChallengeWarmUP.mapper.PostMapper;
import com.alkemy.ChallengeWarmUP.repository.PostRepository;
import com.alkemy.ChallengeWarmUP.repository.specifications.PostSpecifications;
import com.alkemy.ChallengeWarmUP.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostMapper postMapper;
	@Autowired
	private JwtUtils utils;
	@Autowired
	private UserDetailsCustomService userService;
	@Autowired
	private PostSpecifications postSpec;
	
	@Override
	@Transactional(readOnly = true)
	public List<PostBasicDto> getAll() {
		return postMapper.postEntity2DtoList(postRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public PostDto getById(Long id) {
		return postMapper.postEntity2Dto(postRepository.getById(id));
	}

	@Override
	@Transactional
	public PostDto savePost(String token,PostEntity post) {
		Long id;
		String user;
		if(token != null) {
			user = utils.extractUsername(token);
			id = userService.getIdUser(user);
			post.setIdUsuario(id);
		}
		PostDto newPost = postMapper.postEntity2Dto(postRepository.save(post));
		return newPost;
	}

	@Override
	@Transactional(readOnly = true)
	public PostEntity getPostById(Long id) {
		return postRepository.getById(id);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		postRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true) 
	public List<PostBasicDto> getByFilter(String titulo, Long idCategoria) {
		PostFilterDto post = new PostFilterDto(titulo,idCategoria);
		List<PostEntity> entitys = postRepository.findAll(postSpec.getByFilters(post));
		return postMapper.postEntity2DtoList(entitys);
	}
	

}
