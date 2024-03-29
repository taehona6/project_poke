package com.poke.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.MemberVO;

@Mapper
public interface MemberMapper {

	int insertMember(MemberVO mvo);

	MemberVO selectMemberById(String loginId);

	int updatePointById(int price, String memberId);

	List<MemberVO> selectMembersOrderByScore();

	int updateScoreByMemberId(int score, String memberId);

	int updateHasProfile(String status, String memberId);

	MemberVO selectMemberByNickname(String nickname);

	int insertProfile(String memberId, String uuid);

	String selectProfileUuidByMemberId(String memberId);
	void updatePoint(String memberId, int point);

	int selectScoreByMemberId(String memberId);

}
