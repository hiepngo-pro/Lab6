package com.lab6.dao;

import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.lab6.entity.User;

public class UserManager {
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");

    // ------------------- VALIDATION -------------------
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    public static boolean isValidVietnamPhone(String phone) {
        // Vietnam mobile: 0 + (3|5|7|8|9) + 8 digits (tổng 10 số)
        String phoneRegex = "^(0[3|5|7|8|9])([0-9]{8})$";
        return phone != null && Pattern.matches(phoneRegex, phone);
    }

    // ------------------- CRUD -------------------
    public List<User> findAll() {
        EntityManager em = factory.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public User findById(String id) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public void create(User user) {
        if (!isValidEmail(user.getEmail()))
            throw new IllegalArgumentException("Email không hợp lệ: " + user.getEmail());
        if (user.getPhone() != null && !isValidVietnamPhone(user.getPhone()))
            throw new IllegalArgumentException("Số điện thoại Việt Nam không hợp lệ: " + user.getPhone());

        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(user);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Lỗi khi tạo user: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void update(User user) {
        if (!isValidEmail(user.getEmail()))
            throw new IllegalArgumentException("Email không hợp lệ: " + user.getEmail());
        if (user.getPhone() != null && !isValidVietnamPhone(user.getPhone()))
            throw new IllegalArgumentException("Số điện thoại Việt Nam không hợp lệ: " + user.getPhone());

        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(user);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Lỗi khi cập nhật user: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void deleteById(String id) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Lỗi khi xóa user: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    // Bài 3: Tìm user có email kết thúc "@fpt.edu.vn" và không phải admin
    public List<User> findNonAdminFptUsers() {
        EntityManager em = factory.createEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email LIKE :domain AND u.admin = :role";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("domain", "%@fpt.edu.vn");
            query.setParameter("role", false);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void printNonAdminFptUsers() {
        List<User> users = findNonAdminFptUsers();
        if (users.isEmpty()) {
            System.out.println("Không có user nào thỏa mãn.");
        } else {
            users.forEach(u -> System.out.println(u.getFullname() + " - " + u.getEmail()));
        }
    }

    // Đóng factory khi ứng dụng dừng (gọi từ main hoặc context listener)
    public void close() {
        factory.close();
    }
}