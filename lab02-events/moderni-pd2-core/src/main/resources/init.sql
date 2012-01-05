CREATE DATABASE IF NOT EXISTS modernipdtwo;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL ON modernipdtwo.* to 'user'@'localhost';
