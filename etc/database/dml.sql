USE `my`;

INSERT INTO `tb_movie` (`movie_id`,`title`,`description`,`status`,`rating`,`genre`,`thumbnail`,`running_time`,`release_date`,`updated_at`,`created_at`) VALUES (1,'나 홀로 집에','크리스마스 휴가 당일, 늦잠을 자 정신없이 공항으로 출발한 맥콜리스터 가족은 전날 부린 말썽에 대한 벌로 다락방에 들어가 있던 8살 케빈을 깜박 잊고 프랑스로 떠나버린다. 매일 형제들에게 치이며 가족이 전부 없어졌으면 좋겠다고 생각한 케빈은 갑자기 찾아온 자유를 만끽한다.','SHOWING','ALL_AGES','COMEDY','https://m.media-amazon.com/images/M/MV5BNzNmNmQ2ZDEtMTc1MS00NjNiLThlMGUtZmQxNTg1Nzg5NWMzXkEyXkFqcGc@._V1_QL75_UX190_CR0,1,190,281_.jpg',103,'1991-07-06','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie` (`movie_id`,`title`,`description`,`status`,`rating`,`genre`,`thumbnail`,`running_time`,`release_date`,`updated_at`,`created_at`) VALUES (2,'탑건','최고의 파일럿들만이 갈 수 있는 캘리포니아의 한 비행 조종 학교 탑건에서의 사나이들의 우정과 사랑의 모험이 시작된다. 자신을 좇는 과거의 기억과 경쟁자, 그리고 사랑 사이에서 고군분투하는 그의 여정이 펼쳐진다.','SHOWING','FIFTEEN_ABOVE','ACTION','https://m.media-amazon.com/images/M/MV5BZmVjNzQ3MjYtYTZiNC00Y2YzLWExZTEtMTM2ZDllNDI0MzgyXkEyXkFqcGc@._V1_QL75_UX190_CR0,6,190,281_.jpg',109,'1986-05-12','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie` (`movie_id`,`title`,`description`,`status`,`rating`,`genre`,`thumbnail`,`running_time`,`release_date`,`updated_at`,`created_at`) VALUES (3,'탑건:메버릭','해군 최고의 비행사 피트 미첼은 비행 훈련소에서 갓 졸업을 한 신입 비행사들 팀의 훈련을 맡게 된다. 자신을 좇는 과거의 기억과 위험천만한 임무 속에서 고군분투하는 그의 비상이 펼쳐진다.','SHOWING','TWELVE_ABOVE','ACTION','https://m.media-amazon.com/images/M/MV5BMDBkZDNjMWEtOTdmMi00NmExLTg5MmMtNTFlYTJlNWY5YTdmXkEyXkFqcGc@._V1_QL75_UX190_CR0,0,190,281_.jpg',130,'2022-05-18','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie` (`movie_id`,`title`,`description`,`status`,`rating`,`genre`,`thumbnail`,`running_time`,`release_date`,`updated_at`,`created_at`) VALUES (4,'하얼빈','1908년 함경북도 신아산에서 안중근이 이끄는 독립군들은 일본군과의 전투에서 큰 승리를 거둔다. 대한의군 참모중장 안중근은 만국공법에 따라 전쟁포로인 일본인들을 풀어주게 되고, 이 사건으로 인해 독립군 사이에서는 안중근에 대한 의심과 함께 균열이 일기 시작한다.','SCHEDULED','FIFTEEN_ABOVE','ACTION','https://m.media-amazon.com/images/M/MV5BNmY4YzM5NzUtMTg4Yy00Yzc3LThlZDktODk4YjljZmNlODA0XkEyXkFqcGc@._V1_QL75_UY281_CR4,0,190,281_.jpg',113,'2024-12-24','2025-01-09 00:00:00','2025-01-09 00:00:00');

INSERT INTO `tb_movie_showtime` (`showtime_id`,`movie_id`,`start`,`end`,`updated_at`,`created_at`) VALUES (1,1,'08:00:00','09:45:00','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_showtime` (`showtime_id`,`movie_id`,`start`,`end`,`updated_at`,`created_at`) VALUES (2,1,'10:00:00','11:45:00','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_showtime` (`showtime_id`,`movie_id`,`start`,`end`,`updated_at`,`created_at`) VALUES (3,1,'13:00:00','14:45:00','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_showtime` (`showtime_id`,`movie_id`,`start`,`end`,`updated_at`,`created_at`) VALUES (4,1,'15:30:00','17:15:00','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_showtime` (`showtime_id`,`movie_id`,`start`,`end`,`updated_at`,`created_at`) VALUES (5,2,'10:30:00','12:15:00','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_showtime` (`showtime_id`,`movie_id`,`start`,`end`,`updated_at`,`created_at`) VALUES (6,2,'14:30:00','16:15:00','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_showtime` (`showtime_id`,`movie_id`,`start`,`end`,`updated_at`,`created_at`) VALUES (7,3,'11:30:00','14:15:00','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_showtime` (`showtime_id`,`movie_id`,`start`,`end`,`updated_at`,`created_at`) VALUES (8,3,'15:40:00','17:25:00','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_showtime` (`showtime_id`,`movie_id`,`start`,`end`,`updated_at`,`created_at`) VALUES (9,3,'18:50:00','20:45:00','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_showtime` (`showtime_id`,`movie_id`,`start`,`end`,`updated_at`,`created_at`) VALUES (10,3,'07:30:00','09:50:00','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_showtime` (`showtime_id`,`movie_id`,`start`,`end`,`updated_at`,`created_at`) VALUES (11,4,'11:10:00','13:05:00','2025-01-09 00:00:00','2025-01-09 00:00:00');

INSERT INTO `tb_theater` (`theater_id`,`name`,`updated_at`,`created_at`) VALUES (1,'강남점','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_theater` (`theater_id`,`name`,`updated_at`,`created_at`) VALUES (2,'강북점','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_theater` (`theater_id`,`name`,`updated_at`,`created_at`) VALUES (3,'봉천점','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_theater` (`theater_id`,`name`,`updated_at`,`created_at`) VALUES (4,'안양점','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_theater` (`theater_id`,`name`,`updated_at`,`created_at`) VALUES (5,'평촌점','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_theater` (`theater_id`,`name`,`updated_at`,`created_at`) VALUES (6,'인덕원점','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_theater` (`theater_id`,`name`,`updated_at`,`created_at`) VALUES (7,'사당점','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_theater` (`theater_id`,`name`,`updated_at`,`created_at`) VALUES (8,'삼성점','2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_theater` (`theater_id`,`name`,`updated_at`,`created_at`) VALUES (9,'신림점','2025-01-09 00:00:00','2025-01-09 00:00:00');

INSERT INTO `tb_movie_theater_rel` (`movie_theater_rel_id`,`movie_id`,`theater_id`,`updated_at`,`created_at`) VALUES (1,1,1,'2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_theater_rel` (`movie_theater_rel_id`,`movie_id`,`theater_id`,`updated_at`,`created_at`) VALUES (2,2,3,'2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_theater_rel` (`movie_theater_rel_id`,`movie_id`,`theater_id`,`updated_at`,`created_at`) VALUES (3,3,4,'2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_theater_rel` (`movie_theater_rel_id`,`movie_id`,`theater_id`,`updated_at`,`created_at`) VALUES (4,1,4,'2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_theater_rel` (`movie_theater_rel_id`,`movie_id`,`theater_id`,`updated_at`,`created_at`) VALUES (5,1,5,'2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_theater_rel` (`movie_theater_rel_id`,`movie_id`,`theater_id`,`updated_at`,`created_at`) VALUES (6,2,5,'2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_theater_rel` (`movie_theater_rel_id`,`movie_id`,`theater_id`,`updated_at`,`created_at`) VALUES (7,2,1,'2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_theater_rel` (`movie_theater_rel_id`,`movie_id`,`theater_id`,`updated_at`,`created_at`) VALUES (8,3,2,'2025-01-09 00:00:00','2025-01-09 00:00:00');
INSERT INTO `tb_movie_theater_rel` (`movie_theater_rel_id`,`movie_id`,`theater_id`,`updated_at`,`created_at`) VALUES (9,4,8,'2025-01-09 00:00:00','2025-01-09 00:00:00');
