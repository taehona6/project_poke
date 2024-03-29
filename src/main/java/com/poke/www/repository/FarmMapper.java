package com.poke.www.repository;

import org.apache.ibatis.annotations.Mapper;

import com.poke.www.domain.FarmVO;

@Mapper
public interface FarmMapper {

	void insertPokemon(String memberId, String string, String string2, String string3, String string4, String string5, int totalPoint);

	FarmVO selectFarmList(String memberId);

	void deletePokemon(String memberId);

	String selectEndDate(String memberId);

	int selectTotalPoint(String memberId);

}
