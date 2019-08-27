package com.happiestminds.dictionary.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.happiestminds.dictionary.entity.WordEntity;
import com.happiestminds.dictionary.mapstruct.WordEntityMapper;
import com.happiestminds.dictionary.model.WordDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordDictionaryDaoImplTest {

	@InjectMocks
	private WordDictionaryDaoImpl wordDictionaryDaoImpl;

	@Mock
	private EntityManager entityManager;

	@Mock
	private CriteriaBuilder criteriaBuilder;

	@Mock
	private CriteriaQuery<WordEntity> criteriaQuery;

	@Mock
	private Root<WordEntity> root;

	@Mock
	private TypedQuery<WordEntity> typedQuery;

	@Test
	public void testSaveWords() {
		Set<WordDto> wordDtos = new HashSet<>();
		WordDto testWord = new WordDto();
		testWord.setWord("test");
		testWord.setMeaning("test");
		wordDtos.add(testWord);

		wordDictionaryDaoImpl.saveWords(wordDtos);
		verify(entityManager, times(1)).flush();
	}

	@Test
	public void testFetchAllWords() {

		List<WordDto> wordDtos = new ArrayList<>();
		WordDto testWord = new WordDto();
		testWord.setWord("test");
		testWord.setMeaning("test");
		wordDtos.add(testWord);
		List<WordEntity> entities = wordDtos.stream().map(WordEntityMapper.INSTANCE::mapDtoToEntity)
				.collect(Collectors.toList());

		when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		when(criteriaBuilder.createQuery(Mockito.eq(WordEntity.class))).thenReturn(criteriaQuery);
		when(criteriaQuery.from(Mockito.eq(WordEntity.class))).thenReturn(root);
		when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
		when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
		when(typedQuery.getResultList()).thenReturn(entities);

		Set<WordDto> result = wordDictionaryDaoImpl.fetchAllWords();
		assertNotNull(result);
		assertEquals(entities.size(), result.size());
	}

}
