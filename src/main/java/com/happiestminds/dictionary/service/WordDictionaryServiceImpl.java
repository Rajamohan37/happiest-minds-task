package com.happiestminds.dictionary.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.happiestminds.dictionary.dao.WordDictionaryDao;
import com.happiestminds.dictionary.exceptions.WordNotFoundInDBException;
import com.happiestminds.dictionary.model.WordDto;

@Service
public class WordDictionaryServiceImpl implements WordDictionaryService {

	@Autowired
	private WordDictionaryDao wordDictionaryDao;

	@Transactional
	public List<WordDto> persistWordsToDb(MultipartFile file) throws IOException {

		List<String> availableWords = readFile(file);
		Set<WordDto> wordDtos = persistDB(availableWords);

		return new ArrayList<>(wordDictionaryDao.saveWords(wordDtos));
	}

	private static List<String> readFile(MultipartFile file) throws IOException {

		InputStream inputStream = null;
		Scanner sc = null;
		List<String> strs = new ArrayList<>();
		try {
			inputStream = file.getInputStream();
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] strArray = line.split(";");
				Collections.addAll(strs, strArray);
			}

			if (sc.ioException() != null) {
				throw sc.ioException();
			}

			return strs;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
		}

	}

	private Set<WordDto> persistDB(List<String> strs) {

		Set<WordDto> existingWords = wordDictionaryDao.fetchAllWords();

		Set<WordDto> wordDtos = new HashSet<>();
		for (String string : strs) {
			String[] array = string.trim().split(":");

			// If any word doesn't have meaning, ignoring it from storing to DB
			if (array.length < 2)
				continue;

			WordDto newWord = new WordDto();
			newWord.setWord(array[0]);
			newWord.setMeaning(array[1]);

			// Persist only those words which are not in DB
			if (!existingWords.contains(newWord))
				wordDtos.add(newWord);
		}
		return wordDtos;
	}

	public WordDto findByWord(String word) {
		Set<WordDto> existingWords = wordDictionaryDao.fetchAllWords();
		WordDto searchWord = existingWords.stream().filter(dbWord -> dbWord.getWord().equalsIgnoreCase(word))
				.findFirst().orElse(null);

		if (searchWord == null)
			throw new WordNotFoundInDBException("Word is not found. Add it to the dictionary.");
		return searchWord;
	}
 
}
