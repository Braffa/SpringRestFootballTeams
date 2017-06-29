package com.braffa.spring.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.braffa.spring.model.FootballTeam;

@Controller
public class FootballTeamController {

	private static final Logger logger = LoggerFactory.getLogger(FootballTeamController.class);

	// Map to store football teams, ideally we should use database
	Map<String, FootballTeam> footballTeams = new HashMap<String, FootballTeam>();

	@RequestMapping(value = FootballTeamRestURIConstriants.CREATE_FOOTBALL_TEAM, method = RequestMethod.POST)
	public @ResponseBody FootballTeam createFootballTeam(@RequestBody FootballTeam team) {
		logger.debug("createFootballTeam.");
		team.setCreatedDate(new Date());
		footballTeams.put(team.getName(), team);
		return team;
	}

	@RequestMapping(value = FootballTeamRestURIConstriants.GET_FOOTBALLTEAM, method = RequestMethod.GET)
	public @ResponseBody FootballTeam getFootballTeam(@PathVariable("name") String name) {
		logger.debug("getFootballTeam. name = " + name);
		return footballTeams.get(name);
	}

	@RequestMapping(value = FootballTeamRestURIConstriants.DELETE_FOOTBALL_TEAMS, method = RequestMethod.GET)
	public @ResponseBody String deleteFootballTeams() {
		logger.debug("deleteFootballTeams.");
		footballTeams = new HashMap<String, FootballTeam>();
		return "All football teams deleted";
	}

	@RequestMapping(value = FootballTeamRestURIConstriants.GET_ALL_FOOTBALL_TEAMS, method = RequestMethod.GET)
	public @ResponseBody List<FootballTeam> getAllFootballTeams() {
		logger.debug("getAllFootballTeams.");
		List<FootballTeam> LOffootballTeams = getfootballTeams(); 
		return LOffootballTeams;
	}

	@RequestMapping(value = FootballTeamRestURIConstriants.GET_ALL_FOOTBALL_TEAMS_SORTED, method = RequestMethod.GET)
	public @ResponseBody List<FootballTeam> getAllFootballTeamsSorted() {
		logger.debug("getAllFootballTeamsByStadiumCapacity.");
		List<FootballTeam> LOffootballTeams = getfootballTeams(); 
		LOffootballTeams.sort(Comparator.comparing(FootballTeam::getStadiumCapacity));
		return LOffootballTeams;
	}

	private List<FootballTeam> getfootballTeams() {
		logger.debug("getfootballTeams.");
		List<FootballTeam> LOffootballTeams = new ArrayList<FootballTeam>();
		Set<String> key = footballTeams.keySet();
		for (String name : key) {
			LOffootballTeams.add(footballTeams.get(name));
		}
		return LOffootballTeams;
	}
}
