package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> query = session.createQuery(
                    "From MovieSession where movie.id = :movieId"
                            + " and year(showTime) = :year and month(showTime) = :month"
                            + " and day(showTime) = :day");
            query.setParameter("movieId", movieId);
            query.setParameter("year", date.getYear());
            query.setParameter("month", date.getMonthValue());
            query.setParameter("day", date.getDayOfMonth());
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available sessions", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long movieSessionId = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(movieSessionId);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add new movie session", e);

        }
    }
}
