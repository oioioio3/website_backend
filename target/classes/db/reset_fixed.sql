-- 使用website数据库
USE website;

-- 关闭外键检查
SET FOREIGN_KEY_CHECKS = 0;

-- 删除所有已有表（如果存在）
DROP TABLE IF EXISTS contacts;
DROP TABLE IF EXISTS carousels;
DROP TABLE IF EXISTS partners;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS news_category_relations;
DROP TABLE IF EXISTS news;
DROP TABLE IF EXISTS news_categories;
DROP TABLE IF EXISTS navigation;
DROP TABLE IF EXISTS images;

-- 开启外键检查
SET FOREIGN_KEY_CHECKS = 1;

-- 创建表
CREATE TABLE images (
    id INT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE navigation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    path VARCHAR(100) NOT NULL
);

CREATE TABLE news_categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE news (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    introduce TEXT NOT NULL,
    text TEXT NOT NULL,
    img_id INT,
    is_top TINYINT(1) DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (img_id) REFERENCES images(id)
);

CREATE TABLE news_category_relations (
    news_id INT,
    category_id INT,
    PRIMARY KEY (news_id, category_id),
    FOREIGN KEY (news_id) REFERENCES news(id),
    FOREIGN KEY (category_id) REFERENCES news_categories(id)
);

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    img_id INT,
    FOREIGN KEY (img_id) REFERENCES images(id)
);

CREATE TABLE partners (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    img_id INT,
    FOREIGN KEY (img_id) REFERENCES images(id)
);

CREATE TABLE carousels (
    id INT AUTO_INCREMENT PRIMARY KEY,
    img_id INT,
    FOREIGN KEY (img_id) REFERENCES images(id)
);

CREATE TABLE contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 重置自增ID
ALTER TABLE images AUTO_INCREMENT = 1;
ALTER TABLE navigation AUTO_INCREMENT = 1;
ALTER TABLE news_categories AUTO_INCREMENT = 1;
ALTER TABLE news AUTO_INCREMENT = 1;
ALTER TABLE products AUTO_INCREMENT = 1;
ALTER TABLE partners AUTO_INCREMENT = 1;
ALTER TABLE carousels AUTO_INCREMENT = 1;
ALTER TABLE contacts AUTO_INCREMENT = 1;

-- 插入数据
-- 首先插入images，确保ID顺序正确
INSERT INTO images (url) VALUES
('/images/home.png'),
('/images/products.png'),
('/images/news.png'),
('/images/contact.png'),
('/icons/arrow-up.png'),
('/icons/chilun.png'),
('/icons/en.png'),
('/icons/facebook.png'),
('/icons/play.png'),
('/icons/search.png'),
('/icons/talegram.png'),
('/icons/wechat.png'),
('/icons/x.png'),
('/icons/youtube.png'),
('/icons/zh.png');

INSERT INTO navigation (name, path) VALUES
('Home', '/'),
('Products', '/products'),
('News', '/news'),
('Contact', '/contact');

INSERT INTO news_categories (category_name) VALUES
('经济'),
('科技'),
('政治'),
('文化');

INSERT INTO news (title, introduce, text, img_id, is_top, created_at, updated_at) VALUES
('新闻1', '新闻1概要', '新闻1文本', 3, 1, '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('新闻2', '新闻2概要', '新闻2文本', 3, 0, '2024-01-02 00:00:00', '2024-01-02 00:00:00'),
('新闻3', '新闻3概要', '新闻3文本', 3, 0, '2024-01-03 00:00:00', '2024-01-03 00:00:00'),
('新闻4', '新闻4概要', '新闻4文本', 3, 0, '2024-01-03 07:30:00', '2024-01-03 09:00:00'),
('新闻5', '新闻5概要', '新闻5文本', 3, 0, '2024-01-03 07:50:00', '2024-01-03 09:00:00'),
('新闻6', '新闻6概要', '新闻6文本', 3, 0, '2024-01-03 07:55:00', '2024-01-03 09:00:00'),
('新闻7', '新闻7概要', '新闻7文本', 3, 0, '2024-01-03 08:30:00', '2024-01-03 09:00:00'),
('新闻8', '新闻8概要', '新闻8文本', 3, 0, '2024-01-03 09:30:00', '2024-01-03 12:00:00'),
('新闻9', '新闻9概要', '新闻9文本', 3, 0, '2024-01-03 10:30:00', '2024-01-03 14:00:00'),
('新闻10', '新闻10概要', '新闻10文本', 3, 0, '2024-01-03 11:30:00', '2024-01-03 15:00:00'),
('新闻11', '新闻11概要', '新闻11文本', 3, 0, '2024-01-03 12:30:00', '2024-01-03 16:00:00');

INSERT INTO news_category_relations (news_id, category_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 2), (3, 2), (4, 4),
(5, 4), (6, 4), (7, 2),
(8, 2), (9, 1), (9, 2),
(10, 1), (10, 2), (11, 1), (11, 2);

INSERT INTO products (name, img_id) VALUES
('产品1', 2), ('产品2', 2), ('产品3', 2), ('产品4', 2),
('产品5', 2), ('产品6', 2), ('产品7', 2), ('产品8', 2),
('产品9', 2), ('产品10', 2), ('产品11', 2), ('产品12', 2);

INSERT INTO partners (name, img_id) VALUES
('合作伙伴1', 2), ('合作伙伴2', 2), ('合作伙伴3', 2), ('合作伙伴4', 2),
('合作伙伴5', 2), ('合作伙伴6', 2), ('合作伙伴7', 2), ('合作伙伴8', 2),
('合作伙伴9', 2), ('合作伙伴10', 2), ('合作伙伴11', 2), ('合作伙伴12', 2),
('合作伙伴13', 2), ('合作伙伴14', 2), ('合作伙伴15', 2);

INSERT INTO carousels (img_id) VALUES
(2), (2), (2), (2), (2);

INSERT INTO contacts (name, email, message, created_at) VALUES
('联系1', '联系1邮箱', '联系1消息', '2024-01-03 10:00:00'); 