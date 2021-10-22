package com.alkemy.ChallengeWarmUP.service;

import java.util.List;

import com.alkemy.ChallengeWarmUP.DTO.PostBasicDto;
import com.alkemy.ChallengeWarmUP.DTO.PostDto;
import com.alkemy.ChallengeWarmUP.entity.PostEntity;

public interface PostService {
	public List<PostBasicDto> getAll();
	public PostDto getById(Long id);
	public PostDto savePost(String token,PostEntity post);
	public PostEntity getPostById(Long id);
}
