package src.employment.command;

public interface CommandMethod {
	// 뒤로가기
	public abstract void goBack();
	// CRUD
	public abstract void create();
	public abstract void read();
	public abstract void update();
		public abstract void updateTitle();
		public abstract void updateJobType();
		public abstract void updateCareer();
		public abstract void updateHiringProcess();
		public abstract void updateQualifications();
		public abstract void updatePreferred();
		public abstract void updateMainCategory1Id();
		public abstract void updateMainCategory2Id();
		public abstract void updateSubCategory1Id();
		public abstract void updateSubCategory2Id();
	public abstract void delete();
	// 예/아니오
	public abstract void sayYes();
	public abstract void sayNo();
}
