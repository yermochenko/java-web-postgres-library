-- deleting tables
DROP TABLE IF EXISTS "genre";

-- creating tables
CREATE TABLE "genre" (
	"id"   SERIAL PRIMARY KEY,
	"name" TEXT   NOT NULL
);

-- filling table
INSERT INTO "genre"
-------------------------
("id", "name"           ) VALUES
-------------------------
(1   , 'Classic'        ),
(2   , 'Fantasy'        ),
(3   , 'Science Fiction'),
(4   , 'Detective'      );
SELECT setval('genre_id_seq', 4);
