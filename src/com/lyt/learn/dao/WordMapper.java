package com.lyt.learn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyt.learn.model.Word;

public interface WordMapper {
	
	List<Word> searchWord();

	void deleteWord(Integer id);

	void updateWord(@Param("word") Word word);

	void saveWord(Word word);

	Word searchWordById(Integer id);
}