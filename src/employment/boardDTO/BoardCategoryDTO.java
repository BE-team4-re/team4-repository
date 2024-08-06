package src.employment.boardDTO;

public class BoardCategoryDTO {
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

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setMainCategoryId(int mainCategoryId) {
		this.mainCategoryId = mainCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public BoardCategoryDTO(int categoryId, int mainCategoryId, int subCategoryId, String categoryName) {
		this.categoryId = categoryId;
		this.mainCategoryId = mainCategoryId;
		this.subCategoryId = subCategoryId;
		this.categoryName = categoryName;
	}
}
