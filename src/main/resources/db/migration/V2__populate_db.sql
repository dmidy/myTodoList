INSERT INTO USERS (username, password, role, enabled)
VALUES ('user', '$2a$12$fuvH7SmHDGAi11azk2crru3LYGGO6I9t12uQOSLt4DAL9Gmp4ECcG', 'user', 1);

INSERT INTO NOTES (user_id, title, content) VALUES (1, 'Заметка 1', 'Содержимое заметки номер один.');
INSERT INTO NOTES (user_id, title, content) VALUES (1, 'Важное дело', 'Срочно купить молоко и яйца.');