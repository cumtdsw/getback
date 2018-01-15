package com.dsw.getback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsw.getback.query.condition.LostArticlePubCondition;
import com.dsw.getback.query.result.QueryResult;
import com.dsw.getback.service.api.LostArticlePubService;

@Controller
@RequestMapping("/articlePub")
public class LostArticlePubController {

	@Autowired
	protected LostArticlePubService lostArticlePubService;
	
	//http://localhost:8080/getback/articlePub/articleListInfo.do
	@RequestMapping(value="articleListInfo.do",  produces = "application/json;charset=UTF-8")
	@ResponseBody
	public QueryResult articleListInfo() {
		QueryResult result = lostArticlePubService.serachLostArticle(new LostArticlePubCondition());
		return result;
	}
}
