package com.codegym.controller;

import com.codegym.repository.CommentRepository;
import com.codegym.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public String getComments(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Comment> comments = commentRepository.findAllByDate(LocalDate.now());
        req.setAttribute("comments", comments);
        return "/index";
    }

    @PostMapping
    public String saveComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String author = req.getParameter("author");
        String feedback = req.getParameter("feedback");
        int rating = Integer.parseInt(req.getParameter("rating"));

        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setFeedback(feedback);
        comment.setRating(rating);

        commentRepository.save(comment);
        return "redirect:/comments";
    }
}
