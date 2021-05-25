package com.iktakademija.Dan0103.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyControlers {

	// http://localhost:8090/

	@RequestMapping("/hello")
	public String returnHello() {
		return "Hello world";
	}

	@RequestMapping("/greetings")
	public String returnGreetings() {
		return "Hello Man!";
	}

	@RequestMapping("/date")
	public LocalDate returnDate() {	    
		LocalDate date = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		date.format(dtf);
		return date;
	}

	@RequestMapping("/family")
	public List<String> returnList() {
		List<String> list = new ArrayList<>();
		list.add("Item01");
		list.add("Item02");
		list.add("nItem03");
		return list;
	}

	@RequestMapping("/myclass")
	public String returnHtml() {
		// HTML text is plane String
		return "<html>"
		+ "<h1>Heading</h1><p>Paragraf text</p><tr>" 
		+ "<table>"
		+   "<thead>"
		+     "<tr>"
		+       "<th>Header</th>"
		+       "<th>Header</th>"
		+     "</tr>"
		+   "</thead>"
		+   "<tbody>"
		+     "<tr>"
		+       "<td>Petar</td>"
		+       "<td>Iva</td>"
		+     "</tr>"
		+     "<tr>"
		+       "<td>Petrovic</td>"
		+       "<td>Ivic</td>"
		+     "</tr>"
		+   "</tbody>"
		+ "</table>"
		+ "</html>";
	}

	@RequestMapping("/array")
	public Integer[] returnArray() {
		Integer[] array = { 5, 6, 7 };
		return array;
	}

	@RequestMapping("/sortarray")
	public Integer[] returnSortedArray() {
		Integer[] array = { 5, 6, 7 };
		Arrays.sort(array);
		return array;
	}

	@RequestMapping("/minmax")
	public String returnMInMax() {
		Integer[] broj = { 5, 6, 7, 8, 6, 7, 5, 1, 6, 5 };
		Integer min, max;

		min = broj[0]; max = broj[0];
		for (Integer i = 1; i < broj.length; i++) {
			if (min > broj[i]) min = broj[i];
			if (max < broj[i]) max = broj[i];
		}
		System.out.println("LOG: Result displeyed to client.");
		return String.format("%d-%d", min, max);
	}
	
	@RequestMapping("/sumaNiza")
	public String returnSumaNiza() {
		Integer[] array = { 5, 6, 7, 8, 6, 7, 5, 1, 6, 5 };
		Integer sum = 0;

		for (Integer i = 0; i < array.length; i++) {
			sum = sum + array[i];
		}
		System.out.println("LOG: Array calculated");
		return String.format("Suma: ", sum);
	}
	
	@RequestMapping(value = "/recnik/{input}", method = RequestMethod.GET)
	public String returnRecnik(@PathVariable("input") String rec) {
		switch (rec) {
		case "Back":
			return "Iza";			
		case "Front":
			return "Ispred";			
		default:
			return String.format("Rec %s nepostoji u recniku", rec);
		}		
	}
	
}
