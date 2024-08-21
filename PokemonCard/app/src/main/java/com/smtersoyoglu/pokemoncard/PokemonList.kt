package com.smtersoyoglu.pokemoncard

// Pokémon verilerini sağlayan nesne.
object PokemonRepository {
    // Pokémon listesini döner.
    fun getPokemonList(): List<Pokemon> {
        return listOf(
            Pokemon("Bulbasaur", R.drawable.bulbasaur),
            Pokemon("Charmander", R.drawable.charmander),
            Pokemon("Charizard", R.drawable.charizard),
            Pokemon("Squirtle", R.drawable.squirtle),
            Pokemon("Pikachu", R.drawable.pikachu),
            Pokemon("Eevee", R.drawable.eevee),
            Pokemon("Jigglypuff", R.drawable.jigglypuff),
            Pokemon("Grimer", R.drawable.grimer),
            Pokemon("Psyduck", R.drawable.psyduck1),
            Pokemon("Gengar", R.drawable.gengar),
            Pokemon("Alakazam", R.drawable.alakazam),
            Pokemon("Arcanine", R.drawable.arcanine),
            Pokemon("Drowzee", R.drawable.drowzee),
            Pokemon("Hitmonlee", R.drawable.hitmonlee),
            Pokemon("Mankey", R.drawable.mankey),
            Pokemon("Pidgeotto", R.drawable.pidgeotto),
            Pokemon("Slowbro", R.drawable.slowbro),
            Pokemon("Snorlax", R.drawable.snorlax)
        )
    }
}