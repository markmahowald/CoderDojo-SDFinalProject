package com.skilldistillery.coderdojo.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Meeting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time")
	private Date scheduledTime;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	@OneToMany(mappedBy  = "meeting")
	private List<MeetingAttendee> meetingAttendees;

	public List<MeetingAttendee> getMeetingAttendees() {
		return meetingAttendees;
	}

	public void setMeetingAttendees(List<MeetingAttendee> users) {
		this.meetingAttendees = users;
	}
	
	public void addMeetingAttendees(MeetingAttendee user) {
		if (user == null)
			return;
		if (meetingAttendees == null)
			meetingAttendees = new ArrayList<>();
		
		meetingAttendees.add(user);
	}
	
	public void removeMeetingAttendees(MeetingAttendee user) {
		if (user == null)
			return;
		meetingAttendees.remove(user);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Meeting() {
	}

	@Override
	public String toString() {
		return "Meeting [id=" + id + ", name=" + name + ", scheduledTime=" + scheduledTime + "]";
	}

	public Meeting(int id, String name, Date scheduledTime, Location location) {
		this.id = id;
		this.name = name;
		this.scheduledTime = scheduledTime;
		this.location = location;
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
		Meeting other = (Meeting) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
