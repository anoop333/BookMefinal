package gocars.mainproject.bookme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Upbook extends AppCompatActivity {
    ImageView book, upbtn;
    EditText b_name, b_price, b_edition, b_cat;
    Button submit;
    private Bitmap bitmap;

    private Uri filePath;

    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upbook);
        book = findViewById(R.id.imageView);
        upbtn = findViewById(R.id.imageView2);
        b_name = findViewById(R.id.editText12);
        b_price = findViewById(R.id.editText9);
        b_edition = findViewById(R.id.editText10);
        b_cat = findViewById(R.id.editText11);
        submit = findViewById(R.id.button3);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b_name.getText().toString().isEmpty() || b_price.getText().toString().isEmpty() || b_edition.getText().toString().isEmpty() || b_cat.getText().toString().isEmpty()) {
                    Toast.makeText(Upbook.this, "Fill all Fields", Toast.LENGTH_LONG).show();
                } else {


                  up();

                }
            }
        });
        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });


    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                book.setImageBitmap(bitmap);
                getStringImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    public void up()
    {

        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Upbook.this, "Uploading...", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Bitmap... params) {
                bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String, String> data = new HashMap<>();

                data.put("img", uploadImage);
                data.put("bname", b_name.getText().toString());
                data.put("bprice",b_price.getText().toString());
                data.put("bedition",b_edition.getText().toString());
                data.put("bcat",b_cat.getText().toString());

                String result = rh.sendPostRequest("https://anoopsuvarnan1.000webhostapp.com/bookproject/upload.php", data);

                return result;
            }
        }
        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }
}
