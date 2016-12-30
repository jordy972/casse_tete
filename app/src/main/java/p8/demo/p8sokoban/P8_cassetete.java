package p8.demo.p8sokoban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

// declaration de notre activity h�rit�e de Activity
public class P8_cassetete extends Activity {

    private CasseteteView mCasseteteView;
    public static final int level=1;



	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // initialise notre activity avec le constructeur parent    	
        super.onCreate(savedInstanceState);
        // charge le fichier main.xml comme vue de l'activit�
        setContentView(R.layout.main);
        // recuperation de la vue une voie cree � partir de son id
        mCasseteteView = (CasseteteView)findViewById(R.id.CasseteteView);
        // rend visible la vue
        mCasseteteView.setVisibility(View.VISIBLE);
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
