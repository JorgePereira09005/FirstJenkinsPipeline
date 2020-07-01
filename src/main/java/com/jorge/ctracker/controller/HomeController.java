package com.jorge.ctracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jorge.ctracker.models.LocationStats;
import com.jorge.ctracker.services.CoronaVirusDataService;

@Controller
public class HomeController {

	@Autowired
	CoronaVirusDataService coronavirusDataService;
	
	@GetMapping("/")
	public String home(Model theModel) {
		
		List<LocationStats> allStats = coronavirusDataService.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDeltaFromPrevDay()).sum();
		theModel.addAttribute("locationStats", allStats);
		theModel.addAttribute("totalReportedCases", totalReportedCases);
		theModel.addAttribute("totalNewCases", totalNewCases);
		
		return "home";
	}
	
	
}
