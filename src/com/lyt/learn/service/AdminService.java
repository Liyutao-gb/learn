package com.lyt.learn.service;

import java.util.Map;

import javax.annotation.Resource;

import com.lyt.learn.dao.AdminMapper;
import org.springframework.stereotype.Service;

import com.lyt.learn.model.Admin;

@Service
public class AdminService {
	@Resource
	private AdminMapper adminMapper;
	
	public Admin adminLogon(Map<String, String> map) {
		return adminMapper.searchEntityByAccount(map);
	}
	
}
