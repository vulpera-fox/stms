package com.project.stms.service.news;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.stms.command.NewsVO;
import com.project.stms.util.NewsCriteria;

@Mapper
public interface NewsMapper {
	
	public ArrayList<NewsVO> getNews(NewsCriteria cri);
	public int getNewsTotal(NewsCriteria cri);
	public NewsVO getNewsDetail(int post_id);
	public NewsVO getNewsPrev(@Param("post_id")int post_id,
			 				  @Param("cri") NewsCriteria cri);
	public NewsVO getNewsNext(@Param("post_id")int post_id,
			  				  @Param("cri") NewsCriteria cri);
	
	public int updateNewsViewCount(int post_id);
	
}
