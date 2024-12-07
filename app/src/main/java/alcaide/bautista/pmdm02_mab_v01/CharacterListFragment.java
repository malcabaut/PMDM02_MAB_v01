package alcaide.bautista.pmdm02_mab_v01;
import android.os.Bundle;
import android.util.Log;
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
                getString(R.string.mario), // Nombre
                getString(R.string.description_mario), // Descripción
                getString(R.string.skills_mario), // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_mario_0, R.raw.sound_mario_1, R.raw.sound_mario_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_mario // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.luigi, // Imagen
                getString(R.string.luigi), // Nombre
                getString(R.string.description_luigi), // Descripción
                getString(R.string.skills_luigi), // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_luigi_0, R.raw.sound_luigi_1, R.raw.sound_luigi_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_luigi // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.peach, // Imagen
                getString(R.string.peach), // Nombre
                getString(R.string.description_peach), // Descripción
                getString(R.string.skills_peach), // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_peach_0, R.raw.sound_peach_1, R.raw.sound_peach_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_peach // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.toad, // Imagen
                getString(R.string.toad), // Nombre
                getString(R.string.description_toad), // Descripción
                getString(R.string.skills_toad), // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_toad_0, R.raw.sound_peach_1, R.raw.sound_peach_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_toad // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.bowser, // Imagen
                getString(R.string.bowser), // Nombre
                getString(R.string.description_bowser), // Descripción
                getString(R.string.skills_bowser), // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_bowser_0, R.raw.sound_bowser_1, R.raw.sound_bowser_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_bowser // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.yoshi, // Imagen
                getString(R.string.yoshi), // Nombre
                getString(R.string.description_yoshi), // Descripción
                getString(R.string.skills_yoshi), // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_yoshi_0, R.raw.sound_yoshi_1, R.raw.sound_yoshi_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_yoshi // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.daisy, // Imagen
                getString(R.string.daisy), // Nombre
                getString(R.string.description_daisy), // Descripción
                getString(R.string.skills_daisy), // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_daisy_0, R.raw.sound_daisy_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_daisy // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.wario, // Imagen
                getString(R.string.wario), // Nombre
                getString(R.string.description_wario), // Descripción
                getString(R.string.skills_wario), // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_wario_0, R.raw.sound_wario_1, R.raw.sound_wario_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_wario // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.waluigi, // Imagen
                getString(R.string.waluigi), // Nombre
                getString(R.string.description_waluigi), // Descripción
                getString(R.string.skills_waluigi), // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_waluigi_0, R.raw.sound_waluigi_1, R.raw.sound_waluigi_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_waluigi // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.boo, // Imagen
                getString(R.string.boo), // Nombre
                getString(R.string.description_boo), // Descripción
                getString(R.string.skills_boo), // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_boo_0, R.raw.sound_boo_2)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_boo // Fondo
        ));

        characters.add(new CharacterData(
                R.drawable.dk, // Imagen
                getString(R.string.dk), // Nombre
                getString(R.string.description_dk), // Descripción
                getString(R.string.skills_dk), // Habilidades
                new ArrayList<Integer>(Arrays.asList(R.raw.sound_donkey_kong_0, R.raw.sound_donkey_kong_1)), // Lista de sonidos con identificadores de recursos
                R.drawable.background_dk // Fondo
        ));
    }



    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ArrrMarinero");
        }
    }
}
