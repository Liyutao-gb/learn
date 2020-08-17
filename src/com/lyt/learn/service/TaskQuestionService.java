package com.lyt.learn.service;

import java.util.List;

import javax.annotation.Resource;

import com.lyt.learn.dao.TaskQuestionMapper;
import com.lyt.learn.model.TaskQuestion;
import org.springframework.stereotype.Service;

@Service
public class TaskQuestionService {
	@Resource
	private TaskQuestionMapper taskQuestionMapper;
	
	public void saveTaskQuestion(TaskQuestion taskQuestion) {
		taskQuestionMapper.saveEntity(taskQuestion);
	}
	
	public List<TaskQuestion> searchTaskQuestion(Integer taskId) {
		return taskQuestionMapper.searchEntity(taskId);
	}
	
	public void deleteTaskQuestion(Integer id) {
		taskQuestionMapper.deleteEntity(id);
	}
	
	public void updateTaskQuestion(TaskQuestion taskQuestion) {
		taskQuestionMapper.updateEntity(taskQuestion);
	}
	
	public TaskQuestion getTaskQuestionById(Integer id) {
		return taskQuestionMapper.getEntityById(id);
	}

}
