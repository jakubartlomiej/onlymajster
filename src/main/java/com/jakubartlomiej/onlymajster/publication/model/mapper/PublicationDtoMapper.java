package com.jakubartlomiej.onlymajster.publication.model.mapper;

import com.jakubartlomiej.onlymajster.category.exception.CategoryNotFoundException;
import com.jakubartlomiej.onlymajster.category.repository.CategoryRepository;
import com.jakubartlomiej.onlymajster.publication.model.Publication;
import com.jakubartlomiej.onlymajster.publication.model.dto.PublicationDto;
import com.jakubartlomiej.onlymajster.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PublicationDtoMapper {

    public static Publication publicationDtoMapper(UserRepository userRepository, CategoryRepository categoryRepository, Authentication authentication, PublicationDto publicationDto) {
        return Publication.builder()
                .id(publicationDto.getId())
                .text(publicationDto.getText())
                .subject(publicationDto.getSubject())
                .photo(publicationDto.getPhoto())
                .category(categoryRepository.findById(publicationDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException(publicationDto.getCategoryId())))
                .user(userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Username not find: " + authentication.getName())))
                .build();
    }
}