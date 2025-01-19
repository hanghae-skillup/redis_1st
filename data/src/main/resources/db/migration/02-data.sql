-- 좌석 생성 프로시저
DELIMITER //
CREATE PROCEDURE insert_seats()
BEGIN
    -- 1관 좌석 생성 (A-E행, 각 행 1-5번)
    SET @row = 'A';
    SET @seat_no = 1;
    
    WHILE @row <= 'E' DO
        SET @seat_no = 1;
        WHILE @seat_no <= 5 DO
            INSERT INTO seat (theater_id, seat_number, created_at, created_by, updated_at, updated_by)
            VALUES (1, CONCAT(@row, @seat_no), NOW(), 'system', NOW(), 'system');
            SET @seat_no = @seat_no + 1;
        END WHILE;
        SET @row = CHAR(ASCII(@row) + 1);
    END WHILE;

    -- 2관 좌석 생성 (A-E행, 각 행 1-5번)
    SET @row = 'A';
    SET @seat_no = 1;
    
    WHILE @row <= 'E' DO
        SET @seat_no = 1;
        WHILE @seat_no <= 5 DO
            INSERT INTO seat (theater_id, seat_number, created_at, created_by, updated_at, updated_by)
            VALUES (2, CONCAT(@row, @seat_no), NOW(), 'system', NOW(), 'system');
            SET @seat_no = @seat_no + 1;
        END WHILE;
        SET @row = CHAR(ASCII(@row) + 1);
    END WHILE;
END //
DELIMITER ;

-- 영화 데이터 생성 프로시저
DELIMITER //
CREATE PROCEDURE insert_movies()
BEGIN
    -- 영화 제목 리스트 (500개)
    INSERT INTO movie (title, rating, release_date, thumbnail_url, running_time, genre, created_at, created_by, updated_at, updated_by)
    WITH movie_list AS (
        -- 드라마 (100개)
        SELECT 'The Shawshank Redemption' as title, 'FIFTEEN' as rating, 142 as running_time, 'DRAMA' as genre UNION ALL
        SELECT 'Forrest Gump', 'TWELVE', 142, 'DRAMA' UNION ALL
        SELECT 'The Green Mile', 'FIFTEEN', 189, 'DRAMA' UNION ALL
        SELECT '12 Angry Men', 'TWELVE', 96, 'DRAMA' UNION ALL
        SELECT 'Schindler''s List', 'FIFTEEN', 195, 'DRAMA' UNION ALL
        SELECT 'The Godfather', 'EIGHTEEN', 175, 'DRAMA' UNION ALL
        SELECT 'One Flew Over the Cuckoo''s Nest', 'FIFTEEN', 133, 'DRAMA' UNION ALL
        SELECT 'Goodfellas', 'EIGHTEEN', 146, 'DRAMA' UNION ALL
        SELECT 'The Departed', 'EIGHTEEN', 151, 'DRAMA' UNION ALL
        SELECT 'A Beautiful Mind', 'TWELVE', 135, 'DRAMA' UNION ALL
        SELECT 'The Pianist', 'FIFTEEN', 150, 'DRAMA' UNION ALL
        SELECT 'Saving Private Ryan', 'FIFTEEN', 169, 'DRAMA' UNION ALL
        SELECT 'The Social Network', 'TWELVE', 120, 'DRAMA' UNION ALL
        SELECT 'The Theory of Everything', 'TWELVE', 123, 'DRAMA' UNION ALL
        SELECT 'Dallas Buyers Club', 'FIFTEEN', 117, 'DRAMA' UNION ALL
        SELECT 'The Pursuit of Happyness', 'TWELVE', 117, 'DRAMA' UNION ALL
        SELECT 'The King''s Speech', 'TWELVE', 118, 'DRAMA' UNION ALL
        SELECT 'Million Dollar Baby', 'TWELVE', 132, 'DRAMA' UNION ALL
        SELECT 'Rain Man', 'FIFTEEN', 133, 'DRAMA' UNION ALL
        SELECT 'Good Will Hunting', 'FIFTEEN', 126, 'DRAMA' UNION ALL
        SELECT 'The Curious Case of Benjamin Button', 'TWELVE', 166, 'DRAMA' UNION ALL
        SELECT 'American Beauty', 'EIGHTEEN', 122, 'DRAMA' UNION ALL
        SELECT 'Slumdog Millionaire', 'FIFTEEN', 120, 'DRAMA' UNION ALL
        SELECT 'The Reader', 'EIGHTEEN', 124, 'DRAMA' UNION ALL
        SELECT 'The English Patient', 'FIFTEEN', 162, 'DRAMA' UNION ALL
        SELECT 'Life of Pi', 'TWELVE', 127, 'DRAMA' UNION ALL
        SELECT 'The Revenant', 'EIGHTEEN', 156, 'DRAMA' UNION ALL
        SELECT 'Spotlight', 'FIFTEEN', 129, 'DRAMA' UNION ALL
        SELECT 'Room', 'FIFTEEN', 118, 'DRAMA' UNION ALL
        SELECT 'La La Land', 'TWELVE', 128, 'DRAMA' UNION ALL
        SELECT 'Moonlight', 'FIFTEEN', 111, 'DRAMA' UNION ALL
        SELECT 'Manchester by the Sea', 'FIFTEEN', 137, 'DRAMA' UNION ALL
        SELECT 'Three Billboards Outside Ebbing, Missouri', 'EIGHTEEN', 115, 'DRAMA' UNION ALL
        SELECT 'The Shape of Water', 'EIGHTEEN', 123, 'DRAMA' UNION ALL
        SELECT 'Call Me by Your Name', 'EIGHTEEN', 132, 'DRAMA' UNION ALL
        SELECT 'Dunkirk', 'TWELVE', 106, 'DRAMA' UNION ALL
        SELECT 'The Post', 'TWELVE', 116, 'DRAMA' UNION ALL
        SELECT 'Darkest Hour', 'TWELVE', 125, 'DRAMA' UNION ALL
        SELECT 'Phantom Thread', 'FIFTEEN', 130, 'DRAMA' UNION ALL
        SELECT 'The Florida Project', 'FIFTEEN', 111, 'DRAMA' UNION ALL
        SELECT 'First Man', 'TWELVE', 141, 'DRAMA' UNION ALL
        SELECT 'BlacKkKlansman', 'FIFTEEN', 135, 'DRAMA' UNION ALL
        SELECT 'Green Book', 'TWELVE', 130, 'DRAMA' UNION ALL
        SELECT 'Roma', 'FIFTEEN', 135, 'DRAMA' UNION ALL
        SELECT 'A Star Is Born', 'FIFTEEN', 136, 'DRAMA' UNION ALL
        SELECT 'If Beale Street Could Talk', 'FIFTEEN', 119, 'DRAMA' UNION ALL
        SELECT 'Vice', 'FIFTEEN', 132, 'DRAMA' UNION ALL
        SELECT 'The Favourite', 'FIFTEEN', 119, 'DRAMA' UNION ALL
        SELECT 'Bohemian Rhapsody', 'TWELVE', 134, 'DRAMA' UNION ALL
        SELECT 'Marriage Story', 'FIFTEEN', 137, 'DRAMA' UNION ALL
        SELECT 'The Irishman', 'EIGHTEEN', 209, 'DRAMA' UNION ALL
        SELECT 'Little Women', 'ALL', 135, 'DRAMA' UNION ALL
        SELECT 'Jojo Rabbit', 'TWELVE', 108, 'DRAMA' UNION ALL
        SELECT '1917', 'FIFTEEN', 119, 'DRAMA' UNION ALL
        SELECT 'The Trial of the Chicago 7', 'FIFTEEN', 129, 'DRAMA' UNION ALL
        SELECT 'Mank', 'FIFTEEN', 131, 'DRAMA' UNION ALL
        SELECT 'Nomadland', 'TWELVE', 108, 'DRAMA' UNION ALL
        SELECT 'The Father', 'TWELVE', 97, 'DRAMA' UNION ALL
        SELECT 'Minari', 'TWELVE', 115, 'DRAMA' UNION ALL
        SELECT 'Sound of Metal', 'FIFTEEN', 120, 'DRAMA' UNION ALL
        SELECT 'Promising Young Woman', 'EIGHTEEN', 113, 'DRAMA' UNION ALL
        SELECT 'The Power of the Dog', 'FIFTEEN', 126, 'DRAMA' UNION ALL
        SELECT 'CODA', 'TWELVE', 111, 'DRAMA' UNION ALL
        SELECT 'Belfast', 'TWELVE', 98, 'DRAMA' UNION ALL
        SELECT 'King Richard', 'TWELVE', 144, 'DRAMA' UNION ALL
        SELECT 'Don''t Look Up', 'FIFTEEN', 138, 'DRAMA' UNION ALL
        SELECT 'Drive My Car', 'FIFTEEN', 179, 'DRAMA' UNION ALL
        SELECT 'Licorice Pizza', 'FIFTEEN', 133, 'DRAMA' UNION ALL
        SELECT 'The Lost Daughter', 'FIFTEEN', 121, 'DRAMA' UNION ALL
        SELECT 'Being the Ricardos', 'TWELVE', 131, 'DRAMA' UNION ALL
        SELECT 'The Eyes of Tammy Faye', 'TWELVE', 126, 'DRAMA' UNION ALL
        SELECT 'tick, tick...BOOM!', 'TWELVE', 115, 'DRAMA' UNION ALL
        SELECT 'The Tragedy of Macbeth', 'FIFTEEN', 105, 'DRAMA' UNION ALL
        SELECT 'Spencer', 'TWELVE', 111, 'DRAMA' UNION ALL
        SELECT 'Mass', 'FIFTEEN', 111, 'DRAMA' UNION ALL
        SELECT 'C''mon C''mon', 'TWELVE', 109, 'DRAMA' UNION ALL
        SELECT 'The Hand of God', 'EIGHTEEN', 130, 'DRAMA' UNION ALL
        SELECT 'Passing', 'TWELVE', 98, 'DRAMA' UNION ALL
        SELECT 'The Worst Person in the World', 'EIGHTEEN', 128, 'DRAMA' UNION ALL
        SELECT 'A Hero', 'TWELVE', 127, 'DRAMA' UNION ALL
        SELECT 'Parallel Mothers', 'FIFTEEN', 123, 'DRAMA' UNION ALL
        SELECT 'The Card Counter', 'EIGHTEEN', 111, 'DRAMA' UNION ALL
        SELECT 'Blue Bayou', 'FIFTEEN', 119, 'DRAMA' UNION ALL
        SELECT 'Worth', 'TWELVE', 118, 'DRAMA' UNION ALL
        SELECT 'Swan Song', 'TWELVE', 112, 'DRAMA' UNION ALL
        SELECT 'The Tender Bar', 'FIFTEEN', 106, 'DRAMA' UNION ALL
        SELECT 'The Humans', 'FIFTEEN', 108, 'DRAMA' UNION ALL
        SELECT 'The Unforgivable', 'FIFTEEN', 112, 'DRAMA' UNION ALL
        SELECT 'The Last Duel', 'EIGHTEEN', 152, 'DRAMA' UNION ALL
        SELECT 'Cyrano', 'TWELVE', 124, 'DRAMA' UNION ALL
        SELECT 'The Electrical Life of Louis Wain', 'TWELVE', 111, 'DRAMA' UNION ALL
        SELECT 'Benedetta', 'EIGHTEEN', 131, 'DRAMA' UNION ALL
        SELECT 'Red Rocket', 'EIGHTEEN', 128, 'DRAMA' UNION ALL
        SELECT 'The Novice', 'FIFTEEN', 94, 'DRAMA' UNION ALL
        SELECT 'Jockey', 'FIFTEEN', 94, 'DRAMA' UNION ALL
        SELECT 'A Journal for Jordan', 'TWELVE', 131, 'DRAMA' UNION ALL
        SELECT 'The Tiger Rising', 'TWELVE', 102, 'DRAMA' UNION ALL
        SELECT 'Clean', 'FIFTEEN', 94, 'DRAMA' UNION ALL
        SELECT 'Sundown', 'FIFTEEN', 83, 'DRAMA' UNION ALL
        SELECT 'Montana Story', 'FIFTEEN', 113, 'DRAMA' UNION ALL
        SELECT 'After Yang', 'TWELVE', 96, 'DRAMA' UNION ALL
        SELECT 'The Outfit', 'FIFTEEN', 105, 'DRAMA' UNION ALL
        SELECT 'The Duke', 'TWELVE', 96, 'DRAMA' UNION ALL
        SELECT 'Operation Mincemeat', 'TWELVE', 128, 'DRAMA' UNION ALL
        SELECT 'Mothering Sunday', 'EIGHTEEN', 104, 'DRAMA' UNION ALL
        SELECT 'The Survivor', 'FIFTEEN', 129, 'DRAMA' UNION ALL
        SELECT 'Peace by Chocolate', 'ALL', 96, 'DRAMA' UNION ALL

        -- SF/판타지 (80개)
        SELECT 'Inception', 'TWELVE', 148, 'SF' UNION ALL
        SELECT 'The Matrix', 'FIFTEEN', 136, 'SF' UNION ALL
        SELECT 'Interstellar', 'TWELVE', 169, 'SF' UNION ALL
        SELECT 'Blade Runner 2049', 'FIFTEEN', 164, 'SF' UNION ALL
        SELECT 'Avatar', 'TWELVE', 162, 'SF' UNION ALL
        SELECT 'The Lord of the Rings: The Fellowship of the Ring', 'TWELVE', 178, 'FANTASY' UNION ALL
        SELECT 'Harry Potter and the Sorcerer''s Stone', 'ALL', 152, 'FANTASY' UNION ALL
        SELECT 'Star Wars: Episode IV - A New Hope', 'ALL', 121, 'SF' UNION ALL
        SELECT 'E.T. the Extra-Terrestrial', 'ALL', 115, 'SF' UNION ALL
        SELECT 'Back to the Future', 'ALL', 116, 'SF' UNION ALL
        SELECT 'The Matrix Reloaded', 'FIFTEEN', 138, 'SF' UNION ALL
        SELECT 'The Matrix Revolutions', 'FIFTEEN', 129, 'SF' UNION ALL
        SELECT 'Blade Runner', 'FIFTEEN', 117, 'SF' UNION ALL
        SELECT 'Star Wars: Episode V - The Empire Strikes Back', 'ALL', 124, 'SF' UNION ALL
        SELECT 'Star Wars: Episode VI - Return of the Jedi', 'ALL', 131, 'SF' UNION ALL
        SELECT 'The Lord of the Rings: The Two Towers', 'TWELVE', 179, 'FANTASY' UNION ALL
        SELECT 'The Lord of the Rings: The Return of the King', 'TWELVE', 201, 'FANTASY' UNION ALL
        SELECT 'Harry Potter and the Chamber of Secrets', 'ALL', 161, 'FANTASY' UNION ALL
        SELECT 'Harry Potter and the Prisoner of Azkaban', 'ALL', 142, 'FANTASY' UNION ALL
        SELECT 'Harry Potter and the Goblet of Fire', 'TWELVE', 157, 'FANTASY' UNION ALL
        SELECT 'Harry Potter and the Order of the Phoenix', 'TWELVE', 138, 'FANTASY' UNION ALL
        SELECT 'Harry Potter and the Half-Blood Prince', 'TWELVE', 153, 'FANTASY' UNION ALL
        SELECT 'Harry Potter and the Deathly Hallows – Part 1', 'TWELVE', 146, 'FANTASY' UNION ALL
        SELECT 'Harry Potter and the Deathly Hallows – Part 2', 'TWELVE', 130, 'FANTASY' UNION ALL
        SELECT 'The Hobbit: An Unexpected Journey', 'TWELVE', 169, 'FANTASY' UNION ALL
        SELECT 'The Hobbit: The Desolation of Smaug', 'TWELVE', 161, 'FANTASY' UNION ALL
        SELECT 'The Hobbit: The Battle of the Five Armies', 'TWELVE', 144, 'FANTASY' UNION ALL
        SELECT 'Avatar: The Way of Water', 'TWELVE', 192, 'SF' UNION ALL
        SELECT 'Dune', 'TWELVE', 155, 'SF' UNION ALL
        SELECT 'Arrival', 'TWELVE', 116, 'SF' UNION ALL
        SELECT 'Ex Machina', 'FIFTEEN', 108, 'SF' UNION ALL
        SELECT 'The Martian', 'TWELVE', 144, 'SF' UNION ALL
        SELECT 'Edge of Tomorrow', 'TWELVE', 113, 'SF' UNION ALL
        SELECT 'District 9', 'EIGHTEEN', 112, 'SF' UNION ALL
        SELECT 'Looper', 'EIGHTEEN', 119, 'SF' UNION ALL
        SELECT 'Source Code', 'TWELVE', 93, 'SF' UNION ALL
        SELECT 'Moon', 'FIFTEEN', 97, 'SF' UNION ALL
        SELECT 'Children of Men', 'EIGHTEEN', 109, 'SF' UNION ALL
        SELECT 'A.I. Artificial Intelligence', 'TWELVE', 146, 'SF' UNION ALL
        SELECT 'Minority Report', 'FIFTEEN', 145, 'SF' UNION ALL
        SELECT 'The Fifth Element', 'TWELVE', 126, 'SF' UNION ALL
        SELECT 'Total Recall', 'EIGHTEEN', 113, 'SF' UNION ALL
        SELECT 'Starship Troopers', 'EIGHTEEN', 129, 'SF' UNION ALL
        SELECT 'Contact', 'ALL', 150, 'SF' UNION ALL
        SELECT 'Independence Day', 'TWELVE', 145, 'SF' UNION ALL
        SELECT '12 Monkeys', 'EIGHTEEN', 129, 'SF' UNION ALL
        SELECT 'Jurassic Park', 'TWELVE', 127, 'SF' UNION ALL
        SELECT 'The Terminator', 'EIGHTEEN', 107, 'SF' UNION ALL
        SELECT 'Terminator 2: Judgment Day', 'FIFTEEN', 137, 'SF' UNION ALL
        SELECT 'RoboCop', 'EIGHTEEN', 102, 'SF' UNION ALL
        SELECT 'The Thing', 'EIGHTEEN', 109, 'SF' UNION ALL
        SELECT 'Alien', 'EIGHTEEN', 117, 'SF' UNION ALL
        SELECT 'Aliens', 'EIGHTEEN', 137, 'SF' UNION ALL
        SELECT 'Close Encounters of the Third Kind', 'ALL', 138, 'SF' UNION ALL
        SELECT 'Pan''s Labyrinth', 'FIFTEEN', 118, 'FANTASY' UNION ALL
        SELECT 'The NeverEnding Story', 'ALL', 102, 'FANTASY' UNION ALL
        SELECT 'Conan the Barbarian', 'EIGHTEEN', 129, 'FANTASY' UNION ALL
        SELECT 'Willow', 'ALL', 126, 'FANTASY' UNION ALL
        SELECT 'Ladyhawke', 'TWELVE', 121, 'FANTASY' UNION ALL
        SELECT 'The Princess Bride', 'ALL', 98, 'FANTASY' UNION ALL
        SELECT 'Stardust', 'TWELVE', 127, 'FANTASY' UNION ALL
        SELECT 'The Golden Compass', 'TWELVE', 113, 'FANTASY' UNION ALL
        SELECT 'Bridge to Terabithia', 'ALL', 96, 'FANTASY' UNION ALL
        SELECT 'The Chronicles of Narnia: The Lion, the Witch and the Wardrobe', 'ALL', 143, 'FANTASY' UNION ALL
        SELECT 'The Chronicles of Narnia: Prince Caspian', 'ALL', 150, 'FANTASY' UNION ALL
        SELECT 'The Chronicles of Narnia: The Voyage of the Dawn Treader', 'ALL', 113, 'FANTASY' UNION ALL
        SELECT 'Eragon', 'TWELVE', 104, 'FANTASY' UNION ALL
        SELECT 'The Last Unicorn', 'ALL', 92, 'FANTASY' UNION ALL
        SELECT 'Dragonheart', 'TWELVE', 103, 'FANTASY' UNION ALL
        SELECT 'The Dark Crystal', 'ALL', 93, 'FANTASY' UNION ALL
        SELECT 'Legend', 'ALL', 94, 'FANTASY' UNION ALL
        SELECT 'Time Bandits', 'TWELVE', 116, 'FANTASY' UNION ALL
        SELECT 'The Pagemaster', 'ALL', 80, 'FANTASY' UNION ALL
        SELECT 'The Spiderwick Chronicles', 'ALL', 97, 'FANTASY' UNION ALL
        SELECT 'Percy Jackson & the Olympians: The Lightning Thief', 'ALL', 118, 'FANTASY' UNION ALL
        SELECT 'Inkheart', 'ALL', 106, 'FANTASY' UNION ALL
        SELECT 'The Last Mimzy', 'ALL', 90, 'FANTASY' UNION ALL
        SELECT 'The Seeker: The Dark Is Rising', 'ALL', 99, 'FANTASY' UNION ALL
        SELECT 'The Water Horse', 'ALL', 112, 'FANTASY' UNION ALL
        SELECT 'MirrorMask', 'TWELVE', 101, 'FANTASY' UNION ALL
        SELECT 'Penelope', 'ALL', 104, 'FANTASY' UNION ALL

        -- 액션 (80개)
        SELECT 'The Dark Knight', 'FIFTEEN', 152, 'ACTION' UNION ALL
        SELECT 'Mad Max: Fury Road', 'FIFTEEN', 120, 'ACTION' UNION ALL
        SELECT 'Die Hard', 'FIFTEEN', 132, 'ACTION' UNION ALL
        SELECT 'John Wick', 'EIGHTEEN', 101, 'ACTION' UNION ALL
        SELECT 'Mission: Impossible', 'TWELVE', 110, 'ACTION' UNION ALL
        SELECT 'The Avengers', 'TWELVE', 143, 'ACTION' UNION ALL
        SELECT 'Iron Man', 'TWELVE', 126, 'ACTION' UNION ALL
        SELECT 'Black Panther', 'TWELVE', 134, 'ACTION' UNION ALL
        SELECT 'Wonder Woman', 'TWELVE', 141, 'ACTION' UNION ALL
        SELECT 'Spider-Man: Into the Spider-Verse', 'ALL', 117, 'ACTION' UNION ALL
        SELECT 'The Dark Knight Rises', 'FIFTEEN', 165, 'ACTION' UNION ALL
        SELECT 'Batman Begins', 'TWELVE', 140, 'ACTION' UNION ALL
        SELECT 'Captain America: The Winter Soldier', 'TWELVE', 136, 'ACTION' UNION ALL
        SELECT 'Thor: Ragnarok', 'TWELVE', 130, 'ACTION' UNION ALL
        SELECT 'Guardians of the Galaxy', 'TWELVE', 122, 'ACTION' UNION ALL
        SELECT 'Black Widow', 'TWELVE', 134, 'ACTION' UNION ALL
        SELECT 'Doctor Strange', 'TWELVE', 115, 'ACTION' UNION ALL
        SELECT 'Ant-Man', 'TWELVE', 117, 'ACTION' UNION ALL
        SELECT 'Captain Marvel', 'TWELVE', 124, 'ACTION' UNION ALL
        SELECT 'Spider-Man: Homecoming', 'TWELVE', 133, 'ACTION' UNION ALL
        SELECT 'Spider-Man: Far From Home', 'TWELVE', 129, 'ACTION' UNION ALL
        SELECT 'Spider-Man: No Way Home', 'TWELVE', 148, 'ACTION' UNION ALL
        SELECT 'Avengers: Age of Ultron', 'TWELVE', 141, 'ACTION' UNION ALL
        SELECT 'Avengers: Infinity War', 'TWELVE', 149, 'ACTION' UNION ALL
        SELECT 'Avengers: Endgame', 'TWELVE', 181, 'ACTION' UNION ALL
        SELECT 'John Wick: Chapter 2', 'EIGHTEEN', 122, 'ACTION' UNION ALL
        SELECT 'John Wick: Chapter 3 - Parabellum', 'EIGHTEEN', 131, 'ACTION' UNION ALL
        SELECT 'Mission: Impossible - Fallout', 'TWELVE', 147, 'ACTION' UNION ALL
        SELECT 'Mission: Impossible - Rogue Nation', 'TWELVE', 131, 'ACTION' UNION ALL
        SELECT 'Mission: Impossible - Ghost Protocol', 'TWELVE', 133, 'ACTION' UNION ALL
        SELECT 'Fast Five', 'FIFTEEN', 130, 'ACTION' UNION ALL
        SELECT 'Fast & Furious 6', 'FIFTEEN', 130, 'ACTION' UNION ALL
        SELECT 'Furious 7', 'FIFTEEN', 137, 'ACTION' UNION ALL
        SELECT 'The Fate of the Furious', 'FIFTEEN', 136, 'ACTION' UNION ALL
        SELECT 'F9', 'FIFTEEN', 143, 'ACTION' UNION ALL
        SELECT 'Mad Max 2: The Road Warrior', 'EIGHTEEN', 95, 'ACTION' UNION ALL
        SELECT 'Mad Max Beyond Thunderdome', 'FIFTEEN', 107, 'ACTION' UNION ALL
        SELECT 'Die Hard 2', 'EIGHTEEN', 124, 'ACTION' UNION ALL
        SELECT 'Die Hard with a Vengeance', 'EIGHTEEN', 128, 'ACTION' UNION ALL
        SELECT 'Live Free or Die Hard', 'FIFTEEN', 128, 'ACTION' UNION ALL
        SELECT 'The Bourne Identity', 'FIFTEEN', 119, 'ACTION' UNION ALL
        SELECT 'The Bourne Supremacy', 'FIFTEEN', 108, 'ACTION' UNION ALL
        SELECT 'The Bourne Ultimatum', 'FIFTEEN', 115, 'ACTION' UNION ALL
        SELECT 'Jason Bourne', 'FIFTEEN', 123, 'ACTION' UNION ALL
        SELECT 'Casino Royale', 'FIFTEEN', 144, 'ACTION' UNION ALL
        SELECT 'Quantum of Solace', 'FIFTEEN', 106, 'ACTION' UNION ALL
        SELECT 'Skyfall', 'FIFTEEN', 143, 'ACTION' UNION ALL
        SELECT 'Spectre', 'FIFTEEN', 148, 'ACTION' UNION ALL
        SELECT 'No Time to Die', 'FIFTEEN', 163, 'ACTION' UNION ALL
        SELECT 'Edge of Tomorrow', 'TWELVE', 113, 'ACTION' UNION ALL
        SELECT 'Top Gun', 'TWELVE', 110, 'ACTION' UNION ALL
        SELECT 'Top Gun: Maverick', 'TWELVE', 130, 'ACTION' UNION ALL
        SELECT 'Gladiator', 'EIGHTEEN', 155, 'ACTION' UNION ALL
        SELECT '300', 'EIGHTEEN', 117, 'ACTION' UNION ALL
        SELECT 'Kill Bill: Vol. 1', 'EIGHTEEN', 111, 'ACTION' UNION ALL
        SELECT 'Kill Bill: Vol. 2', 'EIGHTEEN', 137, 'ACTION' UNION ALL
        SELECT 'V for Vendetta', 'FIFTEEN', 132, 'ACTION' UNION ALL
        SELECT 'Kingsman: The Secret Service', 'EIGHTEEN', 129, 'ACTION' UNION ALL
        SELECT 'Kingsman: The Golden Circle', 'EIGHTEEN', 141, 'ACTION' UNION ALL
        SELECT 'The Man from U.N.C.L.E.', 'TWELVE', 116, 'ACTION' UNION ALL
        SELECT 'Atomic Blonde', 'EIGHTEEN', 115, 'ACTION' UNION ALL
        SELECT 'Baby Driver', 'FIFTEEN', 113, 'ACTION' UNION ALL
        SELECT 'The Equalizer', 'EIGHTEEN', 132, 'ACTION' UNION ALL
        SELECT 'The Equalizer 2', 'EIGHTEEN', 121, 'ACTION' UNION ALL
        SELECT 'Jack Reacher', 'FIFTEEN', 130, 'ACTION' UNION ALL
        SELECT 'Jack Reacher: Never Go Back', 'FIFTEEN', 118, 'ACTION' UNION ALL
        SELECT 'The Raid: Redemption', 'EIGHTEEN', 101, 'ACTION' UNION ALL
        SELECT 'The Raid 2', 'EIGHTEEN', 150, 'ACTION' UNION ALL
        SELECT 'Dredd', 'EIGHTEEN', 95, 'ACTION' UNION ALL
        SELECT 'Lucy', 'FIFTEEN', 89, 'ACTION' UNION ALL
        SELECT 'Salt', 'FIFTEEN', 100, 'ACTION' UNION ALL
        SELECT 'Wanted', 'EIGHTEEN', 110, 'ACTION' UNION ALL
        SELECT 'Taken', 'FIFTEEN', 93, 'ACTION' UNION ALL
        SELECT 'The A-Team', 'TWELVE', 117, 'ACTION' UNION ALL
        SELECT 'The Expendables', 'EIGHTEEN', 103, 'ACTION' UNION ALL
        SELECT 'Rampage', 'TWELVE', 107, 'ACTION' UNION ALL
        SELECT 'San Andreas', 'TWELVE', 114, 'ACTION' UNION ALL

        -- 범죄/스릴러 (60개)
        SELECT 'Pulp Fiction', 'EIGHTEEN', 154, 'CRIME' UNION ALL
        SELECT 'The Silence of the Lambs', 'EIGHTEEN', 118, 'THRILLER' UNION ALL
        SELECT 'Se7en', 'EIGHTEEN', 127, 'THRILLER' UNION ALL
        SELECT 'Memento', 'FIFTEEN', 113, 'THRILLER' UNION ALL
        SELECT 'Fight Club', 'EIGHTEEN', 139, 'THRILLER' UNION ALL
        SELECT 'The Usual Suspects', 'EIGHTEEN', 106, 'CRIME' UNION ALL
        SELECT 'L.A. Confidential', 'EIGHTEEN', 138, 'CRIME' UNION ALL
        SELECT 'Heat', 'EIGHTEEN', 170, 'CRIME' UNION ALL
        SELECT 'No Country for Old Men', 'EIGHTEEN', 122, 'THRILLER' UNION ALL
        SELECT 'Gone Girl', 'EIGHTEEN', 149, 'THRILLER' UNION ALL
        SELECT 'The Godfather Part II', 'EIGHTEEN', 202, 'CRIME' UNION ALL
        SELECT 'The Godfather Part III', 'EIGHTEEN', 162, 'CRIME' UNION ALL
        SELECT 'Casino', 'EIGHTEEN', 178, 'CRIME' UNION ALL
        SELECT 'Reservoir Dogs', 'EIGHTEEN', 99, 'CRIME' UNION ALL
        SELECT 'The Departed', 'EIGHTEEN', 151, 'CRIME' UNION ALL
        SELECT 'Fargo', 'EIGHTEEN', 98, 'CRIME' UNION ALL
        SELECT 'American Psycho', 'EIGHTEEN', 101, 'THRILLER' UNION ALL
        SELECT 'Zodiac', 'EIGHTEEN', 157, 'THRILLER' UNION ALL
        SELECT 'Mystic River', 'EIGHTEEN', 137, 'CRIME' UNION ALL
        SELECT 'The Girl with the Dragon Tattoo', 'EIGHTEEN', 158, 'THRILLER' UNION ALL
        SELECT 'Shutter Island', 'FIFTEEN', 138, 'THRILLER' UNION ALL
        SELECT 'Inception', 'TWELVE', 148, 'THRILLER' UNION ALL
        SELECT 'Prisoners', 'EIGHTEEN', 153, 'THRILLER' UNION ALL
        SELECT 'Nightcrawler', 'EIGHTEEN', 117, 'THRILLER' UNION ALL
        SELECT 'Drive', 'EIGHTEEN', 100, 'CRIME' UNION ALL
        SELECT 'The Town', 'EIGHTEEN', 125, 'CRIME' UNION ALL
        SELECT 'Inside Man', 'FIFTEEN', 129, 'CRIME' UNION ALL
        SELECT 'Training Day', 'EIGHTEEN', 122, 'CRIME' UNION ALL
        SELECT 'A History of Violence', 'EIGHTEEN', 96, 'CRIME' UNION ALL
        SELECT 'Eastern Promises', 'EIGHTEEN', 100, 'CRIME' UNION ALL
        SELECT 'The Prestige', 'TWELVE', 130, 'THRILLER' UNION ALL
        SELECT 'Black Swan', 'EIGHTEEN', 108, 'THRILLER' UNION ALL
        SELECT 'Donnie Darko', 'FIFTEEN', 113, 'THRILLER' UNION ALL
        SELECT 'Mulholland Drive', 'EIGHTEEN', 147, 'THRILLER' UNION ALL
        SELECT 'Oldboy', 'EIGHTEEN', 120, 'THRILLER' UNION ALL
        SELECT 'The Sixth Sense', 'TWELVE', 107, 'THRILLER' UNION ALL
        SELECT 'Primal Fear', 'EIGHTEEN', 129, 'THRILLER' UNION ALL
        SELECT 'Basic Instinct', 'EIGHTEEN', 127, 'THRILLER' UNION ALL
        SELECT 'Cape Fear', 'EIGHTEEN', 128, 'THRILLER' UNION ALL
        SELECT 'The Talented Mr. Ripley', 'FIFTEEN', 139, 'THRILLER' UNION ALL
        SELECT 'A Simple Plan', 'FIFTEEN', 121, 'THRILLER' UNION ALL
        SELECT 'The Game', 'FIFTEEN', 129, 'THRILLER' UNION ALL
        SELECT 'Collateral', 'EIGHTEEN', 120, 'THRILLER' UNION ALL
        SELECT 'The Machinist', 'FIFTEEN', 101, 'THRILLER' UNION ALL
        SELECT 'Identity', 'EIGHTEEN', 90, 'THRILLER' UNION ALL
        SELECT 'Phone Booth', 'FIFTEEN', 81, 'THRILLER' UNION ALL
        SELECT 'Panic Room', 'FIFTEEN', 112, 'THRILLER' UNION ALL
        SELECT 'Red Dragon', 'EIGHTEEN', 124, 'THRILLER' UNION ALL
        SELECT 'Hannibal', 'EIGHTEEN', 131, 'THRILLER' UNION ALL
        SELECT 'The Bone Collector', 'EIGHTEEN', 118, 'THRILLER' UNION ALL
        SELECT 'Kiss the Girls', 'EIGHTEEN', 117, 'THRILLER' UNION ALL
        SELECT 'Along Came a Spider', 'FIFTEEN', 104, 'THRILLER' UNION ALL
        SELECT 'The Negotiator', 'FIFTEEN', 140, 'THRILLER' UNION ALL
        SELECT 'Insomnia', 'FIFTEEN', 118, 'THRILLER' UNION ALL
        SELECT 'Arlington Road', 'FIFTEEN', 117, 'THRILLER' UNION ALL
        SELECT 'The Devil''s Advocate', 'EIGHTEEN', 144, 'THRILLER' UNION ALL
        SELECT 'The Usual Suspects', 'EIGHTEEN', 106, 'CRIME' UNION ALL
        SELECT 'Ronin', 'FIFTEEN', 122, 'THRILLER' UNION ALL
        SELECT 'The Jackal', 'EIGHTEEN', 124, 'THRILLER' UNION ALL
        SELECT 'Copycat', 'EIGHTEEN', 123, 'THRILLER' UNION ALL

        -- 코미디 (60개)
        SELECT 'The Grand Budapest Hotel', 'FIFTEEN', 99, 'COMEDY' UNION ALL
        SELECT 'Groundhog Day', 'ALL', 101, 'COMEDY' UNION ALL
        SELECT 'The Hangover', 'EIGHTEEN', 100, 'COMEDY' UNION ALL
        SELECT 'Shaun of the Dead', 'FIFTEEN', 99, 'COMEDY' UNION ALL
        SELECT 'Bridesmaids', 'FIFTEEN', 125, 'COMEDY' UNION ALL
        SELECT 'Superbad', 'EIGHTEEN', 113, 'COMEDY' UNION ALL
        SELECT 'The Big Lebowski', 'EIGHTEEN', 117, 'COMEDY' UNION ALL
        SELECT 'Anchorman', 'FIFTEEN', 94, 'COMEDY' UNION ALL
        SELECT 'Mean Girls', 'TWELVE', 97, 'COMEDY' UNION ALL
        SELECT 'Deadpool', 'EIGHTEEN', 108, 'COMEDY' UNION ALL
        SELECT 'The Hangover Part II', 'EIGHTEEN', 102, 'COMEDY' UNION ALL
        SELECT 'The Hangover Part III', 'EIGHTEEN', 100, 'COMEDY' UNION ALL
        SELECT 'Anchorman 2: The Legend Continues', 'FIFTEEN', 119, 'COMEDY' UNION ALL
        SELECT 'Zoolander', 'TWELVE', 89, 'COMEDY' UNION ALL
        SELECT 'Tropic Thunder', 'EIGHTEEN', 107, 'COMEDY' UNION ALL
        SELECT 'Step Brothers', 'EIGHTEEN', 98, 'COMEDY' UNION ALL
        SELECT 'Old School', 'EIGHTEEN', 91, 'COMEDY' UNION ALL
        SELECT 'Wedding Crashers', 'FIFTEEN', 119, 'COMEDY' UNION ALL
        SELECT 'Knocked Up', 'EIGHTEEN', 129, 'COMEDY' UNION ALL
        SELECT '40-Year-Old Virgin', 'EIGHTEEN', 116, 'COMEDY' UNION ALL
        SELECT 'Pineapple Express', 'EIGHTEEN', 111, 'COMEDY' UNION ALL
        SELECT 'This Is the End', 'EIGHTEEN', 107, 'COMEDY' UNION ALL
        SELECT '21 Jump Street', 'FIFTEEN', 109, 'COMEDY' UNION ALL
        SELECT '22 Jump Street', 'FIFTEEN', 112, 'COMEDY' UNION ALL
        SELECT 'Hot Fuzz', 'FIFTEEN', 121, 'COMEDY' UNION ALL
        SELECT 'The World''s End', 'FIFTEEN', 109, 'COMEDY' UNION ALL
        SELECT 'Scott Pilgrim vs. the World', 'TWELVE', 112, 'COMEDY' UNION ALL
        SELECT 'Zombieland', 'EIGHTEEN', 88, 'COMEDY' UNION ALL
        SELECT 'Zombieland: Double Tap', 'EIGHTEEN', 99, 'COMEDY' UNION ALL
        SELECT 'Game Night', 'FIFTEEN', 100, 'COMEDY' UNION ALL
        SELECT 'The Nice Guys', 'EIGHTEEN', 116, 'COMEDY' UNION ALL
        SELECT 'The Other Guys', 'FIFTEEN', 107, 'COMEDY' UNION ALL
        SELECT 'Ted', 'EIGHTEEN', 106, 'COMEDY' UNION ALL
        SELECT 'Ted 2', 'EIGHTEEN', 115, 'COMEDY' UNION ALL
        SELECT 'We''re the Millers', 'EIGHTEEN', 110, 'COMEDY' UNION ALL
        SELECT 'The Heat', 'FIFTEEN', 117, 'COMEDY' UNION ALL
        SELECT 'Central Intelligence', 'TWELVE', 107, 'COMEDY' UNION ALL
        SELECT 'Spy', 'FIFTEEN', 120, 'COMEDY' UNION ALL
        SELECT 'The Interview', 'EIGHTEEN', 112, 'COMEDY' UNION ALL
        SELECT 'Horrible Bosses', 'EIGHTEEN', 98, 'COMEDY' UNION ALL
        SELECT 'Horrible Bosses 2', 'EIGHTEEN', 108, 'COMEDY' UNION ALL
        SELECT 'The Dictator', 'EIGHTEEN', 83, 'COMEDY' UNION ALL
        SELECT 'Borat', 'EIGHTEEN', 84, 'COMEDY' UNION ALL
        SELECT 'Bruno', 'EIGHTEEN', 81, 'COMEDY' UNION ALL
        SELECT 'The Campaign', 'FIFTEEN', 85, 'COMEDY' UNION ALL
        SELECT 'Due Date', 'FIFTEEN', 95, 'COMEDY' UNION ALL
        SELECT 'Get Him to the Greek', 'EIGHTEEN', 109, 'COMEDY' UNION ALL
        SELECT 'Role Models', 'EIGHTEEN', 99, 'COMEDY' UNION ALL
        SELECT 'I Love You, Man', 'FIFTEEN', 105, 'COMEDY' UNION ALL
        SELECT 'Forgetting Sarah Marshall', 'EIGHTEEN', 111, 'COMEDY' UNION ALL
        SELECT 'The Sitter', 'EIGHTEEN', 81, 'COMEDY' UNION ALL
        SELECT 'Your Highness', 'EIGHTEEN', 102, 'COMEDY' UNION ALL
        SELECT 'The Watch', 'EIGHTEEN', 102, 'COMEDY' UNION ALL
        SELECT 'This Is 40', 'EIGHTEEN', 134, 'COMEDY' UNION ALL
        SELECT 'Identity Thief', 'FIFTEEN', 111, 'COMEDY' UNION ALL
        SELECT 'The Change-Up', 'EIGHTEEN', 112, 'COMEDY' UNION ALL
        SELECT 'Hall Pass', 'EIGHTEEN', 105, 'COMEDY' UNION ALL
        SELECT 'The Dilemma', 'TWELVE', 111, 'COMEDY' UNION ALL
        SELECT 'Dinner for Schmucks', 'TWELVE', 114, 'COMEDY' UNION ALL
        SELECT 'The Other Woman', 'FIFTEEN', 109, 'COMEDY' UNION ALL

        -- 애니메이션 (40개)
        SELECT 'Toy Story', 'ALL', 81, 'ANIMATION' UNION ALL
        SELECT 'Spirited Away', 'ALL', 125, 'ANIMATION' UNION ALL
        SELECT 'The Lion King', 'ALL', 88, 'ANIMATION' UNION ALL
        SELECT 'WALL-E', 'ALL', 98, 'ANIMATION' UNION ALL
        SELECT 'Up', 'ALL', 96, 'ANIMATION' UNION ALL
        SELECT 'Inside Out', 'ALL', 95, 'ANIMATION' UNION ALL
        SELECT 'Your Name', 'ALL', 107, 'ANIMATION' UNION ALL
        SELECT 'Frozen', 'ALL', 102, 'ANIMATION' UNION ALL
        SELECT 'Coco', 'ALL', 105, 'ANIMATION' UNION ALL
        SELECT 'Zootopia', 'ALL', 108, 'ANIMATION' UNION ALL
        SELECT 'Toy Story 2', 'ALL', 92, 'ANIMATION' UNION ALL
        SELECT 'Toy Story 3', 'ALL', 103, 'ANIMATION' UNION ALL
        SELECT 'Toy Story 4', 'ALL', 100, 'ANIMATION' UNION ALL
        SELECT 'Finding Nemo', 'ALL', 100, 'ANIMATION' UNION ALL
        SELECT 'Finding Dory', 'ALL', 97, 'ANIMATION' UNION ALL
        SELECT 'The Incredibles', 'ALL', 115, 'ANIMATION' UNION ALL
        SELECT 'Incredibles 2', 'ALL', 118, 'ANIMATION' UNION ALL
        SELECT 'Monsters, Inc.', 'ALL', 92, 'ANIMATION' UNION ALL
        SELECT 'Monsters University', 'ALL', 104, 'ANIMATION' UNION ALL
        SELECT 'Cars', 'ALL', 117, 'ANIMATION' UNION ALL
        SELECT 'Cars 2', 'ALL', 106, 'ANIMATION' UNION ALL
        SELECT 'Cars 3', 'ALL', 102, 'ANIMATION' UNION ALL
        SELECT 'Ratatouille', 'ALL', 111, 'ANIMATION' UNION ALL
        SELECT 'Brave', 'ALL', 93, 'ANIMATION' UNION ALL
        SELECT 'Onward', 'ALL', 102, 'ANIMATION' UNION ALL
        SELECT 'Soul', 'ALL', 100, 'ANIMATION' UNION ALL
        SELECT 'Luca', 'ALL', 95, 'ANIMATION' UNION ALL
        SELECT 'Turning Red', 'ALL', 100, 'ANIMATION' UNION ALL
        SELECT 'Frozen II', 'ALL', 103, 'ANIMATION' UNION ALL
        SELECT 'Big Hero 6', 'ALL', 102, 'ANIMATION' UNION ALL
        SELECT 'Wreck-It Ralph', 'ALL', 101, 'ANIMATION' UNION ALL
        SELECT 'Ralph Breaks the Internet', 'ALL', 112, 'ANIMATION' UNION ALL
        SELECT 'Moana', 'ALL', 107, 'ANIMATION' UNION ALL
        SELECT 'Tangled', 'ALL', 100, 'ANIMATION' UNION ALL
        SELECT 'Princess and the Frog', 'ALL', 97, 'ANIMATION' UNION ALL
        SELECT 'How to Train Your Dragon', 'ALL', 98, 'ANIMATION' UNION ALL
        SELECT 'How to Train Your Dragon 2', 'ALL', 102, 'ANIMATION' UNION ALL
        SELECT 'How to Train Your Dragon: The Hidden World', 'ALL', 104, 'ANIMATION' UNION ALL
        SELECT 'Kung Fu Panda', 'ALL', 92, 'ANIMATION' UNION ALL
        SELECT 'Shrek', 'ALL', 90, 'ANIMATION' UNION ALL

        -- 한국 영화 (40개)
        SELECT '올드보이', 'EIGHTEEN', 120, 'THRILLER' UNION ALL
        SELECT '기생충', 'FIFTEEN', 132, 'DRAMA' UNION ALL
        SELECT '아가씨', 'EIGHTEEN', 144, 'THRILLER' UNION ALL
        SELECT '부산행', 'FIFTEEN', 118, 'THRILLER' UNION ALL
        SELECT '괴물', 'TWELVE', 119, 'ACTION' UNION ALL
        SELECT '추격자', 'EIGHTEEN', 123, 'THRILLER' UNION ALL
        SELECT '신과함께: 죄와 벌', 'TWELVE', 139, 'FANTASY' UNION ALL
        SELECT '극한직업', 'FIFTEEN', 111, 'COMEDY' UNION ALL
        SELECT '베테랑', 'FIFTEEN', 124, 'ACTION' UNION ALL
        SELECT '명량', 'FIFTEEN', 128, 'ACTION' UNION ALL
        SELECT '신과함께: 인과 연', 'TWELVE', 141, 'FANTASY' UNION ALL
        SELECT '국제시장', 'TWELVE', 126, 'DRAMA' UNION ALL
        SELECT '7번방의 선물', 'TWELVE', 127, 'DRAMA' UNION ALL
        SELECT '암살', 'FIFTEEN', 139, 'ACTION' UNION ALL
        SELECT '광해, 왕이 된 남자', 'FIFTEEN', 131, 'DRAMA' UNION ALL
        SELECT '택시운전사', 'FIFTEEN', 137, 'DRAMA' UNION ALL
        SELECT '변호인', 'FIFTEEN', 127, 'DRAMA' UNION ALL
        SELECT '도둑들', 'FIFTEEN', 136, 'ACTION' UNION ALL
        SELECT '관상', 'FIFTEEN', 139, 'DRAMA' UNION ALL
        SELECT '해운대', 'TWELVE', 120, 'DRAMA' UNION ALL
        SELECT '써니', 'FIFTEEN', 124, 'DRAMA' UNION ALL
        SELECT '과속스캔들', 'ALL', 108, 'COMEDY' UNION ALL
        SELECT '타짜', 'EIGHTEEN', 139, 'CRIME' UNION ALL
        SELECT '내부자들', 'EIGHTEEN', 130, 'CRIME' UNION ALL
        SELECT '곡성', 'FIFTEEN', 156, 'THRILLER' UNION ALL
        SELECT '아저씨', 'EIGHTEEN', 119, 'ACTION' UNION ALL
        SELECT '범죄와의 전쟁: 나쁜놈들 전성시대', 'EIGHTEEN', 133, 'CRIME' UNION ALL
        SELECT '전우치', 'TWELVE', 136, 'FANTASY' UNION ALL
        SELECT '미녀는 괴로워', 'ALL', 120, 'COMEDY' UNION ALL
        SELECT '마더', 'EIGHTEEN', 129, 'THRILLER' UNION ALL
        SELECT '설국열차', 'FIFTEEN', 126, 'SF' UNION ALL
        SELECT '박쥐', 'EIGHTEEN', 133, 'THRILLER' UNION ALL
        SELECT '좋은 놈, 나쁜 놈, 이상한 놈', 'FIFTEEN', 130, 'ACTION' UNION ALL
        SELECT '미션임파서블', 'FIFTEEN', 125, 'ACTION' UNION ALL
        SELECT '해빙', 'FIFTEEN', 118, 'THRILLER' UNION ALL
        SELECT '터널', 'TWELVE', 126, 'DRAMA' UNION ALL
        SELECT '인천상륙작전', 'TWELVE', 140, 'ACTION' UNION ALL
        SELECT '군함도', 'FIFTEEN', 132, 'ACTION' UNION ALL
        SELECT '1987', 'FIFTEEN', 129, 'DRAMA' UNION ALL
        SELECT '신세계', 'EIGHTEEN', 134, 'CRIME' UNION ALL

        -- 독립 영화 (40개)
        SELECT 'Little Miss Sunshine', 'FIFTEEN', 101, 'INDIE' UNION ALL
        SELECT 'Whiplash', 'FIFTEEN', 107, 'INDIE' UNION ALL
        SELECT 'Lost in Translation', 'TWELVE', 102, 'INDIE' UNION ALL
        SELECT 'Eternal Sunshine of the Spotless Mind', 'FIFTEEN', 108, 'INDIE' UNION ALL
        SELECT 'Moonlight', 'FIFTEEN', 111, 'INDIE' UNION ALL
        SELECT 'Lady Bird', 'FIFTEEN', 94, 'INDIE' UNION ALL
        SELECT 'The Florida Project', 'EIGHTEEN', 111, 'INDIE' UNION ALL
        SELECT 'Manchester by the Sea', 'FIFTEEN', 137, 'INDIE' UNION ALL
        SELECT 'Room', 'FIFTEEN', 118, 'INDIE' UNION ALL
        SELECT 'Boyhood', 'FIFTEEN', 165, 'INDIE' UNION ALL
        SELECT 'Call Me by Your Name', 'EIGHTEEN', 132, 'INDIE' UNION ALL
        SELECT 'The Lobster', 'EIGHTEEN', 119, 'INDIE' UNION ALL
        SELECT 'Ex Machina', 'FIFTEEN', 108, 'INDIE' UNION ALL
        SELECT 'A Ghost Story', 'TWELVE', 92, 'INDIE' UNION ALL
        SELECT 'The Witch', 'EIGHTEEN', 92, 'INDIE' UNION ALL
        SELECT 'Good Time', 'EIGHTEEN', 101, 'INDIE' UNION ALL
        SELECT 'Swiss Army Man', 'FIFTEEN', 97, 'INDIE' UNION ALL
        SELECT 'The Lighthouse', 'EIGHTEEN', 109, 'INDIE' UNION ALL
        SELECT 'First Reformed', 'FIFTEEN', 113, 'INDIE' UNION ALL
        SELECT 'Eighth Grade', 'FIFTEEN', 93, 'INDIE' UNION ALL
        SELECT 'Mid90s', 'EIGHTEEN', 85, 'INDIE' UNION ALL
        SELECT 'The Farewell', 'ALL', 100, 'INDIE' UNION ALL
        SELECT 'Hereditary', 'EIGHTEEN', 127, 'INDIE' UNION ALL
        SELECT 'Midsommar', 'EIGHTEEN', 147, 'INDIE' UNION ALL
        SELECT 'The Death of Stalin', 'FIFTEEN', 107, 'INDIE' UNION ALL
        SELECT 'Sorry to Bother You', 'EIGHTEEN', 111, 'INDIE' UNION ALL
        SELECT 'The Killing of a Sacred Deer', 'EIGHTEEN', 121, 'INDIE' UNION ALL
        SELECT 'Green Room', 'EIGHTEEN', 95, 'INDIE' UNION ALL
        SELECT 'Blue Ruin', 'EIGHTEEN', 90, 'INDIE' UNION ALL
        SELECT 'Under the Skin', 'EIGHTEEN', 108, 'INDIE' UNION ALL
        SELECT 'Enemy', 'EIGHTEEN', 91, 'INDIE' UNION ALL
        SELECT 'Only Lovers Left Alive', 'FIFTEEN', 123, 'INDIE' UNION ALL
        SELECT 'The Babadook', 'FIFTEEN', 93, 'INDIE' UNION ALL
        SELECT 'It Follows', 'EIGHTEEN', 100, 'INDIE' UNION ALL
        SELECT 'A Most Violent Year', 'FIFTEEN', 125, 'INDIE' UNION ALL
        SELECT 'Locke', 'FIFTEEN', 85, 'INDIE' UNION ALL
        SELECT 'Frank', 'FIFTEEN', 95, 'INDIE' UNION ALL
        SELECT 'The Guest', 'EIGHTEEN', 100, 'INDIE' UNION ALL
        SELECT 'What We Do in the Shadows', 'FIFTEEN', 86, 'INDIE' UNION ALL
        SELECT 'The Spectacular Now', 'FIFTEEN', 95, 'INDIE'
    )
    SELECT 
        title,
        rating,
        DATE_ADD('2024-01-01', INTERVAL FLOOR(RAND() * 365) DAY) as release_date,
        CONCAT('https://example.com/movies/', LOWER(REPLACE(title, ' ', '-')), '.jpg') as thumbnail_url,
        running_time,
        genre,
        NOW() as created_at,
        'system' as created_by,
        NOW() as updated_at,
        'system' as updated_by
    FROM movie_list;
END //
DELIMITER ;

-- 상영 일정 생성 프로시저
DELIMITER //
CREATE PROCEDURE insert_screenings()
BEGIN
    DECLARE start_date DATE;
    DECLARE end_date DATE;
    
    SET start_date = CURDATE();
    SET end_date = DATE_ADD(start_date, INTERVAL 1 MONTH);
    
    -- 각 영화, 극장, 날짜별로 상영 시간 생성
    INSERT INTO screening (movie_id, theater_id, start_time, created_at, created_by, updated_at, updated_by)
    WITH RECURSIVE dates AS (
        SELECT start_date as date
        UNION ALL
        SELECT DATE_ADD(date, INTERVAL 1 DAY)
        FROM dates
        WHERE date < end_date
    ),
    time_slots AS (
        SELECT '10:00:00' as start_time UNION ALL
        SELECT '13:00:00' UNION ALL
        SELECT '16:00:00' UNION ALL
        SELECT '19:00:00' UNION ALL
        SELECT '22:00:00'
    )
    SELECT 
        m.id as movie_id,
        t.id as theater_id,
        TIMESTAMP(d.date, ts.start_time) as start_time,
        NOW() as created_at,
        'system' as created_by,
        NOW() as updated_at,
        'system' as updated_by
    FROM movie m
    CROSS JOIN theater t
    CROSS JOIN dates d
    CROSS JOIN time_slots ts
    -- 상영 시간이 겹치지 않도록 필터링
    WHERE NOT EXISTS (
        SELECT 1
        FROM screening s
        WHERE s.theater_id = t.id
        AND s.start_time BETWEEN 
            TIMESTAMP(d.date, ts.start_time) 
            AND DATE_ADD(TIMESTAMP(d.date, ts.start_time), INTERVAL m.running_time MINUTE)
    )
    -- 랜덤하게 일부 상영 시간만 선택 (모든 시간대를 다 채우지 않음)
    AND RAND() < 0.7
    ORDER BY t.id, start_time;
END //
DELIMITER ;

-- 기존 상영관 데이터 유지
INSERT INTO theater (name, created_at, created_by, updated_at, updated_by)
VALUES 
('1관', NOW(), 'system', NOW(), 'system'),
('2관', NOW(), 'system', NOW(), 'system');

-- 좌석 데이터 생성
CALL insert_seats();

-- 영화 데이터 생성
CALL insert_movies();

-- 상영 일정 생성
CALL insert_screenings();

-- 프로시저 삭제
DROP PROCEDURE IF EXISTS insert_movies;
DROP PROCEDURE IF EXISTS insert_screenings;
DROP PROCEDURE IF EXISTS insert_seats;
