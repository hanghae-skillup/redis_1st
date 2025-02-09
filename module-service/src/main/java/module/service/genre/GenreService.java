package module.service.genre;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dto.genre.GenreDTO;
import lombok.RequiredArgsConstructor;
import module.repository.genre.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreService {

	private final GenreRepository genreRepository;
	private final ModelMapper modelMapper;

	public List<GenreDTO> getAllGenre() {
		return genreRepository.findAll().stream()
			.map(entity-> modelMapper.map(entity,GenreDTO.class))
			.toList();
	}
}
