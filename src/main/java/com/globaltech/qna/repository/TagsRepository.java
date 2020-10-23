package com.globaltech.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globaltech.qna.model.Tags;

@Repository
public interface TagsRepository extends JpaRepository<Tags, String> {

	Tags findByTag(String tagName);

}
