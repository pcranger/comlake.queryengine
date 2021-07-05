package com.ulake.queryengine.repository;

import com.ulake.queryengine.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, String> {

}