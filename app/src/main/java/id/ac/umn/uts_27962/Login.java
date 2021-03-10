package id.ac.umn.uts_27962;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

public class Login extends AppCompatActivity {
    private Button btnLogin;
    private EditText textUser, textPass;

    private String trueUsername = "uasmobile";
    private String truePassword = "uasmobilegenap";

    private String userString, passString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        textUser = (EditText) findViewById(R.id.textUser);
        textPass = (EditText) findViewById(R.id.textPass);

        Toolbar toolbar = (Toolbar)findViewById(R.id.menu_ToolbarLogin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userString = textUser.getText().toString();
                passString = textPass.getText().toString();
                Log.i("hi" ,userString);
                if(userString.equals(trueUsername) && passString.equals((truePassword))){
                    Intent gotoLogin = new Intent(Login.this, listLagu.class);
                    startActivityForResult(gotoLogin, 1);
                } else {
                    errorMes();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void errorMes(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.errorMesg))
                .setTitle(getString(R.string.error))
                .setPositiveButton(getString(R.string.btnPop), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) { dialog.dismiss(); }
                });
        builder.create().show();
    }
}