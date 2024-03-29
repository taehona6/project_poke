package com.poke.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poke.www.domain.ItemStorageVO;
import com.poke.www.domain.PokemonStorageVO;
import com.poke.www.domain.ProductVO;
import com.poke.www.repository.StorageMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService{
	private final StorageMapper mapper;

	@Override
	public int addItem(String memberId, int productId) {
		
		return mapper.insertItem(memberId,productId);
	}

	@Override
	public List<ItemStorageVO> getItemsByMemberId(String memberId) {
		return mapper.selectItemsByMemberId(memberId);
	}

	@Override
	public int removeItemByStorageId(int storageId) {
		return mapper.deleteItemByStorageId(storageId);
	}

	@Override
	public int getProductIdByStorageId(int storageId) {
		return mapper.selectProductIdByStorageId(storageId);
	}

	@Override
	public ProductVO getProductByStorageId(int storageId) {
		return mapper.selectProductByStorageId(storageId);
	}

	@Override
	public int addPokemon(String memberId,int pokemonId) {
		return mapper.insertPokemon(memberId,pokemonId);
	}

	@Override
	public List<PokemonStorageVO> getPokemonsByMemberId(String memberId) {
		return mapper.selectPokemonsByMemberId(memberId);
	}

	@Override
	public int removePokemonByStorageId(int storageId) {
		return mapper.deletePokemonByStorageId(storageId);
	}

	@Override
	public PokemonStorageVO getPokemonByStorageId(int storageId) {
		return mapper.selectPokemonByStorageId(storageId);
	}

	@Override
	public List<PokemonStorageVO> getPokemonsByMemberIdAndNotInPokemonIds(String memberId, String addedPokemonIds) {
		return mapper.selectPokemonsByMemberIdAndNotInPokemonIds(memberId,addedPokemonIds);
	}
}
