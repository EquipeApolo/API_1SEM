create database AccountSystem;

create user 'user'@'localhost' identified by 'teste';

grant ALL PRIVILEGES on AccountSystem.* to user@'localhost';