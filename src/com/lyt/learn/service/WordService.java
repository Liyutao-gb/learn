package com.lyt.learn.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.lyt.learn.dao.CourseMapper;
import com.lyt.learn.dao.WordMapper;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyt.learn.model.Course;
import com.lyt.learn.model.Word;

@Service
public class WordService {
	
	@Resource
	private WordMapper wordMapper;
	@Resource
	private CourseMapper courseMapper;
	
	
	@SuppressWarnings("deprecation")
	public PageInfo<Word> searchWord(int page, int pageSize) {
		// 获取第1页，5条内容，默认查询总数count 物理分页
		PageHelper.startPage(page, pageSize);
		List<Word> list = wordMapper.searchWord();
		// 查询当前课程并绑定名称到Word-belongTo参数上
		List<Course> courseList = courseMapper.searchEntity();
		for (Word word : list) {
			for (Course course : courseList) {
				if(StringUtils.equals(word.getBelongTo(), String.valueOf(course.getId()))) {
					word.setBelongTo(course.getName());
				}
			}
		}
		// 随机函数处理单词，保证每天刷新的单词不一致
		List<Word> returnList = new ArrayList<Word>();
		List<Word> notSatisfy = new ArrayList<Word>();
		Date date = new Date();
		for (Word word : list) {
			// 如果当前数据库一共不足五个，返回全部
			if(list.size() <= 5) {
				returnList = list;
				break;
			}
			// 库里多于5个，每日精选五个
			if(returnList.size() == 5) {
				break;
			}
			// 如果当前日期中整除ID余数不为0则不要
			if(date.getDate() % word.getId() == 0) {
				returnList.add(word);
			} else {
				// 全部不满足条件的集合
				notSatisfy.add(word);
			}
			
		}
		// 循环结束，若当前返回集合小于5，存在两种情况：1-库里总数小于5 2-满足情况小于5
		if(list.size() <= 5) {
			// 1-库里小于5返回全部
			PageInfo<Word> pageInfo = new PageInfo<Word>(returnList);
			return pageInfo;
		} else {
			// 2-库里总数一定大于5，但满足条件的小于5，从剩余集合中随机找剩下数目
			int nowCount = returnList.size();
			int needCount = 5-nowCount;
			// 循环添加
			for(int i = 0; i < needCount; i++) {
				// 避免重复添加
				returnList.add(notSatisfy.get(i));
			}
			PageInfo<Word> pageInfo = new PageInfo<Word>(returnList);
			return pageInfo;
		}
	}
	
	public List<Word> searchAllWord() {
		return wordMapper.searchWord();
	}

	public void deleteWord(Integer id) {
		wordMapper.deleteWord(id);
	}

	public void updateWord(Word word) {
		wordMapper.updateWord(word);
	}

	public void saveWord(Word word) {
		wordMapper.saveWord(word);
	}

	public Word searchWordById(Integer id) {
		return wordMapper.searchWordById(id);
	}

	public PageInfo<Word> searchTeacherWord(int page, int pageSize) {
		// 获取第1页，10条内容，默认查询总数count 物理分页
		PageHelper.startPage(page, pageSize);
		List<Word> list = wordMapper.searchWord();
		// 查询当前课程并绑定名称到Word-belongTo参数上
		List<Course> courseList = courseMapper.searchEntity();
		for (Word word : list) {
			for (Course course : courseList) {
				if(StringUtils.equals(word.getBelongTo(), String.valueOf(course.getId()))) {
					word.setBelongTo(course.getName());
				}
			}
		}
		PageInfo<Word> pageInfo = new PageInfo<Word>(list);
		return pageInfo;
	}
	
}
