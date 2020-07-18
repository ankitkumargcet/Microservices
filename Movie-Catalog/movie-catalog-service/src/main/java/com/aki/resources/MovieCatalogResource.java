package com.aki.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.aki.models.CatalogItems;
import com.aki.models.Movie;
import com.aki.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/v1/{userId}")
	List<CatalogItems> getCatalogV1(@PathVariable("userId") String userId) {

		// Get all rated movie ID's
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);

		return ratings.getUserRating().stream().map(rating -> {
			// For each movie ID, call movie info service and get details
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

			// Put them all together
			return new CatalogItems(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
		}).collect(Collectors.toList());

	}

	@RequestMapping("/v2/{userId}")
	List<CatalogItems> getCatalogV2(@PathVariable("userId") String userId) {

		// Get all rated movie ID's
		UserRating ratings = webClientBuilder.build()
								.get()
								.uri("http://localhost:8083/ratingsdata/users/" + userId)
								.retrieve()
								.bodyToMono(UserRating.class)
								.block();

		return ratings.getUserRating().stream().map(rating -> {
			Movie movie = webClientBuilder.build()
								.get()
								.uri("http://localhost:8082/movies/" + rating.getMovieId())
								.retrieve()
								.bodyToMono(Movie.class)
								.block();
			return new CatalogItems(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
		}).collect(Collectors.toList());

	}
}
