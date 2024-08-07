package src.employment.board;
/*
	* InsertBoardCategory 에서는 초기화를 위해서 Enum이 사용됩니다.
	* 위의 경우 외에는 사용하지 말 것.
	* 대신, 변환이 필요한 경우 아래 두 개의 메서드를 사용하세요.
	* src.employment.elements.content.PrintBoard.convertCategoryIdToCategoryName -> 정수형 아이디 to 문자열 이름.
	* src.employment.elements.content.PrintBoard.convertCategoryNameToCategoryId -> 문자열 이름 to 정수형 아이디.
*/
public enum BoardCategoryEnum {
	// CATEGORY_ID_category_id
	CATEGORY_ID_1(1, 1, "서울"),
	CATEGORY_ID_2(1, 2, "부산"),
	CATEGORY_ID_3(1, 3, "인천"),
	CATEGORY_ID_4(1, 4, "세종"),
	CATEGORY_ID_5(1, 5, "대전"),
	CATEGORY_ID_6(1, 6, "울산"),
	CATEGORY_ID_7(1, 7, "대구"),
	CATEGORY_ID_8(1, 8, "광주"),
	CATEGORY_ID_9(1, 9, "경기"),
	CATEGORY_ID_10(1, 10, "강원"),
	CATEGORY_ID_11(1, 11, "충북"),
	CATEGORY_ID_12(1, 12, "충남"),
	CATEGORY_ID_13(1, 13, "전북"),
	CATEGORY_ID_14(1, 14, "전남"),
	CATEGORY_ID_15(1, 15, "경북"),
	CATEGORY_ID_16(1, 16, "경남"),
	CATEGORY_ID_17(1, 17, "제주"),
	CATEGORY_ID_18(2, 18, "인공지능"),
	CATEGORY_ID_19(2, 19, "게임개발"),
	CATEGORY_ID_20(2, 20, "데이터분석가"),
	CATEGORY_ID_21(2, 21, "백엔드"),
	CATEGORY_ID_22(2, 22, "프론트엔드"),
	CATEGORY_ID_23(2, 23, "정보보안"),
	CATEGORY_ID_24(2, 24, "앱개발"),
	CATEGORY_ID_25(2, 25, "풀스택"),
	CATEGORY_ID_26(2, 26, "임베디드"),
	CATEGORY_ID_27(2, 27, "devops"),
	CATEGORY_ID_28(2, 28, "클라우드"),
	CATEGORY_ID_29(2, 29, "네트워크");

	private final int mainId;
	private final int subId;
	private final String categoryName;
	
	private BoardCategoryEnum(int mainId, int subId, String categoryName) {
		this.mainId = mainId;
		this.subId = subId;
		this.categoryName = categoryName;
	}

	public int getMainId() {
		return mainId;
	}
	
	public int getSubId() {
		return subId;
	}

	public String getCategoryName() {
		return categoryName;
	}
	
}
