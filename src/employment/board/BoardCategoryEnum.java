package src.employment.board;

public enum BoardCategoryEnum {
	// CATEGORY_ID_category_id
	CATEGORY_ID_1(1, 1, "지역별"),
	CATEGORY_ID_2(1, 2, "서울"),
	CATEGORY_ID_3(1, 3, "부산"),
	CATEGORY_ID_4(1, 4, "인천"),
	CATEGORY_ID_5(1, 5, "세종"),
	CATEGORY_ID_6(1, 6, "대전"),
	CATEGORY_ID_7(1, 7, "울산"),
	CATEGORY_ID_8(1, 8, "대구"),
	CATEGORY_ID_9(1, 9, "광주"),
	CATEGORY_ID_10(1, 10, "경기"),
	CATEGORY_ID_11(1, 11, "강원"),
	CATEGORY_ID_12(1, 12, "충북"),
	CATEGORY_ID_13(1, 13, "충남"),
	CATEGORY_ID_14(1, 14, "전북"),
	CATEGORY_ID_15(1, 15, "전남"),
	CATEGORY_ID_16(1, 16, "경북"),
	CATEGORY_ID_17(1, 17, "경남"),
	CATEGORY_ID_18(1, 18, "제주"),
	CATEGORY_ID_19(19, 19, "직무별"),
	CATEGORY_ID_20(19, 20, "개발PM"),
	CATEGORY_ID_21(19, 21, "게임개발"),
	CATEGORY_ID_22(19, 22, "데이터분석가"),
	CATEGORY_ID_23(19, 23, "백엔드"),
	CATEGORY_ID_24(19, 24, "프론트엔드"),
	CATEGORY_ID_25(19, 25, "정보보안"),
	CATEGORY_ID_26(19, 26, "앱개발"),
	CATEGORY_ID_27(19, 27, "풀스택");
	
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
