DROP TABLE IF EXISTS score;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS images;

CREATE TABLE images(
  id_image INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  insert_date date NOT NULL,
  points INT
);

CREATE TABLE score(
  score_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  image_id INT(6) NOT NULL,
  image_name VARCHAR(255) NOT NULL,
  user_name VARCHAR(255) NOT NULL,
  FOREIGN KEY (image_id) REFERENCES images(id_image)
);

CREATE TABLE tags
(
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  image_name VARCHAR(255) NOT NULL,
  tag VARCHAR(255) NOT NULL,
  FOREIGN KEY (image_name) REFERENCES images (name)
);

CREATE TABLE users
(
  user_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  user_name VARCHAR(255),
  user_password VARCHAR(255),
  email VARCHAR(100) NOT NULL
);

INSERT INTO images (title, name, points,insert_date) VALUES ('title','0Ay9Za7DxKIeTFIU0LEDJbMANnhDdl4pMonJun08110657CEST2020.jpg',0,NOW());
INSERT INTO images (title, name, points,insert_date) VALUES ('title','2gFeZa1UWnRGMhMNNgMp5mwGIDzKLpHCWedJun03151435CEST2020.jpg',0,NOW());
INSERT INTO images (title, name, points,insert_date) VALUES ('title','5cJNSLGLwKJIeMllmRSdc3OE9gOjikSbMonJun08110631CEST2020.jpg',0,NOW());
INSERT INTO images (title, name, points,insert_date) VALUES ('title','7J8jFeL98dpmjDuSczIsahCj05BtGwJ2MonJun08101833CEST2020.jpg',0,NOW());
INSERT INTO images (title, name, points,insert_date) VALUES ('title','48MhFUpW5CACtS30UNMauFu1cmS9aIbuWedJun03161427CEST2020.jpg',0,NOW());
INSERT INTO images (title, name, points,insert_date) VALUES ('title','292j81F8tlNpd9Kd8pFKbu018jLpHG3PMonJun08101944CEST2020.jpg',0,NOW());

INSERT INTO tags (image_name, tag) VALUES ('0Ay9Za7DxKIeTFIU0LEDJbMANnhDdl4pMonJun08110657CEST2020.jpg','TAG2');
INSERT INTO tags (image_name, tag) VALUES ('0Ay9Za7DxKIeTFIU0LEDJbMANnhDdl4pMonJun08110657CEST2020.jpg','TAG1');

INSERT INTO tags (image_name, tag) VALUES ('2gFeZa1UWnRGMhMNNgMp5mwGIDzKLpHCWedJun03151435CEST2020.jpg','TAG2');
INSERT INTO tags (image_name, tag) VALUES ('2gFeZa1UWnRGMhMNNgMp5mwGIDzKLpHCWedJun03151435CEST2020.jpg','TAG1');
