CREATE TABLE MEMBER(
 MEMBER_ID VARCHAR(20) PRIMARY KEY
 , MEMBER_PW VARCHAR(100) NOT NULL
 , MEMBER_NAME VARCHAR(20) NOT NULL
 , MEMBER_BIRTH VARCHAR(20) NOT NUll
 , GENDER VARCHAR(10) NOT NULL
 , MEMBER_EMAIL VARCHAR(50) NOT NULL UNIQUE
 , MEMBER_TEL VARCHAR(20) NOT NULL
 , MEMBER_POST VARCHAR(10)
 , MEMBER_ADDR VARCHAR(50) 
 , ADDR_DETAIL VARCHAR(50)
 , JOIN_DATE DATETIME DEFAULT CURRENT_TIMESTAMP
 , MEMBER_ROLL VARCHAR(20) DEFAULT 'USER' 
 
);
DROP TABLE member; 


-- 기본키 : 키본키 컬럼 값만 알면 수많은 행 중에서도 하나의 행을 찾을 수 있는 해주는 성질
-- 외래키
CREATE TABLE ITEM_INFO(
	ITEM_CODE INT AUTO_INCREMENT PRIMARY KEY
	, ITEM_TITLE VARCHAR(50) NOT NULL
	, ITEM_CONTENT VARCHAR(500) NOT NULL
	, ITEM_PRICE INT DEFAULT 0
	, ITEM_DATE DATETIME DEFAULT CURRENT_TIMESTAMP
	, ITEM_PEOPLE INT DEFAULT 0
	, PEOPLE_CNT INT DEFAULT 0
	, ITEM_PLACE VARCHAR(100) NOT NULL
	, PLACE_DETAIL VARCHAR(100) NOT NULL
	, ITEM_START_DATE DATETIME NOT NULL
	, ITEM_END_DATE DATETIME	
	, ITEM_TEL VARCHAR(20)
	, ITEM_URL VARCHAR(500)
	, ITEM_ROAD_PLACE VARCHAR(100)
	, ITEM_NUM_PLACE VARCHAR(100)
	, ITEM_X DOUBLE
	, ITEM_Y DOUBLE
	, CATE_CODE INT
);

ALTER TABLE item_info ADD COLUMN READ_CNT INT DEFAULT 0;



DROP TABLE item_info;

CREATE TABLE ITEM_WISH(
	WISH_CODE INT AUTO_INCREMENT PRIMARY KEY
	, ITEM_CODE INT NOT NULL REFERENCES ITEM_INFO (ITEM_CODE)
	, MEMBER_ID VARCHAR(20) NOT NULL REFERENCES MEMBER (MEMBER_ID)
	, WISH_DATE DATETIME DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE item_wish;

CREATE OR REPLACE VIEW WISH_VIEW
AS
SELECT WISH_CODE
	,WISH_DATE
	,ITEM.ITEM_CODE
	,ITEM_TITLE
	,ITEM_CONTENT
	,ITEM_PRICE
	,ITEM_DATE
	,ITEM_PEOPLE
	,PEOPLE_CNT
	,ITEM_PLACE
	,PLACE_DETAIL
   ,CATE_CODE
	
	,M.MEMBER_ID
	,MEMBER_NAME
	,CONCAT(MEMBER_ADDR ,' ', ADDR_DETAIL) ADDRESS
	,MEMBER_BIRTH
	,GENDER
	,MEMBER_EMAIL
	,MEMBER_TEL
	,JOIN_DATE
FROM item_wish WISH
JOIN ITEM_INFO ITEM
ON WISH.ITEM_CODE = ITEM.ITEM_CODE
 JOIN member M
ON M.MEMBER_ID = WISH.MEMBER_ID;


CREATE TABLE RESERVE(
	RESERVE_CODE INT AUTO_INCREMENT PRIMARY KEY
	, MEMBER_ID VARCHAR(20) NOT NULL REFERENCES member(MEMBER_ID)
	, RESERVE_DATE DATETIME DEFAULT CURRENT_TIMESTAMP 
	, ITEM_CODE INT NOT NULL REFERENCES ITEM_INFO (ITEM_CODE)
	);
	
DROP TABLE reserve;
SELECT * FROM reserve;

CREATE TABLE item_image(
	IMG_CODE INT AUTO_INCREMENT PRIMARY KEY
	, ORIGIN_FILE_NAME VARCHAR(100) NOT NULL
	, ATTACHED_FILE_NAME VARCHAR(100) NOT NULL
	, IS_MAIN VARCHAR(2) NOT NULL -- 'Y','N'
	, ITEM_CODE INT NOT NULL REFERENCES ITEM_INFO(ITEM_CODE)
);

CREATE TABLE MEMBER_IMAGE(
	MEMBER_IMG_CODE INT AUTO_INCREMENT PRIMARY KEY
	, ORIGIN_FILE_NAME VARCHAR(100) NOT NULL
	, ATTACHED_FILE_NAME VARCHAR(100) NOT NULL
	, MEMBER_ID VARCHAR(100) NOT NULL REFERENCES member(MEMBER_ID)
);


SELECT* FROM item_wish;

SELECT *
FROM reserve
WHERE member_id = 'cc'
AND item_code = 1400;



INSERT INTO item_image (
	 IMG_CODE
	, ORIGIN_FILE_NAME
	, ATTACHED_FILE_NAME
	, IS_MAIN
	, ITEM_CODE 
) VALUES 
(1, '강릉축제_01.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc881.jpg','Y',1349 ),
(2, '강릉축제_02.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc882.jpg','N',1349 ),
(3, '전국생활문화축제강릉_01.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc883.jpg','Y',1350),
(4, '전국생활문화축제강릉_02.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc884.jpg','N',1350 ),
(5, '강릉와인_01.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc885.jpg','Y',1351 ),
(6, '강릉와인_02.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc886.jpg','N',1351 ),
(7, '강릉누들_01.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc887.jpg','Y',1352 ),
(8, '강릉누들_02.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc888.jpg','N',1352 ),
(9, '구리코스모스_01.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc889.jpg','Y',1353 ),
(10, '구리코스모스_02.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc810.jpg','N',1353 ),
(11, '구리유채꽃_01.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc811.jpg','Y',1354 ),
(12, '구리유채꽃_02.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc812.jpg','N',1354 ),
(13, '18구리코스모스_01.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc813.jpg','Y',1355 ),
(14, '18구리코스모스_02.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc814.jpg','N',1355 ),
(15, '19구리유채꽃_01.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc815.jpg','Y',1356 ),
(16, '19구리유채꽃_02.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc816.jpg','N',1356 ),
(17, '19구리코스_01.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc817.jpg','Y',1357 ),
(18, '19구리코스_02.jpg','9ad92874-da5f-4a6f-8b35-8804eb3cc818.jpg','N',1357 );



DELETE FROM item_info
WHERE ITEM_CONTENT='';
COMMIT; 

DROP TABLE RESERVE;

SELECT * FROM MEMBER;

SELECT * FROM item_info;

DELETE FROM reserve
WHERE MEMBER_ID='CC';

DROP TABLE item_INFO;

INSERT INTO member()

VALUES();


SELECT  
 RESERVE_CODE
 , RESERVE_DATE
 , RESERVE_PRICE
 ,
FROM reserve
WHERE MEMBER_ID='cc';

-- ---------------------

SELECT 
	RESERVE_CODE
	, RESERVE_DATE
	, MEMBER_ID
	, RESERVE.ITEM_CODE
	, ITEM_PRICE
FROM reserve INNER JOIN item_info
ON reserve.ITEM_CODE = item_info.ITEM_CODE 
WHERE MEMBER_ID='CC';


SELECT 
 ITEM_CODE
 , ITEM_TITLE
 , ITEM_CONTENT
 , ITEM_PRICE
 , ITEM_START_DATE
 , ITEM_PEOPLE
 , ITEM_PLACE
FROM item_info;

CREATE TABLE category(
	
);



SELECT * FROM reserve;

SELECT MEMBER_ID
	, MEMBER_PW
	, MEMBER_NAME
	, MEMBER_BIRTH
	, MEMBER_EMAIL
	, MEMBER_TEL
	, GENDER
	, MEMBER_ADDR 
	, ADDR_DETAIL
FROM member;

UPDATE member SET MEMBER_TEL='011,1111,2222'
WHERE MEMBER_ID='aa';

COMMnollooIT;



UPDATE member SET * 
WHERE MEMBER_ID= 1;

CREATE TABLE ITEM_INFO(
	ITEM_CODE INT AUTO_INCREMENT PRIMARY KEY
	, ITEM_TITLE VARCHAR(300) NOT NULL
	, ITEM_CONTENT VARCHAR(2000) NOT NULL
	, ITEM_PRICE INT DEFAULT 0
	, ITEM_DATE DATETIME DEFAULT CURRENT_TIMESTAMP
	, ITEM_PEOPLE INT DEFAULT 0
	, PEOPLE_CNT INT DEFAULT 0
	, ITEM_PLACE VARCHAR(500) NOT NULL
	, PLACE_DETAIL VARCHAR(500)
	, ITEM_START_DATE DATETIME NOT NULL
	, ITEM_END_DATE DATETIME 
	, ITEM_TEL VARCHAR(100)
	, ITEM_URL VARCHAR(1000)
	, ITEM_ROAD_PLACE VARCHAR(500)
	, ITEM_NUM_PLACE VARCHAR(500)
	, ITEM_X DOUBLE
	, ITEM_Y DOUBLE
	, CATE_CODE INT DEFAULT 1
);