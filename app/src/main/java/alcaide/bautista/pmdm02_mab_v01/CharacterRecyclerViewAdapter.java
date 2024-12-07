package alcaide.bautista.pmdm02_mab_v01;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import alcaide.bautista.pmdm02_mab_v01.databinding.CharacterCardviewBinding;

public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private final ArrayList<CharacterData> characters; // Lista de personajes
    private final Context context; // Contexto para usar en el evento de clic

    // Constructor que recibe la lista de personajes y el contexto
    public CharacterRecyclerViewAdapter(ArrayList<CharacterData> characters, Context context){
        this.characters = characters;
        this.context = context;
    }

    // Método que infla el layout y crea el ViewHolder
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

    // Método que vincula los datos con el ViewHolder
    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        // Obtenemos el personaje actual de la lista
        CharacterData currentCharacter = this.characters.get(position);

        // Enlazamos los datos del personaje con el ViewHolder
        holder.bind(currentCharacter);

// Manejo del evento de clic
        holder.itemView.setOnClickListener(view -> {
            // Cambiar el fondo al hacer clic para simular un "toque"
            view.setBackgroundColor(context.getResources().getColor(R.color.colorToque));  // Color de fondo al tocar

            // Animación de escala para el toque
            view.animate().scaleX(0.90f).scaleY(0.90f).setDuration(250).withEndAction(() -> {
                // Después de la animación, restauramos la escala al tamaño original
                view.animate().scaleX(1f).scaleY(1f).setDuration(100);
            });

            // Devolver el color de fondo original después de un pequeño retraso (simulando un "toque" rápido)
            view.postDelayed(() -> view.setBackgroundColor(context.getResources().getColor(android.R.color.transparent)), 200); // Vuelve al color original después de 200 ms

            // Reproducir el sonido asociado al personaje
            playCharacterSound(currentCharacter);

            // Usamos un Handler para retrasar la transición después de las animaciones
            new Handler().postDelayed(() -> {
                // Llamar al método de la actividad principal con el personaje y la vista
                itemClicked(currentCharacter, view);
            }, 500);  // Retrasar 400 ms para esperar que las animaciones hayan terminado
        });


    }

    // Método para reproducir el sonido del personaje
    private void playCharacterSound(CharacterData character) {
        // Usamos MediaPlayer para reproducir el sonido
        if (character.getSound() != null && !character.getSound().isEmpty()) {
            // Obtener el primer sonido de la lista
            int soundResId = character.getRandomSound(); // Usamos el primer sonido de la lista

            // Reproducir el sonido
            MediaPlayer mediaPlayer = MediaPlayer.create(context, soundResId);
            mediaPlayer.start();

            // Libera el MediaPlayer cuando haya terminado de reproducir el sonido
            mediaPlayer.setOnCompletionListener(mp -> mp.release());
        }
    }

    // Método que retorna el número de ítems en el RecyclerView
    @Override
    public int getItemCount() {
        return characters.size(); // Retorna el tamaño de la lista de personajes
    }

    // Método que maneja el evento de clic en un ítem
    private void itemClicked(CharacterData currentCharacter, View view) {
        // Llama al método characterClicked en MainActivity pasando el personaje y la vista
        ((MainActivity) context).characterClicked(currentCharacter, view);
    }
}