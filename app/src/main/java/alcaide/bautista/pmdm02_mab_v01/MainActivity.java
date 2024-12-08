package alcaide.bautista.pmdm02_mab_v01;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import alcaide.bautista.pmdm02_mab_v01.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Vincular la Toolbar al NavController
        setSupportActionBar(binding.toolbar);


        // Configura del controlador de navegación.
        // Configura el controlador de navegación usando binding
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.navHostFragment.getId());

        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
            // Vincular la Toolbar al NavController
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                    .build();
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
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

        // Pasa la lista de sonidos como un array de enteros
        if (character.getSound() != null && !character.getSound().isEmpty()) {
            int[] soundArray = new int[character.getSound().size()];
            for (int i = 0; i < character.getSound().size(); i++) {
                soundArray[i] = character.getSound().get(i);
            }
            bundle.putIntArray("sounds", soundArray);
        }

        // Navegar al fragmento de detalles del personaje con el Bundle
        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Utiliza el método navigateUp del NavController
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

}