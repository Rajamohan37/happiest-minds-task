package com.happiestminds.dictionary.dao;

import java.util.Set;

import com.happiestminds.dictionary.model.WordDto;

public interface WordDictionaryDao {
	
	Set<WordDto> saveWords(Set<WordDto> wordDtos);
	
	Set<WordDto> fetchAllWords();

}
