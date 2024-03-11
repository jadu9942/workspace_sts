
-- group by 통계 쿼리에서 사용

SELECT * FROM emp;

-- 직원들의 급여의 합 조회
SELECT SUM(SAL)
FROM emp;

-- 각 직급별 급여의 합
SELECT JOB, SUM(SAL)
FROM emp
GROUP BY JOB;

-- 부서번호 별 인원수 조회
-- COUNT는 NULL은 제외 되고 조회
SELECT DEPTNO, COUNT(EMPNO) 
FROM emp
GROUP BY DEPTNO;

-- 데이터 각각 컬럼에 대해서 행을 조회 하는데 행의 개수가 다르면 조회 불가능
-- 다중행 함수: 데이터의 개수과 상관없이
--             조회 결과가 1행 나오는 함수 EX) SUM COUNT MIN MAX AVG  
--  사번 사원명 모든 직원의 급여의 합을 조회

SELECT 
EMPNO, ENAME FROM emp;


-- 중복을 제거한 데이터 조회
SELECT DISTINCT JOB FROM emp;



-- ------------------------------------------------------------------------------------------------------

-- 8 GROUP BY에 들어간 컬럼만 같이 조회 가능
-- 만약 커미션의 편균이 NULL이라면 0.0으로 조회
SELECT 
	JOB, SUM(SAL), AVG(SAL)
	, IFNULL(AVG(COMM), 0.0)
FROM emp
GROUP BY JOB
ORDER BY JOB ASC;


SELECT COMM
	, IFNULL(COMM,10) -- 앞에 있는 값이 NULL이면 10 나옴
FROM emp;



-- 1월에 입사한 사원들을 제외하고 입사한 월별 사원들의 입사자 수
SELECT 
	DATE_FORMAT(HIREDATE, '%M') 입사월
	, COUNT(EMPNO)
FROM emp
WHERE DATE_FORMAT(HIREDATE, '%m') != '01'
GROUP BY DATE_FORMAT(HIREDATE, '%m');



-- 쿼리 해석 순서
-- FROM -> WHERE -> SELECT
-- GROUP BY는 SELECT 다음으로 해석 그래서 GROUP BY는 별칭 사용 가능
-- ORDER BY는 쿼리문에서 문법상으로 마지막 해석도 마지막

SELECT 
	DATE_FORMAT(HIREDATE, '%M') 입사월 -- 3
	, COUNT(EMPNO)
FROM emp
WHERE DATE_FORMAT(HIREDATE, '%m') != '01' -- 2
GROUP BY 입사월 -- 4
ORDER BY 입사월; -- 5




-- 월별 입사자 수를 조회
-- 월별 입사자 수 가 2명 이상인 데이터만 조회


-- (조회 시 월별 입사자 수가 높은 순으로 조회)
SELECT -- 2
	DATE_FORMAT(HIREDATE, '%m') 입사월
	, COUNT(EMPNO) 입사인원
FROM emp -- 1
WHERE DATE_FORMAT(HIREDATE,'%m')!='10'
GROUP BY 입사월 -- 3
HAVING COUNT(EMPNO) >=2
ORDER BY 입사인원 DESC; -- 4

-- HAVING GROUP BY 다음으로 실행


-- 사원들의 입사 월을 조회
SELECT HIREDATE
	, SUBSTRING(HIREDATE, 6 ,2) 
	, DATE_FORMAT(HIREDATE, '%m') -- 날짜 조회
FROM emp;




