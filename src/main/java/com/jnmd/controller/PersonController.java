package com.jnmd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jnmd.domain.Person;

@Controller
@Scope("prototype")
public class PersonController {
	
	@RequestMapping("testAJax")
	public ModelAndView testAJax(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Contact_list");
		return mv;
	}
	
	//方法一
	@RequestMapping("testAjaxController")
	@ResponseBody       //将方法返回的 对象 自动封装成为JSON格式
	public Person test(Person person) {
		Person p1 = new Person("json", 25);
	    if(("张三").equals(person.getName()) && person.getAge() == 24){
	    	return p1;
	    }
	    return new Person();
	}
	
	//方法二
	@RequestMapping("testAjaxController2")
	@ResponseBody
	public JSONObject test2(Person person) {
		System.out.println(person);
		List<Person> lists = new ArrayList<Person>();
		Person p1, p2, p3;
		p1 = new Person(1, "张三", 21);
		p2 = new Person(2, "李四", 22);
		p3 = new Person(3, "王五", 23);
		lists.add(p1);
		lists.add(p2);
		lists.add(p3);
	    JSONObject json = new JSONObject();
	    if(("张三").equals(person.getName()) && person.getAge() == 23){
	    	System.out.println("11111");
		    json.put("msg", "1"); 
		    json.put("test", lists);
		    return json;
	    }
	    json.put("msg", "0"); 
		return json;  	
	}
	
	//方法三
	@RequestMapping("testAjaxController3")
	public void test3(Person person, HttpServletResponse response) throws IOException {
		Person p3 = new Person("FastJson", 25);
	    if(("张三").equals(person.getName()) && person.getAge() == 24){
	    	System.out.println("222");
	    	String str = JSON.toJSONString(p3);
	    	//指定响应数据格式及编码
			response.setContentType("application/json charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write(str);
			//pw.write("...");
			pw.flush();
			pw.close();
	    }
	}
	
	//方法四
	@RequestMapping("testAjaxController4")
	public void test4(Person person, HttpServletResponse response) throws IOException {
		Person p4 = new Person("Gson", 25);
	    if(("张三").equals(person.getName()) && person.getAge() == 24){
	    	System.out.println("222");
	    	Gson gson = new Gson();
	    	String str = gson.toJson(p4);
	    	//指定响应数据格式及编码
			response.setContentType("application/json charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write(str);
			pw.flush();
			pw.close();
	    }
	}
	
	
}
