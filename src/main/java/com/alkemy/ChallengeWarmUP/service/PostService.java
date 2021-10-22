package com.alkemy.ChallengeWarmUP.service;

import java.util.List;

import com.alkemy.ChallengeWarmUP.dto.PostBasicDto;
import com.alkemy.ChallengeWarmUP.dto.PostDto;
import com.alkemy.ChallengeWarmUP.entity.PostEntity;

public interface PostService {
	public List<PostBasicDto> getAll();
	public PostDto getById(Long id);
	public PostDto savePost(String token,PostEntity post);
	public PostEntity getPostById(Long id);
	public void deleteById(Long id);
	public List<PostBasicDto> getByFilter(String titulo, Long idCategoria);
}
