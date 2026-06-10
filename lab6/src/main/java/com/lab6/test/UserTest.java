package com.lab6.test;

import com.lab6.dao.UserManager;
import com.lab6.entity.User;

public class UserTest {
    public static void main(String[] args) {
        UserManager manager = new UserManager();

        // 1. Find all
        System.out.println("=== Danh sách tất cả users ===");
        manager.findAll().forEach(System.out::println);

        // 2. Find by ID
        System.out.println("\n=== Tìm user U01 ===");
        User u = manager.findById("U01");
        System.out.println(u);

        // 3. Create new user (có validation)
        System.out.println("\n=== Tạo user mới ===");
        try {
            User newUser = new User("U06", "456", "Nguyễn Thị E", "e@fpt.edu.vn", false, "0978123456");
            manager.create(newUser);
            System.out.println("Tạo thành công!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // 4. Update user (kiểm tra validation)
        System.out.println("\n=== Cập nhật user U01 ===");
        User existing = manager.findById("U01");
        if (existing != null) {
            existing.setFullname("Nguyễn Văn A (Đã sửa)");
            existing.setEmail("invalid-email"); // email sai -> sẽ bị lỗi
            try {
                manager.update(existing);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            // Sửa đúng
            existing.setEmail("newemail@gmail.com");
            existing.setPhone("0911111111");
            manager.update(existing);
            System.out.println("Cập nhật thành công!");
        }

        // 5. Delete user
        System.out.println("\n=== Xóa user U06 ===");
        manager.deleteById("U06");
        System.out.println("Đã xóa U06 (nếu tồn tại)");

        // 6. Bài 3: Tìm và in user email @fpt.edu.vn và không admin
        System.out.println("\n=== KẾT QUẢ BÀI 3 ===");
        manager.printNonAdminFptUsers();

        manager.close();
    }
}