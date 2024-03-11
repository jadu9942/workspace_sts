-- 조건문 IF 
--        CASE 

SELECT ITEM_CODE
	, ITEM_NAME
	, ITEM_STOCK 
	, ITEM_STATUS
	, IF(ITEM_STATUS=1,'준비중', IF(ITEM_STATUS=2,'판매중','매진')) AS '상태1'
	, CASE 
	   WHEN 조건 THEN 값 
		WHEN 조건 THEN 값 
		ELSE 값 
		END  