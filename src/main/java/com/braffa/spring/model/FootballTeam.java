package com.braffa.spring.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class FootballTeam implements Serializable {

	private static final long serialVersionUID = -7788619177798333712L;

	private String name;
	private String city;
	private String owner;
	private int stadiumCapacity;
	private String competition;
	private int numberOfPlayers;
	private Date createdDate;
	
	public FootballTeam() {
		
	}

	public FootballTeam(String name, String city, String owner, int stadiumCapacity, String competition,
			int numberOfPlayers, Date createdDate) {
		this.name = name;
		this.city = city;
		this.owner = owner;
		this.stadiumCapacity = stadiumCapacity;
		this.competition = competition;
		this.numberOfPlayers = numberOfPlayers;
		this.createdDate = createdDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getStadiumCapacity() {
		return stadiumCapacity;
	}

	public void setStadiumCapacity(int stadiumCapacity) {
		this.stadiumCapacity = stadiumCapacity;
	}

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "FootballTeam [name=" + name + ", city=" + city + ", owner=" + owner
				+ ", stadiumCapacity=" + stadiumCapacity + ", competition=" + competition + ", numberOfPlayers="
				+ numberOfPlayers + ", createdDate=" + createdDate + "]";
	}

}
