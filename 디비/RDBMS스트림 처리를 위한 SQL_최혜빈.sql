-- 0312시험


-- 1 
CREATE TABLE my_member(
	MEMBER_NUMBER INT PRIMARY KEY 
	, MEMBER_ID VARCHAR(20) NOT NULL
	, MEMBER_PW VARCHAR(20) NOT NULL
	, MEMBER_NAME VARCHAR(20) NOT NULL
	, MEMBER_AGE INT NOT NULL
	, MEMBER_EMAIL VARCHAR(50)



-- 2
INSERT INTO my_member(
	MEMBER_NUMBER
	, MEMBER_ID
	, MEMBER_PW
	, MEMBER_NAME
	, MEMBER_AGE
	, MEMBER_EMAIL
)
VALUES(
	1
	,'JAVA'
	,'JAVA4545'
	,'김자반'
	, 25
	, 'java25@naver.com'
);


DELETE FROM my_member;

COMMIT; 


-- 3
UPDATE my_member
SET MEMBER_NAME='김자바' 
, MEMBER_ID='KIMJAVA'
WHERE MEMBER_NUMBER=1;


-- -----------------------------------------------
SELECT * FROM emp;
SELECT * FROM dept;
SELECT * FROM salgrade;
-- -----------------------------------------------



-- 4 
 SELECT 
	EMPNO
	, ENAME
	, SAL
	, COMM
 FROM emp
 WHERE (SAL>=500 AND SAL<=3000)
 AND COMM IS NOT NULL;


-- 5 
SELECT 
	EMPNO
	, ENAME
	, HIREDATE
FROM emp
WHERE ENAME LIKE '%기' OR ENAME LIKE '%김%'
ORDER BY EMPNO DESC;



-- 6
SELECT 
	EMPNO
	, ENAME 
	, DEPTNO
	, CASE WHEN DEPTNO=10 THEN '인사부'
			 WHEN DEPTNO=20 THEN '영업부'
			 WHEN DEPTNO=30 THEN '개발부'
	  ELSE '생산부'
	  END 부서명
FROM emp;



-- 7 
SELECT 
	EMPNO
	, ENAME
	, HIREDATE
	, IFNULL(COMM,0) COMM 
	, DATE_FORMAT(HIREDATE, '%m') 입사월
	
FROM emp
WHERE DATE_FORMAT(HIREDATE, '%m')=1
ORDER BY HIREDATE ASC;


-- 8
SELECT 
	DEPTNO,SUM(SAL), AVG(SAL), AVG(COMM)
FROM emp
GROUP BY DEPTNO
ORDER BY SUM(SAL) DESC;



-- 9
SELECT 
	EMPNO
	, ENAME
	, HIREDATE
	, SAL
	, DEPTNO
	, (SELECT DNAME FROM DEPT WHERE emp.DEPTNO=dept.DEPTNO) DNAME
FROM emp
WHERE DEPTNO= (SELECT DEPTNO FROM dept WHERE DNAME='인사부');



-- 10
SELECT 
	EMPNO
	, ENAME
	, HIREDATE
	, SAL
	, EMP.DEPTNO
	, DNAME
FROM emp
INNER JOIN dept 
ON emp.DEPTNO= dept.DEPTNO
WHERE DNAME!='인사부' AND SAL>=500
ORDER BY EMPNO DESC ,ENAME ASC;

