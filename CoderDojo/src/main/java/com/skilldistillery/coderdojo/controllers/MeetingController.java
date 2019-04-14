package com.skilldistillery.coderdojo.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.coderdojo.entities.Meeting;
import com.skilldistillery.coderdojo.entities.MeetingAttendee;
import com.skilldistillery.coderdojo.entities.User;
import com.skilldistillery.coderdojo.services.MeetingAttendeeService;
import com.skilldistillery.coderdojo.services.MeetingService;
import com.skilldistillery.coderdojo.services.UserService;

@RestController
@CrossOrigin({ "*", "http://localhost:4202" })
@RequestMapping("api")
public class MeetingController {
	
	@Autowired
	private MeetingService service;
	
	@Autowired
	private MeetingAttendeeService maservice;
	
	
	@Autowired
	private UserService uservice;

	//  GET Meetings
	@GetMapping("meetings")
	public Set<Meeting> index(HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		return service.findAllMeetings(principal.getName());
	}
		
	//  GET Meetings
	@GetMapping("schedule")
	public Set<Meeting> index(HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req);
		return service.findAllMeetings();
	}

	@GetMapping("meetings/{mid}")
	public Meeting show(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("mid") Integer mid,
			Principal principal) {
		try {
			Meeting meeting = service.show(principal.getName(),mid);
			if (meeting == null) {
				res.setStatus(404);
			} else {
				StringBuffer url = req.getRequestURL();
				url.append("/");
				url.append(mid);
				res.setHeader("Location", url.toString());

				res.setStatus(201);
			}

			return meeting;
		} catch (Exception e) {
			res.setStatus(500);
			return null;
		}
	}

	@PostMapping("meetings")
	public Meeting create(HttpServletRequest req, HttpServletResponse res, 
			@RequestBody Meeting meeting,
			Principal principal) {
		try {
			service.create(principal.getName(),meeting);
			StringBuffer url = req.getRequestURL();
			System.out.println("PostController" + url.toString());
			url.append("/");
			url.append(meeting.getId());
			res.setHeader("Location", url.toString());
			res.setStatus(201);
			return meeting;
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			return null;
		}
	}

	@PutMapping("meetings/{mid}") 
	public Meeting update(
			HttpServletRequest req, 
			HttpServletResponse res,
			@PathVariable("mid") Integer mid,
			@RequestBody Meeting meeting,
			Principal principal) {
			meeting = service.update(principal.getName(),mid, meeting);
	        if (meeting == null) {
	            res.setStatus(404);
	        }
	        return meeting;
		
	}

	@DeleteMapping("meetings/{mid}")
	public Boolean destroy(
			HttpServletRequest req,
			HttpServletResponse res, 
			@PathVariable("mid") Integer mid,
			Principal principal) {
		System.out.println(mid);
		try {
			if (service.show(principal.getName(), mid) == null) {
				res.setStatus(404);
				return false;
			} else {
				service.destroy(principal.getName(), mid);
				res.setStatus(204);
				return true;
			}

		} catch (Exception e) {
			res.setStatus(409);
			return false;
		}
		
	}
	
	
	@PutMapping("meetings/{mid}/attendee/{aid}") 
	public MeetingAttendee updateAttendee(
			HttpServletRequest req, 
			HttpServletResponse res,
			@PathVariable("mid") Integer mid,
			@PathVariable("aid") Integer aid,
			@RequestBody MeetingAttendee ma,
			Principal principal) {
			MeetingAttendee meetingAttendee = maservice.showMAById(principal.getName(),aid);
			User user = uservice.findByUsername(principal.getName());
			Meeting meeting = service.show(principal.getName(), mid);
			if(meeting != null && user !=null) {
				meetingAttendee = maservice.update(principal.getName(), meeting, ma);
			}
			
	        if (meeting == null) {
	            res.setStatus(404);
	        }
	        return meetingAttendee;
		
	}
	

}
