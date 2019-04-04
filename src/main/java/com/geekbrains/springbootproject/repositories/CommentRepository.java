package com.geekbrains.springbootproject.repositories;

import com.geekbrains.springbootproject.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
