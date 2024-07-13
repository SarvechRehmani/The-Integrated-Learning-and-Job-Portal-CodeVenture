package com.codeventure.entities.job;

import java.util.Date;
import java.util.List;

import com.codeventure.entities.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Job {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jId;
    private String jTitle;
    @Column(length = 1500)
    private String jDescription;

    private String jType;
    private String jLocation;
    private Date jDeadline;
    private String jExperience;
    private String jEducation;
    private String jSkills;
    private String jCompanyName;
    @Column(length = 1500)
    private String jCompanyDes;
    private String jCompanyContact;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "job", orphanRemoval = true)
	@JsonIgnore
	private List<JobsApplications> jobsApplications;

	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Job(long jId, String jTitle, String jDescription, String jType, String jLocation, Date jDeadline, String jExperience, String jEducation, String jSkills, String jCompanyName, String jCompanyDes, String jCompanyContact, User user, List<JobsApplications> jobsApplications) {
		this.jId = jId;
		this.jTitle = jTitle;
		this.jDescription = jDescription;
		this.jType = jType;
		this.jLocation = jLocation;
		this.jDeadline = jDeadline;
		this.jExperience = jExperience;
		this.jEducation = jEducation;
		this.jSkills = jSkills;
		this.jCompanyName = jCompanyName;
		this.jCompanyDes = jCompanyDes;
		this.jCompanyContact = jCompanyContact;
		this.user = user;
		this.jobsApplications = jobsApplications;
	}

	public long getjId() {
		return jId;
	}


	public void setjId(long jId) {
		this.jId = jId;
	}


	public String getjTitle() {
		return jTitle;
	}


	public void setjTitle(String jTitle) {
		this.jTitle = jTitle;
	}


	public String getjDescription() {
		return jDescription;
	}


	public void setjDescription(String jDescription) {
		this.jDescription = jDescription;
	}


	public String getjType() {
		return jType;
	}


	public void setjType(String jType) {
		this.jType = jType;
	}


	public String getjLocation() {
		return jLocation;
	}


	public void setjLocation(String jLocation) {
		this.jLocation = jLocation;
	}


	public Date getjDeadline() {
		return jDeadline;
	}


	public void setjDeadline(Date jDeadline) {
		this.jDeadline = jDeadline;
	}


	public String getjExperience() {
		return jExperience;
	}


	public void setjExperience(String jExperience) {
		this.jExperience = jExperience;
	}


	public String getjEducation() {
		return jEducation;
	}


	public void setjEducation(String jEducation) {
		this.jEducation = jEducation;
	}


	public String getjSkills() {
		return jSkills;
	}


	public void setjSkills(String jSkills) {
		this.jSkills = jSkills;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public String getjCompanyName() {
		return jCompanyName;
	}


	public void setjCompanyName(String jCompanyName) {
		this.jCompanyName = jCompanyName;
	}


	public String getjCompanyDes() {
		return jCompanyDes;
	}


	public void setjCompanyDes(String jCompanyDes) {
		this.jCompanyDes = jCompanyDes;
	}


	public String getjCompanyContact() {
		return jCompanyContact;
	}


	public void setjCompanyContact(String jCompanyContact) {
		this.jCompanyContact = jCompanyContact;
	}


	public List<JobsApplications> getJobsApplications() {
		return jobsApplications;
	}

	public void setJobsApplications(List<JobsApplications> jobsApplications) {
		this.jobsApplications = jobsApplications;
	}

	@Override
	public String toString() {
		return "Job{" +
				"jId=" + jId +
				", jTitle='" + jTitle + '\'' +
				", jDescription='" + jDescription + '\'' +
				", jType='" + jType + '\'' +
				", jLocation='" + jLocation + '\'' +
				", jDeadline=" + jDeadline +
				", jExperience='" + jExperience + '\'' +
				", jEducation='" + jEducation + '\'' +
				", jSkills='" + jSkills + '\'' +
				", jCompanyName='" + jCompanyName + '\'' +
				", jCompanyDes='" + jCompanyDes + '\'' +
				", jCompanyContact='" + jCompanyContact + '\'' +
				", user=" + user +
				", jobsApplications=" + jobsApplications +
				'}';
	}
}
