package com.major.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.major.model.Project;
import com.major.model.Risk;
import com.major.model.RiskStateTrace;
import com.major.model.User;
import com.major.model.ViewObject;
import com.major.service.ProjectService;
import com.major.service.RiskService;
import com.major.service.RiskStateTraceService;

import tools.RequestUtil;

@Controller
public class RiskStateTraceController {
	@Autowired
	RiskStateTraceService riskStateTraceService;
	
	@Autowired
	RiskService riskService;
	
	@Autowired
	ProjectService projectService;
	
	@RequestMapping(path = {"/riskStateTraceList" })
	public String projectList(Model model, 
			@RequestParam("riskId") Integer riskId, 
			HttpSession session) {
		
//		List<RiskStateTrace> riskStateTraceList = riskStateTraceService.getByRiskId(riskId);
//		
//		Risk risk = riskService.getRisk(riskId);
//		model.addAttribute("riskStateTraceList", riskStateTraceList);
//		model.addAttribute("user", (User) session.getAttribute("user"));
//		model.addAttribute("risk", risk);
//		
//		return "riskStateTraceList";
		List<RiskStateTrace> riskStateTraceList = riskStateTraceService.getByRiskId(riskId);
		Risk risk = riskService.getRisk(riskId);
		List<ViewObject> vos = new ArrayList<>();
		for(RiskStateTrace r : riskStateTraceList) {
			ViewObject vo = new ViewObject();
			Risk risk1 = riskService.getRisk(riskId);
			Project project = projectService.getProject(risk1.getProjectId());
			vo.set("riskState", r);
			vo.set("risk", risk1);
			vo.set("project", project);
			vos.add(vo);
		}
		model.addAttribute("riskStateTraceList", vos);
		model.addAttribute("user", (User) session.getAttribute("user"));
		model.addAttribute("risk", risk);
		return "riskStateTraceList";
	}
	
	@RequestMapping(path = {"/allRiskStateTraceList" })
	public String projectList(Model model, 
			
			HttpSession session) {
		
		List<RiskStateTrace> riskStateTraceList = riskStateTraceService.getAllRiskStateTraces();
		List<ViewObject> vos = new ArrayList<>();
		for(RiskStateTrace r : riskStateTraceList) {
			ViewObject vo = new ViewObject();
			Risk risk = riskService.getRisk(r.getriskId());
			Project project = projectService.getProject(risk.getProjectId());
			vo.set("riskState", r);
			vo.set("risk", risk);
			vo.set("project", project);
			vos.add(vo);
		}
		model.addAttribute("riskStateTraceList", vos);
		model.addAttribute("user", (User) session.getAttribute("user"));
		
		return "riskStateTraceList";
	}
	
	@RequestMapping(path = {"/getRiskStateTraceById" })
	@ResponseBody
	public RiskStateTrace getProjectById(@RequestParam("riskStateTraceId") Integer riskStateTraceId) {
		return riskStateTraceService.getRiskStateTrace(riskStateTraceId);
	}
	
	@RequestMapping(path = {"/editRiskStateTrace" })
	@ResponseBody
	public String editProject(HttpServletRequest request) {
		Integer riskStateTraceId = RequestUtil.getPositiveInteger(request, "riskStateTraceId", null);
		Integer riskId = RequestUtil.getPositiveInteger(request, "riskId", null);
		String name = RequestUtil.getString(request, "name", null);
		String description = RequestUtil.getString(request, "description", null);
		
		if(riskStateTraceId == null) {
			Map<String, Object> msgMap = riskStateTraceService.addRiskStateTrace(riskId, name, description);
			return (String) msgMap.get("msg");
		} else {
			RiskStateTrace riskStateTrace = new RiskStateTrace();
			riskStateTrace.setId(riskStateTraceId);
			riskStateTrace.setriskId(riskId);
			riskStateTrace.setName(name);
			riskStateTrace.setDescription(description);
			Map<String, Object> msgMap = riskStateTraceService.updateRiskStateTrace(riskStateTrace);
			return (String) msgMap.get("msg");
		}
	}
	
	@RequestMapping(path = {"/deleteRiskStateTraceById" })
	@ResponseBody
	public String deleteProjectById(@RequestParam("riskStateTraceId") Integer riskStateTraceId) {
		riskStateTraceService.deleteRiskStateTrace(riskStateTraceId);
		return "删除成功！";
	}
}
