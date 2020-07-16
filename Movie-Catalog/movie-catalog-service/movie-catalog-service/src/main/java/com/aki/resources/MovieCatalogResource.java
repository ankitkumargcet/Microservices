package com.aki.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aki.models.CatalogItems;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@RequestMapping("/{userId}")
	List<CatalogItems> getCatalog(@PathVariable("userId") String userId) {
		return Collections.singletonList(new CatalogItems("Transformers", "Hollywood Movie", 4));
	}
}
