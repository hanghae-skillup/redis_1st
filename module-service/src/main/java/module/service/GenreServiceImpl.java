package module.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dto.internal.GenreDTO;
import lombok.RequiredArgsConstructor;
import module.repository.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

	private final GenreRepository genreRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<GenreDTO> getAllGenre() {
		return genreRepository.findAll().stream()
			.map(entity-> modelMapper.map(entity,GenreDTO.class))
			.toList();
	}
}
