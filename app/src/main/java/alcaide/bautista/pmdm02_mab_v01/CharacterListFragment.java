package alcaide.bautista.pmdm02_mab_v01;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.Arrays;

import alcaide.bautista.pmdm02_mab_v01.databinding.CharacterListFragmentBinding;

public class CharacterListFragment extends Fragment {

    private CharacterListFragmentBinding binding;
    private ArrayList<CharacterData> characters;
    private CharacterRecyclerViewAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = CharacterListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loadCharacters();


        adapter = new CharacterRecyclerViewAdapter(characters, getActivity());
        binding.charactersRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.charactersRecyclerview.setAdapter(adapter);


    }


    private void loadCharacters() {
        characters = new ArrayList<CharacterData>();

        characters.add(new CharacterData(
                R.drawable.mario, // Imagen
                "Mario", // Nombre
                "Mario es el héroe principal del Reino Champiñón. Es valiente, optimista y siempre dispuesto a ayudar. Se le reconoce por su overol azul, su gorra roja y su característico bigote. Es conocido por salvar a la Princesa Peach y derrotar a Bowser en múltiples ocasiones.", // Descripción
                "Habilidad: Salta más alto que cualquier otro, corre a gran velocidad y tiene gran resistencia.", // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_mario_0, R.raw.sound_mario_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.mario_fondo // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.luigi, // Imagen
                "Luigi", // Nombre
                "Luigi es el hermano de Mario y un héroe en el Reino Champiñón. Se distingue por su overol verde y su gorra del mismo color. Aunque más miedoso que su hermano, siempre está dispuesto a enfrentar cualquier desafío.", // Descripción
                "Habilidad: Salta más alto que Mario, tiene una mayor capacidad para evitar peligros debido a su agilidad, pero es más tímido en situaciones extremas.", // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_luigi_0, R.raw.sound_luigi_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.luigi_fondo // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.peach, // Imagen
                "Princess Peach", // Nombre
                "La Princesa Peach es la líder amable y carismática del Reino Champiñón. Siempre se preocupa por sus súbditos y busca crear un mundo armonioso. Reconocida por su vestido rosa y su naturaleza bondadosa, también sabe defenderse cuando es necesario.", // Descripción
                "Habilidad: Puede flotar y planear en el aire por períodos cortos. Usa magia para curarse y ayudar a otros.", // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_peach_0, R.raw.sound_peach_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.peach_fondo // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.toad, // Imagen
                "Toad", // Nombre
                "Toad es un residente fiel del Reino Champiñón y sirve a la Princesa Peach. Reconocido por su distintiva cabeza con manchas rojas, es rápido y de gran valor, siempre listo para ayudar a sus amigos.", // Descripción
                "Habilidad: Extraordinaria velocidad y una fuerza sorprendente para su tamaño, capaz de levantar objetos más grandes que él.", // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_toad_0, R.raw.sound_toad_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.toad_fondo // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.bowser, // Imagen
                "Bowser", // Nombre
                "Bowser, el temible rey de los Koopas, es el archienemigo de Mario y la mayor amenaza para el Reino Champiñón. Con su apariencia de gran tamaño, su caparazón de espinas y su imponente actitud, siempre tiene planes malvados en mente.", // Descripción
                "Habilidad: Puede escupir fuego a gran distancia y tiene una increíble fuerza, capaz de causar temblores al golpear el suelo.", // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_bowser_0, R.raw.sound_bowser_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.bowser_fondo // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.yoshi, // Imagen
                "Yoshi", // Nombre
                "Yoshi es un dinosaurio de la Isla de Yoshi que se ha convertido en el compañero más confiable de Mario. Es conocido por su lealtad y su increíble habilidad para comerse a los enemigos y objetos con su larga lengua.", // Descripción
                "Habilidad: Su lengua es increíblemente larga y le permite devorar frutas y enemigos, además de ser capaz de saltar muy alto.", // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_yoshi_0, R.raw.sound_yoshi_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.yoshi_fondo // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.daisy, // Imagen
                "Princess Daisy", // Nombre
                "La Princesa Daisy es la alegre y valiente líder de Sarasaland. Con su vestido amarillo y su naturaleza extrovertida, se une a Mario y sus amigos en diversos deportes y aventuras. Es conocida por su gran coraje y energía.", // Descripción
                "Habilidad: Puede flotar en el aire y utilizar magia para dar apoyo a sus amigos y hacer que el ambiente se vuelva más favorable.", // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_daisy_0, R.raw.sound_daisy_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.daisy_fondo // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.wario, // Imagen
                "Wario", // Nombre
                "Wario es el autoproclamado archienemigo de Mario. Viste un overol morado y una gorra amarilla, además de su característico bigote en zigzag. Es egoísta y busca la riqueza a toda costa.", // Descripción
                "Habilidad: Salta alto, corre rápido, y tiene una fuerza impresionante. También es experto en el uso de trucos y trampas.", // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_wario_0, R.raw.sound_wario_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.wario_fondo // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.waluigi, // Imagen
                "Waluigi", // Nombre
                "Waluigi es el aliado y rival de Wario. Tiene una actitud despectiva hacia Mario y Luigi, y siempre está buscando formas de arruinar sus planes. Con sus largas piernas y brazos, es ágil y astuto.", // Descripción
                "Habilidad: Usa trucos sucios para desequilibrar a sus oponentes en el campo de juego, y sus extremidades largas lo hacen muy ágil.", // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_waluigi_0, R.raw.sound_waluigi_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.waluigi_fondo // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.boo, // Imagen
                "Boo", // Nombre
                "Boo es un fantasma travieso que habita en lugares oscuros y abandonados. Aunque suele esconderse, siempre está dispuesto a hacerle la vida más difícil a los héroes del Reino Champiñón.", // Descripción
                "Habilidad: Puede atravesar paredes y volverse invisible para espiar a sus enemigos sin ser detectado.", // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_boo_0, R.raw.sound_boo_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.boo_fondo // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.dk, // Imagen
                "Donkey Kong", // Nombre
                "Donkey Kong es el rey de la jungla, conocido por su increíble fuerza y su amor por los plátanos. Con su corbata roja con sus iniciales y su aspecto imponente, es un defensor feroz de su territorio.", // Descripción
                "Habilidad: Super fuerza, capaz de lanzar barriles y causar temblores al golpear el suelo. Su resistencia es prácticamente inigualable.", // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_donkey_kong_0, R.raw.sound_donkey_kong_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.dk_fondo // Fondo
        ));





    }

    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.lista_de_presonajes);
        }
    }
}
