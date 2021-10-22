package com.alkemy.ChallengeWarmUP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ChallengeWarmUP.dto.PostBasicDto;
import com.alkemy.ChallengeWarmUP.dto.PostDto;
import com.alkemy.ChallengeWarmUP.entity.PostEntity;
import com.alkemy.ChallengeWarmUP.mapper.PostMapper;
import com.alkemy.ChallengeWarmUP.serviceImpl.PostServiceImpl;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostServiceImpl postService;
	@Autowired
	private PostMapper postMapper;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<PostBasicDto>> getAll(){
		return ResponseEntity.ok(postService.getAll());
	}
	
	@PostMapping
	public ResponseEntity<?> savePost(@RequestBody PostEntity post, @RequestHeader("Authorization")String header){
		String token = header.substring(7); 
		if(!post.getImagen().endsWith(".jpg") && !post.getImagen().endsWith(".png")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La imagen debe ser un formato .jpg o .png");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(postService.savePost(token,post));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		PostDto post = postService.getById(id);
		if(post != null) {
			return ResponseEntity.ok().body(post);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El post no se encuentra registrado");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostDto post){
		PostEntity newPost = postService.getPostById(id);
		if(newPost != null) {
			return ResponseEntity.ok().body(postService.savePost(null,postMapper.postUpdate(newPost, post)));
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El post no se encuentra registrado");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePost(@PathVariable Long id){
		PostEntity post = postService.getPostById(id);
		if(post != null) {
			postService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El post no se encuentra registrado");
	}
	
	@GetMapping
	public ResponseEntity<?> getDetailsByFilter(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) Long category		
	){
		List<PostBasicDto> entitys = postService.getByFilter(title, category);
		return ResponseEntity.ok().body(entitys);
	}
}
