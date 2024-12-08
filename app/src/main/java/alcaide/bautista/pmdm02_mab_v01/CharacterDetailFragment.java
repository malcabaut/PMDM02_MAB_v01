package alcaide.bautista.pmdm02_mab_v01;

import static java.util.Objects.*;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Random;

import alcaide.bautista.pmdm02_mab_v01.databinding.CharacterDetailFragmentBinding;


public class CharacterDetailFragment extends Fragment {

    private CharacterDetailFragmentBinding binding;
    private int[] soundArray; // Array para almacenar los sonidos
    private String characterName; // Nombre del personaje
    private static final String DK_NAME = "Donkey Kong"; // Nombre específico del personaje DK (debe coincidir con tu String)

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
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
            characterName = name;
            String description = getArguments().getString("description");
            String skill = getArguments().getString("skills");
            int background = getArguments().getInt("background");  // Obtener el fondo del personaje
            // Recuperar el array de sonidos desde los argumentos
            soundArray = getArguments().getIntArray("sounds");

            // Establecer los valores en los elementos del layout
            binding.image.setImageResource(image);
            binding.name.setText(name);
            binding.description.setText(description);
            binding.skills.setText(skill);

            // Verificar que el valor de background sea válido
            Log.d("CharacterDetail", "Background resource: " + background);  // Asegúrate de que no sea 0

            binding.itemDetailFragment.setBackgroundResource(background);

            requireNonNull(binding.image).setOnTouchListener(this::onTouch);

            // Configurar evento de clic en la imagen
            binding.image.setOnClickListener(v -> playRandomSound());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.app_character_description);
        }
    }


    // Manejar el toque con dos dedos
    private void handleDoubleTouch() {
        if (characterName != null && characterName.equalsIgnoreCase(DK_NAME)) {
            playEasterEggVideo(); // Reproducir el video si el personaje es DK
            Log.d("CharacterDetail", "El personaje es:"+characterName);
        } else {
            Log.d("CharacterDetail", "El personaje no es DK. Toque ignorado.");
        }
    }

    // Método para reproducir un sonido aleatorio
    private void playRandomSound() {
        if (soundArray != null && soundArray.length > 0) {
            // Seleccionar un sonido aleatorio
            int randomSound = soundArray[new Random().nextInt(soundArray.length)];

            // Crear el reproductor de sonido
            MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), randomSound);

            // Iniciar la reproducción
            mediaPlayer.start();

            // Liberar el reproductor una vez que termine de reproducir
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        } else {
            Log.w("CharacterDetail", "No hay sonidos disponibles para reproducir.");
        }
    }

    // Método para reproducir el video
    private void playEasterEggVideo() {
        Log.d("CharacterDetail", "Reproduciendo video del Easter Egg para DK.");
        binding.videoView.setVisibility(View.VISIBLE); // Asegúrate de tener el VideoView en tu layout

        Uri videoUri = Uri.parse("android.resource://" + requireContext().getPackageName() + "/" + R.raw.easter_egg_01);
        binding.videoView.setVideoURI(videoUri);

        binding.videoView.setOnPreparedListener(MediaPlayer::start);
        binding.videoView.setOnCompletionListener(mp -> {
            binding.videoView.setVisibility(View.GONE); // Ocultar el VideoView después de terminar
        });
    }

    private boolean onTouch(View v, MotionEvent event) {
        if (event.getPointerCount() == 2 && event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
            handleDoubleTouch(); // Aquí manejas el toque con 2 dedos
            v.performClick(); // Llamamos a performClick para manejar la accesibilidad
            return true;
        }
        return false;
    }
}
