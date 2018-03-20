package com.adobe.training.core.service;

import java.util.List;

import com.adobe.training.core.models.UserInfo;

public interface IUserInfoService {
	
	public List<UserInfo> getAllUserInfo();
	public int getAllUserInfoCount();
	
}
