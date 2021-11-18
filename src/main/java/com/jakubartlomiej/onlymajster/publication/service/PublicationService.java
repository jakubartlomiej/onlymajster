package com.jakubartlomiej.onlymajster.publication.service;

import com.jakubartlomiej.onlymajster.category.repository.CategoryRepository;
import com.jakubartlomiej.onlymajster.publication.model.Publication;
import com.jakubartlomiej.onlymajster.publication.model.dto.PublicationDto;
import com.jakubartlomiej.onlymajster.publication.repository.PublicationRepository;
import com.jakubartlomiej.onlymajster.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.jakubartlomiej.onlymajster.publication.model.mapper.PublicationDtoMapper.publicationDtoMapper;

@Service
@AllArgsConstructor
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public Iterable<Publication> findAll(String pageNumber, String pageSize) {
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
        return publicationRepository.findAll(pageable);
    }

    public Publication save(PublicationDto publicationDto, Authentication authentication) {
        return publicationRepository.save(publicationDtoMapper(userRepository, categoryRepository, authentication, publicationDto));
    }

    public Publication update(PublicationDto publicationDto, Long id, Authentication authentication) {
        publicationDto.setId(id);
        return publicationRepository.save(publicationDtoMapper(userRepository, categoryRepository, authentication, publicationDto));
    }

    public Optional<Publication> findById(Long id) {
        return publicationRepository.findById(id);
    }

    public Iterable<Publication> findByUserId(Long id, String pageNumber, String pageSize) {
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize), Sort.by("createdAt").descending());
        return publicationRepository.findByUserId(id, pageable);
    }

    public void deleteById(Long id) {
        publicationRepository.deleteById(id);
    }
}