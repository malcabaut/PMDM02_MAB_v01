package alcaide.bautista.pmdm02_mab_v01;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import alcaide.bautista.pmdm02_mab_v01.databinding.CharacterCardviewBinding;

/**
 * Adaptador para el RecyclerView que muestra la lista de personajes.
 * Este adaptador maneja la vinculación de datos con las vistas y gestiona los eventos de clic en los ítems.
 */
public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private final ArrayList<CharacterData> characters; // Lista de personajes
    private final Context context; // Contexto para usar en el evento de clic

    /**
     * Constructor del adaptador.
     *
     * @param characters La lista de personajes a mostrar en el RecyclerView.
     * @param context El contexto para usar en la reproducción de sonidos y eventos de clic.
     */
    public CharacterRecyclerViewAdapter(ArrayList<CharacterData> characters, Context context) {
        this.characters = characters;
        this.context = context;
    }

    /**
     * Infla el layout para cada ítem del RecyclerView y crea el ViewHolder.
     *
     * @param parent El contenedor en el que se va a agregar el ítem.
     * @param viewType El tipo de vista que se está creando (no se utiliza en este caso).
     * @return El ViewHolder creado con el layout inflado.
     */
    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflamos el layout usando ViewBinding
        CharacterCardviewBinding binding = CharacterCardviewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        // Retornamos un nuevo ViewHolder con el binding
        return new CharacterViewHolder(binding);
    }

    /**
     * Vincula los datos del personaje con el ViewHolder correspondiente.
     *
     * @param holder El ViewHolder donde se vinculan los datos.
     * @param position La posición del ítem en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        // Obtenemos el personaje actual de la lista
        CharacterData currentCharacter = this.characters.get(position);

        // Enlazamos los datos del personaje con el ViewHolder
        holder.bind(currentCharacter);

        // Manejo del evento de clic
        holder.itemView.setOnClickListener(view -> {
            view.setEnabled(false);  // Deshabilitar la interacción para evitar clics rápidos
            // Cambiar el fondo al hacer clic para simular un "toque"
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorToque));  // Color de fondo al tocar

            // Obtener el mensaje y mostrar el Toast con el nombre del personaje
            String message = context.getString(R.string.app_select_character) + " " + currentCharacter.getName();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            // Animación de escala para el toque
            view.animate().scaleX(0.90f).scaleY(0.90f).setDuration(250).withEndAction(() -> {
                // Restauramos la escala al tamaño original después de la animación
                view.animate().scaleX(1f).scaleY(1f).setDuration(100);
            });

            // Devolver el color de fondo original después de un pequeño retraso (simulando un "toque" rápido)
            view.postDelayed(() -> view.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent)), 200);

            // Reproducir el sonido asociado al personaje
            playCharacterSound(currentCharacter);

            // Usamos un Handler para retrasar la transición después de las animaciones
            new Handler(Looper.getMainLooper()).postDelayed(() -> itemClicked(currentCharacter, view), 500);  // Retrasar 500 ms para esperar que las animaciones hayan terminado
        });
    }

    /**
     * Reproduce el sonido asociado a un personaje.
     * Usa MediaPlayer para reproducir el sonido del personaje si está disponible.
     *
     * @param character El personaje cuyo sonido se va a reproducir.
     */
    private void playCharacterSound(CharacterData character) {
        // Verificar que el personaje tenga un sonido asociado
        if (character.getSound() != null && !character.getSound().isEmpty()) {
            // Obtener un sonido aleatorio asociado al personaje
            int soundResId = character.getRandomSound();

            // Reproducir el sonido usando MediaPlayer
            MediaPlayer mediaPlayer = MediaPlayer.create(context, soundResId);
            mediaPlayer.start();

            // Liberar el MediaPlayer cuando termine de reproducir el sonido
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        }
    }

    /**
     * Devuelve el número de ítems en el RecyclerView.
     *
     * @return El tamaño de la lista de personajes.
     */
    @Override
    public int getItemCount() {
        return characters.size(); // Retorna el tamaño de la lista de personajes
    }

    /**
     * Maneja el evento de clic en un ítem del RecyclerView.
     * Llama al método `characterClicked` en la actividad principal.
     *
     * @param currentCharacter El personaje que fue clickeado.
     * @param view La vista del ítem clickeado.
     */
    private void itemClicked(CharacterData currentCharacter, View view) {
        // Llama al método characterClicked en MainActivity pasando el personaje y la vista
        ((MainActivity) context).characterClicked(currentCharacter, view);
    }
}
