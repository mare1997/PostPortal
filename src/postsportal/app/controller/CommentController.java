INSERT INTO users(name,username,pasword,user_type) VALUES ('Marko','a','a','ADMIN');
INSERT INTO users(name,username,pasword,user_type) VALUES ('Nikola','p','p','PUBLISHER');
INSERT INTO users(name,username,pasword,user_type) VALUES ('Milos','u','u','USER');
INSERT INTO users(name,username,pasword,user_type) VALUES ('Una','aa','aa','ADMIN');
INSERT INTO users(name,username,pasword,user_type) VALUES ('Lea','pp','pp','PUBLISHER');

INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('Title post 1','Description title 1','2018-1-1',1,1,45.264861,19.829698,1);
INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('Title post 2','Description title 2','2018-2-2',2,2,44.786568,20.448922,2);
INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('Title post 3','Description title 3','2018-2-3',11,1,45.264861,19.829698,2);
INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('Title post 4','Description title 4','2018-2-1',45,5,44.786568,20.448922,1);
INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('Title post 5','Description title 5','2018-4-1',10,150,36.123248,-5.452579,4);
INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('Title post 6','Description title 6','2018-5-1',10,1,36.123248,-5.452579,4);
INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('Title post 7','Description title 7','2018-6-1',15,10,36.123248,-5.452579,5);
INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('Title post 8','Description title 8','2018-7-1',13,10,56.134248,-5.452579,5);


INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 1','Text comment 1','2018-4-12',4,1,1,1);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 2','Text comment 2','2018-4-3',4,7,1,2);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 3','Text comment 3','2018-4-3',15,0,2,3);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 4','Text comment 4','2018-5-12',4,0,2,4);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 5','Text comment 5','2018-4-1',4,2,3,5);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 6','Text comment 6','2018-5-12',4,0,3,1);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 7','Text comment 7','2018-5-12',12,11,4,2);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 8','Text comment 8','2018-5-12',114,11,4,4);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 9','Text comment 9','2018-5-12',12,2,5,5);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 10','Text comment 10','2018-5-12',12,23,5,4);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 11','Text comment 11','2018-5-12',1,3,6,1);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 12','Text comment 12','2018-5-12',2,2,6,2);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 13','Text comment 13','2018-5-12',1,2,7,3);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 14','Text comment 14','2018-5-12',12,43,7,4);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 15','Text comment 15','2018-5-12',1,4,8,5);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Comment 16','Text comment 16','2018-5-12',1,2,8,3);


INSERT INTO tags(name)VALUES ('HELLO');
INSERT INTO tags(name)VALUES ('COM');
INSERT INTO tags(name)VALUES ('COOL');
INSERT INTO tags(name)VALUES ('NEW');
INSERT INTO tags(name)VALUES ('MOVIE');
INSERT INTO tags(name)VALUES ('BEST');

INSERT INTO post_tags(post_id,tag_id)VALUES (1,1);
INSERT INTO post_tags(post_id,tag_id)VALUES (1,2);
INSERT INTO post_tags(post_id,tag_id)VALUES (1,3);
INSERT INTO post_tags(post_id,tag_id)VALUES (2,1);
INSERT INTO post_tags(post_id,tag_id)VALUES (2,2);
INSERT INTO post_tags(post_id,tag_id)VALUES (2,3);
INSERT INTO post_tags(post_id,tag_id)VALUES (3,4);
INSERT INTO post_tags(post_id,tag_id)VALUES (3,6);
INSERT INTO post_tags(post_id,tag_id)VALUES (3,5);
INSERT INTO post_tags(post_id,tag_id)VALUES (4,1);
INSERT INTO post_tags(post_id,tag_id)VALUES (4,5);
INSERT INTO post_tags(post_id,tag_id)VALUES (5,2);
INSERT INTO post_tags(post_id,tag_id)VALUES (5,3);
INSERT INTO post_tags(post_id,tag_id)VALUES (6,6);
INSERT INTO post_tags(post_id,tag_id)VALUES (6,1);
INSERT INTO post_tags(post_id,tag_id)VALUES (7,5);
INSERT INTO post_tags(post_id,tag_id)VALUES (7,4);
INSERT INTO post_tags(post_id,tag_id)VALUES (8,2);
INSERT INTO post_tags(post_id,tag_id)VALUES (8,4);