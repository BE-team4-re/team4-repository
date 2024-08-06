package src.employment.board;

public class BoardDTO {
	private int employmentBoardId;
	private String title;
	private String jobType;
	private String career;
	private String hiringProcess;
	private String qualifications;
	private String preferred;
	private int mainCategory1Id;
	private int mainCategory2Id;
	private int subCategory1Id;
	private int subCategory2Id;
	private String companyName;
	private String localName;
	private String jobName;

	public int getEmploymentBoardId() {
		return employmentBoardId;
	}
	public String getTitle() {
		return title;
	}
	public String getJobType() {
		return jobType;
	}
	public String getCareer() {
		return career;
	}
	public String getHiringProcess() {
		return hiringProcess;
	}
	public String getQualifications() {
		return qualifications;
	}
	public String getPreferred() {
		return preferred;
	}
	public int getMainCategory1Id() {
		return mainCategory1Id;
	}
	public int getSubCategory1Id() {
		return subCategory1Id;
	}
	public int getMainCategory2Id() {
		return mainCategory2Id;
	}
	public int getSubCategory2Id() {
		return subCategory2Id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getLocalName() {return  localName;}
	public String getJobName() {return jobName;}

	public void setEmploymentBoardId(int employmentBoardId) {
		this.employmentBoardId = employmentBoardId;
	}

	public void setTitle(String title) {
		if (title != null) {
			this.title = title;
		}
	}

	public void setJobType(String jobType) {
		if (jobType != null) {
			this.jobType = jobType;
		}
	}

	public void setCareer(String career) {
		if (career != null) {
			this.career = career;
		}
	}

	public void setHiringProcess(String hiringProcess) {
		if (hiringProcess != null) {
			this.hiringProcess = hiringProcess;
		}
	}

	public void setQualifications(String qualifications) {
		if (qualifications != null) {
			this.qualifications = qualifications;
		}
	}

	public void setPreferred(String preferred) {
		if (preferred != null) {
			this.preferred = preferred;
		}
	}

	public void setMainCategory1Id(int mainCategory1Id) {
		if (mainCategory1Id != 0) {
			this.mainCategory1Id = mainCategory1Id;
		}
	}

	public void setSubCategory1Id(int subCategory1Id) {
		if (subCategory1Id != 0) {
			this.subCategory1Id = subCategory1Id;
		}
	}

	public void setMainCategory2Id(int mainCategory2Id) {
		if (mainCategory2Id != 0) {
			this.mainCategory2Id = mainCategory2Id;
		}
	}

	public void setSubCategory2Id(int subCategory2Id) {
		if (subCategory2Id != 0) {
			this.subCategory2Id = subCategory2Id;
		}
	}

	public void setCompanyName(String companyName) {
		if (companyName != null) {
			this.companyName = companyName;
		}
	}

	// 생성자에서 모든 값들을 초기화.
	public BoardDTO(int employmentBoardId, String title, String jobType, String career,
					String hiringProcess, String qualifications, String preferred, int mainCategory1Id,
					int mainCategory2Id, int subCategory1Id, int subCategory2Id, String companyName) {
		this.employmentBoardId = employmentBoardId;
		this.title = title;
		this.jobType = jobType;
		this.career = career;
		this.hiringProcess = hiringProcess;
		this.qualifications = qualifications;
		this.preferred = preferred;
		this.mainCategory1Id = mainCategory1Id;
		this.mainCategory2Id = mainCategory2Id;
		this.subCategory1Id = subCategory1Id;
		this.subCategory2Id = subCategory2Id;
		this.companyName = companyName;
	}

	public BoardDTO(int employmentBoardId, String title, String jobType, String career,
					String hiringProcess, String qualifications, String preferred, int mainCategory1Id,
					int mainCategory2Id, int subCategory1Id, int subCategory2Id, String companyName, String localName, String jobName) {
		this.employmentBoardId = employmentBoardId;
		this.title = title;
		this.jobType = jobType;
		this.career = career;
		this.hiringProcess = hiringProcess;
		this.qualifications = qualifications;
		this.preferred = preferred;
		this.mainCategory1Id = mainCategory1Id;
		this.mainCategory2Id = mainCategory2Id;
		this.subCategory1Id = subCategory1Id;
		this.subCategory2Id = subCategory2Id;
		this.companyName = companyName;
		this.localName = localName;
		this.jobName = jobName;
	}
	
}
