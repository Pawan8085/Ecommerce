package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
