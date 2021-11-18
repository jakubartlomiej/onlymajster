package com.jakubartlomiej.onlymajster.publication.repository;

import com.jakubartlomiej.onlymajster.publication.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    Page<Publication> findByUserId(long id, Pageable pageable);
}