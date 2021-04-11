package com.cdms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cdms.dao.StudentHomeworkMapper;
import com.cdms.pojo.Group;
import com.cdms.pojo.Homework;
import com.cdms.pojo.StudentHomework;
import com.cdms.pojo.response.JsonResponseBody;
import com.cdms.utils.JsonResponse;

@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private StudentHomeworkMapper shm;
	
	@PostMapping("/upload")
	public JsonResponseBody uploadFile(@RequestParam("file") MultipartFile[] file, 
			@RequestParam("courseId") String courseId, @RequestParam("homeworkId") String homeworkId, 
			@RequestParam("groupId") String groupId) throws IOException {
		if(file.length != 1 || homeworkId == null || courseId == null || groupId == null) {
			return JsonResponse.responseFailure("文件超过1个，或未传入作业相关信息");
		}
		
		String fileName = file[0].getOriginalFilename();
		String filePath = System.getProperty("user.dir") + //项目地址
				File.separator + "UploadFile" + //上传文件总文件夹
				File.separator + courseId + //按课程ID初次划分
				File.separator + homeworkId + //按作业ID再次划分
				File.separator + groupId + //最后用小组ID再划分一次
				File.separator + fileName;//文件名可以保留原名
		
		StudentHomework sh = new StudentHomework();
		Homework h = new Homework();
		Group g = new Group();
		h.setId(Integer.parseInt(homeworkId));
		g.setId(Integer.parseInt(groupId));
		sh.setCourseId(Integer.parseInt(courseId));
		sh.setGroup(g);
		sh.setHomework(h);
		sh.setFileName(fileName);
		
		try {
			shm.updateSH(sh);
		} catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonResponse.responseFailure("文件名写入失败");
		}
		
		File saveFile = new File(filePath);
	    if (saveFile.getParentFile() != null || !saveFile.getParentFile().isDirectory()) {
	    	saveFile.getParentFile().mkdirs();
	    }
        
	    try {
	    	FileInputStream fis = (FileInputStream)file[0].getInputStream();
	        FileOutputStream fos = new FileOutputStream(filePath);
	        FileChannel inChannel = fis.getChannel();
	        FileChannel outChannel = fos.getChannel();
	        inChannel.transferTo(0, inChannel.size(), outChannel);
	        inChannel.close();
	        outChannel.close();
	        fos.close();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
		return JsonResponse.responseSuccess();
	}
	
	@PostMapping("/download")
	@ResponseBody
	public ResponseEntity<ByteArrayResource> downloadFile(@RequestBody StudentHomework sh, HttpServletRequest request) throws Exception {
		String fileName = shm.selectSH(sh).getFileName();
	    String filePath = System.getProperty("user.dir") + //项目地址
				File.separator + "UploadFile" + //上传文件总文件夹
				File.separator + sh.getCourseId() + //按课程ID初次划分
				File.separator + sh.getHomework().getId() + //按作业ID再次划分
				File.separator + sh.getGroup().getId() + //最后用小组ID再划分一次
				File.separator + fileName;

	    String userAgent = request.getHeader("User-Agent");
	    if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
	        fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
	    } else {
	        fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
	    }

	    byte[] data = Files.readAllBytes(Paths.get(filePath));
	    ByteArrayResource resource = new ByteArrayResource(data);

	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
	            .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(data.length)
	            .body(resource);
	}
	
}
