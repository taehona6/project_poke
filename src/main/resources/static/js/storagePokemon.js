
spreadPokemons()

function spreadPokemons(){
    getPokemonListFromServer(memberId).then(result=>{
		console.log(result)
        let pokemonBox = document.getElementById('pokemonBox')
        pokemonBox.innerHTML = ''
        if(result.length>0){
            for(let pokemon of result){
                pokemonBox.innerHTML +=  `
                <div style="border:1px solid black; width:200px;float:left">
	                <p>[${pokemon.pokemonId}] ${pokemon.name}</p>
	               
                </div>
                `
            }
        }else{
            pokemonBox.innerHTML = `<p>보유중인 포켓몬이 없습니다.</p>`
        }
    })
}

async function getPokemonListFromServer(memberId){
    try {
        const url = "/storage/pokemon-list/"+memberId
        const resp = await fetch(url)
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error)
    }
}




