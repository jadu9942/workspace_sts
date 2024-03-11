-- 기본 게시판 테이블
CREATE TABLE BASIC_BOARD (
	BOARD_NUM INT PRIMARY KEY -- NOT NULL, 중복X
	, BOARD_TITLE VARCHAR(50) NOT NULL
	, BOARD_CONTENT VARCHAR(50)
	, WRITER VARCHAR(20) NOT NULL
	, CREATE_DATE VARCHAR(20) NOT NULL -- '2023-05-10'
	, READ_CNT INT DEFAULT 0
);

DROP TABLE basic_board;
SELECT * FROM basic_board;

-- update 문법(수정) 
UPDATE basic_board
SET 
WRITER = '김자바';
WHERE BOARD_NUM=1;
-- 작성자, 내용 바꾸기

UPDATE basic_board
SET 
    WRITER = '김자바'
    , BOARD_CONTENT= '변경내용'
WHERE BOARD_NUM=1;

COMMIT;

 INSERT INTO BOARD (
            TITLE
            , CONTENT
            , WRITER
        ) VALUES (
            '헤헤'
            , '아무거나'
            , 'java'

        );
        COMMMIT;


SELECT * FROM board;

SELECT COUNT(*) FROM board;
-- 게시글의 전체 데이터 수 조회
SELECT COUNT(*) FROM emp; 
SELECT * FROM emp;
SELECT COUNT(EMPNO) FROM emp;
-- (컬럼명) COUNT를 쓸 때는 컬럼명을 쓰는 게 맞는데 
-- 아무 컬럼이나 넣는 게 아니라 NULL이 없는 컬럼 넣어줘야 함
-- 그런데 어디에 NULL이 들어간지 모른다 그런데 NULL값이 들어가지 않는 곳이 있는데 
-- PRIMARY KEY가 있는 컬럼이다 이걸 조회하면 된다

SELECT *
FROM board
ORDER BY BOARD_NUM;
LIMIT 5 OFFSET 10;
-- 1PAGE: 0
-- 2PAGE: 5
-- LIMIT :전체 기준으로 줄을 세워서 처음 데이터부터만 조회
-- OFFSET: 게시글 1-100까지 조회하고 정렬시켜서 그 중 다섯개만 뽑는데
-- 처음 다섯개 건너 뛰고 난 다음 숫자부터 다섯개 띄우기


        