package com.alkemy.ChallengeWarmUP.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ChallengeWarmUP.DTO.PostBasicDto;
import com.alkemy.ChallengeWarmUP.DTO.PostDto;
import com.alkemy.ChallengeWarmUP.auth.service.JwtUtils;
import com.alkemy.ChallengeWarmUP.auth.service.UserDetailsCustomService;
import com.alkemy.ChallengeWarmUP.entity.PostEntity;
import com.alkemy.ChallengeWarmUP.mapper.PostMapper;
import com.alkemy.ChallengeWarmUP.repository.PostRepository;
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
	
	@Override
	public List<PostBasicDto> getAll() {
		return postMapper.postEntity2DtoList(postRepository.findAll());
	}

	@Override
	public PostDto getById(Long id) {
		return postMapper.postEntity2Dto(postRepository.getById(id));
	}

	@Override
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
	public PostEntity getPostById(Long id) {
		return postRepository.getById(id);
	}
	

}
