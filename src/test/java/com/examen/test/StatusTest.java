package com.examen.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import java.util.Calendar;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.examen.App;
import com.examen.model.StatusUpdate;
import com.examen.model.StatusUpdateDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
@Transactional
public class StatusTest {
	
	@Autowired
	private StatusUpdateDao statusUpdateDao;
	
	@Test
	public void testSave() {
		
		StatusUpdate status =new StatusUpdate("Try to save text");
		statusUpdateDao.save(status);
		
		assertNotNull("Non-null ID", status.getId());
		assertNotNull("Non-null Date", status.getAdded());
		
		StatusUpdate retrieved = statusUpdateDao.findById(status.getId()).orElse(null);;
		
		assertEquals("Matching satatus uppdate",status, retrieved); //check if saved status is same like retrieved status from DB
		//Optional<StatusUpdate> optionalEntity = statusUpdateDao.findById(status.getId());
		//StatusUpdate retrieved = optionalEntity.get();
		
		
			
		}
	
		@Test
		public void testFindLatest() {
			
			Calendar calendar = Calendar.getInstance();
			
			StatusUpdate latestStatusUpdate = null;
			
			for(int i = 0 ; i < 10; i++) {
				calendar.add(calendar.DAY_OF_YEAR, 1);
				
				StatusUpdate status = new StatusUpdate("Status update:" + i, calendar.getTime());
				
				statusUpdateDao.save(status);
				
				latestStatusUpdate = status;
			}
			
			StatusUpdate retrived = statusUpdateDao.findFirstByOrderByAddedDesc(); 
			assertEquals("Latest status update",latestStatusUpdate,retrived);
			
	}
	
}
