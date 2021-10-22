package com.alkemy.ChallengeWarmUP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.ChallengeWarmUP.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long>{
}
