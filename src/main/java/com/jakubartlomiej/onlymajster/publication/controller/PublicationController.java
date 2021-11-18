package com.jakubartlomiej.onlymajster.publication.controller;

import com.jakubartlomiej.onlymajster.publication.model.Publication;
import com.jakubartlomiej.onlymajster.publication.model.dto.PublicationDto;
import com.jakubartlomiej.onlymajster.publication.service.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/publication")
public class PublicationController {
    private final PublicationService publicationService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Publication> findAll(@RequestParam(defaultValue = "0", name = "page") String pageNumber,
                                         @RequestParam(defaultValue = "10", name = "size") String pageSize) {
        return publicationService.findAll(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Publication> findById(@PathVariable Long id) {
        return publicationService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Publication> addPublication(@RequestBody @Valid PublicationDto publicationDto, Authentication authentication) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/publication").toUriString());
        return ResponseEntity.created(uri).body(publicationService.save(publicationDto, authentication));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable(name = "id") Long id, @RequestBody @Valid PublicationDto publicationDto, Authentication authentication) {
        return ResponseEntity.ok(publicationService.update(publicationDto, id, authentication));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable Long id,
                                          @RequestParam(defaultValue = "0", name = "page") String pageNumber,
                                          @RequestParam(defaultValue = "10", name = "size") String pageSize) {
        return ResponseEntity.ok(publicationService.findByUserId(id, pageNumber, pageSize));
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (publicationService.findById(id).isPresent()) {
            publicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}