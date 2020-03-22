package com.example.tripandroidproject.View.NavDrawer_UpComingTrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripandroidproject.Contract.Trip.RetrieveTripContract;
import com.example.tripandroidproject.AddTrip.AddTripActivity;
import com.example.tripandroidproject.POJOs.Trip;
import com.example.tripandroidproject.Presenter.Trip.RetrieveTripPresenter;
import com.example.tripandroidproject.R;
import com.example.tripandroidproject.View.Login.LoginActivity;
import com.example.tripandroidproject.View.SaveUserLogIn;
import com.example.tripandroidproject.View.UnderTest.TestReminder;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class NavDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , RetrieveTripContract.IRetrieveTripView{

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    TextView name,email;
    private FirebaseAuth mAuth;
    private SaveUserLogIn saveUserLogIn;
    private GoogleSignInClient mGoogleSignInClient;

    //    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView recyclerView;
    private List<Trip> input;
    private RetrieveTripPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        mAuth = FirebaseAuth.getInstance();
        saveUserLogIn = new SaveUserLogIn(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setPersonInfo();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        presenter = new RetrieveTripPresenter(this,this);
//        UpComingTripView upComingTripView = new UpComingTripView(this);
//        myAdapter = upComingTripView.getAdapter();
//        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.profile:
                Toast.makeText(NavDrawer.this, "Profile Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.upComingTrip:
                Toast.makeText(NavDrawer.this, "UpComingTrip us Selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,NavDrawer.class));
                break;
            case R.id.history:
                Toast.makeText(NavDrawer.this, "History us Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.synch:
                Intent intent = new Intent(this, TestReminder.class);
                startActivity(intent);
                break;
            case R.id.logout:
                Toast.makeText(NavDrawer.this, "Logout Selected", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                // ...
                            }
                        });
                saveUserLogIn.clearUserData();
                saveUserLogIn.setUserLoggedIn(false);
                Intent intentLoginActivity = new Intent(this, LoginActivity.class);
                startActivity(intentLoginActivity);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu_filter) {
        getMenuInflater().inflate(R.menu.menu_filter,menu_filter);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.repeated:
                Toast.makeText(NavDrawer.this, "Repeated Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.unrepeated:
                Toast.makeText(NavDrawer.this, "UnRepeated us Selected", Toast.LENGTH_SHORT).show();

                break;
            default:
                break;
        }
        return true;
    }

    public void setPersonInfo(){
        View hView =  navigationView.getHeaderView(0);
        email = (TextView)hView.findViewById(R.id.navEmail);
        name  = (TextView) hView.findViewById(R.id.navName);
        ImageView imageView = (ImageView) hView.findViewById(R.id.profilePic);

        Intent intent = getIntent();
        email.setText(intent.getStringExtra("Email"));
        name.setText(intent.getStringExtra("Name"));
        String imageUri = intent.getStringExtra("imgUri");
//        Picasso.get().load(imageUri).resize(120, 120).centerCrop().into(imageView);
    }

    @Override
    public void setAdapter(RecyclerView.Adapter myAdapter) {
        this.myAdapter = myAdapter;
        this.myAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(myAdapter);
    }

    public void GoToAddTrip(View view) {
        Intent intent = new Intent(NavDrawer.this, AddTripActivity.class);
        startActivity(intent);
    }
}
