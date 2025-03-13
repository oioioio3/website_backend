package com.pairuan.website;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseReset {
    
    private static final String DB_URL = "jdbc:mysql://website-mysql.ns-bog5hrmv.svc:3306/website?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mp5cx7rj";
    
    public static void main(String[] args) {
        try {
            // Register MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connect to database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connection successful!");
            
            // Create Statement object
            Statement stmt = conn.createStatement();
            
            // Drop all tables
            dropAllTables(stmt);
            
            // Execute SQL statements to create tables
            createTables(stmt);
            
            // Execute SQL statements to insert data
            insertData(stmt);
            
            // Close resources
            stmt.close();
            conn.close();
            System.out.println("Database reset completed!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void dropAllTables(Statement stmt) throws SQLException {
        System.out.println("Starting to clear database...");
        
        // Disable foreign key checks
        stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
        
        // Get all table names
        java.sql.ResultSet rs = stmt.executeQuery("SHOW TABLES");
        while (rs.next()) {
            String tableName = rs.getString(1);
            System.out.println("Dropping table: " + tableName);
            stmt.execute("DROP TABLE IF EXISTS " + tableName);
        }
        
        // Enable foreign key checks
        stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
        System.out.println("Database cleared!");
    }
    
    private static void createTables(Statement stmt) throws SQLException {
        System.out.println("Starting to create tables...");
        
        // Create images table
        stmt.execute("CREATE TABLE images (\n" +
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    url VARCHAR(255) NOT NULL UNIQUE\n" +
                ")");
        
        // Create navigation table
        stmt.execute("CREATE TABLE navigation (\n" +
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    name VARCHAR(50) NOT NULL,\n" +
                "    path VARCHAR(100) NOT NULL\n" +
                ")");
        
        // Create news_categories table
        stmt.execute("CREATE TABLE news_categories (\n" +
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    category_name VARCHAR(50) NOT NULL UNIQUE\n" +
                ")");
        
        // Create news table
        stmt.execute("CREATE TABLE news (\n" +
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    title VARCHAR(255) NOT NULL,\n" +
                "    introduce TEXT NOT NULL,\n" +
                "    text TEXT NOT NULL,\n" +
                "    img_id INT,\n" +
                "    is_top TINYINT(1) DEFAULT 0,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                "    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n" +
                "    FOREIGN KEY (img_id) REFERENCES images(id)\n" +
                ")");
        
        // Create news_category_relations table
        stmt.execute("CREATE TABLE news_category_relations (\n" +
                "    news_id INT,\n" +
                "    category_id INT,\n" +
                "    PRIMARY KEY (news_id, category_id),\n" +
                "    FOREIGN KEY (news_id) REFERENCES news(id),\n" +
                "    FOREIGN KEY (category_id) REFERENCES news_categories(id)\n" +
                ")");
        
        // Create products table
        stmt.execute("CREATE TABLE products (\n" +
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    name VARCHAR(100) NOT NULL,\n" +
                "    img_id INT,\n" +
                "    FOREIGN KEY (img_id) REFERENCES images(id)\n" +
                ")");
        
        // Create partners table
        stmt.execute("CREATE TABLE partners (\n" +
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    name VARCHAR(100) NOT NULL,\n" +
                "    img_id INT,\n" +
                "    FOREIGN KEY (img_id) REFERENCES images(id)\n" +
                ")");
        
        // Create carousels table
        stmt.execute("CREATE TABLE carousels (\n" +
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    img_id INT,\n" +
                "    FOREIGN KEY (img_id) REFERENCES images(id)\n" +
                ")");
        
        // Create contacts table
        stmt.execute("CREATE TABLE contacts (\n" +
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    name VARCHAR(100) NOT NULL,\n" +
                "    email VARCHAR(100) NOT NULL,\n" +
                "    message TEXT NOT NULL,\n" +
                "    created_at DATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                ")");
        
        System.out.println("Tables created successfully!");
    }
    
    private static void insertData(Statement stmt) throws SQLException {
        System.out.println("Starting to insert data...");
        
        // Insert images data
        stmt.execute("INSERT INTO images (url) VALUES\n" +
                "('/images/home.png'),\n" +
                "('/images/products.png'),\n" +
                "('/images/news.png'),\n" +
                "('/images/contact.png'),\n" +
                "('/icons/arrow-up.png'),\n" +
                "('/icons/chilun.png'),\n" +
                "('/icons/en.png'),\n" +
                "('/icons/facebook.png'),\n" +
                "('/icons/play.png'),\n" +
                "('/icons/search.png'),\n" +
                "('/icons/talegram.png'),\n" +
                "('/icons/wechat.png'),\n" +
                "('/icons/x.png'),\n" +
                "('/icons/youtube.png'),\n" +
                "('/icons/zh.png')");
        
        // Insert navigation data
        stmt.execute("INSERT INTO navigation (name, path) VALUES\n" +
                "('Home', '/'),\n" +
                "('Products', '/products'),\n" +
                "('News', '/news'),\n" +
                "('Contact', '/contact')");
        
        // Insert news_categories data
        stmt.execute("INSERT INTO news_categories (category_name) VALUES\n" +
                "('经济'),\n" +
                "('科技'),\n" +
                "('政治'),\n" +
                "('文化')");
        
        // Insert news data
        stmt.execute("INSERT INTO news (title, introduce, text, img_id, is_top, created_at, updated_at) VALUES\n" +
                "('新闻1', '新闻1概要', '新闻1文本', 3, 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00'),\n" +
                "('新闻2', '新闻2概要', '新闻2文本', 3, 0, '2024-01-02 00:00:00', '2024-01-02 00:00:00'),\n" +
                "('新闻3', '新闻3概要', '新闻3文本', 3, 0, '2024-01-03 00:00:00', '2024-01-03 00:00:00'),\n" +
                "('新闻4', '新闻4概要', '新闻4文本', 3, 0, '2024-01-03 07:30:00', '2024-01-03 09:00:00'),\n" +
                "('新闻5', '新闻5概要', '新闻5文本', 3, 0, '2024-01-03 07:50:00', '2024-01-03 09:00:00'),\n" +
                "('新闻6', '新闻6概要', '新闻6文本', 3, 0, '2024-01-03 07:55:00', '2024-01-03 09:00:00'),\n" +
                "('新闻7', '新闻7概要', '新闻7文本', 3, 0, '2024-01-03 08:30:00', '2024-01-03 09:00:00'),\n" +
                "('新闻8', '新闻8概要', '新闻8文本', 3, 0, '2024-01-03 09:30:00', '2024-01-03 12:00:00'),\n" +
                "('新闻9', '新闻9概要', '新闻9文本', 3, 0, '2024-01-03 10:30:00', '2024-01-03 14:00:00'),\n" +
                "('新闻10', '新闻10概要', '新闻10文本', 3, 0, '2024-01-03 11:30:00', '2024-01-03 15:00:00'),\n" +
                "('新闻11', '新闻11概要', '新闻11文本', 3, 0, '2024-01-03 12:30:00', '2024-01-03 16:00:00')");
        
        // Insert news_category_relations data
        stmt.execute("INSERT INTO news_category_relations (news_id, category_id) VALUES\n" +
                "(1, 1), (1, 2), (1, 3),\n" +
                "(2, 2), (3, 2), (4, 4),\n" +
                "(5, 4), (6, 4), (7, 2),\n" +
                "(8, 2), (9, 1), (9, 2),\n" +
                "(10, 1), (10, 2), (11, 1), (11, 2)");
        
        // Insert products data
        stmt.execute("INSERT INTO products (name, img_id) VALUES\n" +
                "('产品1', 2), ('产品2', 2), ('产品3', 2), ('产品4', 2),\n" +
                "('产品5', 2), ('产品6', 2), ('产品7', 2), ('产品8', 2),\n" +
                "('产品9', 2), ('产品10', 2), ('产品11', 2), ('产品12', 2)");
        
        // Insert partners data
        stmt.execute("INSERT INTO partners (name, img_id) VALUES\n" +
                "('合作伙伴1', 2), ('合作伙伴2', 2), ('合作伙伴3', 2), ('合作伙伴4', 2),\n" +
                "('合作伙伴5', 2), ('合作伙伴6', 2), ('合作伙伴7', 2), ('合作伙伴8', 2),\n" +
                "('合作伙伴9', 2), ('合作伙伴10', 2), ('合作伙伴11', 2), ('合作伙伴12', 2),\n" +
                "('合作伙伴13', 2), ('合作伙伴14', 2), ('合作伙伴15', 2)");
        
        // Insert carousels data
        stmt.execute("INSERT INTO carousels (img_id) VALUES\n" +
                "(2), (2), (2), (2), (2)");
        
        // Insert contacts data
        stmt.execute("INSERT INTO contacts (name, email, message, created_at) VALUES\n" +
                "('联系1', '联系1邮箱', '联系1消息', '2024-01-03 10:00:00')");
        
        System.out.println("Data inserted successfully!");
    }
} 