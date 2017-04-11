package org.springboot.sample.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springboot.sample.config.LoadHtml;
import org.springboot.sample.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/users")   
public class VueAdminController {
	private Logger logger = Logger.getLogger(VueAdminController.class);

	@RequestMapping(value="/vueAdminindex",method=RequestMethod.GET)
	public String junmptovueadmin(HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {

		try {
			String path = request.getServletContext().getRealPath("/");
			String txtHtml = LoadHtml.setReplace("481dc9ce-a75c-4fee-847b-b0de2f4a4c3e");
			LoadHtml.loadIndex(path, txtHtml);
			return "index";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}

	}
	
	 static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());

	    @ApiOperation(value="获取用户列表", notes="")
	    @RequestMapping(value={""}, method=RequestMethod.GET)
	    public List<User> getUserList() {
	        List<User> r = new ArrayList<User>(users.values());
	        return r;
	    }

	    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
	    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	    @RequestMapping(value="", method=RequestMethod.POST)
	    public String postUser(@RequestBody User user) {
	        users.put(user.getId(), user);
	        return "success";
	    }

	    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
	    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	    @RequestMapping(value="/{id}", method=RequestMethod.GET)
	    public User getUser(@PathVariable Long id) {
	        return users.get(id);
	    }

	    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
	            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	    })
	    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
	    public String putUser(@PathVariable Integer id, @RequestBody User user) {
	        User u = users.get(id);
	        u.setName(user.getName());
	        u.setAge(user.getAge());
	        users.put(id, u);
	        return "success";
	    }

	    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
	    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	    public String deleteUser(@PathVariable Long id) {
	        users.remove(id);
	        return "success";
	    }


}
