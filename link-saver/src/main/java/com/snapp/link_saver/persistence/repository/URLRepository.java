package com.snapp.link_saver.persistence.repository;

import com.snapp.link_saver.persistence.entity.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends JpaRepository<URL, String> {
}
