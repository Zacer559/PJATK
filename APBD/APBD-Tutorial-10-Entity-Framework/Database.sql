-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-03-22 07:12:20.376

-- tables
-- Table: Enrollment
CREATE TABLE Enrollment (
    IdEnrollment int  NOT NULL,
    Semester int  NOT NULL,
    IdStudy int  NOT NULL,
    StartDate date  NOT NULL,
    CONSTRAINT Enrollment_pk PRIMARY KEY  (IdEnrollment)
);

-- Table: Student
CREATE TABLE Student (
    IndexNumber nvarchar(100)  NOT NULL,
    FirstName nvarchar(100)  NOT NULL,
    LastName nvarchar(100)  NOT NULL,
	Passwordd nvarchar(100) NOT NULL,
    BirthDate date  NOT NULL,
    IdEnrollment int  NOT NULL,
    CONSTRAINT Student_pk PRIMARY KEY  (IndexNumber)
);

-- Table: Studies
CREATE TABLE Studies (
    IdStudy int  NOT NULL,
    Name nvarchar(100)  NOT NULL,
    CONSTRAINT Studies_pk PRIMARY KEY  (IdStudy)
);
-- Table: Tokens
CREATE TABLE Tokens (
      Login nvarchar(100) NOT NULL,
	  Token nvarchar(100) NOT NULL,
);
-- Table: Salts
CREATE TABLE Salts (
    IdSalt nvarchar(100)  NOT NULL,
	Value nvarchar(100) NOT NULL
	CONSTRAINT Salts_pk PRIMARY KEY  (IdSalt)
);

-- foreign keys
-- Reference: Enrollment_Studies (table: Enrollment)
ALTER TABLE Enrollment ADD CONSTRAINT Enrollment_Studies
    FOREIGN KEY (IdStudy)
    REFERENCES Studies (IdStudy);
-- Reference: Student_Salt (table: Salts)
ALTER TABLE Salts ADD CONSTRAINT Student_Salt
    FOREIGN KEY (IdSalt)
    REFERENCES Student (IndexNumber);
-- Reference: Student_Enrollment (table: Student)
ALTER TABLE Student ADD CONSTRAINT Student_Enrollment
    FOREIGN KEY (IdEnrollment)
    REFERENCES Enrollment (IdEnrollment);
-- End of file.




-- Insert values to study
INSERT INTO Studies (IdStudy,Name)
values (1,'Art');
INSERT INTO Studies (IdStudy,Name)
values (2,'IT');
-- Insert values to Enrollment
INSERT INTO Enrollment
VALUES  (1,1,1,'10-10-2020');
-- Insert valuest to student
INSERT INTO Student (IndexNumber,FirstName,LastName,Passwordd,BirthDate,IdEnrollment)
VALUES ('s555','Franek','Kimono','6+YjjIlYqosPaFiF4Mb3h9qjaBt1NSJI1gzxosPEOik=','10-12-2020',1);
-- Insert values to Salts
INSERT INTO Salts
VALUES  ('s555','s2345');


end;

