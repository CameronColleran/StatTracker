/**
 * EditDetailsActivity.java: Class which allows user to change/update their personal data
 *
 * @author Cameron Colleran
 * @version 1.0
 */

package edu.miracostacollege.stattracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.miracostacollege.stattracker.model.DBHelper;
import edu.miracostacollege.stattracker.model.Player;

public class EditDetailsActivity extends AppCompatActivity
{
    /** Instance variables */
    DBHelper dbHelper;
    ImageView editDetailsImageView;
    EditText firstNameDetailsEditText;
    EditText lastNameDetailsEditText;
    EditText ageDetailsEditText;
    EditText heightDetailsEditText;
    EditText weightDetailsEditText;
    private Uri currentImage;
    public static final int RESULT_LOAD_IMAGE = 101;
    Player player;

    /**
     * OnCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        dbHelper = new DBHelper(this);

        // Wiring up views
        editDetailsImageView = findViewById(R.id.editDetailsImageView);
        firstNameDetailsEditText = findViewById(R.id.firstNameDetailsEditText);
        lastNameDetailsEditText = findViewById(R.id.lastNameDetailsEditText);
        ageDetailsEditText = findViewById(R.id.ageDetailsEditText);
        heightDetailsEditText = findViewById(R.id.heightDetailsEditText);
        weightDetailsEditText = findViewById(R.id.weightDetailsEditText);

        player = dbHelper.getAllPlayers().get(0);

        // Setting views from player data
        editDetailsImageView.setImageURI(player.getUri());
        firstNameDetailsEditText.setText(player.getFirstName());
        lastNameDetailsEditText.setText(player.getLastName());
        ageDetailsEditText.setText(String.valueOf(player.getAge()));
        heightDetailsEditText.setText(player.getHeight());
        weightDetailsEditText.setText(String.valueOf(player.getWeight()));

        currentImage = player.getUri();


    }

    /**
     * OnResume
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        editDetailsImageView.setImageURI(player.getUri());
    }

    /**
     * Method to get uri from context
     *
     * @param context
     * @param resId
     * @return
     */
    public static Uri getUriToResource(Context context, int resId)
    {
        Resources res = context.getResources();

        String uriString = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                res.getResourcePackageName(resId) + "/" + res.getResourceTypeName(resId)
                + "/" + res.getResourceEntryName(resId);

        return Uri.parse(uriString);
    }

    /**
     * Method to select an image on the device
     *
     * @param v
     */
    public void selectPlayerImage(View v)
    {
        // To request permissions from the user, lets see what they have enabled already
        List<String> permissionsRequestList = new ArrayList<>();

        // Make up request code for permissions
        int permsRequestCode = 100;

        // Permissions: Camera, Read Ext. Storage, Write Ext. Storage
        // Permissions are integer codes (GRANTED or not)
        int haveCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        // If permission doesn't already exist, add it to the permissionRequestList
        if (haveCameraPermission != PackageManager.PERMISSION_GRANTED)
        {
            permissionsRequestList.add(Manifest.permission.CAMERA);
        }

        // Repeating process for other permissions
        int haveReadExtStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (haveReadExtStorage != PackageManager.PERMISSION_GRANTED)
        {
            permissionsRequestList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        int haveWriteExtStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (haveWriteExtStorage != PackageManager.PERMISSION_GRANTED)
        {
            permissionsRequestList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        // If the size of the list > 0, we have some permissions to request
        if (permissionsRequestList.size() > 0)
        {
            // Convert the list into an array for requesting
            String[] perms = new String[permissionsRequestList.size()];
            permissionsRequestList.toArray(perms);
            // Request the permissions
            ActivityCompat.requestPermissions(this, perms, permsRequestCode);
        }

        // Check to ensure that all 3 permissions have been granted
        if (haveCameraPermission == PackageManager.PERMISSION_GRANTED && haveReadExtStorage == PackageManager.PERMISSION_GRANTED && haveWriteExtStorage == PackageManager.PERMISSION_GRANTED)
        {
            // Open the image gallery on the phone

            // Make an intent to pick an image from the gallery
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, RESULT_LOAD_IMAGE);
        }
        // User has NOT enabled permissions
        else
        {
            Toast.makeText(this, "App requires access to external storage. Please enable permissions", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * OnActivityResult method
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        // This method is called after firing an intent for a result
        // Identify that we're loading an image
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
        {
            currentImage = data.getData();
            System.out.println(currentImage.toString());
            System.out.println(editDetailsImageView.toString());
            editDetailsImageView.setImageURI(currentImage);
        }
    }

    /**
     * Method which updates player in database based off input in views
     *
     * @param v
     */
    public void updatePlayer(View v)
    {
        Uri imageUri = currentImage;
        String firstName = firstNameDetailsEditText.getText().toString();
        String lastName = lastNameDetailsEditText.getText().toString();
        int age = Integer.parseInt(ageDetailsEditText.getText().toString());
        String height = heightDetailsEditText.getText().toString();
        double weight = Double.parseDouble(weightDetailsEditText.getText().toString());

        // Prompt user to enter all data if they haven't already
        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(String.valueOf(age)) || TextUtils.isEmpty(height) || TextUtils.isEmpty(String.valueOf(weight)))
        {
            Toast.makeText(this, "Must enter all information", Toast.LENGTH_LONG).show();
            return;
        }

        // Getting to update properly
        long id = dbHelper.getAllPlayers().get(0).getId();

        // Making new player from information
        Player player = new Player();
        player.setId(id);
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setAge(age);
        player.setHeight(height);
        player.setWeight(weight);
        player.setUri(imageUri);

        System.out.println(imageUri.toString());

        dbHelper.updatePlayer(player);

        Toast.makeText(this, "Information updated!", Toast.LENGTH_LONG).show();
    }
}
