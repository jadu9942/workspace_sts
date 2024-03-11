-- 기본키(PRIMARY KEY:PK)-제약조건의 일종 
--  NULL이 들어갈 수 없고, 테이블 내에서 중복 불가
-- 
-- 회원정보 테이블 
CREATE TABLE BOARD_MEMBER(
  -- 컬럼명 자료형 [제약조건(있으면 넣고 없으면 넣지 않음 생략가능)]
  MEMBER_ID VARCHAR (20) PRIMARY KEY
  --                20글자 미만
  , MEMBER_PW VARCHAR (20) NOT NULL 
  , MEMBER_NAME VARCHAR(20) NOT NULL
  , GENDER VARCHAR(2) -- '남','여'
  , MEMBER_EMAIL VARCHAR (30) UNIQUE
  --                          중복 불가
  ,IS_ADMIN VARCHAR(10) DEFAULT 'N'-- 'N' 일반회원, 'Y'관리자 
  -- 관리자 인지 아닌지 ()/ IS_ADMIN에 데이터를 넣지 않으면 자동으로 N이 들어감
  
);

SELECT * FROM board_member;

-- 게시글 정보 테이블 
DROP TABLE BOARD; -- 테이블 지우기 데이터 남아있음 (ROLLBACK 불가능)
DELETE FROM BOARD; -- 테이블은 남아있고 데이터만 사라짐(ROLLBACK 가능)
-- WRITER: 외래키(FOREIGN KEY: FK) 제약조건 
CREATE TABLE BOARD(
  BOARD_NUM INT AUTO_INCREMENT PRIMARY KEY
  --            자동으로 들어감(입력하지 않아도 중복되지 않게 차례대로 1씩 증가)
	, TITLE VARCHAR(30) NOT NULL 
	, CONTENT VARCHAR(30)
	, WRITER VARCHAR(20) REFERENCES board_member (MEMBER_ID) -- WRITER는 board_member테이블 안에 있는 MEMBER_ID 데이터 참조하겠다
	--                                                         외래키 제약조건 
	, CREATE_DATE DATETIME DEFAULT CURRENT_TIMESTAMP
	--           날짜 데이터       현재 시간      
	, READ_CNT INT DEFAULT 0

);

CREATE TABLE BOARD_REPLY(
	REPLY_NUM INT AUTO_INCREMENT  PRIMARY KEY
	, CONTENT VARCHAR(50) NOT NULL 
	, CREATE_DATE DATETIME DEFAULT CURRENT_TIMESTAMP
	, WRITER VARCHAR(20) REFERENCES board_member(MEMBER_ID)
	-- 로그인 한 사람만 댓글 작성 가능하게 할 것 
	-- 회원 정보는 멤버테이블에 있는데 멤버테이블에 있는 회원아이디만 들어올 수 있음
	, BOARD_NUM INT REFERENCES board (BOARD_NUM)
	-- 댓글이 어디에 달려있는지 원본 글번호 필요함  
	-- (외래키 필요->)
	
);

SELECT * FROM board_reply;


SELECT * FROM BOARD;

INSERT INTO BOARD (TITLE, CONTENT, WRITER) 
VALUES ('AA','BB',NULL);

DELETE FROM BOARD;


SELECT MEMBER_ID
        FROM BOARD_MEMBER
        WHERE MEMBER_ID='java'
        AND MEMBER_PW='1234';




SELECT NOW();
-- 쿼리 실행시 날짜 시간 나옴


UPDATE board_member
SET
IS_ADMIN = 'Y'
WHERE MEMBER_ID = 'java3';
COMMIT;