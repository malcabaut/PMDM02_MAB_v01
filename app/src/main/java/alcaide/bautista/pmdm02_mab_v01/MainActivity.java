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



    // Método para manejar el clic en un juego
    public void characterClicked(CharacterData character, View view) {
        // Crear un Bundle para pasar los datos al GameDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("name", character.getName()); // Pasa el nombre del juego

        bundle.putInt("image", character.getImage());  // Pasa el identificador de la imagen
        bundle.putString("description", character.getDescription()); // Pasa la descripción o más datos que necesites

        // Navegar al GameDetailFragment con el Bundle
        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);
    }
    @Override
    public boolean onSupportNavigateUp() {
        // Utiliza el método navigateUp del NavController
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

}