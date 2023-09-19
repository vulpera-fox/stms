package com.project.stms.service.question;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
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
}
