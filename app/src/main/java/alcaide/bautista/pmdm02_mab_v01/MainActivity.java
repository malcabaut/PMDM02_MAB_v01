package alcaide.bautista.pmdm02_mab_v01;

import android.os.Bundle;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import alcaide.bautista.pmdm02_mab_v01.databinding.ActivityMainBinding;
/**
 * Actividad principal que gestiona la navegación de la aplicación,
 * incluyendo el menú lateral (DrawerLayout), el menú superior y
 * las interacciones con los fragmentos.
 */
public class MainActivity extends AppCompatActivity {

    // Declaración de las variables necesarias para la navegación y el menú lateral.
    private ActionBarDrawerToggle toggle;
    private NavController navController;
    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;  // Configuration for handling navigation actions


    /**
     * Método que se ejecuta cuando se crea la actividad.
     * Se configura la vista, la navegación y el menú lateral.
     *
     * @param savedInstanceState el estado guardado de la actividad (si es que existe)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configurar ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar Toolbar como ActionBar
        setSupportActionBar(binding.toolbar);


        // Configurar la navegación entre fragmentos
        setupNavigation();


        // Configurar el ActionBarDrawerToggle (icono hamburguesa)
       //configureToggleMenu();

        // Configurar eventos para las interacciones con el menú lateral
        //configureMenuInteraction();
    }


    /**
     * Configura el controlador de navegación y la vinculación con el NavHostFragment.
     * Esto permite manejar las transiciones entre los fragmentos.
     */
    private void setupNavigation() {
        // Obtener el NavController desde el NavHostFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            // Obtener el controlador de navegación
            navController = navHostFragment.getNavController();

            // Configuración del AppBar para sincronizarlo con el NavController y el DrawerLayout
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                    .setOpenableLayout(binding.drawerLayout) // Vincular el DrawerLayout
                    .build();

            // Sincronizar el ActionBar con el NavController
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

            // Vincular el NavController con el NavigationView
            NavigationUI.setupWithNavController(binding.navView, navController);
        }
    }

    /**
     * Configura el menú toggle del DrawerLayout (el icono de la hamburguesa).
     * Este menú abre y cierra el DrawerLayout cuando el usuario interactúa con él.
     */
    private void configureToggleMenu() {

        // Configurar el ActionBarDrawerToggle para que sincronice el DrawerLayout con la toolbar
        toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.toolbar,
                R.string.app_open_drawer,
                R.string.app_close_drawer
        );

        // Vincular el listener al DrawerLayout
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); // Sincronizar el estado del menú (abierto/cerrado)


    }




    /**
     * Configura las interacciones del menú lateral, incluyendo clics en los elementos del menú y el header.
     */
    private void configureMenuInteraction() {
        // Configurar el listener para los elementos del menú lateral
        binding.navView.setNavigationItemSelectedListener(menuItem -> {
            // Si se selecciona "Home", navega al fragmento de lista de personajes
            if (menuItem.getItemId() == R.id.nav_home) {
                navController.navigate(R.id.characterListFragment);
            }

            // Cerrar el menú lateral después de seleccionar un ítem
            //binding.drawerLayout.closeDrawers();
            return true;
        });

        // Configurar el clic en la imagen del header
        View headerView = binding.navView.getHeaderView(0);
        ImageView profileImageView = headerView.findViewById(R.id.header_image);

    }


    /**
     * Infla el menú de opciones superior.
     * Este método es llamado para mostrar el menú en la barra superior (toolbar).
     *
     * @param menu el menú que se va a inflar
     * @return true si se infla correctamente, false si no
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Maneja la acción de navegar hacia atrás en la pila de navegación.
     * Esto permite la interacción con el botón de "Up" en la ActionBar.
     *
     * @return true si la navegación fue exitosa, false en caso contrario
     */
    @Override
    public boolean onSupportNavigateUp() {
        // Intentar navegar hacia atrás en el controlador de navegación
        return navController != null && navController.navigateUp() || super.onSupportNavigateUp();
    }

    /**
     * Maneja la selección de elementos del menú de opciones superior.
     * En este caso, solo muestra un diálogo con la información "Acerca de...".
     *
     * @param item el item del menú que fue seleccionado
     * @return true si la acción fue procesada, false en caso contrario
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_about) {
            showAboutDialog(); // Muestra el diálogo "Acerca de..."
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * Muestra un diálogo con la información "Acerca de...".
     */
    private void showAboutDialog() {
        Spanned formattedText = HtmlCompat.fromHtml(getString(R.string.app_about_info), HtmlCompat.FROM_HTML_MODE_LEGACY);

        new AlertDialog.Builder(this)
                .setTitle(R.string.app_about)
                .setMessage(formattedText)
                .setIcon(R.drawable.icon_mario_app)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }



    /**
     * Maneja la interacción cuando un usuario hace clic en un personaje.
     * Navega al fragmento de detalles del personaje con la información pasada como parámetros.
     *
     * @param character el objeto de datos del personaje
     * @param view la vista que se clickeó
     */
    public void characterClicked(@NonNull CharacterData character, View view) {
        // Crear un bundle con los datos del personaje
        Bundle bundle = new Bundle();
        bundle.putString("name", character.getName());
        bundle.putInt("image", character.getImage());
        bundle.putString("description", character.getDescription());
        bundle.putString("skills", character.getSkills());
        bundle.putInt("background", character.getBackground() != 0 ? character.getBackground() : R.drawable.characters);


        // Si el personaje tiene sonidos asociados, agregarlos al bundle
        if (character.getSound() != null && !character.getSound().isEmpty()) {
            bundle.putIntArray("sounds", character.getSound().stream().mapToInt(i -> i).toArray());
        }

        // Navegar al fragmento de detalles del personaje
        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);
    }
}
