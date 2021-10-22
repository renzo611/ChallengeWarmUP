package com.alkemy.ChallengeWarmUP.mapper;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.ChallengeWarmUP.dto.PostBasicDto;
import com.alkemy.ChallengeWarmUP.dto.PostDto;
import com.alkemy.ChallengeWarmUP.entity.CategoriaEntity;
import com.alkemy.ChallengeWarmUP.entity.PostEntity;
import com.alkemy.ChallengeWarmUP.service.CategoriaService;

@Component
public class PostMapper {
	@Autowired
	private CategoriaService catSer;
	
	public List<PostBasicDto> postEntity2DtoList(List<PostEntity> posts){
		List<PostBasicDto> newPosts = new ArrayList<>();
		for (PostEntity postEntity : posts) {
			newPosts.add(this.postEnitity2DtoBasic(postEntity));
		}
		
		Collections.sort(newPosts, new Comparator<PostBasicDto>() {
			@Override
			public int compare(PostBasicDto p1, PostBasicDto p2) {
				return p2.getFechaCreacion().compareTo(p1.getFechaCreacion());
			}
		});
		return newPosts;
	}
	
	public PostBasicDto postEnitity2DtoBasic(PostEntity post) {
		PostBasicDto newPost = new PostBasicDto();
		CategoriaEntity cat = new CategoriaEntity();
		cat = catSer.getById(post.getIdcategoria());
		newPost.setCategoria(cat.getNombre());
		newPost.setFechaCreacion(this.setFecha(post.getFechaCreacion()));
		newPost.setId(post.getId());
		newPost.setImagen(post.getImagen());
		newPost.setTitulo(post.getTitulo());
		return newPost;
	}
	
	public PostDto postEntity2Dto(PostEntity post) {
		PostDto newPost = new PostDto();
		CategoriaEntity cat = new CategoriaEntity();
		cat = catSer.getById(post.getIdcategoria());
		newPost.setCategoria(cat.getNombre());
		newPost.setContenido(post.getContenido());
		newPost.setFechaCreacion(this.setFecha(post.getFechaCreacion()));
		newPost.setId(post.getId());
		newPost.setIdUsuario(post.getIdUsuario());
		newPost.setImagen(post.getImagen());
		newPost.setTitulo(post.getTitulo());
		return newPost;
	}
	
	private String setFecha(Date fecha) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(fecha);
	}
	
	public PostEntity postUpdate(PostEntity newPost, PostDto post) {
		newPost.setContenido(post.getContenido());
		newPost.setImagen(post.getImagen());
		newPost.setTitulo(post.getTitulo());
		return newPost;
	}
}
