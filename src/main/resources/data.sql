INSERT INTO ROLE VALUES (1111, 'Admin');
INSERT INTO ROLE VALUES (3333, 'Staff');
INSERT INTO ROLE VALUES (2222, 'Student');
INSERT INTO ROLE VALUES (4444, 'Faculty');
--
INSERT INTO LOCATION_TYPE VALUES (1, 'DINING_HALL');
INSERT INTO LOCATION_TYPE VALUES (2, 'MEDITATION_HALL');
INSERT INTO LOCATION_TYPE VALUES (3, 'FLYING_HALL');
INSERT INTO LOCATION_TYPE VALUES (4, 'CLASSROOM');
INSERT INTO LOCATION_TYPE VALUES (5, 'GYMNASIUM');
INSERT INTO LOCATION_TYPE VALUES (6, 'DORMITORY');
--
INSERT INTO MEMBER VALUES (111, 'user1@miu.edu', 'first', 'user', '$2a$12$uKceNi9oD/.HT0PFlLo9GuyVArSp2g6AWRFeD/QZ0lLNXYriv8/t6');
INSERT INTO MEMBER VALUES (222, 'user2@miu.edu', 'second', 'user', '$2a$12$uKceNi9oD/.HT0PFlLo9GuyVArSp2g6AWRFeD/QZ0lLNXYriv8/t6');
--
INSERT INTO MEMBER_ROLE VALUES (111, 1111);
INSERT INTO MEMBER_ROLE VALUES (111, 3333);
INSERT INTO MEMBER_ROLE VALUES (111, 4444);
INSERT INTO MEMBER_ROLE VALUES (222, 2222);


INSERT INTO BADGE VALUES (1111, true, '9782-abc', 111);
INSERT INTO BADGE VALUES (2222, false, '9782-def', 111);

