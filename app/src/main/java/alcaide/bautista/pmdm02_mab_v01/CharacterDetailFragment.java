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


import alcaide.bautista.pmdm02_mab_v01.databinding.CharacterDetailFragmentBinding;


public class CharacterDetailFragment extends Fragment {

    private CharacterDetailFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener datos del argumento que inicia este fragmento
        if (getArguments() != null) {

            // Iterar sobre todos los argumentos del Bundle
            for (String key : getArguments().keySet()) {
                Object value = getArguments().get(key);
                Log.d("CharacterDetail", "Key: " + key + " Value: " + value);
            }

            int image = getArguments().getInt("image");
            String name = getArguments().getString("name");
            String description = getArguments().getString("description");
            String skill = getArguments().getString("skills");
            int background = getArguments().getInt("background");  // Obtener el fondo del personaje

            // Establecer los valores en los elementos del layout
            binding.image.setImageResource(image);
            binding.name.setText(name);
            binding.description.setText(description);
            binding.skills.setText(skill);

            // Verificar que el valor de background sea válido
            Log.d("CharacterDetail", "Background resource: " + background);  // Asegúrate de que no sea 0

            binding.itemDetailFragment.setBackgroundResource(background);

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("hola");
        }
    }
}
