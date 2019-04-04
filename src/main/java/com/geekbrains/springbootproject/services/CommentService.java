package com.geekbrains.springbootproject.services;

import com.geekbrains.springbootproject.entities.Comment;
import com.geekbrains.springbootproject.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void saveComment(Comment comment) {
        if (comment == null) return;
        commentRepository.save(comment);
    }
}
