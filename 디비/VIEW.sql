
-- VIEW 
-- 뷰 : 가상 테이블 실제 테이블은 아님) 테이블처럼 행동하는 것
-- 뷰가 유용하게 사용되는 경우(2가지)
-- 1) 테이블의 데이터 조회 시 조인이 지속적으로 반복되는 경우 
-- 2) 테이블의 특정 데이터의 보안성을 확보하기 위해서 

-- 데이터 복사가 아니라 SELECT문을 미리 만들어놓는 것


SELECT * FROM emp;  

-- EMP 테이블에 대한 첫번째 VIEW 생성
CREATE VIEW EMP_VIEW_1 
AS    -- 셀렉트 한 결과로 VIEW 만들거에요
SELECT EMPNO, ENAME, JOB
FROM emp;

SELECT * FROM emp_view_1
WHERE EMPNO =1001;      -- VIEW 조회 WHERE절도 가능 


-- OR REPLACE VIEW 없으면 만들고 있으면 덮어쓰기(수정된 걸로 조회됨)
CREATE OR REPLACE VIEW EMP_VIEW_2 
AS 
SELECT EMPNO, ENAME, SAL, COMM
FROM emp 
WHERE SAL >= 350; 

SELECT * FROM EMP_VIEW_2; -- 뷰를 만든다고 해서 데이터가 생성되는 게 아니라 조회만 하는 것 (데이터 없음)
DROP VIEW EMP_VIEW_2;







