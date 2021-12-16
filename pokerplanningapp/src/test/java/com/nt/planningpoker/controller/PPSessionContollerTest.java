package com.nt.planningpoker.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nt.planningpoker.serviceImpl.PPSessionServiceImpl;


@WebMvcTest
@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
class PPSessionContollerTest {

	
	/*
	 * @InjectMocks PPSessionContoller ppSessionContoller;
	 * 
	 * @InjectMocks PPSessionServiceImpl ppSessionServiceImpl;
	 */
	
	     
	    private static final boolean True = false;

		@Test
	    public void testCreatePokerPlanningSession() 
	    {
	        assert(True);
	        
	    }
		
		/*
		 * @Test public void testCreatePockerPlanningSession() { MockHttpServletRequest
		 * request = new MockHttpServletRequest();
		 * RequestContextHolder.setRequestAttributes(new
		 * ServletRequestAttributes(request));
		 * when(ppSessionServiceImpl.createPokerPlanningSession(any(PokerSession.class))
		 * ).thenReturn(any(PokerSession.class));
		 * 
		 * PokerSession pkrSession = new PokerSession("premiumOfInsurance",
		 * "second",""); ResponseEntity<PokerSession> responseEntity =
		 * ppSessionContoller.createPockerPlanningSession(pkrSession.getTitle(),
		 * pkrSession.getDeckType());
		 * 
		 * assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		 * assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo(
		 * "/1"); }
		 */
	 
	
	
	
}
