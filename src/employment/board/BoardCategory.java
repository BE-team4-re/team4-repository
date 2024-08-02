package src.employment.board;

public class BoardCategory {
	private int categoryId;
	private int mainCategoryId;
	private int subCategoryId;
	private String categoryName;

	public int getCategoryId() {
		return categoryId;
	}

	public int getMainCategoryId() {
		return mainCategoryId;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	// 생성자에서 모든 값을 set 하고, setter 는 사용하지 않음. 
	public BoardCategory(int categoryId, int mainCategoryId, int subCategoryId, String categoryName) {
		this.categoryId = categoryId;
		this.mainCategoryId = mainCategoryId;
		this.subCategoryId = subCategoryId;
		this.categoryName = categoryName;
	}

}
