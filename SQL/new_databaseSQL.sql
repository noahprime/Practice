CREATE TABLE groceries(id INTEGER PRIMARY KEY, name TEXT, quantity INTEGER, aisle INTEGER);
INSERT INTO groceries VALUES (1,'Bananas',4,7);
INSERT INTO groceries VALUES (2,'Peanut Butter',1,2);
INSERT INTO groceries VALUES (3,'Chocolate Bars',2,2);
INSERT INTO groceries VALUES (4,'Ice Cream',1,12);
INSERT INTO groceries VALUES (5,'Cherries',6,2);
INSERT INTO groceries VALUES (6,'Syrup',1,4);

SELECT * FROM groceries;

SELECT * FROM groceries WHERE aisle > 5 ORDER BY aisle;

SELECT aisle, SUM(quantity) FROM groceries GROUP BY aisle;

DROP TABLE exercise;
CREATE TABLE exercise
(id INTEGER PRIMARY KEY DEFAULT IDENTITY(1,1),
  type TEXT,
  minutes INTEGER,
  calories INTEGER,
  heart_rate INTEGER
);

INSERT INTO exercise(type,minutes,calories,heart_rate)
VALUES ('biking',20,100,110);

INSERT INTO exercise(type,minutes,calories,heart_rate)
VALUES ('biking',10,30,105);

INSERT INTO exercise(type,minutes,calories,heart_rate)
VALUES ('dancing',15,200,120);

INSERT INTO exercise(type,minutes,calories,heart_rate)
VALUES ('climbing',50,150,90);

INSERT INTO exercise(type,minutes,calories,heart_rate)
VALUES ('soccer',90,400,130);

SELECT * FROM exercise;


