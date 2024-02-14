package com.poke.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poke.www.domain.PokedexVO;
import com.poke.www.domain.PokemonStorageVO;
import com.poke.www.service.PokedexService;
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
	
	@GetMapping
	public String getPokedexPage() {
		return "/pokedex/pokedex";
	}
	
	@PostMapping
	@ResponseBody
	public String registerPokedex(@RequestBody String strStorageId) {
		int storageId = Integer.parseInt(strStorageId);
		PokemonStorageVO pokemon = storageService.getPokemonByStorageId(storageId);
		
		if(pokedexService.getPokemonByMemberIdAndPokemonId(pokemon.getMemberId(),pokemon.getPokemonId())==null) {
			pokedexService.addPokemon(pokemon.getMemberId(),pokemon.getPokemonId());
			storageService.removePokemonByStorageId(storageId);
		}else {
			return "already";
		}
		
		
		return "ok";
	}
	
}