package module.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.internal.GenreDTO;
import lombok.RequiredArgsConstructor;
import module.service.GenreService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/genres")
public class GenreController {

	private final GenreService genreService;

	@GetMapping("/all")
	public ResponseEntity<List<GenreDTO>> getGenreList(){
		List<GenreDTO> allGenre = genreService.getAllGenre();
		return ResponseEntity.ok(allGenre);
	}
}
