CREATE DATABASE IF NOT EXISTS events;
CREATE USER 'events_user'@'localhost' IDENTIFIED BY 'events_pass';
GRANT ALL ON events.* to 'events_user'@'localhost';
