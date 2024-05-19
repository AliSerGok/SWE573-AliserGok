DROP SCHEMA IF EXISTS `spiny-database`;

CREATE SCHEMA `spiny-database`;
USE `spiny-database`;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
SET foreign_key_checks = 1;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `enabled` tinyint NOT NULL,  
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--
INSERT INTO `user` (`username`, `password`, `enabled`, `first_name`, `last_name`, `email`)
VALUES 
('john', '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 1, 'John', 'Doe', 'john@example.com'),
('mary', '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 1, 'Mary', 'Smith', 'mary@example.com'),
('susan', '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 1, 'Susan', 'Public', 'susan@example.com'),
('michael', '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 1, 'Michael', 'Johnson', 'michael@example.com'),
('emma', '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 1, 'Emma', 'Wilson', 'emma@example.com');

CREATE TABLE `user_profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(225) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  
  KEY `FK_USER_idx` (`user_id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
  
  INSERT INTO `user_profile` (`name`, `description`, `gender`, `age`, `city`, `user_id`)
VALUES 
('John Doe', 'John is an active member of our sports club. He loves playing football and tennis.', 'Male', 35, 'New York', 1),
('Mary Smith', 'Mary is the lead instructor of our art workshop. She loves teaching children the beauty of art and enhancing their creativity.', 'Female', 40, 'Los Angeles', 2),
('Susan Public', 'Susan is the founder and leader of our nature hiking club. She enjoys exploring nature and encouraging people to connect with nature.', 'Female', 45, 'Chicago', 3),
('Michael Johnson', 'Michael is an active member of our volunteer cleaning team. He strives to serve our community and raise awareness about cleanliness.', 'Male', 30, 'Houston', 4),
('Emma Wilson', 'Emma is the vocalist and guitarist of our local music group. She enjoys making music and entertaining people.', 'Female', 28, 'Miami', 5);

  
  CREATE TABLE `community` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(225) DEFAULT NULL,
  `owner_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  
  KEY `FK_owner_idx` (`owner_id`),
  CONSTRAINT `FK_owner` FOREIGN KEY (`owner_id`) 
  REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `community` (`name`, `description`, `owner_id`)
VALUES 
('Local Sports Club', 'Bringing together sports enthusiasts for various sporting events and activities.', 1),
('Art Workshop for Kids', 'Offering creative art workshops to children, teaching painting, sculpture, and crafts.', 2),
('Nature Hiking Club', 'Exploring and enjoying nature through weekly hiking trips and outdoor activities.', 3),
('Volunteer Cleaning Team', 'Working together to keep our community clean and beautiful through regular cleaning events.', 4),
('Local Music Group', 'Promoting local music talent through concerts, gigs, and community events.', 5),
('Chess Enthusiasts Club', 'Bringing together chess lovers for regular matches, tournaments, and strategy discussions.', 2),
('Book Club', 'A space for book lovers to discuss literature, share recommendations, and explore new genres.', 2),
('Community Garden Project', 'Creating and maintaining a communal garden space for growing organic produce and fostering community connections.', 2),
('Tech Enthusiasts Forum', 'A platform for tech enthusiasts to discuss the latest trends, gadgets, and innovations in the tech world.', 3),
('Culinary Arts Society', 'Exploring the world of culinary arts through cooking workshops, food tastings, and cultural exchanges.', 3),
('Film Buffs Society', 'Celebrating cinema through screenings, discussions, and events focused on classic and contemporary films.', 1),
('Fitness and Wellness Center', 'Promoting a healthy lifestyle through fitness classes, yoga sessions, and wellness workshops.', 1),
('Language Exchange Club', 'Facilitating language learning and cultural exchange through language practice sessions and social events.', 1),
('Photography Club', 'Exploring the art of photography through photo walks, workshops, and exhibitions.', 2),
('Environmental Advocacy Group', 'Taking action to protect and preserve the environment through awareness campaigns and community initiatives.', 2),
('Local Business Network', 'Supporting local businesses through networking events, workshops, and collaborative projects.', 4),
('Pet Lovers Community', 'Bringing together pet owners to share experiences, tips, and resources for pet care and wellbeing.', 4),
('Adventure Seekers Club', 'Embarking on outdoor adventures such as hiking, camping, and extreme sports.', 1),
('Entrepreneurship Hub', 'Fostering innovation and collaboration among entrepreneurs through mentorship programs and startup events.', 2),
('Creative Writing Circle', 'Inspiring creativity and literary expression through writing workshops, readings, and peer feedback sessions.', 5);

-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `role` (name)
VALUES 
('ROLE_USER'),('ROLE_MODERATOR'),('ROLE_OWNER');

SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(2, 1),
(3, 1),
(3, 2),
(3, 3),
(4, 1),
(5, 1),
(2, 2);

DROP TABLE IF EXISTS `community_followers`;

CREATE TABLE `community_followers` (
  `community_id` int(11) NOT NULL,
  `follower_id` int(11) NOT NULL,
  
  PRIMARY KEY (`community_id`,`follower_id`),
  
  KEY `FK_COMM_idx` (`community_id`),
  
  CONSTRAINT `FK_COMM_05` FOREIGN KEY (`community_id`) 
  REFERENCES `community` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_FOLL` FOREIGN KEY (`follower_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `community_followers` (`community_id`, `follower_id`)
VALUES 
(1, 2), (1, 3), (1, 4),  
(2, 3), (2, 4), (2, 5),  
(3, 4), (3, 5), (3, 1), 
(4, 5), (4, 1), (4, 2), 
(5, 1), (5, 2), (5, 3); 

DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `community_id` INT,
  `owner_id` INT,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_community_id` FOREIGN KEY (`community_id`) REFERENCES `community` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO `template` (`name`, `community_id`) VALUES
('Community Events', 1),
('General Discussion', 2),
('Tech News', 3),
('Foodies Corner', 4),
('Support Center', 2),
('Travel Enthusiasts', 5),
('Photography Showcase', 6),
('Creative Writing', 2),
('Fitness Tips', 7),
('Movie Buffs', 2);

DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255),
  `creation_date` DATETIME,
  `update_date` DATETIME,
  `like_post` INT,
  `dislike_post` INT,
  `community_id` INT,
  `user_id` INT,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_post_community_id` FOREIGN KEY (`community_id`) REFERENCES `community` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_post_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO `post` (`title`, `creation_date`, `update_date`, `like_post`, `dislike_post`, `community_id`, `user_id`) 
VALUES 
('Güneşli bir gün', '2024-05-12', '2024-05-12', 10, 2, 1, 3),
('Yaz mevsimi ve deniz keyfi', '2024-05-12', '2024-05-12', 15, 3, 2, 5),
('Bir kitap önerisi: Da Vinci Şifresi', '2024-05-12', '2024-05-12', 20, 5, 3, 2),
('Kültür ve sanat etkinliği: Resim Sergisi', '2024-05-12', '2024-05-12', 25, 6, 4, 4),
('Bisikletle şehir turu keyfi', '2024-05-12', '2024-05-12', 30, 8, 5, 1),
('Yeni keşfedilen bir restoran: Lezzet Durağı', '2024-05-12', '2024-05-12', 12, 1, 1, 4),
('Doğa yürüyüşü ve kamp keyfi', '2024-05-12', '2024-05-12', 18, 4, 2, 2),
('Bilim ve teknoloji konferansı: Geleceği Keşfetmek', '2024-05-12', '2024-05-12', 22, 7, 3, 5),
('Ev yapımı pasta tarifleri', '2024-05-12', '2024-05-12', 27, 9, 4, 3),
('En sevdiğim film: Yeşil Yol', '2024-05-12', '2024-05-12', 35, 10, 5, 1),
('Bir hafta sonu kaçamağı: Dağ evi macerası', '2024-05-12', '2024-05-12', 14, 3, 1, 5),
('Sağlıklı yaşam önerileri', '2024-05-12', '2024-05-12', 17, 4, 2, 3),
('Gezi ve seyahat ipuçları: Seyahat Rehberi', '2024-05-12', '2024-05-12', 21, 6, 3, 1),
('Yoga ve meditasyonun faydaları', '2024-05-12', '2024-05-12', 26, 8, 4, 2),
('Ev dekorasyonu trendleri', '2024-05-13', '2024-05-13', 32, 11, 5, 4),
('Birçok dilde selam: Merhaba, Bonjour, Hello', '2024-05-13', '2024-05-13', 8, 2, 1, 1),
('Tarihi ve kültürel miras: Antik Kentler Turu', '2024-05-13', '2024-05-13', 23, 7, 2, 4),
('Klasik müzik ziyafeti: Beethoven Konseri', '2024-05-13', '2024-05-13', 28, 9, 3, 3),
('Dünya mutfağı lezzetleri: Gastronomi Festivali', '2024-05-13', '2024-05-13', 31, 10, 4, 5),
('Spor ve egzersiz önerileri', '2024-05-13', '2024-05-13', 16, 5, 5, 2);


DROP TABLE IF EXISTS `data_field`;
CREATE TABLE `data_field` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `input_value` VARCHAR(255),
  `is_required` BOOLEAN NOT NULL DEFAULT FALSE,
  `post_id` INT,
  `value` VARCHAR(15),
  `template_id` INT,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_template_id` FOREIGN KEY (`template_id`) REFERENCES `template` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
INSERT INTO `data_field` (`name`, `is_required`, `input_value`, `value`, `template_id`, `post_id`) VALUES
('Event Date', TRUE, '2024-05-20', 'text', 1, 1), ('Location', TRUE, 'New York', 'text', 1, 1), ('Description', TRUE, 'Lorem ipsum dolor sit amet', 'text', 1, 1), ('Event Image', FALSE, 'event.jpg', 'image', 1, 1),
('Topic', TRUE, 'Technology', 'text', 2, 2), ('Content', TRUE, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'text', 2, 2), ('Tags', FALSE, 'tech, innovation', 'text', 2, 2), ('Thumbnail', FALSE, 'thumbnail.jpg', 'image', 2, 2),
('Headline', TRUE, 'Breaking News', 'text', 3, 3), ('Article', TRUE, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'text', 3, 3), ('Source', TRUE, 'CNN', 'text', 3, 3), ('Thumbnail', FALSE, 'thumbnail.jpg', 'image', 3, 3),
('Dish Name', TRUE, 'Spaghetti Carbonara', 'text', 4, 4), ('Recipe', TRUE, 'Ingredients: spaghetti, eggs, bacon, parmesan cheese', 'text', 4, 4), ('Ingredients', TRUE, 'spaghetti, eggs, bacon, parmesan cheese', 'text', 4, 4), ('Image', FALSE, 'spaghetti.jpg', 'image', 4, 4),
('Issue', TRUE, 'Bug in software', 'text', 5, 5), ('Description', TRUE, 'The software crashes when the user tries to save the file', 'text', 5, 5), ('Screenshot', FALSE, 'screenshot.jpg', 'image', 5, 5), ('Priority', FALSE, 'High', 'text', 5, 5),
('Destination', TRUE, 'Paris', 'text', 6, 6), ('Travel Date', TRUE, '2024-07-10', 'text', 6, 6), ('Experience', TRUE, 'Wonderful trip, enjoyed the Eiffel Tower', 'text', 6, 6), ('Photo', FALSE, 'eiffel.jpg', 'image', 6, 6),
('Photo Title', TRUE, 'Sunset', 'text', 7, 7), ('Description', TRUE, 'Beautiful sunset over the ocean', 'text', 7, 7), ('Camera Used', FALSE, 'Canon EOS 5D Mark IV', 'text', 7, 7), ('Image', FALSE, 'sunset.jpg', 'image', 7, 7),
('Title', TRUE, 'The Great Gatsby', 'text', 8, 8), ('Content', TRUE, 'A novel by F. Scott Fitzgerald', 'text', 8, 8), ('Genre', TRUE, 'Fiction', 'text', 8, 8), ('Cover Image', FALSE, 'gatsby.jpg', 'image', 8, 8),
('Tip Title', TRUE, 'Cooking Tip', 'text', 9, 9), ('Description', TRUE, 'Always preheat the oven before baking', 'text', 9, 9), ('Category', TRUE, 'Food', 'text', 9, 9), ('Poster', FALSE, 'cookingtip.jpg', 'image', 9, 9),
('Movie Title', TRUE, 'Inception', 'text', 10, 10), ('Review', TRUE, 'Mind-bending thriller directed by Christopher Nolan', 'text', 10, 10), ('Rating', TRUE, '9.5', 'decimal', 10, 10), ('Audio File', FALSE, 'inception.mp3', 'audio', 10, 10);







