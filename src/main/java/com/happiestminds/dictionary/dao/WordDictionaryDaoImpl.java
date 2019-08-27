package com.happiestminds.dictionary.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.happiestminds.dictionary.entity.WordEntity;
import com.happiestminds.dictionary.mapstruct.WordEntityMapper;
import com.happiestminds.dictionary.model.WordDto;

@Repository
public class WordDictionaryDaoImpl implements WordDictionaryDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Set<WordDto> saveWords(Set<WordDto> wordDtos) {
		List<WordEntity> entities = new ArrayList<>();
		wordDtos.stream().map(WordEntityMapper.INSTANCE::mapDtoToEntity).collect(Collectors.toList());
		
		entities.forEach(entity -> entityManager.persist(entity)); 

		return entities.stream().map(WordEntityMapper.INSTANCE::mapEntityToDto).collect(Collectors.toSet());
	}

	public Set<WordDto> fetchAllWords() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<WordEntity> cq = cb.createQuery(WordEntity.class);
		Root<WordEntity> root = cq.from(WordEntity.class);

		cq.select(root);

		TypedQuery<WordEntity> words = entityManager.createQuery(cq);
		List<WordEntity> wordEntities = words.getResultList();

		return wordEntities.stream().map(WordEntityMapper.INSTANCE::mapEntityToDto).collect(Collectors.toSet());
	}

}
