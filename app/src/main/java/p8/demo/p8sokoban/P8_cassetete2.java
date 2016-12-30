package p8.demo.p8sokoban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


/**
 * Created by ryad on 29/12/16.
 */

public class P8_cassetete2 extends Activity {

    private CasseteteView2 mCasseteteView2;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // initialise notre activity avec le constructeur parent
        super.onCreate(savedInstanceState);
        // charge le fichier main.xml comme vue de l'activit�
        setContentView(R.layout.main2);
        // recuperation de la vue une voie cree � partir de son id
        mCasseteteView2 = (CasseteteView2) findViewById(R.id.CasseteteView2);
        // rend visible la vue
        mCasseteteView2.setVisibility(View.VISIBLE);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.level_1:
                Intent intent1 = new Intent(getApplicationContext(), P8_cassetete.class);
                startActivity(intent1);

                return true;
            case R.id.level_2:
                Intent intent2 = new Intent(getApplicationContext(), P8_cassetete2.class);
                startActivity(intent2);
                return true;
            case R.id.level_3:
                Intent intent3 = new Intent(getApplicationContext(), P8_cassetete3.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}