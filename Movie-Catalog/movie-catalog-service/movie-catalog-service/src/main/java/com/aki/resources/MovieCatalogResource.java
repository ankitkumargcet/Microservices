package com.aki.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.aki.models.CatalogItems;
import com.aki.models.Movie;
import com.aki.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	List<CatalogItems> getCatalog(@PathVariable("userId") String userId) {
		
		// Get all rated movie ID's
		List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new Rating("5678", 3));
		
		return ratings.stream().map(rating->{
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItems(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
		})
		.collect(Collectors.toList());

		// For each movie ID, call movie info service and get details

		// Put them all together

	}
}
