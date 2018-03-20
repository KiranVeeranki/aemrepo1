package com.adobe.training.core.service.impl;

import com.adobe.training.core.models.UserInfo;
import com.adobe.training.core.service.IUserInfoService;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(immediate = true)
@Service
public class UserInfoServiceImpl implements IUserInfoService {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	@Reference
	ResourceResolverFactory resourceResolverFactory;

	private Session session;

	@Reference
	QueryBuilder queryBuilder;

	@Override
	public List<UserInfo> getAllUserInfo() {

		try {
			Map<String, Object> predicateMap = new HashMap<>();
			// Mention the subServiceName you had used in the User Mapping
			predicateMap.put(ResourceResolverFactory.SUBSERVICE, "userInfoService");
			if (resourceResolverFactory == null) {
				log.info(
						"*************************************Resource REsolver null*************************************************");
			}
			ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(predicateMap);
			session = resourceResolver.adaptTo(Session.class);

			predicateMap.put("path", "/home/users/");
			predicateMap.put("type", "rep:User");
			predicateMap.put("p.offset", "0");
			predicateMap.put("p.limmit", "-1");

			Query query = queryBuilder.createQuery(PredicateGroup.create(predicateMap), session);

			SearchResult result = query.getResult();

			// paging metadata
			int userCount = result.getHits().size();

			List<UserInfo> userInfos = new ArrayList<>();
			// iterating over the results
			for (Hit hit : result.getHits()) {
				String path = hit.getPath();
				UserInfo userInfo = new UserInfo();
				userInfo.setHeading(path);
				userInfo.setUserCount(userCount);
			}

			// close the session
			session.logout();

			// return result list
			return userInfos;

		} catch (Exception e) {
			e.printStackTrace();
			// log.info(e.getMessage());
		}
		return null;
	}

	@Override
	public int getAllUserInfoCount() {
		Map<String, Object> predicateMap = new HashMap<>();
		// Mention the subServiceName you had used in the User Mapping
		predicateMap.put(ResourceResolverFactory.SUBSERVICE, "userInfoService");
		if (resourceResolverFactory == null) {
			System.out.println("Resource REsolver count 0");
			
			log.info(
					"*************************************Resource REsolver count 0 *************************************************");
			return 150;
		}
		ResourceResolver resourceResolver;
		try {
			resourceResolver = resourceResolverFactory.getServiceResourceResolver(predicateMap);
			session = resourceResolver.adaptTo(Session.class);

			predicateMap.put("path", "/home/users/");
			predicateMap.put("type", "rep:User");
			predicateMap.put("p.offset", "0");
			predicateMap.put("p.limmit", "-1");

			Query query = queryBuilder.createQuery(PredicateGroup.create(predicateMap), session);

			SearchResult result = query.getResult();

			// paging metadata
			int userCount = result.getHits().size();

			// close the session
			session.logout();
			return userCount;
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}

}
