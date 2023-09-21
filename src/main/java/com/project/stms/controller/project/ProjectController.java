package com.project.stms.controller.project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.stms.command.FileVO;
import com.project.stms.command.ProjectVO;
import com.project.stms.command.ServerVO;
import com.project.stms.command.TaskVO;
import com.project.stms.command.UserVO;
import com.project.stms.service.notification.NotificationService;
import com.project.stms.service.notification.SseService;
import com.project.stms.service.project.ProjectService;
import com.project.stms.service.s3.S3Service;
import com.project.stms.util.Criteria;
import com.project.stms.util.PageVO;

@Controller
@RequestMapping("project")
public class ProjectController {
	
	@Autowired
	@Qualifier("projectService")
	ProjectService projectService;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	@Qualifier("notificationService")
	NotificationService notificationService;
	
	@Autowired
	SseService sseService;
	
	
	@GetMapping("/ProjectMain")
	public String ProjectMain(ProjectVO vo, Model mo, HttpSession session) {
		
		String myRole = (String)session.getAttribute("user_role");
		System.out.println(myRole);
		String myId = (String)session.getAttribute("user_id");
		
		if(myRole.equals("ROLE_ADMIN")) {
			List<ProjectVO> pList = projectService.getList();
			mo.addAttribute("pList", pList);
		} else if(myRole.equals("ROLE_ENGINEER")) {
			List<ProjectVO> pList = projectService.getRoledList(myId);
			mo.addAttribute("pList", pList);
		}
		
		
		List<ProjectVO> reqPList = projectService.getRequestList();
		
		
		mo.addAttribute("reqPList", reqPList);
		
		
		
		return "/project/ProjectMain";
	}
	
	
	
	
	
	@GetMapping("/ProjectRegist")
	public String ProjectRegist(Model mo, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		

		String myId = (String)session.getAttribute("user_id");

		
		System.out.println(myId + " 나의아이디");
		
		List<ServerVO> sList = projectService.getMyServer(myId);
		
		System.out.println(sList.toString());
		
		mo.addAttribute("sList", sList);
		
		return "/project/ProjectRegist";
	}
	
	
	
	
	
	@PostMapping("/registForm")
	public String registForm(ProjectVO vo,
							 @RequestParam(required = false, name = "fileList") List<MultipartFile> list,

							 HttpSession session,

							 HttpServletRequest request) {

		
		session = request.getSession();
		
		System.out.println(vo.toString());
		
		projectService.requestProject(vo);
		
		if(list != null) {
			System.out.println("리스트가있어요");
			projectService.insertFiles(list, projectService.getProjectInfoForFiles().getPjt_id());
		}

//		notificationService.createProjectNotification("ADMIN", (String)session.getAttribute("user_id"), vo.getPjt_nm());

		
		
		return "redirect:/main";
	}
	
	
	@GetMapping("/searchForm")
	public String searchForm(Model mo,
							 @RequestParam(required = false, value = "server_type") String server_type,
							 @RequestParam(required = false, value = "pjt_end_dt") String pjt_end_dt,
							 @RequestParam(required = false, value = "ins_user_id") String ins_user_id) {
		
		System.out.println(server_type + " server_type");
		System.out.println(pjt_end_dt + " pjt_end_dt");
		System.out.println(ins_user_id + " ins_user_id");
		
		List<ProjectVO> pList = projectService.getFilteredList(server_type, pjt_end_dt, ins_user_id);
		
		mo.addAttribute("pList", pList);
		
		return "/project/ProjectMain";
	}
	
	
	@GetMapping("/searchName")
	public String searchName(Model mo, @RequestParam(required = false, value = "pjt_nm") String pjt_nm) {
		
		System.out.println(pjt_nm);
		
		List<ProjectVO> pList = projectService.getProjectByName(pjt_nm);
		
		mo.addAttribute("pList", pList);
		
		return "/project/ProjectMain";
	}
	
	
	
	@GetMapping("/ProjectCreate")
	public String ProjectCreate(@RequestParam("pjt_id") int pjt_id, Model mo, Criteria cri, HttpSession session) {
		
		
		ProjectVO pVO = projectService.getProjectDetail(pjt_id);
		
		List<UserVO> norUList = projectService.getNormalUserDetailByPage(pjt_id);
		
		int total = projectService.getTotal();
		
		PageVO pageVO = new PageVO(cri, total);
		
		pageVO.setPnCount(5);
		
		System.out.println(pageVO.toString());
		
		mo.addAttribute("pVO", pVO);
		
		mo.addAttribute("norUList", norUList);
		
		mo.addAttribute("pageVO", pageVO);
		
		mo.addAttribute("ins_user_id", (String) session.getAttribute("user_id"));
		
		
		return "/project/ProjectCreate";
	}
	
	
	
	@GetMapping("/getDetail")
	public String getDetail(@RequestParam("pjt_id") int pjt_id,
							Model mo, Criteria cri, HttpSession session) {
		
		System.out.println(session.getAttribute("user_role"));
		
		
		
		// 프로젝트 세부정보
		ProjectVO pVO = projectService.getProjectDetail(pjt_id);
		
		System.out.println(pVO.toString());
		
		
		
		// 각 기간 별 작업 진행도
		String startDateStr = pVO.getPjt_st_dt();
        String endDateStr = pVO.getPjt_end_dt();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);
        
        System.out.println(startDate.until(endDate).getDays()+1 + "총 날짜===============");
        
        int pjt_period = startDate.until(endDate).getDays()+1;
        
        if(pjt_period <= 10) {
        	System.out.println("10보다작음");
        	
        	List<String> dayChartPjt_date = new ArrayList<>();
        	List<Integer> dayChartCompleteTask = new ArrayList<>();
        	LocalDate currentDate = startDate;
        	while (!currentDate.isAfter(endDate)) {
        		System.out.println(currentDate.format(formatter));
        		String pjt_date = currentDate.format(formatter);
        		dayChartPjt_date.add(pjt_date);
        		dayChartCompleteTask.add(projectService.getCompletedTask(pjt_date, pjt_id));
        		currentDate = currentDate.plusDays(1); // 다음 날짜로 이동
        	}
        	mo.addAttribute("chartPjt_date", dayChartPjt_date);
        	mo.addAttribute("chartCompleteTask", dayChartCompleteTask);
        
        // 프로젝트 기간이 10일 이상 ~ 2달 미만일 경우
        } else if(pjt_period <= 63) {
        	System.out.println("63보다작음");
        	List<String> weekChartPjt_date = new ArrayList<>();
        	List<Integer> weekhartCompleteTask = new ArrayList<>();
        	LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                LocalDate endOfWeek = currentDate.with(TemporalAdjusters.nextOrSame(LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), 1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfWeek()));
                System.out.println(currentDate + " ~ " + endOfWeek);
                weekChartPjt_date.add(endOfWeek.format(formatter));
                weekhartCompleteTask.add(projectService.getCompletedTask(endOfWeek.toString(), pjt_id));
                currentDate = endOfWeek.plusDays(1);
            }
            
            System.out.println(weekChartPjt_date.toString() + " 위크차트 스트링");
            System.out.println(weekhartCompleteTask.toString() + " 위크차트 완료 스트링");
            mo.addAttribute("chartPjt_date", weekChartPjt_date);
        	mo.addAttribute("chartCompleteTask", weekhartCompleteTask);
           
        // 프로젝트 기간이 2달 이상일경우
        } else {
        	System.out.println("제일큼");
        	List<String> monthChartPjt_date = new ArrayList<>();
        	List<Integer> monthhartCompleteTask = new ArrayList<>();
        	LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                LocalDate endOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
                System.out.println(currentDate + " ~ " + endOfMonth);
                monthChartPjt_date.add(endOfMonth.format(formatter));
                monthhartCompleteTask.add(projectService.getCompletedTask(endOfMonth.toString(), pjt_id));
                
                currentDate = endOfMonth.plusDays(1);
            }
            mo.addAttribute("chartPjt_date", monthChartPjt_date);
        	mo.addAttribute("chartCompleteTask", monthhartCompleteTask);
        }
        
        
		mo.addAttribute("pVO", pVO);
		
		// 고객사 정보
		UserVO cusUList = projectService.getCustomerUserDetail(pjt_id);
		// 관리자 정보
		UserVO adUList = projectService.getAdminUserDetail(pjt_id);
		// 작업자 정보
		List<UserVO> engiUList = projectService.getUserByProject(pjt_id);
		// 프로젝트에 참여하지 않은 작업자 리스트
		List<UserVO> notAddedUList = projectService.getMemberNotAdded(pjt_id, new Criteria());
		
		System.out.println(notAddedUList.size() + " 추가될 작업자의 명 수");
		
		// 사용안함
		int total = projectService.getNotAddedTotal(pjt_id);
		PageVO pageVO = new PageVO(cri, total);
		pageVO.setPnCount(5);
		mo.addAttribute("pageVO", pageVO);
		
		
		mo.addAttribute("cusUList", cusUList);
		mo.addAttribute("adUList", adUList);
		mo.addAttribute("engiUList", engiUList);
		mo.addAttribute("notAddedList", notAddedUList);
		
		List<TaskVO> tList = projectService.getTaskDetail(pjt_id);
		
		
		if(tList.size() > 0) {
			mo.addAttribute("tList", tList);
		} else {
			
			mo.addAttribute("errMsg", "작업이 없습니다");
		}
		
		List<FileVO> fList = projectService.getFileName(pjt_id);
		
		for(FileVO fVO : fList) {
			System.out.println(fVO.toString());
		}
		
		mo.addAttribute("fList", fList);
		
		
		return "/project/ProjectDetail";
	}
	
	
	@PostMapping("/submitForm")
	public String submitForm(ProjectVO pVO, @RequestParam(required = false, value = "users") List<String> users, HttpSession session) {
		
		
		System.out.println(pVO.toString());
		
		projectService.updateProjectInfo(pVO);
		
		
		//고객에게 승인 알림전송
//		notificationService.createProjectNotification((String)session.getAttribute("user_id"), pVO.getReq_user_id(), pVO.getPjt_nm());
		
		if(users != null) {
			for(int i = 0; i < users.size(); i++) {
				projectService.insertUserInfo(users.get(i), pVO.getPjt_id());
				
				//작업자에게 할당 알림전송
//				notificationService.createProjectNotification(users.get(i), "ADMIN", pVO.getPjt_nm());
			}
		}
		
		
		return "redirect:/project/ProjectMain";
	}
	
	@PostMapping("/updateMemberForm")
	public String updateMemberForm(@RequestParam(required = false, value = "users") List<String> users,
								   int pjt_id, Model mo, RedirectAttributes ra) {
		
		System.out.println(users.toString());
		
		ra.addFlashAttribute("pjt_id", pjt_id);
		
		for(String user : users) {
			projectService.updateMemberAtProject(user, pjt_id);
		}
		
		
		
		return "redirect:/project/getDetail?pjt_id=" + pjt_id;
	}
	
	
	
	
	@GetMapping("/getFile")
	public String getFile(@RequestParam("keyName") String keyName, RedirectAttributes ra) {
		
		System.out.println(keyName + " 키네임========");
		
		boolean result = s3Service.readFiles(keyName);
		
//			s3Service.checkFile(keyName);
		if(result) {
			s3Service.downloadFiles(keyName);
		} else {
			ra.addFlashAttribute("noFileMsg", "다운로드 하려는 파일이 없습니다. 문의를 남겨 주세요.");
		}
		
		
		
		
		
		return "redirect:/project/ProjectMain";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
