INSERT INTO USERS (username, password, role, enabled)
VALUES ('user', '$2a$12$fuvH7SmHDGAi11azk2crru3LYGGO6I9t12uQOSLt4DAL9Gmp4ECcG', 'user', 1);

INSERT INTO USERS (username, password, role, enabled)
VALUES ('bob', '$2a$12$Vkha6mUMW3K55ZBXs3MJ4eYeoTN9ublFeQ0S5achQLMTSSU4vA0hK', 'user', 1);

INSERT INTO NOTES (user_id, title, content) VALUES (1, 'Заметка 1', 'Содержимое заметки номер один.');
INSERT INTO NOTES (user_id, title, content) VALUES (1, 'Важное дело', 'Срочно купить молоко и яйца.');
INSERT INTO NOTES (user_id, title, content) VALUES (2, 'Запомнить', 'Запомни это содержимое');
INSERT INTO NOTES (user_id, title, content) VALUES (2, 'Встреча 1', 'Сегодня в 14.00 на главной площади');