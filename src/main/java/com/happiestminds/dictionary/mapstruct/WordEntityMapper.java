package com.happiestminds.dictionary.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.happiestminds.dictionary.entity.WordEntity;
import com.happiestminds.dictionary.model.WordDto;

@Mapper
public interface WordEntityMapper {

	WordEntityMapper INSTANCE = Mappers.getMapper(WordEntityMapper.class);

	WordDto mapEntityToDto(WordEntity wordEntity);

	WordEntity mapDtoToEntity(WordDto wordDto);
}
