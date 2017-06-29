package com.braffa.spring;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.braffa.spring.controller.FootballTeamRestURIConstriants;
import com.braffa.spring.model.FootballTeam;

public class TestFootballTeamController {

	public static final String SERVER_URI = "http://localhost:8080/SpringRestFootballTeams";

	@Before
	public void init() {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getForObject(SERVER_URI + FootballTeamRestURIConstriants.DELETE_FOOTBALL_TEAMS, String.class);

		FootballTeam team = new FootballTeam("Manchester United", "Manchester", "Jon Smith", 25000, "FA Cup", 35,
				new Date());
		FootballTeam response = restTemplate.postForObject(
				SERVER_URI + FootballTeamRestURIConstriants.CREATE_FOOTBALL_TEAM, team, FootballTeam.class);

		team = new FootballTeam("Chelsea", "London", "Steve Ssmith", 50000, "FA Cup", 55, new Date());
		response = restTemplate.postForObject(SERVER_URI + FootballTeamRestURIConstriants.CREATE_FOOTBALL_TEAM, team,
				FootballTeam.class);

		team = new FootballTeam("Middlesbrough", "Middlesbrough", "Mickey Mouse", 10000, "FA Cup", 65, new Date());
		response = restTemplate.postForObject(SERVER_URI + FootballTeamRestURIConstriants.CREATE_FOOTBALL_TEAM, team,
				FootballTeam.class);

		team = new FootballTeam("Everton", "Liverpool", "Mickey Mouse", 35000, "FA Cup", 25, new Date());
		response = restTemplate.postForObject(SERVER_URI + FootballTeamRestURIConstriants.CREATE_FOOTBALL_TEAM, team,
				FootballTeam.class);
	}

	@Test
	public void deleteTest() {
		RestTemplate restTemplate = new RestTemplate();
		String st = restTemplate.getForObject(SERVER_URI + FootballTeamRestURIConstriants.DELETE_FOOTBALL_TEAMS,
				String.class);
		assertEquals("All football teams deleted", st);
	}

	@Test
	public void createTest() {
		FootballTeam team = new FootballTeam("Manchester City", "Manchester", "Joe Brown", 75000, "FA Cup", 55,
				new Date());
		RestTemplate restTemplate = new RestTemplate();
		FootballTeam response = restTemplate.postForObject(
				SERVER_URI + FootballTeamRestURIConstriants.CREATE_FOOTBALL_TEAM, team, FootballTeam.class);
		FootballTeam footballTeam = (FootballTeam) response;
		assertEquals("Manchester City", footballTeam.getName());
		assertEquals("Manchester", footballTeam.getCity());
		assertEquals("Joe Brown", footballTeam.getOwner());
		assertEquals(75000, footballTeam.getStadiumCapacity());
		assertEquals("FA Cup", footballTeam.getCompetition());
		assertEquals(55, footballTeam.getNumberOfPlayers());
		assertTrue(footballTeam.getCreatedDate() instanceof Date);
	}

	@Test
	public void testGetAll() {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> footballTeams = restTemplate
				.getForObject(SERVER_URI + FootballTeamRestURIConstriants.GET_ALL_FOOTBALL_TEAMS, List.class);
		assertEquals(4, footballTeams.size());
	}

	@Test
	public void testGetByName() {
		RestTemplate restTemplate = new RestTemplate();
		FootballTeam footballTeam = restTemplate.getForObject(SERVER_URI + "/rest/footballTeam/Manchester United",
				FootballTeam.class);
		assertEquals("Manchester United", footballTeam.getName());
		assertEquals("Manchester", footballTeam.getCity());
		assertEquals("Jon Smith", footballTeam.getOwner());
		assertEquals(25000, footballTeam.getStadiumCapacity());
		assertEquals("FA Cup", footballTeam.getCompetition());
		assertEquals(35, footballTeam.getNumberOfPlayers());
		assertTrue(footballTeam.getCreatedDate() instanceof Date);
	}

	@Test
	public void testGetAllSorted() {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> footballTeams = restTemplate.getForObject(
				SERVER_URI + FootballTeamRestURIConstriants.GET_ALL_FOOTBALL_TEAMS_SORTED,
				List.class);
		
		LinkedHashMap footballTeam = footballTeams.get(0);
		assertEquals(10000, footballTeam.get("stadiumCapacity"));
		
		footballTeam = footballTeams.get(1);
		assertEquals(25000, footballTeam.get("stadiumCapacity"));
		
		footballTeam = footballTeams.get(2);
		assertEquals(35000, footballTeam.get("stadiumCapacity"));
		
		footballTeam = footballTeams.get(3);
		assertEquals(50000, footballTeam.get("stadiumCapacity"));
	}
}
