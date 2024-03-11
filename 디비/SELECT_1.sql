-- 데이터 조회
-- 주석

-- 데이터 조회
-- 문법
-- SELECT 컬럼명들 FROM 테이블명;
-- 1. emp 모든 사원의 사번, 이름,급여정보조회
SELECT EMPNO, ENAME, SAL FROM emp;

-- 모든 사원의 이름, 직급, 입사일, 부서번호 조회
SELECT ENAME, JOB,HIREDATE, DEPTNO FROM emp;

 -- 모든 사원의 모든 정보를 조회 사원이 가지고 있는 모든 열
 -- *(에스테리스크): ALL
SELECT * FROM emp; 

-- 조건을  통해 조회
-- 급여가 300 이상인 사원들의 사번, 사원명, 급여조회
SELECT EMPNO, ENAME, SAL 
FROM emp 
WHERE SAL>=600;

-- 직급이 대리인 사원들의 사원명, 직급, 급여를 조회
SELECT ENAME, JOB, SAl
FROM emp
WHERE JOB='대리';

-- 직급이 과장이고 급여가 400 이상인 사원들의 모든 정보 조회
-- 같지 않다 :! =,<>
SELECT *
FROM emp
WHERE JOB='과장' AND SAL>=400;

-- COMM 컬럼이 NULL인 사원의 모든 정보 조회
SELECT *
FROM emp
WHERE COMM IS NULL;
-- WHERE COMM IS NOT NULL; 널이 아닐 때 조회

-- 급여가 500미만이거나 700이상이면서 직급은 차장이고 COMM은 NULL인 사원들의 
-- 사번 사원명 급여 직급 커미션 정보를 조회

SELECT EMPNO, ENAME, SAL, JOB, COMM
FROM emp
WHERE (SAL<500 OR SAL>=700) 
AND JOB='차장' AND COMM IS NULL; 





-- 와일드 카드: %, _
-- % : 
-- _: 
-- 사원명에서 '이'라는 글자가 포함된 사원 조회
SELECT * 
FROM emp 
WHERE ENAME LIKE '%이%';
--  (글자 수 랜덤) 이 (글자수 랜덤) 


-- 사원명이 세 글자이면서 중간 글자가 '이' 인 사원 조회
SELECT * 
FROM emp 
WHERE ENAME LIKE '_이_';
-- (글자수 하나 )이(글자수 하나) 

-- 사원명에서 세번글 글자가 '이' 인 사원 조회
SELECT * 
FROM emp 
WHERE ENAME LIKE '__이%';
--  (글자수 하나 )(글자수 하나)이(글자수 랜덤)

--  -------------------------------------
-- UPPER()-> 대문자로 변경 
-- LOWER()-> 소문자로 변경
SELECT 'java', UPPER('java'), LOWER('JAVA');

-- BOARD 테이블에서 제목에 java라는 글자가 포함된 
--게시글의 모든 내용을 조회하는 쿼리
-- 단 java라는 글자는 대, 소문자 구분하지 않고 조회


SELECT TITLE 
FROM board;
WHERE TILTE LIKE CONCAT('%',#{} , '%');

SELECT CONCAT('aa','bb','cc');
-- 문자열 나열되어서 나옴 

-- jjjjj







  