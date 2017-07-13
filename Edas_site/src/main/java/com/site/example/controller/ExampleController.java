package  com.site.example.controller;

//import com.baosight.shgt.service.example.api.command.ChangeNameCommand;
//import com.baosight.shgt.service.example.api.doSth.DoSomething;
//import com.baosight.shgt.service.example.api.exception.UserNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.service.api.SampleService;
import com.site.example.model.Users;
import com.site.example.model.UsersDao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.oyys.yjyg.service.yy.api.jtbase.TYySegment;
import com.oyys.yjyg.service.yy.api.jtbase.returnBean.TYySegmentBean;

import com.oyys.yjyg.service.yy.api.jtagreement.TJtAgreementDataQueryApi;
import com.oyys.yjyg.service.yy.api.jtagreement.vo.TJtAgreementVo;
import com.oyys.yjyg.service.yy.api.jtagreement.command.QueryAgreementListCommand;

/**
 * Created by wen190 on 2017/6/5.
 */
@RequestMapping(value = "/example")
@Controller
public class ExampleController {

    @Autowired(required = false)
    private SampleService sampleService;
    
    @Autowired(required = false)
    private TYySegment tYySegment;
    
    @Autowired(required = false)
    private TJtAgreementDataQueryApi tJtAgreementDataQueryApi;
    
    @Autowired  
	 private JdbcTemplate jdbcTemplate; 
    @Autowired  
	 private UsersDao usersDao; 
    
    @RequestMapping(value = "/sayHi")
    @ResponseBody
    public String sayHi() {
//        ChangeNameCommand changeNameCommand = new ChangeNameCommand();
//        changeNameCommand.setUserId(2);
        //return doSomething.changeName(changeNameCommand);
    	String sayString = sampleService.echo("wen12");
    	return sayString;
    }
    

    
    @RequestMapping(value = "/myName")
    @ResponseBody
    public String myName() {
//        ChangeNameCommand changeNameCommand = new ChangeNameCommand();
//        changeNameCommand.setUserId(2);
        //return doSomething.changeName(changeNameCommand);
    	//String sayString = sampleService.echo("wen12");
    	return "我的 ne："+getName(2);
    }
    
    @RequestMapping(value = "/test")
    @ResponseBody
    public String TYySegment() {
    	//com.oyys.yjyg.service.yy.api.jtbase.TYySegment
//        ChangeNameCommand changeNameCommand = new ChangeNameCommand();
//        changeNameCommand.setUserId(2);
        //return doSomething.changeName(changeNameCommand);
    	//String sayString = sampleService.echo("wen12");
    	//查询所有地区公司
    	//List<TYySegmentBean> mylist= tYySegment.getAll();
    	//System.out.print(mylist);
    	//QueryAgreementListCommand queryAgreementListCommand ;
    	//tJtAgreementDataQueryApi.queryTJtAgreementsup(queryAgreementListCommand)
    	return "列表";
    }
    
    
    
    
    @RequestMapping("/info/{id}")
    public String info(Map<String,Object> map, @PathVariable int id){
//    	Map<String,Object>; 
//       map1.put("name", "Angel -- wen! ");
//       map1.put("gender",1);//gender:性别，1：男；0：女；
    	List<Users> userList = usersDao.findById(id);
   	 if (userList != null && userList.size()!=0) {  
   		 map.put("info", userList.get(0));
   	 }else{
   		 map.put("info", new HashMap<String,Object>());
   	 }
       return "example/hellopage";
    }
    
    
    /**
     * 根据id , 查用户名
     * @param a
     * @return
     */
    public String getName(int id) {
    	Users User = jdbcTemplate.queryForObject("select * from users where id=?", new Object[] {id },
                new BeanPropertyRowMapper<>(Users.class));
        return User.getName();
    }
    
    public static void main(String[] args) throws Exception {  
        
    }  
}
