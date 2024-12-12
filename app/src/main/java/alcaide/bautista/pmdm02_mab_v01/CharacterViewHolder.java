package alcaide.bautista.pmdm02_mab_v01;

import androidx.recyclerview.widget.RecyclerView;
import alcaide.bautista.pmdm02_mab_v01.databinding.CharacterCardviewBinding;

public class CharacterViewHolder extends RecyclerView.ViewHolder {

    private CharacterCardviewBinding binding;

    public CharacterViewHolder(CharacterCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind (CharacterData characters){
        binding.image.setImageResource(characters.getImage());
        binding.name.setText(characters.getName());
        binding.executePendingBindings();
    }

}
