package com.happiestminds.dictionary.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.happiestminds.dictionary.model.WordDto;

public interface WordDictionaryService {
	 
	List<WordDto> persistWordsToDb(MultipartFile file) throws IOException;
	
	WordDto findByWord(String word);
}
