package com.project.stms.service.question;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.stms.command.NewsVO;
import com.project.stms.util.NewsCriteria;

@Mapper
public interface QuestionMapper {

	public ArrayList<NewsVO> getQlist(NewsCriteria cri);
	public int getTotal(NewsCriteria cri);
	public NewsVO getQdetail(int post_id);
	public int updateQviewCount(int post_id);
	public int regiQuestion(NewsVO vo);
	public int deleteQ(int post_id);
	public ArrayList<NewsVO> getMyQlist(@Param("cri")NewsCriteria cri, 
										@Param("user_id")String user_id);
	public int getQtotal(NewsCriteria cri);
	public int modifyQ(NewsVO vo);
	
}
