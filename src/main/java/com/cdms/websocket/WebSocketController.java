package com.cdms.websocket;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdms.pojo.Course;
import com.cdms.pojo.Group;
import com.cdms.pojo.StudentJudge;
import com.cdms.pojo.WXGrade;
import com.cdms.pojo.response.WXResponse;

@RestController
@RequestMapping("/ws")
public class WebSocketController {

	@PostMapping("/setGroup")
	@PreAuthorize("hasRole('ROLE_teacher')")
	@ResponseBody
	public void setReplyGroup(@RequestBody Group group) {
		WXResponse response = new WXResponse();
    	response.setDatatype("group");
    	response.setData(group);
		WebSocketService.sendToStudent(response, group.getCourseId().toString());
	}
	
	@PostMapping("/tellStart")
	@PreAuthorize("hasRole('ROLE_teacher')")
	@ResponseBody
	public void tellStudentStart(@RequestBody StudentJudge sj) {
		WXResponse response = new WXResponse();
		String msg = "start";
    	response.setDatatype("message");
    	response.setData(msg);
		WebSocketService.sendToStudentStart(response, sj.getCourseId(), sj.getNum());
	}
	
	@PostMapping("/tellEnd")
	@PreAuthorize("hasRole('ROLE_teacher')")
	@ResponseBody
	public void tellStudentEnd(@RequestBody Course course) {
		WXResponse response = new WXResponse();
		String msg = "end";
    	response.setDatatype("message");
    	response.setData(msg);
		WebSocketService.sendToStudent(response, course.getId().toString());
	}
	
	@PostMapping("/submitGradeToTeacher")
	@PreAuthorize("hasRole('ROLE_student')")
	@ResponseBody
	public void submitGradeToTeacher(@RequestBody WXGrade wxg) {
		WXResponse response = new WXResponse();
    	response.setDatatype("grade");
    	response.setData(wxg);
		WebSocketService.sendToTeacher(response, wxg.getCourseId());
	}
	
}
