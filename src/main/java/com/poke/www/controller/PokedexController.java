package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poke.www.domain.PokedexDTO;
import com.poke.www.domain.PokedexVO;
import com.poke.www.domain.PokemonStorageVO;
import com.poke.www.domain.PokemonVO;
import com.poke.www.service.MemberService;
import com.poke.www.service.PokedexService;
import com.poke.www.service.PokemonService;
import com.poke.www.service.StorageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pokedex")
public class PokedexController {
	private final PokedexService pokedexService;
	private final StorageService storageService;
	private final PokemonService pokemonService;
	private final MemberService memberService;
	
	@GetMapping("/{memberId}")
	public String getPokedexPage(@PathVariable("memberId")String memberId) {
		return "/pokedex/pokedex";
	}
	
	@PostMapping
	@ResponseBody
	public String registerPokedex(@RequestBody String strStorageId) {
		int storageId = Integer.parseInt(strStorageId);
		PokemonStorageVO pokemonStorageVO = storageService.getPokemonByStorageId(storageId);
		String memberId = pokemonStorageVO.getMemberId();
		int pokemonId = pokemonStorageVO.getPokemonId();
		PokemonVO pokemon = pokemonService.getPokemonByPokemonId(pokemonId);
		int score = pokemon.getScore();
		
		// 도감에 등록 되어있는지 확인
		if(pokedexService.getPokemonByMemberIdAndPokemonId(memberId,pokemonId)==null) {
			//도감에 포켓몬 추가
			pokedexService.addPokemon(memberId,pokemonId);
			//포켓몬보관함에서 포켓몬 삭제
			storageService.removePokemonByStorageId(storageId);
			//
			memberService.addScore(score,memberId);
		}else {
			return "already";
		}
			
		return "ok";
	}
	
	@PostMapping("/list")
	@ResponseBody
	public PokedexDTO getPokedexList(@RequestBody String memberId){
		List<PokemonVO> allPokemonList = pokemonService.getPokemons();
		List<PokedexVO> userPokemonList = pokedexService.getPokemonsByMemberId(memberId);
		
		return new PokedexDTO(userPokemonList,allPokemonList);
	}
}