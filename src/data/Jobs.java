package data;

import java.util.List;

public class Jobs {
	List<Job> jobList;

	
	
	
	public Jobs() {
		super();
	}




	public Jobs(List<Job> jobList) {
		super();
		this.jobList = jobList;
	}




	public List<Job> getJobList() {
		return jobList;
	}




	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}




	@Override
	public String toString() {
		return "Jobs [jobList=" + jobList + "]";
	}
	
	
	
}
