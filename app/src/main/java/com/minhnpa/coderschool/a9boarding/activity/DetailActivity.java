package com.minhnpa.coderschool.a9boarding.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.model.Post;
import com.minhnpa.coderschool.a9boarding.utils.PermissionUtils;
import com.minhnpa.coderschool.a9boarding.utils.StringUtils;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private SupportMapFragment mMapFragment;
    private GoogleMap mGoogleMap;
    private GoogleApiClient mGoogleApiClient;
    private LatLng mCurrentLoction;
    private Marker mMarker;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivFab)
    FloatingActionButton btnBookMark;
    @BindView(R.id.btnCall)
    ImageButton btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setupActiobBar();

        setupMap();
        setupGoogleApiClient();
        PermissionUtils.requestLocaiton(this);

        Bundle bundle = getIntent().getBundleExtra("DATA");
        Post post = ((Post) bundle.getSerializable("POST"));
        if(post.isBookMark()){
            btnBookMark.setImageDrawable(getResources().getDrawable(R.drawable.ic_bookmark_active2));
        }

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCall();
            }
        });
    }

    private void onCall() {

//        int permissionCheck =

        Intent callIntent =  new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:0938513856"));
        if (callIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(callIntent);
        }

    }

    private void setupActiobBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    private void setupGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    private void setupMap() {
        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    public void onClickGetDirection() {
        double lat_i = 10.8577524f;
        double long_i = 106.759623f;
        String uri = String.format(Locale.ENGLISH, StringUtils.MAP_DIRECTION_URI,
                lat_i, long_i, "Destination");
        Uri gmmIntentUri = Uri.parse(uri);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
//            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//
//            CustomTabsIntent customTabsIntent = builder.build();
//            customTabsIntent.launchUrl(this, gmmIntentUri);
        }
    }

    @SuppressWarnings("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (PermissionUtils.checkLocation(this)) {
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (location != null) {
                updateLoction(location);
            }
        } else {
            PermissionUtils.requestLocaiton(this);
        }
        getLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (i == CAUSE_NETWORK_LOST) {
            toastMessage("Check your connection!");
        } else if (i == CAUSE_SERVICE_DISCONNECTED) {
            toastMessage("The service has been disconnectedk");
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        toastMessage(connectionResult.getErrorMessage());
    }

    @SuppressWarnings("MissingPermission")
    private void getLocation() {
        if (PermissionUtils.checkLocation(this)) {
            LocationRequest locationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
        } else {
            PermissionUtils.requestLocaiton(this);
        }
    }

    private void updateLoction(Location location) {
        if (mGoogleMap != null) {
            mCurrentLoction = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(mCurrentLoction);
            mGoogleMap.moveCamera(cameraUpdate);
            if (mMarker != null) {
                mMarker.remove();
            }
            mMarker = mGoogleMap.addMarker(new MarkerOptions()
                    .position(mCurrentLoction)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    onClickGetDirection();
                    return true;
                }
            });
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLoction = new LatLng(location.getLatitude(), location.getLongitude());
        updateLoction(location);
//        toastMessage(location.getLatitude() + " " + location.getLongitude());
    }

    public void onCommentClick(View view) {
        Intent intent = new Intent(this, CommentActivity.class);
//        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }
}