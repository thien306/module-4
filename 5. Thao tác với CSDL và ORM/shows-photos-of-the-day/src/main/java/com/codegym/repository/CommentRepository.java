package com.codegym.repository;

import com.codegym.model.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class CommentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Comment comment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(comment);
        transaction.commit();
        session.close();
    }

    public List<Comment> findAllByDate(LocalDate date) {
        Session session = sessionFactory.openSession();
        List<Comment> comments = session.createQuery("from Comment where date = :date", Comment.class)
                .setParameter("date", date)
                .list();
        session.close();
        return comments;
    }

    public void update(Comment comment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(comment);
        transaction.commit();
        session.close();
    }

    public Comment findById(Long id) {
        Session session = sessionFactory.openSession();
        Comment comment = session.get(Comment.class, id);
        session.close();
        return comment;
    }
}
