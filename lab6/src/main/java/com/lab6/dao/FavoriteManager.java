package com.lab6.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import com.lab6.dto.Report;
import com.lab6.entity.User;
import com.lab6.entity.Video;

public class FavoriteManager {
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");

    // Lấy tất cả video yêu thích của một user
    public List<Video> findVideosByUser(String userId) {
        EntityManager em = factory.createEntityManager();
        try {
            TypedQuery<Video> query = em.createNamedQuery("Video.findByUser", Video.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Tìm video theo từ khóa trong title (có trong favorites)
    public List<Video> findVideosByKeyword(String keyword) {
        EntityManager em = factory.createEntityManager();
        try {
            TypedQuery<Video> query = em.createNamedQuery("Video.findByKeyword", Video.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Tìm user thích một video
    public List<User> findUsersByVideo(String videoId) {
        EntityManager em = factory.createEntityManager();
        try {
            String jpql = "SELECT o.user FROM Favorite o WHERE o.video.id = :vid";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("vid", videoId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Lấy video có hoặc không có yêu thích
    public List<Video> findVideosByFavoriteStatus(boolean hasFavorites) {
        EntityManager em = factory.createEntityManager();
        try {
            String jpql;
            if (hasFavorites) {
                jpql = "SELECT v FROM Video v WHERE v.favorites IS NOT EMPTY";
            } else {
                jpql = "SELECT v FROM Video v WHERE v.favorites IS EMPTY";
            }
            TypedQuery<Video> query = em.createQuery(jpql, Video.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Báo cáo tổng hợp lượt thích theo video (title)
    public List<Report> getLikeReport() {
        EntityManager em = factory.createEntityManager();
        try {
            String jpql = "SELECT new com.lab6.dto.Report(o.video.title, COUNT(o), MAX(o.likeDate), MIN(o.likeDate)) " +
                          "FROM Favorite o GROUP BY o.video.title";
            TypedQuery<Report> query = em.createQuery(jpql, Report.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Lấy 10 video ngẫu nhiên (dùng Native Query)
    public List<Video> getRandom10Videos() {
        EntityManager em = factory.createEntityManager();
        try {
            TypedQuery<Video> query = em.createNamedQuery("Video.random10", Video.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Gọi Stored Procedure spFavoriteByYear
    public List<Report> getReportByYear(int year) {
        EntityManager em = factory.createEntityManager();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("spFavoriteByYear");
            query.registerStoredProcedureParameter("Year", Integer.class, ParameterMode.IN);
            query.setParameter("Year", year);
            query.execute();

            List<?> rawResults = query.getResultList();
            List<Report> reports = new ArrayList<>();
            for (Object rawRow : rawResults) {
                Object[] row = (Object[]) rawRow;
                String title = (String) row[0];
                Long likes = ((Number) row[1]).longValue();
                Date newest = (Date) row[2];
                Date oldest = (Date) row[3];
                reports.add(new Report(title, likes, newest, oldest));
            }
            return reports;
        } finally {
            em.close();
        }
    }

    public void close() {
        factory.close();
    }
}