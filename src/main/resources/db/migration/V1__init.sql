CREATE TABLE socks (
                       id SERIAL NOT NULL,
                       color varchar(20) NOT NULL,
                       cottonPart SMALLINT NOT NULL CHECK ( cottonPart >= 0 AND cottonPart <= 100),
                       quantity INTEGER NOT NULL CHECK ( quantity > 0 ),
                       PRIMARY KEY (id),
                       UNIQUE (color, cottonPart)

);

INSERT INTO socks (color, cottonPart, quantity) VALUES
                                                    ('red', 32, 12),
                                                    ('green', 12, 32),
                                                    ('yellow', 65, 19),
                                                    ('blue', 24, 7),
                                                    ('black', 70, 43),
                                                    ('white', 5, 23),
                                                    ('pink', 39, 2),
                                                    ('orange', 19, 23),
                                                    ('white', 12, 10),
                                                    ('gray', 15, 20),
                                                    ('red', 16, 20),
                                                    ('black', 25, 12);

