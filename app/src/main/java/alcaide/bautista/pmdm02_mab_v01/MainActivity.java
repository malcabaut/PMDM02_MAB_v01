package alcaide.bautista.pmdm02_mab_v01;

import android.os.Bundle;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.core.text.HtmlCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import alcaide.bautista.pmdm02_mab_v01.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ocultar la barra de estado
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

// Comprobar el idioma guardado
        String savedLanguage = PreferencesHelper.getLanguage(this);
        Toast.makeText(this, "Idioma guardado: " + savedLanguage, Toast.LENGTH_SHORT).show();

// Cambiar el idioma de la aplicación basado en el idioma guardado
        LocaleListCompat appLocales = LocaleListCompat.forLanguageTags(savedLanguage);
        AppCompatDelegate.setApplicationLocales(appLocales);


        // Configuración de ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar Toolbar como ActionBar
        setSupportActionBar(binding.toolbar);

        // Configurar la navegación entre fragmentos
        setupNavigation();

        // Configurar eventos del menú lateral
        configureMenuInteraction();
    }

    private void setupNavigation() {
        // Obtener el NavController desde el NavHostFragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();

            // Configurar AppBarConfiguration
            mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.characterListFragment)
                    .setOpenableLayout(binding.drawerLayout)
                    .build();

            // Sincronizar el ActionBar con NavController y DrawerLayout
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

            // Vincular el NavigationView con el NavController
            NavigationUI.setupWithNavController(binding.navView, navController);
        }
    }

    private void configureMenuInteraction() {
        // Configurar el listener para los elementos del menú lateral
        binding.navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.nav_home) {
                navController.navigate(R.id.characterListFragment);
            }
            // Cerrar el menú lateral después de seleccionar un ítem
            binding.drawerLayout.closeDrawers();
            return true;
        });

        // Configurar el SwitchCompat para cambiar el idioma
        View headerView = binding.navView.getHeaderView(0);

        // Encuentra el SwitchCompat dentro del item personalizado
        MenuItem switchLanguageItem = binding.navView.getMenu().findItem(R.id.nav_switch_language);
        View actionView = switchLanguageItem.getActionView();
        androidx.appcompat.widget.SwitchCompat switchLanguage = actionView.findViewById(R.id.switch_language);

        // Configurar el estado inicial del SwitchCompat basado en las preferencias guardadas
        String currentLanguage = PreferencesHelper.getLanguage(this);
        switchLanguage.setChecked(currentLanguage.equals("en")); // Inglés si está activado

        // Listener para cambios en el SwitchCompat
        switchLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Guardar la preferencia de idioma
            PreferencesHelper.setLanguage(this, isChecked);

            // Mostrar un mensaje al usuario
            String language = isChecked ? "Inglés" : "Español";
            Toast.makeText(this, "Idioma cambiado a: " + language, Toast.LENGTH_SHORT).show();

            // Reiniciar la actividad para reflejar el cambio de idioma (opcional)
            recreate();
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            showAboutDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAboutDialog() {
        Spanned formattedText = HtmlCompat.fromHtml(getString(R.string.app_about_info), HtmlCompat.FROM_HTML_MODE_LEGACY);
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_about)
                .setMessage(formattedText)
                .setIcon(R.drawable.icon_mario_app)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    public void characterClicked(@NonNull CharacterData character, View view) {
        Bundle bundle = new Bundle();
        bundle.putString("name", character.getName());
        bundle.putInt("image", character.getImage());
        bundle.putString("description", character.getDescription());
        bundle.putString("skills", character.getSkills());
        bundle.putInt("background", character.getBackground() != 0 ? character.getBackground() : R.drawable.characters);

        if (character.getSound() != null && !character.getSound().isEmpty()) {
            bundle.putIntArray("sounds", character.getSound().stream().mapToInt(i -> i).toArray());
        }

        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);
    }
}
