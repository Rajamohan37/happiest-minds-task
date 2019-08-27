package com.happiestminds.dictionary.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.happiestminds.dictionary.exceptions.FileExtensionNotSupportedException;
import com.happiestminds.dictionary.model.WordDto;
import com.happiestminds.dictionary.service.WordDictionaryService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class WordDictionaryController {

	@Autowired
	private WordDictionaryService wordDictionaryService;

	@RequestMapping(value = "/persist-words", method = RequestMethod.POST)
	public List<WordDto> persistWords(@RequestParam("file") MultipartFile file) throws IOException {
		if (!Files.getFileExtension(file.getResource().getFilename()).equalsIgnoreCase("txt"))
			throw new FileExtensionNotSupportedException("Not a Supported file format");

		return wordDictionaryService.persistWordsToDb(file);
	}

	@RequestMapping(value = "/search-word", method = RequestMethod.GET)
	public WordDto findByWord(@RequestParam("word") String word) throws IOException {

		return wordDictionaryService.findByWord(word);
	}

}
