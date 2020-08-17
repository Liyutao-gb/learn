package com.lyt.learn.service;

import java.util.List;

import javax.annotation.Resource;

import com.lyt.learn.dao.DiscussPostMapper;
import com.lyt.learn.model.DiscussPost;
import org.springframework.stereotype.Service;

@Service
public class DiscussPostService {
	@Resource
	private DiscussPostMapper discussPostMapper;
	
	public void saveDiscussPost(DiscussPost discussPost) {
		discussPostMapper.saveEntity(discussPost);
	}
	
	public List<DiscussPost> searchDiscussPost(Integer id) {
		return discussPostMapper.searchDiscussPost(id);
	}

	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return discussPostMapper.deleteByPrimaryKey(id);
	}
}
