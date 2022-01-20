package com.poker.planning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poker.planning.entity.UserStrories;
import com.poker.planning.model.UserLink;
import com.poker.planning.repo.Pokerplaning;
import com.poker.planning.service.PokerService;
import com.poker.planning.serviceimpl.PokerServiceImpl;



@Controller
@RequestMapping("/api")
public class pokerprocesscontroller {
   @Autowired
   PokerService pokerserive;
	
	
@PostMapping(value="/pokerplanning")
@ResponseBody
public UserLink process( @RequestBody UserStrories user){
	
	UserLink link=new UserLink();
    UserStrories pokerplanningcreated=pokerserive.proess(user);
	int  fab=fab();
	link.setUserlist(pokerplanningcreated);
	link.setDekType(fab);
	
	return link;
	
	
}
private int fab() {
	int a=0,b=1;
	int c=0;
	for(int i=1;i<=10;i++) {
        c=a+b;
        
        a=b;
        b=c;
		
	}
	return c;

	
	
}
}
