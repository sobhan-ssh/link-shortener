package com.snapp.link_saver.persistence.repository;

import com.snapp.link_saver.persistence.entity.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URL, String> {

    Optional<URL> findByShortLink(String shortLink);

}
