package alcaide.bautista.pmdm02_mab_v01;

import static java.util.Objects.requireNonNull;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

import alcaide.bautista.pmdm02_mab_v01.databinding.CharacterListFragmentBinding;

/**
 * Fragmento que muestra una lista de personajes en una RecyclerView.
 * Este fragmento carga y muestra una lista de personajes en el interfaz de usuario,
 * configurando también un mensaje de bienvenida a través de un Snackbar.
 * Además, se encarga de ajustar el título del ActionBar cuando la actividad comienza.
 */
public class CharacterListFragment extends Fragment {

    // Objeto de binding para la vista del fragmento
    private CharacterListFragmentBinding binding;

    // Lista que contiene los personajes a mostrar
    private ArrayList<CharacterData> characters;

    /**
     * Método que se llama para crear la vista del fragmento.
     * Se infla el layout del fragmento y se muestra un mensaje de bienvenida utilizando un Snackbar.

     * @param inflater           El inflador de la vista.
     * @param container          El contenedor en el que se va a mostrar la vista.
     * @param savedInstanceState El estado previamente guardado de la instancia (si existe).
     * @return La vista inflada para este fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflar el layout utilizando el binding generado
        binding = CharacterListFragmentBinding.inflate(inflater, container, false);

        // Mostrar el mensaje de bienvenida utilizando un Snackbar
        Snackbar.make(binding.getRoot(), getString(R.string.welcome_message), Snackbar.LENGTH_SHORT).show();

        // Retornar la raíz del binding, que es la vista principal del fragmento
        return binding.getRoot();
    }

    /**
     * Método que se llama después de que la vista ha sido creada.
     * En este método, se carga la lista de personajes y se configura el adaptador para la RecyclerView.
     * @param view               La vista inflada para el fragmento.
     * @param savedInstanceState El estado previamente guardado de la instancia (si existe).
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Cargar los personajes en la lista
        loadCharacters();

        // Crear un adaptador para la RecyclerView con la lista de personajes y la actividad actual
        CharacterRecyclerViewAdapter adapter = new CharacterRecyclerViewAdapter(characters, getActivity());

        // Establecer un LinearLayoutManager para la RecyclerView, para una vista en lista vertical
        binding.charactersRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        // Establecer el adaptador en la RecyclerView
        binding.charactersRecyclerview.setAdapter(adapter);
    }

    /**
     * Método que carga los personajes en la lista de personajes.
     * Cada personaje se crea utilizando datos como nombre, imagen, descripción, habilidades, sonidos y fondo.
     * Este método podría ser modificado para cargar los datos desde una base de datos o una API.
     */

    private void loadCharacters() {
        characters = new ArrayList<>();

        characters.add(new CharacterData(
                R.drawable.mario, // Imagen
                getString(R.string.mario), // Nombre
                getString(R.string.description_mario), // Descripción
                getString(R.string.skills_mario), // Habilidades
                new ArrayList<>(Arrays.asList(R.raw.sound_mario_0, R.raw.sound_mario_1, R.raw.sound_mario_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_mario // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.luigi, // Imagen
                getString(R.string.luigi), // Nombre
                getString(R.string.description_luigi), // Descripción
                getString(R.string.skills_luigi), // Habilidades
                new ArrayList<>(Arrays.asList(R.raw.sound_luigi_0, R.raw.sound_luigi_1, R.raw.sound_luigi_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_luigi // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.peach, // Imagen
                getString(R.string.peach), // Nombre
                getString(R.string.description_peach), // Descripción
                getString(R.string.skills_peach), // Habilidades
                new ArrayList<>(Arrays.asList(R.raw.sound_peach_0, R.raw.sound_peach_1, R.raw.sound_peach_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_peach // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.toad, // Imagen
                getString(R.string.toad), // Nombre
                getString(R.string.description_toad), // Descripción
                getString(R.string.skills_toad), // Habilidades
                new ArrayList<>(Arrays.asList(R.raw.sound_toad_0, R.raw.sound_toad_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_toad // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.bowser, // Imagen
                getString(R.string.bowser), // Nombre
                getString(R.string.description_bowser), // Descripción
                getString(R.string.skills_bowser), // Habilidades
                new ArrayList<>(Arrays.asList(R.raw.sound_bowser_0, R.raw.sound_bowser_1, R.raw.sound_bowser_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_bowser // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.yoshi, // Imagen
                getString(R.string.yoshi), // Nombre
                getString(R.string.description_yoshi), // Descripción
                getString(R.string.skills_yoshi), // Habilidades
                new ArrayList<>(Arrays.asList(R.raw.sound_yoshi_0, R.raw.sound_yoshi_1, R.raw.sound_yoshi_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_yoshi // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.daisy, // Imagen
                getString(R.string.daisy), // Nombre
                getString(R.string.description_daisy), // Descripción
                getString(R.string.skills_daisy), // Habilidades
                new ArrayList<>(Arrays.asList(R.raw.sound_daisy_0, R.raw.sound_daisy_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_daisy // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.wario, // Imagen
                getString(R.string.wario), // Nombre
                getString(R.string.description_wario), // Descripción
                getString(R.string.skills_wario), // Habilidades
                new ArrayList<>(Arrays.asList(R.raw.sound_wario_0, R.raw.sound_wario_1, R.raw.sound_wario_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_wario // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.waluigi, // Imagen
                getString(R.string.waluigi), // Nombre
                getString(R.string.description_waluigi), // Descripción
                getString(R.string.skills_waluigi), // Habilidades
                new ArrayList<>(Arrays.asList(R.raw.sound_waluigi_0, R.raw.sound_waluigi_1, R.raw.sound_waluigi_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_waluigi // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.boo, // Imagen
                getString(R.string.boo), // Nombre
                getString(R.string.description_boo), // Descripción
                getString(R.string.skills_boo), // Habilidades
                new ArrayList<>(Arrays.asList(R.raw.sound_boo_0, R.raw.sound_boo_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_boo // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.dk, // Imagen
                getString(R.string.dk), // Nombre
                getString(R.string.description_dk), // Descripción
                getString(R.string.skills_dk), // Habilidades
                new ArrayList<>(Arrays.asList(R.raw.sound_donkey_kong_0, R.raw.sound_donkey_kong_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_dk // Fondo
        ));
    }

    /**
     * Método que se llama cuando el fragmento es visible y está listo para interactuar con el usuario.
     * Aquí se cambia el título del ActionBar para reflejar el contexto actual.
     */
    @Override
    public void onStart() {
        super.onStart();

        // Cambiar el título del ActionBar para este fragmento
        if (getActivity() != null) {
            requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.app_character_list);
        }
    }
}