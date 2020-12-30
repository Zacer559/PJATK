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

-- foreign keys
-- Reference: Enrollment_Studies (table: Enrollment)
ALTER TABLE Enrollment ADD CONSTRAINT Enrollment_Studies
    FOREIGN KEY (IdStudy)
    REFERENCES Studies (IdStudy);

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
INSERT INTO Student (IndexNumber,FirstName,LastName,BirthDate,IdEnrollment)
VALUES ('s555','Franek','Kimono','10-12-2020',1);
-- Create procedure
create procedure PromoteStudent @studyName nvarchar(50),
@semester int
as
begin
	Set XACT_abort on;
	Begin Tran

	Declare @idStudy int = (select IdStudy from Studies where Name=@studyName);
	Declare @sem int = (select semester from Enrollment where Semester=@semester);
	if (@idStudy is null or @sem is null)
	begin
		raiserror('Study or semester does not exists', 20, -1) with log;
	end

	Declare @nextEnrollmentId int = (select IdEnrollment from Enrollment
										where IdStudy=@idStudy and semester=@semester+1);

	IF @nextEnrollmentId Is null
	begin
		select @nextEnrollmentId=max(IdEnrollment)+1
		from Enrollment;

		Insert into Enrollment
		values(@nextEnrollmentId,@semester+1,@idStudy,GetDate());

	end;

	Declare @currentEnrollmentId int = (select IdEnrollment from Enrollment
										where IdStudy=@idStudy and semester=@semester);

	update Student
	set IdEnrollment=@nextEnrollmentId
	where IdEnrollment=@currentEnrollmentId;



	select * from Enrollment where IdEnrollment=@nextEnrollmentId;

	rollback

end;
