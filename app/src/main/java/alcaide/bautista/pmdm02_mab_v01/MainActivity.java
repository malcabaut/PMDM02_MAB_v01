package alcaide.bautista.pmdm02_mab_v01;

import static androidx.core.app.PendingIntentCompat.getActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


import alcaide.bautista.pmdm02_mab_v01.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura el Toolbar como el ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar); // Asegúrate de que tienes un Toolbar en tu layout
        setSupportActionBar(toolbar);

        // Configurar el título del ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Nuevo Título");
        }

        // Configura del controlador de navegación.
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

    }



    // Método para manejar el clic en un personaje
    public void characterClicked(CharacterData character, View view) {
        // Crear un Bundle para pasar los datos al fragmento de detalles del personaje
        Bundle bundle = new Bundle();

        // Pasa el nombre del personaje
        bundle.putString("name", character.getName());

        // Pasa el identificador de la imagen del personaje
        bundle.putInt("image", character.getImage());

        // Pasa la descripción del personaje
        bundle.putString("description", character.getDescription());

        // Pasa las habilidades del personaje, asegurándose de que no sea null
        String skills = character.getSkills() != null ? character.getSkills() : "Habilidades no disponibles";
        bundle.putString("skills", skills);

        // Pasa el fondo del personaje, asegurándose de que el valor no sea 0 (lo que indicaría que no se ha establecido)
        int background = character.getBackground() != 0 ? character.getBackground() : R.drawable.background_mario;
        bundle.putInt("background", background);

        // Log para depuración, asegurarse de que los datos estén correctamente preparados
        Log.d("CharacterDetail", "Navigating with character: " + character.getName() +
                " Image: " + character.getImage() +
                " Description: " + character.getDescription() +
                " Skills: " + skills +
                " Background: " + background);

        // Navegar al fragmento de detalles del personaje con el Bundle
        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Utiliza el método navigateUp del NavController
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

}