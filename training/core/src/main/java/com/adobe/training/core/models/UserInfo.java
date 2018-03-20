package com.adobe.training.core.models;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.adobe.training.core.service.IUserInfoService;
   
@Model(adaptables = Resource.class)
public class UserInfo {
    
    String firstName;
    String lastName;
    String technology;
    String heading;
    String description;
    int userCount;
    @OSGiService
    IUserInfoService userInfoService;
    
	Map<String,String> map=new HashMap<>();
       
    public String getFirstName() {
    	this.firstName="Kiran";
        return firstName;
    }
    public String getLastName() {
    	this.lastName="Veeranki";
        return lastName;
    }
    public String getTechnology() {
        return technology;
    }
    public Map<String,String> getMap(){
    	map.put("1","Products");
    	map.put("2","AboutUs");
    	return map;
    }
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getUserCount() {
		return userInfoService.getAllUserInfoCount();
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
}
