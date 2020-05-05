package com.examen.test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.examen.App;
import com.examen.service.FileService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
@Transactional
public class FileServiceTest {				//test for extensions method in fileService
	
	@Autowired
	FileService fileService;
	
	@Test
	public void testGetExtention() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {			
		Method method= FileService.class.getDeclaredMethod("getFileExtention", String.class);  //make possible to test private methods
		method.setAccessible(true);
		
		
		assertEquals("should be png", "png" ,(String)method.invoke(fileService, "test.png"));
		assertEquals("should be jpg", "jpg" ,(String)method.invoke(fileService, "test.jpg"));
		assertEquals("should be jpeg", "jpeg" ,(String)method.invoke(fileService, "test.jpeg"));
		//assertEquals("should be jpeg", "jpeg" ,(String)method.invoke(fileService, "test.xyg"));
	}
	
	@Test
	public void testIsImageExtention() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {			
		Method method= FileService.class.getDeclaredMethod("isImageExtention", String.class);  //make possible to test private methods
		method.setAccessible(true);
		
		
		assertTrue("png should be valid",(Boolean)method.invoke(fileService, "png"));
		assertTrue("PNG should be valid",(Boolean)method.invoke(fileService, "PNG"));
		assertTrue("jpg should be valid",(Boolean)method.invoke(fileService, "jpg"));
		assertTrue("JPG should be valid",(Boolean)method.invoke(fileService, "JPG"));
		assertTrue("jpeg should be valid",(Boolean)method.invoke(fileService, "jpeg"));
		assertFalse("xyg should be invalidvalid",(Boolean)method.invoke(fileService, "xyg"));
	}

}
