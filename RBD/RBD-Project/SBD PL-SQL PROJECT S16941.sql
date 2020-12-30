--DROPPING ALL TABLES
BEGIN
    FOR c IN ( SELECT table_name FROM user_tables WHERE table_name not LIKE 'A1_%' )
        LOOP
            EXECUTE IMMEDIATE 'DROP TABLE ' || c.table_name || ' CASCADE CONSTRAINTS';
        END LOOP;
END;
/
--DROPPING ALL TRIGGERS
BEGIN
    FOR c IN ( SELECT TRIGGER_name FROM user_tRIGGERS WHERE TRIGGER_name not LIKE 'A1_%' )
        LOOP
            EXECUTE IMMEDIATE 'DROP TRIGGER ' || c.tRIGGER_name || ' CASCADE CONSTRAINTS';
        END LOOP;
END;
/
-- Table: Customer
CREATE TABLE Customer
(
    IdCustomer integer      NOT NULL,
    first_name varchar2(20) NOT NULL,
    last_name  varchar2(20) NOT NULL,
    email      varchar2(20) NOT NULL,
    CONSTRAINT Customer_pk PRIMARY KEY (IdCustomer)
);

-- Table: Delivery
CREATE TABLE Delivery
(
    IdDelivery       integer NOT NULL,
    IdSupplier       integer NOT NULL,
    date_of_delivery date    NOT NULL,
    CONSTRAINT Delivery_pk PRIMARY KEY (IdDelivery)
);

-- Table: Employee
CREATE TABLE Employee
(
    IdEmloyee  integer      NOT NULL,
    first_name varchar2(20) NOT NULL,
    last_name  varchar2(20) NOT NULL,
    salary     number(5, 0) NOT NULL,
    email      varchar2(20) NOT NULL,
    CONSTRAINT Employee_pk PRIMARY KEY (IdEmloyee)
);

-- Table: Product
CREATE TABLE Product
(
    IdProduct      integer      NOT NULL,
    type           varchar2(20) NOT NULL,
    name           varchar2(20) NOT NULL,
    price_in_store number(5, 2) NOT NULL,
    CONSTRAINT Product_pk PRIMARY KEY (IdProduct)
);

-- Table: ProductDelivery
CREATE TABLE ProductDelivery
(
    IdProductDelivery  integer      NOT NULL,
    IdProduct          integer      NOT NULL,
    IdDelivery         integer      NOT NULL,
    amount_of_products number(5, 0) NOT NULL,
    price              number(5, 2) NOT NULL,
    CONSTRAINT ProductDelivery_pk PRIMARY KEY (IdProductDelivery)
);

-- Table: Supplier
CREATE TABLE Supplier
(
    IdSupplier      integer      NOT NULL,
    company_name    varchar2(20) NOT NULL,
    office_location varchar2(20) NOT NULL,
    description     varchar2(20) NOT NULL,
    CONSTRAINT Supplier_pk PRIMARY KEY (IdSupplier)
);

-- Table: Transactions
CREATE TABLE Transactions
(
    IdTransaction       integer      NOT NULL,
    IdEmloyee           integer      NOT NULL,
    IdCustomer          integer      NOT NULL,
    IdProduct           integer      NOT NULL,
    date_of_transaction date         NOT NULL,
    amount              number(5, 0) NOT NULL,
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
VALUES (1, 1, TO_DATE('2018-09-09', 'YYYY-MM-DD'));
INSERT INTO Delivery (iddelivery, idsupplier, date_of_delivery)
VALUES (2, 2, TO_DATE('2018-09-07', 'YYYY-MM-DD'));
INSERT INTO Delivery (iddelivery, idsupplier, date_of_delivery)
VALUES (3, 3, TO_DATE('2018-09-12', 'YYYY-MM-DD'));
INSERT INTO Delivery (iddelivery, idsupplier, date_of_delivery)
VALUES (4, 1, TO_DATE('2018-09-12', 'YYYY-MM-DD'));


INSERT INTO ProductDelivery (idproductdelivery, idproduct, iddelivery, amount_of_products, price)
VALUES (1, 1, 1, 100, 7);
INSERT INTO ProductDelivery (idproductdelivery, idproduct, iddelivery, amount_of_products, price)
VALUES (2, 2, 2, 1000, 5);
INSERT INTO ProductDelivery (idproductdelivery, idproduct, iddelivery, amount_of_products, price)
VALUES (3, 3, 3, 100, 10);


INSERT INTO Transactions (idtransaction, idEmloyee, idcustomer, idproduct, date_of_transaction, amount)
VALUES (1, 4, 1, 1, TO_DATE('2019-01-01', 'YYYY-MM-DD'), 59);
INSERT INTO Transactions (idtransaction, idEmloyee, idcustomer, idproduct, date_of_transaction, amount)
VALUES (2, 5, 2, 2, TO_DATE('2019-01-02', 'YYYY-MM-DD'), 20);
INSERT INTO Transactions (idtransaction, idemloyee, idcustomer, idproduct, date_of_transaction, amount)
VALUES (3, 6, 3, 3, TO_DATE('2019-01-03', 'YYYY-MM-DD'), 59);
INSERT INTO Transactions (idtransaction, idEmloyee, idcustomer, idproduct, date_of_transaction, amount)
VALUES (4, 4, 2, 1, TO_DATE('2019-01-09', 'YYYY-MM-DD'), 100);
INSERT INTO Transactions (idtransaction, idEmloyee, idcustomer, idproduct, date_of_transaction, amount)
VALUES (5, 5, 3, 2, TO_DATE('2019-01-08', 'YYYY-MM-DD'), 500);
INSERT INTO Transactions (idtransaction, idemloyee, idcustomer, idproduct, date_of_transaction, amount)
VALUES (6, 6, 1, 3, TO_DATE('2019-01-17', 'YYYY-MM-DD'), 20);


INSERT INTO Customer (idcustomer, first_name, last_name, email)
SELECT Employee.idEmloyee, Employee.first_name, Employee.last_name, Employee.email
FROM Employee;
------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------PROJECT WITH PROFESSOR IDA--------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------

---------
--UNION--
---------

--EMAILS--
SELECT first_name, last_name, email
FROM Customer
UNION
SELECT first_name, last_name, email
FROM Employee;



----------------------
--SOME BASIC QUERIES--
----------------------
UPDATE Product
SET Product.price_in_store = Product.price_in_store * 0.9;

UPDATE Product
SET Product.price_in_store = Product.price_in_store * 1.1;

UPDATE Employee
SET Employee.salary = Employee.salary + 100;

UPDATE Employee
SET Employee.salary = Employee.salary - 100;


--WORKERS WITH SALARY BIGGER THAN 90
SELECT FIRST_NAME, LAST_NAME
FROM EMPLOYEE
WHERE SALARY > 90;

--------------------------------------------
-- AT LEAST TWO TABLES AND WHERE STATEMENT--
--------------------------------------------
--HOW MANY TRANSACTION EACH WORKER
SELECT LAST_NAME, COUNT(IDTRANSACTION)
FROM EMPLOYEE
         INNER JOIN TRANSACTIONS ON
    EMPLOYEE.IDEMLOYEE = TRANSACTIONS.IDEMLOYEE
GROUP BY LAST_NAME;


--WHICH CUSTOMERS WERE BUYING BETWEEN X,Y
SELECT LAST_NAME
FROM CUSTOMER
         INNER JOIN TRANSACTIONS ON
    CUSTOMER.IDCUSTOMER = TRANSACTIONS.IDCUSTOMER
WHERE DATE_OF_TRANSACTION BETWEEN TO_DATE('2019-01-05', 'YYYY-MM-DD') AND TO_DATE('2019-01-15', 'YYYY-MM-DD')
GROUP BY LAST_NAME;

--SHOW WHO IS SUPPLIER OF PRODUCT GIVEN BY USER

ACCEPT p prompt 'write name of product'
SELECT NAME, COMPANY_NAME
FROM PRODUCT
         INNER JOIN productdelivery ON
    PRODUCT.IDPRODUCT = PRODUCTDELIVERY.IDPRODUCT
         INNER JOIN DELIVERY ON
    PRODUCTDELIVERY.IDDELIVERY = DELIVERY.IDDELIVERY
         INNER JOIN SUPPLIER ON
    DELIVERY.IDSUPPLIER = SUPPLIER.IDSUPPLIER
WHERE NAME = NVL('&p', 'shovel');



-----------------------------
-- WITH AGGREGATE FUNCTIONS--
-----------------------------
--MOST EXPENSIVE PRODUCTS
SELECT NAME, PRICE_IN_STORE
FROM PRODUCT
WHERE PRICE_IN_STORE = (SELECT MAX(PRICE_IN_STORE) FROM PRODUCT);


--TOTAL INCOME
SELECT SUM(AMOUNT * PRICE_IN_STORE) AS TOTAL_INCOME
FROM TRANSACTIONS
         INNER JOIN PRODUCT ON
    TRANSACTIONS.IDPRODUCT = PRODUCT.IDPRODUCT;

--AVERAGE INCOME FROM ONE PRODUCT(AFTER COSTS OF BUYING)
SELECT AVG(PRICE_IN_STORE) - AVG(PRICE) AS AVERAGE_INCOME_FROM_PRODUCTS
FROM PRODUCT
         INNER JOIN PRODUCTDELIVERY ON
    PRODUCT.IDPRODUCT = PRODUCTDELIVERY.IDPRODUCT;


-----------------
--WITH SUBQUERY--
-----------------
-- EMPLOYEES WHICH EARNS MORE THAN AVERAGE SALARY
SELECT LAST_NAME
FROM EMPLOYEE
WHERE EMPLOYEE.SALARY > (SELECT AVG(SALARY) FROM EMPLOYEE);


----------------------
--CORELATED SUBQUERY--
----------------------

-- PRODUCTS THAT ARE MORE EXPENSIVE THAN AVERAGE PRICE OF PRODUCT FOR EACH CATEGORY
SELECT NAME
FROM PRODUCT P
WHERE P.PRICE_IN_STORE > (SELECT AVG(PRICE_IN_STORE) FROM PRODUCT WHERE PRODUCT.TYPE = P.TYPE);



CREATE TABLE CUSTOMERA AS
SELECT *
FROM CUSTOMER;
CREATE TABLE EMPLOYEEA AS
SELECT *
FROM EMPLOYEE;
CREATE TABLE PRODUCTA AS
SELECT *
FROM PRODUCT;
CREATE TABLE TRANSACTIONSA AS
SELECT *
FROM TRANSACTIONS;
------------------
--AFTER TRIGGERS--
------------------


--SHOWING HOW MANY TRANSACTIONS WE HAVE AFTER DELETING ONE OF THEM
set ServerOutput ON
create or replace trigger ZERO
    after DELETE
    On transactionsa
declare
    rnumber number;
begin
    select count(idtransaction) into rnumber from transactionsa;
    DBMS_OUTPUT.PUT_line('We have ' || rnumber || ' transactions in the system');
end;
/

delete
from TRANSACTIONSA
where IDTRANSACTION = 1;
DROP TRIGGER ZERO;


--INSERTING NEW EMPLOYEES INTO CUSTOMERS TABLE
Set ServerOutput ON
Create or replace trigger FIRST
    after insert
    on EMPLOYEEA
    for each row
Begin
    insert into CUSTOMERA values (:new.idemloyee, :new.first_name, :new.last_name, :new.email);
End;
/
INSERT INTO EmployeeA (idemloyee, first_name, last_name, salary, email)
VALUES (333, 'Pale', 'Ale', 2200, 'dr@gmail.com');
SELECT *
FROM EMPLOYEEA;
DROP TRIGGER FIRST;


--INFO ABOUT CHANGING PRICE
set ServerOutput ON;
create or replace trigger SECOND
    After Update
    On productA
    for each row
begin
    DBMS_OUTPUT.PUT_line(
                ' THE PRICE OF THE PRODUCT CHANGED FROM ' || :old.price_in_store || ' to ' || :new.price_in_store);
end;
/

UPDATE PRODUCTA
SET PRICE_IN_STORE = 999
WHERE IDPRODUCT = 1;
DROP TRIGGER SECOND;
-------------------
--BEFORE TRIGGERS--
-------------------


--ADDING/REMOVING/UPDATING CUSTMORES DATA
SET SERVEROUTPUT ON;
Create or replace trigger THIRD
    BEFORE insert or update or delete
    on EMPLOYEEA
    for each row
Begin
    IF INSERTING THEN
        dbms_output.put_line('We have new worker !!!');
        if updating then
            dbms_output.put_line('worker data was updated');
            if deleting then
                dbms_output.put_line('worker data was deleted');
            end if;
        end if;
    end if;
END;
/
INSERT INTO EmployeeA (idemloyee, first_name, last_name, salary, email)
VALUES (333, 'Pale', 'Ale', 2200, 'dr@gmail.com');
DROP TRIGGER THIRD;


-- AUTO NEW IDCUMSTOMER
Set ServerOutput ON
Create or replace trigger FOUR
    before insert
    on CUSTOMERA
    for each row
Begin
    select NVL(Max(IDCUSTOMER + 1), 1) into :new.IDCUSTOMER from CUSTOMERA;
End;
/
INSERT INTO CUSTOMERA(first_name, last_name, email)
VALUES ('Stefan', 'Jaki', 'sjaki@elo.pl');
DROP TRIGGER FOUR;

--INFORMATION ABOUT CHANGING CLIENT DATA
set ServerOutput ON
create or replace trigger fifth
    before update
    On customera
    for each row
begin
    dbms_output.put_line(' Your about to change customer data');
end;
/

update customera
set email='ddd@gmail.com'
where idcustomer = 1;
drop trigger fifth;
SET SERVEROUTPUT OFF;


------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------PROJECT WITH PROFESSOR YOSHI------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------


---- 1 PL/SQL block with CURSOR
---- Printing all customers and checking that all Customers ID is bigger than 0;
SET SERVEROUTPUT ON
DECLARE
    c_id   customer.idcustomer%type;
    c_name customer.last_name%type;
    c_addr customer.email%type;
    ex_invalid_id EXCEPTION;
    CURSOR c_customers is
        SELECT idcustomer, last_name, email
        FROM customer;
BEGIN
    OPEN c_customers;
    LOOP
        FETCH c_customers into c_id, c_name, c_addr;
        EXIT WHEN c_customers%notfound;
        IF c_id <= 0 THEN
            RAISE ex_invalid_id;
        ELSE
            dbms_output.put_line(c_id || ' ' || c_name || ' ' || c_addr);
        END IF;
    END LOOP;
    CLOSE c_customers;
EXCEPTION
    WHEN ex_invalid_id THEN
        dbms_output.put_line('ID must be greater than zero!');
    WHEN
        no_data_found THEN
        dbms_output.put_line('No such customer!');
    WHEN
        others THEN
        dbms_output.put_line('Error!');

END;
/

---- PL/SQL 1 procedure that return value(s)
---- This procedure is finding the product to sell with the biggest profit per unit.
DECLARE
    c              number;
    a              number;
    shop_id        Product.IdProduct%type;
    shop_id_result Product.Name%type;
    shop_price     Product.price_in_store%type;
    too_cheap exception;
    CURSOR c_product is
        SELECT idproduct, price_in_store
        FROM product;
    PROCEDURE BestProducttoSell(z OUT number, zzz OUT Product.Name%type) IS

    BEGIN
        z := -999999999;
        OPEN c_product;
        LOOP
            FETCH c_product into shop_id, shop_price;
            SELECT price INTO a FROM ProductDelivery where IdProduct = shop_id;
            EXIT WHEN c_product%notfound;
            IF (shop_price - a) < 0 THEN
                RAISE too_cheap;

            END IF;
            IF (z < (shop_price - a)) THEN
                z := (shop_price - a);
                SELECT name INTO zzz FROM Product where IdProduct = shop_id;
            END IF;
        END LOOP;
        CLOSE c_product;
    EXCEPTION
        WHEN too_cheap THEN
            dbms_output.put_line('One or more product that we are selling is cheaper than we are buying!!!');
    END;


BEGIN
    BestProducttoSell(c, shop_id_result);
    dbms_output.put_line('Product with the biggest earnings is ' || shop_id_result || ' with profit equal ' || c);
END;
/


---- PL/SQL Function
/*
Find the biggest quarterly sale for given year. If the sale os bigger
than 2500 the tax will be 15%, else 12%.
Show that period,sale and tax. Return the profit.
*/
SET SERVEROUTPUT ON;
create or replace FUNCTION TAX(V_YEAR INTEGER) RETURN Number
    IS
    f_date   DATE   := TO_DATE(v_year || '/01/01', 'yyyy/mm/dd');
    b_f_date Date;
    l_date   DATE   := f_date + 91;
    b_l_date Date;
    v_sum    Number := 0;
    copy_sum Number;
    v_tax    Number;
BEGIN

    loop
        EXIT WHEN EXTRACT(YEAR FROM f_date) > V_YEAR;
        copy_sum := v_sum;
        SELECT nvl(sum(Amount * Price_in_store), 0)
        into v_sum
        FROM (
                 SELECT amount
                 FROM Transactions P
                 WHERE date_of_transaction BETWEEN f_date and l_date)
                 Inner join Product PL on PL.IdProduct = IdProduct;

        If v_sum < copy_sum Then
            v_sum := copy_sum;
            b_f_date := f_date;
            b_l_date := l_date;
        End If;

        f_date := l_date + 1;
        l_date := f_date + 90;
    end loop;

    If v_sum > 2500 Then
        v_tax := v_sum * 0.15;
    Else
        v_tax := v_sum * 0.12;
    End If;

    DBMS_OUTPUT.PUT_LINE(
                'For ' || b_f_date || ' to ' || b_l_date || ' period the sale is ' || v_sum || ' the tax is ' || v_tax);
    Return (v_sum - v_tax);
END Tax;
declare
    a number;
BEGIN
    a := TAX(2019);
    DBMS_OUTPUT.PUT_LINE('So the total profit in that period of time was ' || a);

end;
/
----PL/SQL TRIGGER FOR EACH ROW
/*
If You want to see more triggers You can go to the end of "PROJECT WITH PROFESSOR IDA" section (From line 318 to line 430)
Create trigger on ProductList
 - does not allow to add a product it it exists
 - does not allow to change name of product to existed name
 - before deleting any product add the product info in ProductHistory table.
*/

Create table ProductHistory
(
    IdProdukt      Integer,
    Type           varchar(20),
    ProductName    varchar(50),
    Price_in_store number,
    RemoveDate     Date
);

SET SERVEROUTPUT ON;
create or replace trigger Protect_Product_List
    Before Insert or Update or Delete
    on Product
    FOR EACH ROW
Declare
    v_ifexists Integer;
    v_repeating Exception;
begin
    If Inserting Then
        Select count(1)
        Into v_ifexists
        from Product PL
        Where Upper(PL.Name) = Upper(:new.Name);
    ELSIF Updating Then
        IF :new.Name = :old.Name and :new.Price_in_store = :old.Price_in_store and :old.TYPE = :new.TYPE Then
            Raise v_repeating;
        End If;
    ELSIF Deleting Then
        Insert Into ProductHistory(IdProdukt, Type, ProductName, Price_in_store, RemoveDate)
        Values (:old.IdProduct, :old.type, :old.Name, :old.Price_in_Store, SYSDATE);
    End If;

Exception
    When v_repeating Then
        dbms_output.put_line('User trying update with excisting value!');
end;
/

UPDATE Product
SET Product.Name           = 'rake',
    Product.Type           = 'tool',
    Product.Price_in_store = 19.80
Where Product.IdProduct = 1;


