package com.lyt.learn.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.lyt.learn.model.Course;
import com.lyt.learn.model.Word;
import com.lyt.learn.service.CourseService;
import com.lyt.learn.service.WordService;

/**
 * 
 * @ClassName:
 * @Description: TODO(这里用一句话描述这个类的作用)
 */
@Controller
public class WordController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private WordService wordService;
	
	@RequestMapping("/searchWord")
	public ModelAndView searchDiscuss() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("searchWord");
		return mav;
	}
	@RequestMapping("/wordListByPage")
	@ResponseBody()
	public PageInfo<Word> discussListByPage(int page, int pageSize,HttpSession session) {
		return wordService.searchWord(page,pageSize);
	}
	
	@RequestMapping("/searchteacherWord")
	public ModelAndView searchteacherWord() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("searchteacherWord");
		return mav;
	}
	
	@RequestMapping("/teacherWordListByPage")
	@ResponseBody()
	public PageInfo<Word> teacherWordListByPage(int page, int pageSize,HttpSession session) {
		return wordService.searchTeacherWord(page,pageSize);
	}

	@RequestMapping("/deleteTeacherWord/{id}")
	public ModelAndView deleteTeacherWord(@PathVariable("id") Integer id) {
		wordService.deleteWord(id);
		return new ModelAndView("redirect:/searchteacherWord.html");
	}
	
	@RequestMapping("/deleteWord/{id}")
	public ModelAndView deleteWord(@PathVariable("id") Integer id) {
		wordService.deleteWord(id);
		return new ModelAndView("redirect:/searchWord.html");
	}
	
	@RequestMapping("/updateWord/{id}")
	public ModelAndView updateWord(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("courses", courseService.searchCourse());
		mav.addObject("word", wordService.searchWordById(id));
		mav.setViewName("updateWord");
		return mav;
	}
	
	@RequestMapping("/editWord/{id}")
	public ModelAndView editWord(@PathVariable("id") Integer id,
			HttpSession session, Word word) {
		word.setId(id);
		List<Course> list = courseService.searchCourse();
		// 前端所属课程转课程ID
		for (Course course : list) {
			if(StringUtils.equals(course.getName(), word.getBelongTo())) {
				word.setBelongTo(String.valueOf(course.getId()));
			}
		}
		wordService.updateWord(word);
		return new ModelAndView("redirect:/searchteacherWord.html");
	}
	
	@RequestMapping("/createWord")
	public ModelAndView createWord() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("courses", courseService.searchCourse());
		mav.setViewName("createWord");
		return mav;
	}

	@RequestMapping("/saveWord")
	public ModelAndView saveWord(Word word, HttpSession session) {
		List<Course> list = courseService.searchCourse();
		// 前端所属课程转课程ID
		for (Course course : list) {
			if(StringUtils.equals(course.getName(), word.getBelongTo())) {
				word.setBelongTo(String.valueOf(course.getId()));
			}
		}
		wordService.saveWord(word);
		return new ModelAndView("redirect:/searchteacherWord.html");
	}
}
