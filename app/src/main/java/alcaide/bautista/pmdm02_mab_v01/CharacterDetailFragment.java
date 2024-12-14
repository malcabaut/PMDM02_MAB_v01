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

/**
 * Fragmento para mostrar los detalles de un personaje.
 * Este fragmento permite mostrar la imagen, nombre, descripción, habilidades y reproducir sonidos o un video de Easter Egg si el personaje es "Donkey Kong".
 */
public class CharacterDetailFragment extends Fragment {

    private CharacterDetailFragmentBinding binding;
    private int[] soundArray; // Array para almacenar los sonidos
    private String characterName; // Nombre del personaje
    private static final String DK_NAME = "Donkey Kong"; // Nombre específico del personaje DK (debe coincidir con tu String)

    /**
     * Infla el layout para este fragmento.
     *
     * @param inflater El objeto LayoutInflater que se usa para inflar el layout.
     * @param container El contenedor en el que se añadirá la vista inflada.
     * @param savedInstanceState El estado guardado del fragmento, si lo hay.
     * @return La vista inflada para este fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Configura los elementos de la vista después de que la vista haya sido creada.
     *
     * @param view La vista que se ha creado para este fragmento.
     * @param savedInstanceState El estado guardado del fragmento, si lo hay.
     */
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

            // Obtener los datos del personaje
            int image = getArguments().getInt("image");
            String name = getArguments().getString("name");
            characterName = name;
            String description = getArguments().getString("description");
            String skill = getArguments().getString("skills");
            int background = getArguments().getInt("background");  // Obtener el fondo del personaje
            soundArray = getArguments().getIntArray("sounds"); // Recuperar el array de sonidos

            // Establecer los valores en los elementos del layout
            binding.image.setImageResource(image);
            binding.name.setText(name);
            binding.description.setText(description);
            binding.skills.setText(skill);

            // Establecer el fondo del personaje
            Log.d("CharacterDetail", "Background resource: " + background);  // Asegúrate de que no sea 0
            binding.itemDetailFragment.setBackgroundResource(background);

            // Configurar el listener para el toque en la imagen
            requireNonNull(binding.image).setOnTouchListener(this::onTouch);

            // Configurar el evento de clic en la imagen para reproducir sonido
            binding.image.setOnClickListener(v -> playRandomSound());
        }
    }

    /**
     * Cambia el título del ActionBar cuando el fragmento empieza.
     */
    @Override
    public void onStart() {
        super.onStart();
        // Cambiar el título del ActionBar
        if (getActivity() != null) {
            requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.app_character_description);
        }
    }

    /**
     * Maneja el toque con dos dedos en la pantalla. Si el personaje es "Donkey Kong",
     * reproduce un video de Easter Egg.
     */
    private void handleDoubleTouch() {
        if (characterName != null && characterName.equalsIgnoreCase(DK_NAME)) {
            playEasterEggVideo(); // Reproducir el video si el personaje es DK
            Log.d("CharacterDetail", "El personaje es:" + characterName);
        } else {
            Log.d("CharacterDetail", "El personaje no es DK. Toque ignorado.");
        }
    }

    private MediaPlayer mediaPlayer;

    /**
     * Reproduce un sonido aleatorio desde el array de sonidos.
     * Si no hay sonidos disponibles, muestra una advertencia.
     */
    private void playRandomSound() {
        if (soundArray != null && soundArray.length > 0) {
            // Seleccionar un sonido aleatorio
            int randomSound = soundArray[new Random().nextInt(soundArray.length)];
            if (mediaPlayer != null) {
                mediaPlayer.release();  // Liberar el media player anterior
            }
            mediaPlayer = MediaPlayer.create(getContext(), randomSound);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(mp -> mediaPlayer.release()); // Liberar el MediaPlayer al terminar de reproducir
        } else {
            Log.w("CharacterDetail", "No hay sonidos disponibles para reproducir.");
        }
    }

    /**
     * Reproduce el video de Easter Egg si el personaje es "Donkey Kong".
     */
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

    /**
     * Detecta el toque en la pantalla. Si el toque es con dos dedos, ejecuta el método
     * para manejar el toque y reproducir el Easter Egg.
     *
     * @param v La vista en la que ocurrió el toque.
     * @param event El evento de toque que contiene detalles sobre el gesto.
     * @return true si el toque es manejado, false de lo contrario.
     */
    private boolean onTouch(View v, MotionEvent event) {
        if (event.getPointerCount() == 2 && event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
            handleDoubleTouch(); // Aquí manejas el toque con 2 dedos
            v.performClick(); // Llamamos a performClick para manejar la accesibilidad
            return true;
        }
        return false;
    }
}
