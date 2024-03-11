-- 현재 쿼리 탭에서는 AUTOCOMMIT 을 비활성화
SET @@AUTOCOMMIT=0;

-- 데이터의 변경사항을 취소(이전 상태로 돌리기)
ROLLBACK;
-- 데이터의 변경사항을 저장
COMMIT;


-- 공부용 테이블 생성
CREATE TABLE STUDENT(
   STU_NO INT  PRIMARY KEY
   --          무결성 제약조건 중 가장 기초인 PRIMARY KEY
   --           학번은 중복된 데이터 못 들어가고 NULL 못 들어감
   ,STU_NAME VARCHAR(10)
   --              (최대 글자수)
   ,SCORE INT 
   ,ADDR VARCHAR(20)
); 

SELECT *
FROM student;



  SELECT STU_NAME
  FROM student
  WHERE STU_NO=1;
  
   SELECT SCORE
   FROM STUDENT
   WHERE STU_NO=1;
   
     SELECT STU_NO
        , STU_NAME
        ,SCORE, ADDR
        FROM STUDENT
        WHERE STU_NO=1;
SELECT STU_NO
        , STU_NAME
        ,SCORE
        ,ADDR
        FROM student;


-- 데이터 삽입 
-- INSERT INTO 테이블명 (컬럼들) VALUES (값들);
INSERT INTO student (STU_NO, STU_NAME, SCORE, ADDR) 
VALUES (1, '김자바', 80, '울산');
-- 두번이상 실행하면 오류 생김 이미 있는 자료에서 중복 됨

INSERT INTO student (STU_NO, STU_NAME) 
VALUES (2, '이자바');
--  컬럼수와 VALUES 같아야하고 순서도 같아야 함

INSERT INTO student (STU_NO, STU_NAME) 
VALUES(3,'최자바');
COMMIT;

INSERT INTO student (STU_NAME, SCORE) 
VALUES('최자바',90);
-- NULL 못들어가는 컬럼 명시해줘야함 

INSERT INTO student VALUES (5, '홍길동',80, '서울'); 
-- 컬럼명 생략가능 
-- 컬럼명을 명시하지 않으면 테이블 생성시 작성한 컬럼순으로 데이터를 삽입할 수 있다
COMMIT;

SELECT * FROM student;
-- 데이터 삭제 
-- DELETE FROM 테이블명 [WHERE 조건];  []는 생략가능 
DELETE  FROM student; 

ROLLBACK;

-- 학번이 1번인 학생을 삭제하는 쿼리
DELETE FROM student WHERE STU_NO=1; 

-- 학번이 3번이상이면서 주소가 NULL인 학생은 삭제
DELETE FROM student WHERE STU_NO>=3 AND ADDR IS NULL;
COMMIT; 