package com.globaltech.qna.service;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globaltech.qna.model.Tags;
import com.globaltech.qna.repository.TagsRepository;

@Service
public class TagsService {
	
	@Autowired
	private TagsRepository tagsRepository;

	public String newTag(String tagName) {
		Tags tags = new Tags();
		tags.setTag(tagName);
		return tagsRepository.save(tags).getId();
	}
	
	public boolean isExistByName(String tagName) {
		Tags tags = tagsRepository.findByTag(tagName);
		if(tags == null) {
			return false;
		}
		return true;
	}
	
	public boolean isExistById(String tagId) {
		return tagsRepository.existsById(tagId);
	}
	
	public boolean isExistByList(List<String> tagIds) {
		ListIterator<String> listIterator = tagIds.listIterator();
		while (listIterator.hasNext()) {
			if(! tagsRepository.existsById(listIterator.next())){
				return false;
			}
		}
		return true;
	}

	public List<Tags> fetchAll() {
		return tagsRepository.findAll();
	}

	public Tags fetchById(String tagId) {
		return tagsRepository.getOne(tagId);
	}

	public String updateTags(String tagId, String tagName) {
		Tags tags = tagsRepository.getOne(tagId);
		tags.setTag(tagName);
		return tagsRepository.save(tags).getId();
	}
}
