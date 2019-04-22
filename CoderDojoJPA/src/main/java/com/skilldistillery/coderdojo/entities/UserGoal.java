package com.skilldistillery.coderdojo.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_goal")
public class UserGoal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean completed;
	
	@Column(name="completed_date")
	private Date completedDate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_achievement_id")
	private UserAchievement userAchievement;
	
	@ManyToOne
	@JoinColumn(name="goal_id")
	private Goal goal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public UserAchievement getUserAchievement() {
		return userAchievement;
	}

	public void setUserAchievement(UserAchievement userAchievement) {
		this.userAchievement = userAchievement;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserGoal other = (UserGoal) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserGoal [id=" + id + ", completed=" + completed + ", completedDate=" + completedDate
				+ ", userAchievement=" + userAchievement + ", goal=" + goal + "]";
	}

	public UserGoal(int id, boolean completed, Date completedDate, UserAchievement userAchievement, Goal goal) {
		super();
		this.id = id;
		this.completed = completed;
		this.completedDate = completedDate;
		this.userAchievement = userAchievement;
		this.goal = goal;
	}

	public UserGoal() {
		super();
	}
	
	
	

}
