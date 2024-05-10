CREATE TABLE note (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      content TEXT
);

CREATE TABLE users (
                      user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      role VARCHAR(50) NOT NULL,
                      enabled TINYINT DEFAULT NULL
);