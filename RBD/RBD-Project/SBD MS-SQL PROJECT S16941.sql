
-- Table: Customer
CREATE TABLE Customer
(
    IdCustomer integer      NOT NULL,
    first_name varchar(20) NOT NULL,
    last_name  varchar(20) NOT NULL,
    email      varchar(20) NOT NULL,
    CONSTRAINT Customer_pk PRIMARY KEY (IdCustomer)
);

-- Table: Delivery
CREATE TABLE Delivery
(
    IdDelivery       integer NOT NULL,
    IdSupplier       integer NOT NULL,
    date_of_delivery datetime    NOT NULL,
    CONSTRAINT Delivery_pk PRIMARY KEY (IdDelivery)
);

-- Table: Employee
CREATE TABLE Employee
(
    IdEmloyee  integer      NOT NULL,
    first_name varchar(20) NOT NULL,
    last_name  varchar(20) NOT NULL,
    salary     int NOT NULL,
    email      varchar(20) NOT NULL,
    CONSTRAINT Employee_pk PRIMARY KEY (IdEmloyee)
);

-- Table: Product
CREATE TABLE Product
(
    IdProduct      integer      NOT NULL,
    type           varchar(20) NOT NULL,
    name           varchar(20) NOT NULL,
    price_in_store decimal(5, 2) NOT NULL,
    CONSTRAINT Product_pk PRIMARY KEY (IdProduct)
);

-- Table: ProductDelivery
CREATE TABLE ProductDelivery
(
    IdProductDelivery  integer      NOT NULL,
    IdProduct          integer      NOT NULL,
    IdDelivery         integer      NOT NULL,
    amount_of_products int NOT NULL,
    price              decimal(5, 2) NOT NULL,
    CONSTRAINT ProductDelivery_pk PRIMARY KEY (IdProductDelivery)
);

-- Table: Supplier
CREATE TABLE Supplier
(
    IdSupplier      integer      NOT NULL,
    company_name    varchar(20) NOT NULL,
    office_location varchar(20) NOT NULL,
    description     varchar(20) NOT NULL,
    CONSTRAINT Supplier_pk PRIMARY KEY (IdSupplier)
);

-- Table: Transactions
CREATE TABLE Transactions
(
    IdTransaction       integer      NOT NULL,
    IdEmloyee           integer      NOT NULL,
    IdCustomer          integer      NOT NULL,
    IdProduct           integer      NOT NULL,
    date_of_transaction datetime         NOT NULL,
    amount              int NOT NULL,
    CONSTRAINT Transactions_pk PRIMARY KEY (IdTransaction)
);

-- foreign keys
-- Reference: Delivery_Supplier (table: Delivery)
ALTER TABLE Delivery
    ADD CONSTRAINT Delivery_Supplier
        FOREIGN KEY (IdSupplier)
            REFERENCES Supplier (IdSupplier);

-- Reference: ProductDelivery_Delivery (table: ProductDelivery)
ALTER TABLE ProductDelivery
    ADD CONSTRAINT ProductDelivery_Delivery
        FOREIGN KEY (IdDelivery)
            REFERENCES Delivery (IdDelivery);

-- Reference: ProductDelivery_Product (table: ProductDelivery)
ALTER TABLE ProductDelivery
    ADD CONSTRAINT ProductDelivery_Product
        FOREIGN KEY (IdProduct)
            REFERENCES Product (IdProduct);

-- Reference: Transaction_Customer (table: Transactions)
ALTER TABLE Transactions
    ADD CONSTRAINT Transaction_Customer
        FOREIGN KEY (IdCustomer)
            REFERENCES Customer (IdCustomer);

-- Reference: Transaction_Employee (table: Transactions)
ALTER TABLE Transactions
    ADD CONSTRAINT Transaction_Employee
        FOREIGN KEY (IdEmloyee)
            REFERENCES Employee (IdEmloyee);

-- Reference: Transaction_Product (table: Transactions)
ALTER TABLE Transactions
    ADD CONSTRAINT Transaction_Product
        FOREIGN KEY (IdProduct)
            REFERENCES Product (IdProduct);


INSERT INTO Customer (idcustomer, first_name, last_name, email)
VALUES (1, 'Stefan', 'Jaki', 'sjaki@elo.pl');
INSERT INTO Customer (idcustomer, first_name, last_name, email)
VALUES (2, 'Alfons', 'Ale', 'aleale@elo.pl');
INSERT INTO Customer (idcustomer, first_name, last_name, email)
VALUES (3, 'Bree', 'Larson', 'larson@breee.com');

INSERT INTO Employee (idemloyee, first_name, last_name, salary, email)
VALUES (4, 'Fide', 'Gif', 2000, 'fg@gmail.com');
INSERT INTO Employee (idemloyee, first_name, last_name, salary, email)
VALUES (5, 'Abdul', 'Maro', 10000, 'gh@gmail.com');
INSERT INTO Employee (idemloyee, first_name, last_name, salary, email)
VALUES (6, 'Pale', 'Ale', 2200, 'dr@gmail.com');


INSERT INTO Product (idproduct, type, name, price_in_store)
VALUES (1, 'tool', 'rake', 20);
INSERT INTO Product (idproduct, type, name, price_in_store)
VALUES (2, 'tool', 'shovel', 30);
INSERT INTO Product (idproduct, type, name, price_in_store)
VALUES (3, 'seed', 'strawbery', 1.20);

INSERT INTO Supplier (idsupplier, company_name, office_location, description)
VALUES (1, 'BraciaKwiatek', 'Wieliczka', 'Good quality');
INSERT INTO Supplier (idsupplier, company_name, office_location, description)
VALUES (2, 'SeedExp', 'London', 'Good quality');
INSERT INTO Supplier (idsupplier, company_name, office_location, description)
VALUES (3, 'FineExport', 'Hongkong', 'Good quality');

INSERT INTO Delivery (iddelivery, idsupplier, date_of_delivery)
VALUES (1, 1, CONVERT(DATETIME, '2018-09-09'));
INSERT INTO Delivery (iddelivery, idsupplier, date_of_delivery)
VALUES (2, 2, CONVERT(DATETIME, '2018-09-07'));
INSERT INTO Delivery (iddelivery, idsupplier, date_of_delivery)
VALUES (3, 3, CONVERT(DATETIME, '2018-09-12'));
INSERT INTO Delivery (iddelivery, idsupplier, date_of_delivery)
VALUES (4, 1, CONVERT(DATETIME, '2018-09-12'));


INSERT INTO ProductDelivery (idproductdelivery, idproduct, iddelivery, amount_of_products, price)
VALUES (1, 1, 1, 100, 7);
INSERT INTO ProductDelivery (idproductdelivery, idproduct, iddelivery, amount_of_products, price)
VALUES (2, 2, 2, 1000, 5);
INSERT INTO ProductDelivery (idproductdelivery, idproduct, iddelivery, amount_of_products, price)
VALUES (3, 3, 3, 100, 10);


INSERT INTO Transactions (idtransaction, idEmloyee, idcustomer, idproduct, date_of_transaction, amount)
VALUES (1, 4, 1, 1, CONVERT(DATETIME, '2019-01-01'), 59);
INSERT INTO Transactions (idtransaction, idEmloyee, idcustomer, idproduct, date_of_transaction, amount)
VALUES (2, 5, 2, 2, CONVERT(DATETIME, '2019-01-02'), 20);
INSERT INTO Transactions (idtransaction, idemloyee, idcustomer, idproduct, date_of_transaction, amount)
VALUES (3, 6, 3, 3, CONVERT(DATETIME, '2019-01-03'), 59);
INSERT INTO Transactions (idtransaction, idEmloyee, idcustomer, idproduct, date_of_transaction, amount)
VALUES (4, 4, 2, 1, CONVERT(DATETIME, '2019-01-09'), 100);
INSERT INTO Transactions (idtransaction, idEmloyee, idcustomer, idproduct, date_of_transaction, amount)
VALUES (5, 5, 3, 2, CONVERT(DATETIME, '2019-01-08'), 500);
INSERT INTO Transactions (idtransaction, idemloyee, idcustomer, idproduct, date_of_transaction, amount)
VALUES (6, 6, 1, 3, CONVERT(DATETIME, '2019-01-17'), 20);


INSERT INTO Customer (idcustomer, first_name, last_name, email)
SELECT Employee.idEmloyee, Employee.first_name, Employee.last_name, Employee.email
FROM Employee;
GO
------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------PROJECT WITH PROFESSOR YOSHI------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------

-- T/SQL 1 TABLE VALUED FUNCTION
/*Create a table-valued function which returns a table containing the 
all transaction id,customer id and date of transaction with the type of product specified as an argument.
*/




CREATE FUNCTION TransactionsByProductType
(
  @idType VARCHAR(20)	
)
RETURNS @IndicatedTransactions TABLE 
(	
	IdTransaction INT,
	IdCustomer INT,
	Date_Of_Transaction DATE
)
AS
BEGIN
	Insert Into @IndicatedTransactions
	Select IDTRANSACTION,IDCUSTOMER,DATE_OF_TRANSACTION
	From Transactions
	Inner join Product on Product.IdProduct=Transactions.IdProduct
	where Product.type=@idType;
	


	
	RETURN 
END

GO

Select * 
from TransactionsByProductType('tool');
Go
-- T/SQL PROCEDURE WITH RETURN VALUE 
/*Create a procedure which returns the customer with the biggest number of transactions.*/
Create PROCEDURE CustomerWithMaxTransactions
(
	@name varchar(50) out,
	@transaction_count Int out
 )
AS
BEGIN
	SET NOCOUNT ON;

	select @name=Last_name,@transaction_count=count(IdTransaction)
	from Customer e
	Inner Join Transactions d on d.IdCustomer=e.IdCustomer
	where d.IdCustomer=e.IdCustomer
	group by last_name
END

GO

Declare 
@name varchar(50),
@transaction_count Int;

Exec CustomerWithMaxTransactions @name out, @transaction_count out;

Select @name as Name, @transaction_count as Transactions
GO



-- T/SQL SCALAR FUNCTION 
/*This is scalar function which is returning average yearly salary.*/

Create  FUNCTION AveragePayment()
RETURNS INT
AS
BEGIN
	DECLARE @result INT;
	
	Begin
		Select @result=AVG(Salary*12)
		from Employee
		
	End;

	RETURN @result;

END

Go
Declare
@payment INT;

Exec @payment=AveragePayment;

select @payment
GO


-- TRIGGER 
-- 1.does not allow insert employee whom salary is bigger than avegrage salary 
-- 2.if deleted employee had more than 2 transactions save in GoodExWorker table
-- Take a note that to create that trigger first we have to create AveragePayment function because it is used here
Create table GoodExWorker
(
 IdEmployee Int Primary Key,
 First_Name varchar(50),
 Last_Name varchar(50),
 Email varchar(50)
)
Go
Create TRIGGER ProtectingData
ON Employee
AFTER  INSERT,DELETE
AS 
Declare 
@salary Int,
@emp_count Int,
@emp_Id Int,
@avg_sal Int
BEGIN	
	Exec @avg_sal=AveragePayment;
	SET NOCOUNT ON;
	IF exists (Select * from inserted)
	Begin
		select @salary=Salary
		from inserted;

		if @salary*12>(@avg_sal)
	    Begin
			RaisError('Salary is too big!',16,1);
			Rollback tran
	    End
	 End
	 ELSE IF exists (Select * from deleted)
	 Begin
		select @emp_Id=IdEmloyee from deleted;

		select @emp_count=COUNT(1)
		from Transactions
		where Transactions.IdEmloyee=@emp_Id

		If @emp_count>2
		Begin
			Insert GoodExWorker
			Select IdEmloyee,First_Name,Last_Name,Email from deleted
		End
	 End
END
Go

Insert Into Employee(IdEmloyee,First_Name,Last_Name,Salary,Email)
values (1111,'Jaś','Fasola',450000,'jas.fasola@gmail.com');
GO

