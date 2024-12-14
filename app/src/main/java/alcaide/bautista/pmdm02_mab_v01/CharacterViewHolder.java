package alcaide.bautista.pmdm02_mab_v01;

import androidx.recyclerview.widget.RecyclerView;
import alcaide.bautista.pmdm02_mab_v01.databinding.CharacterCardviewBinding;
/**
 * ViewHolder para un ítem de personaje en un RecyclerView.
 *
 * Esta clase enlaza los datos de un personaje con un diseño de tarjeta utilizando
 * el CharacterCardviewBinding, que es una clase de enlace generada para el layout XML.
 */
public class CharacterViewHolder extends RecyclerView.ViewHolder {
    // Objeto de enlace para acceder a las vistas dentro del layout de la tarjeta de personaje
    private CharacterCardviewBinding binding;

    /**
     * Constructor para el CharacterViewHolder.
     *
     * @param binding El objeto de enlace que permite acceder a las vistas en el layout.
     */
    public CharacterViewHolder(CharacterCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    /**
     * Enlaza los datos del personaje a las vistas en el layout.
     *
     * Este método establece la imagen y el nombre del personaje en las vistas correspondientes
     * en el layout y asegura que cualquier enlace pendiente se ejecute.
     *
     * @param characters Los datos del personaje que se enlazarán a las vistas del layout.
     */
    public void bind (CharacterData characters){
        // Estableciendo el recurso de imagen para la vista de imagen del personaje
        binding.image.setImageResource(characters.getImage());
        // Estableciendo el texto del nombre para la vista del nombre del personaje
        binding.name.setText(characters.getName());
        // Ejecutando cualquier enlace pendiente para actualizar las vistas con los nuevos datos
        binding.executePendingBindings();
    }

}
