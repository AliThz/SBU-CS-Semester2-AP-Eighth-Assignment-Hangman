-- Database: SBU-CS-Semester2-AP-Eighth-Assignment-Hangman

-- DROP DATABASE IF EXISTS "SBU-CS-Semester2-AP-Eighth-Assignment-Hangman";

CREATE DATABASE "SBU-CS-Semester2-AP-Eighth-Assignment-Hangman"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.utf8'
    LC_CTYPE = 'English_United States.utf8'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

create table UserInfo(
	"Name" varchar(30),
	"Username" varchar(20) unique,
	"Password" varchar(10)
)

create table GameInfo(
	"GameID" uuid,
	"Username" varchar(20) unique,
	"Word" varchar(10),
	"WrongGuesses" integer,
	"Time" integer,
	"Win" boolean
)