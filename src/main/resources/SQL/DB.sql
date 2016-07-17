CREATE DATABASE hr;
USE hr;
CREATE TABLE depts (
  deptno INT PRIMARY KEY
);
CREATE TABLE emps (
  empid  INT AUTO_INCREMENT PRIMARY KEY,
  deptno INT,
  FOREIGN KEY (deptno) REFERENCES depts (deptno)
);

INSERT INTO depts VALUES(1);
INSERT INTO emps VALUES(1,1);

  SELECT
    d.deptno,
    min(e.empid)
  FROM hr.emps AS e
    JOIN hr.depts AS d
      ON e.deptno = d.deptno
  GROUP BY d.deptno
  HAVING count(*) > 1;