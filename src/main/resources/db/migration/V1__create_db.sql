CREATE TABLE USERS (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       enabled TINYINT DEFAULT NULL
);

CREATE TABLE NOTES (
                      note_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      user_id BIGINT,
                      title VARCHAR(255) NOT NULL,
                      content TEXT,
                      FOREIGN KEY (user_id) REFERENCES users(user_id)
);