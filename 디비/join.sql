-- 별칭 사용 (조회 시 컬럼명을 변경)
-- 컬럼명을 조회할 때는 테이블명.컬럼명으로 조회
-- 통상적으로 테이블명. 은 생략

SELECT EMPNO
	,ENAME
	,SAL
	FROM emp;
	
SELECT EMPNO
	,ENAME AS NM
	-- AS + 별칭 조회할 때 일시적으로  NM으로 이름 바꿀 수 있음(한글도 가능)
	,SAL 급여
	-- AS 키워드 생략해도 별칭 사용 가능 
	FROM emp;	
	
	
-- 테이블명도 별칭 사용 가능한지(문법적으로 가능 )
SELECT E.EMPNO
	, E.ENAME
	, E. SAL
	FROM emp AS E;
	-- 이 문법은 JOIN 할 때 사용함(두개 이상 테이블에서 데이터 조회할 때)
	


-- join 
SELECT *FROM emp;
SELECT *FROM dept;

-- 사원의 사번, 이름, 부서명을 조회
-- 사번 이름은 EMP 테이블에 부서명은 DEPT에서 

-- 1. CROSS JOIN(공부를 위해 학습, 실무에서는 안씀)
SELECT EMPNO, ENAME, DNAME
FROM emp CROSS JOIN dept; 
-- CROSS JOIN 했을 때 여러 가지 데이터 중복되어서 나옴 

-- 2. INNER JOIN(교집합) 두 테이블의 데이터 중 공통으로 가지고 있는 데이터 조회
-- ON: 조인하는 두 테이블이 공통적으로 지니는 컬럼의 값이 같다 라는 조건을 줄 것 
SELECT EMPNO, ENAME, DNAME 
FROM EMP INNER JOIN dept
ON emp.DEPTNO=dept.DEPTNO;
-- 두 테이블 중 동시에 같은 거 조회 ->DEPTNO
-- 어떤 테이블에 있는 DEPTNO 데이터인지 알기 위해 EMP.DEPTNO
SELECT EMPNO, ENAME, DNAME, E.DEPTNO
FROM emp E INNER JOIN dept D
ON E.DEPTNO = D.DEPTNO
WHERE E.DEPTNO=20;
--  E.DEPTNO 중복되는 컬럼 앞에 테이블명. 써주기


-- 데이터 조회시 정렬하여 출려하기
-- 사원의 모든 정보를 조회하되, 급여가 낮은  순(오름차순)부터 조회(기준을 삼아 조회)
-- 오름차순 : ASC, 생략가능(생략하면 자동으로 오름차순)
-- 내림차순: DESC
SELECT *
FROM emp
ORDER BY SAL DESC ; -- ASCENDING(오름차순)ASC, DESCENDING(내림차순)DESC

-- 사원의 모든 데이터를 조회하되, 급여 기준 내림차순 정렬 
-- 급여가 같다면 사번기준 오름차순 정렬 (, 로 나열해주면 됨)
SELECT *
FROM emp 
ORDER BY SAL DESC, EMPNO ASC; 

-- 급여가 200이상이면서 커미션은 NULL이 아닌 
-- 사원의 사번, 이름, 급여, 부서번호, 부서명을 조회 
-- 단, 부서번호 기준 오름 차순 정렬 후 부서번호가 같다면 
-- 급여기준 내림차순으로 정렬
-- 추가적으로 사번은 '사원번호' 라는 별칭을 사용해서 조회
-- SELECT 쿼리에서 정렬할 게 있으면 맨 마지막에 작성

SELECT EMPNO 사원번호
	, ENAME
	, SAL
	, EMP.DEPTNO 
	, DNAME
	FROM EMP INNER JOIN dept
	ON emp.DEPTNO= dept.DEPTNO
	WHERE SAL >=200 AND COMM IS NOT NULL
 	ORDER BY EMP.DEPTNO ASC, SAL DESC;



