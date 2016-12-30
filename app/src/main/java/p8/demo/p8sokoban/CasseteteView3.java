package p8.demo.p8sokoban;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ryad on 29/12/16.
 */

public class CasseteteView3 extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    // Declaration des images
    private Bitmap      blue1;
    private Bitmap 		green1;
    private Bitmap 		black1;
    private Bitmap 		fond1;
    private Bitmap      marron1;
    private Bitmap      red1;
    private Bitmap 		violet1;
    private Bitmap      grena1;
    private Bitmap 		espace;
    private Bitmap[] []	espace1 = new Bitmap[5][5];
    private Bitmap 		win;

    // Declaration des objets Ressources et Context permettant d'acc�der aux ressources de notre application et de les charger
    private Resources mRes;
    private Context mContext;

    // tableau modelisant la carte du jeu
    int[][] carte;

    // ancres pour pouvoir centrer la carte du jeu
    int        carteTopAnchor;                   // coordonn�es en Y du point d'ancrage de notre carte
    int        carteLeftAnchor;                  // coordonn�es en X du point d'ancrage de notre carte

    // taille de la carte
    final int    carteWidth    = 9;
    final int    carteHeight   = 5;
    static final int    carteTileSize = 20;



    // constante modelisant les differentes types de cases
    static final int    CST_black     = 0;
    static final int    CST_marron     = 1;
    static final int    CST_red     = 2;
    static final int    CST_green     = 3;
    static final int    CST_blue     = 4;
    static final int    CST_violet      = 5;
    static final int    CST_grena     = 6;
    static final int    CST_espace      = 8;
    static final int    CST_zone      = 11;







    // tableau de reference du terrain
    int [][] ref    = {
            {CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace},
            {CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace},
            {CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace},
            {CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace},
            {CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace, CST_espace}
    };


    int [][] solution   = {
            {CST_blue, CST_blue, CST_blue, CST_blue , CST_blue, CST_marron, CST_grena, CST_grena, CST_grena},
            {CST_blue, CST_blue, CST_black, CST_black, CST_marron, CST_marron, CST_marron, CST_violet, CST_grena},
            {CST_black, CST_black, CST_black, CST_black, CST_marron, CST_violet, CST_violet, CST_violet, CST_grena},
            {CST_black, CST_green, CST_green, CST_marron, CST_marron, CST_violet, CST_violet, CST_grena, CST_grena},
            {CST_green, CST_green, CST_green, CST_green, CST_green, CST_violet, CST_red, CST_red, CST_red}
    };


    // position de reference des cubres rouges
    int [][] reftabred   = {
            {13, 1},
            {14, 1},
            {15, 1}
    };


    // position courante des cubes rouge
    int [][] tabred   = {
            {13, 1},
            {14, 1},
            {15, 1}
    };

    //position de reference de la figure rouge
    int xred=0;
    int yred= 478;

    // position de reference du rouge
    int refxred = 0;
    int refyred = 478;




    // position de reference des cubes vert
    int [][] reftabgreen   = {
            {0, 1},
            {1, 1},
            {1, 2},
            {2, 1},
            {2, 2},
            {3, 1},
            {4, 1}
    };


    // position courante des cubes vert
    int [][] tabgreen   = {
            {0, 1},
            {1, 1},
            {1, 2},
            {2, 1},
            {2, 2},
            {3, 1},
            {4, 1}
    };

    //position de reference de la figure verte
    int xgreen=0;
    int ygreen= 478;

    // position de reference du vert
    int refxgreen = 0;
    int refygreen = 478;



    // position de reference des cubes bleu
    int [][] reftabblue   = {
            {3, 2},
            {3, 3},
            {4, 2},
            {4, 3},
            {5, 3},
            {6, 3},
            {7, 3}
    };


    // position courante des cubes bleu
    int [][] tabblue   = {
            {3, 2},
            {3, 3},
            {4, 2},
            {4, 3},
            {5, 3},
            {6, 3},
            {7, 3}
    };

    //position de reference de la figure bleu
    int xblue=0;
    int yblue= 478;

    // position de reference du bleu
    int refxblue = 0;
    int refyblue = 478;


    // position de reference des cubes marron
    int [][] reftabmarron   = {
            {10, 1},
            {11, 1},
            {11, 2},
            {11, 3},
            {12, 3},
            {13, 3},
            {13, 4},
            {14, 3}
    };


    // position courante des cubes marron
    int [][] tabmarron   = {
            {10, 1},
            {11, 1},
            {11, 2},
            {11, 3},
            {12, 3},
            {13, 3},
            {13, 4},
            {14, 3}
    };

    //position de reference de la figure marron
    int xmarron=0;
    int ymarron= 478;

    // position de reference du marron
    int refxmarron = 0;
    int refymarron = 478;



    // position de reference des cubes grena
    int [][] reftabgrena   = {
            {13, 5},
            {14, 2},
            {14, 5},
            {15, 2},
            {15, 3},
            {15, 4},
            {15, 5}
    };


    // position courante des cubes grena
    int [][] tabgrena   = {
            {13, 5},
            {14, 2},
            {14, 5},
            {15, 2},
            {15, 3},
            {15, 4},
            {15, 5}
    };

    //position de reference de la figure grena
    int xgrena=0;
    int ygrena= 478;

    // position de reference du grena
    int refxgrena = 0;
    int refygrena = 478;


    // position de reference des cubes black
    int [][] reftabblack   = {
            {7, 1},
            {7, 2},
            {8, 2},
            {9, 2},
            {9, 3},
            {10, 2},
            {10, 3}
    };


    // position courante des cubes black
    int [][] tabblack   = {
            {7, 1},
            {7, 2},
            {8, 2},
            {9, 2},
            {9, 3},
            {10, 2},
            {10, 3}
    };

    //position de reference de la figure black
    int xblack=0;
    int yblack= 478;


    // position de reference du noir
    int refxblack = 0;
    int refyblack = 478;



    // position de reference des cubes violet
    int [][] reftabviolet   = {
            {0, 2},
            {0, 3},
            {0, 4},
            {1, 3},
            {1, 4},
            {2, 4},
            {2, 5}
    };


    // position courante des cubes violet
    int [][] tabviolet   = {
            {0, 2},
            {0, 3},
            {0, 4},
            {1, 3},
            {1, 4},
            {2, 4},
            {2, 5}
    };

    //position de reference de la figure violet
    int xviolet=0;
    int yviolet= 478;



    // position de reference du violet
    int refxviolet = 0;
    int refyviolet = 478;





    /* compteur et max pour animer les zones d'arriv�e des diamants */
    int currentStepZone = 0;
    int maxStepZone     = 4;

    // thread utiliser pour animer les zones de depot des diamants
    private     boolean in      = true;
    private     Thread  cv_thread;
    SurfaceHolder holder;

    Paint paint;



    /**
     * The constructor called from the main JetBoy activity
     *
     * @param context
     * @param attrs
     */
    public CasseteteView3(Context context, AttributeSet attrs) {
        super(context, attrs);


        // permet d'ecouter les surfaceChanged, surfaceCreated, surfaceDestroyed
        holder = getHolder();
        holder.addCallback(this);

        // chargement des images
        mContext	= context;
        mRes 		= mContext.getResources();
        violet1 	= BitmapFactory.decodeResource(mRes, R.drawable.violet);
        grena1		= BitmapFactory.decodeResource(mRes, R.drawable.grena);
        marron1 	= BitmapFactory.decodeResource(mRes, R.drawable.marron);
        red1		= BitmapFactory.decodeResource(mRes, R.drawable.red);
        green1 		= BitmapFactory.decodeResource(mRes, R.drawable.green);
        blue1 		= BitmapFactory.decodeResource(mRes, R.drawable.blue);
        black1 		= BitmapFactory.decodeResource(mRes, R.drawable.black);
        espace		= BitmapFactory.decodeResource(mRes, R.drawable.espace);
        espace1[0] [0]	= BitmapFactory.decodeResource(mRes, R.drawable.espace_1);
        espace1[1] [1]	= BitmapFactory.decodeResource(mRes, R.drawable.espace_2);
        espace1[0][2] 	= BitmapFactory.decodeResource(mRes, R.drawable.espace_3);
        espace1[0][3] 	= BitmapFactory.decodeResource(mRes, R.drawable.espace_4);
        fond1 		= BitmapFactory.decodeResource(mRes, R.drawable.fond);
        win 		= BitmapFactory.decodeResource(mRes, R.drawable.win);




        // initialisation des parmametres du jeu
        initparameters();

        // creation du thread
        cv_thread   = new Thread(this);
        // prise de focus pour gestion des touches
        setFocusable(true);




    }


    //Bitmap btmp = Bitmap.create(drawable.getBitmap());





    // chargement du niveau a partir du tableau de reference du niveau
    private void loadlevel() {
        for (int i=0; i< carteWidth; i++) {
            for (int j=0; j< carteHeight; j++) {
                carte[j][i]= ref[j][i];
            }
        }
    }

    // initialisation du jeu
    public void initparameters() {
        paint = new Paint();
        paint.setColor(0x000000);

        paint.setDither(true);
        paint.setColor(0x000000);  //pour les bordure des cases
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(10);
        paint.setTextAlign(Paint.Align.LEFT);
        carte           = new int[carteHeight][carteWidth];
        loadlevel();
        carteTopAnchor  = getHeight()/5;
        carteLeftAnchor = getWidth()/5;

        for (int i=0; i< 3; i++) {
            tabred[i][1] = reftabred[i][1];
            tabred[i][0] = reftabred[i][0];
        }

        for (int i=0; i< 7; i++) {
            tabgreen[i][1] = reftabgreen[i][1];
            tabgreen[i][0] = reftabgreen[i][0];
        }

        for (int i=0; i< 8; i++) {
            tabmarron[i][1] = reftabmarron[i][1];
            tabmarron[i][0] = reftabmarron[i][0];
        }

        for (int i=0; i< 7; i++) {
            tabblack[i][1] = reftabblack[i][1];
            tabblack[i][0] = reftabblack[i][0];
        }

        for (int i=0; i< 7; i++) {
            tabblue[i][1] = reftabblue[i][1];
            tabblue[i][0] = reftabblue[i][0];
        }

        for (int i=0; i< 7; i++) {
            tabgrena[i][1] = reftabgrena[i][1];
            tabgrena[i][0] = reftabgrena[i][0];
        }

        for (int i=0; i< 7; i++) {
            tabviolet[i][1] = reftabviolet[i][1];
            tabviolet[i][0] = reftabviolet[i][0];
        }

        if ((cv_thread!=null) && (!cv_thread.isAlive())) {
            cv_thread.start();
            Log.e("-FCT-", "cv_thread.start()");
        }
    }



    // dessin du gagne si gagne
    private void paintwin(Canvas canvas) {
        canvas.drawBitmap(win, carteLeftAnchor+ 3*carteTileSize, carteTopAnchor+ 4*carteTileSize, null);
    }

    // dessin de la carte du jeu
    private void paintcarte(Canvas canvas) {
        Bitmap marron = marron1.createScaledBitmap (marron1, 20, 20, true);
        Bitmap red = red1.createScaledBitmap (red1, 20, 20, true);
        Bitmap fond = fond1.createScaledBitmap (fond1, 20, 20, true);
        Bitmap black = black1.createScaledBitmap (black1, 20, 20, true);
        Bitmap blue = red1.createScaledBitmap (blue1, 20, 20, true);
        Bitmap green = fond1.createScaledBitmap (green1, 20, 20, true);

        for (int i=0; i< carteHeight; i++) {
            for (int j=0; j< carteWidth; j++) {
                switch (carte[i][j]) {

                    case CST_zone:
                        //canvas.drawBitmap(zone[currentStepZone],carteLeftAnchor+ j*carteTileSize, carteTopAnchor+ i*carteTileSize, null);
                        break;
                    case CST_espace:
                        canvas.drawBitmap(espace,carteLeftAnchor+ j*carteTileSize, carteTopAnchor+ i*carteTileSize, null);
                        break;
                    case CST_black:
                        canvas.drawBitmap(black,carteLeftAnchor+ j*carteTileSize, carteTopAnchor+ i*carteTileSize, null);
                        break;
                    case CST_marron:
                        canvas.drawBitmap(marron,carteLeftAnchor+ j*carteTileSize, carteTopAnchor+ i*carteTileSize, null);
                        break;
                    case CST_red:
                        canvas.drawBitmap(red,carteLeftAnchor+ j*carteTileSize, carteTopAnchor+ i*carteTileSize, null);
                        break;
                    case CST_green:
                        canvas.drawBitmap(green,carteLeftAnchor+ j*carteTileSize, carteTopAnchor+ i*carteTileSize, null);
                        break;
                    case CST_blue:
                        canvas.drawBitmap(blue,carteLeftAnchor+ j*carteTileSize, carteTopAnchor+ i*carteTileSize, null);
                        break;
                }
            }
        }
    }










    // dessin de la figure rouge
    private void paintred(Canvas canvas) {
        Bitmap red = red1.createScaledBitmap (red1, 20, 20, true);

        for (int i=0; i< 3; i++) {
            canvas.drawBitmap(red,xred+tabred[i][0]*carteTileSize, yred - tabred[i][1]*carteTileSize, null);
        }

    }



    private void paintgreen(Canvas canvas) {
        Bitmap green = green1.createScaledBitmap (green1, 20, 20, true);

        for (int i=0; i< 7; i++) {
            canvas.drawBitmap(green,xgreen+tabgreen[i][0]*carteTileSize, ygreen - tabgreen[i][1]*carteTileSize, null);
        }
    }



    private void paintblue(Canvas canvas) {
        Bitmap blue = blue1.createScaledBitmap (blue1, 20, 20, true);
        for (int i=0; i< 7; i++) {
            canvas.drawBitmap(blue,xblue+tabblue[i][0]*carteTileSize, yblue - tabblue[i][1]*carteTileSize, null);

        }
    }




    private void paintgrena(Canvas canvas) {
        Bitmap grena = grena1.createScaledBitmap (grena1, 20, 20, true);
        for (int i=0; i< 7; i++) {
            canvas.drawBitmap(grena,xgrena+tabgrena[i][0]*carteTileSize, ygrena - tabgrena[i][1]*carteTileSize, null);
        }
    }



    private void paintmarron(Canvas canvas) {
        Bitmap marron = marron1.createScaledBitmap (marron1, 20, 20, true);
        for (int i=0; i< 8; i++) {
            canvas.drawBitmap(marron,xmarron+tabmarron[i][0]*carteTileSize, ymarron - tabmarron[i][1]*carteTileSize, null);
        }
    }




    private void paintblack(Canvas canvas) {
        Bitmap black = black1.createScaledBitmap (black1, 20, 20, true);
        for (int i=0; i< 7; i++) {
            canvas.drawBitmap(black,xblack+tabblack[i][0]*carteTileSize, yblack - tabblack[i][1]*carteTileSize, null);
        }
    }


    private void paintviolet(Canvas canvas) {
        Bitmap violet = violet1.createScaledBitmap (violet1, 20, 20, true);
        for (int i=0; i< 7; i++) {
            canvas.drawBitmap(violet,xviolet+tabviolet[i][0]*carteTileSize, yviolet- tabviolet[i][1]*carteTileSize, null);
        }
    }

    // permet d'identifier si la partie est gagnee (toutes les figures en plave *********************** A FAIRE **************************)
    private boolean isWon() {
        for (int i=0; i< 4; i++) {
            // if (!IsCell(diamants[i][1], diamants[i][0], CST_zone)) {
            return false;
            //}
        }
        return true;
    }

    // dessin du jeu (fond uni, en fonction du jeu gagne ou pas dessin du plateau et du joueur des diamants et des fleches)
    private void nDraw(Canvas canvas) {
        canvas.drawRGB(224,192,104);

        if (isWon()) {
            paintcarte(canvas);
            paintwin(canvas);
        } else {
            Log.e( "-FCT-","isWon()" );

            paintcarte(canvas);
            paintred(canvas);
            paintgreen(canvas);
            paintblue(canvas);
            paintmarron(canvas);
            paintgrena(canvas);
            paintblack(canvas);
            paintviolet(canvas);
        }

    }

    // callback sur le cycle de vie de la surfaceview
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("-> FCT <-", "surfaceChanged "+ width +" - "+ height);
        initparameters();
    }

    public void surfaceCreated(SurfaceHolder arg0) {
        Log.i("-> FCT <-", "surfaceCreated");
    }


    public void surfaceDestroyed(SurfaceHolder arg0) {
        Log.i("-> FCT <-", "surfaceDestroyed");
    }

    /**
     * run (run du thread cr��)
     * on endort le thread, on modifie le compteur d'animation, on prend la main pour dessiner et on dessine puis on lib�re le canvas
     */
    public void run() {
        Canvas c = null;
        while (in) {
            try {
                cv_thread.sleep(40);
                currentStepZone = (currentStepZone + 1) % maxStepZone;
                try {
                    c = holder.lockCanvas(null);
                    nDraw(c);
                } finally {
                    if (c != null) {
                        holder.unlockCanvasAndPost(c);
                    }
                }
            } catch(Exception e) {
                Log.e("-> RUN <-", "PB DANS RUN");
            }
        }
    }

    // verification que nous sommes dans le tableau
    private boolean IsOut(int x, int y) {
        if ((x < 0) || (x > carteWidth- 1)) {
            return true;
        }
        if ((y < 0) || (y > carteHeight- 1)) {
            return true;
        }
        return false;
    }

    //controle de la valeur d'une cellule
    private boolean IsCell(int x, int y, int mask) {
        if (carte[y][x] == mask) {
            return true;
        }
        return false;
    }

    // controle si nous avons un diamant dans la case
    /*private boolean IsDiamant(int x, int y) {
        for (int i=0; i< 4; i++) {
            if ((diamants[i][1] == x) && (diamants[i][0] == y)) {
                return true;
            }
        }
        return false;
    }*/

    // met � jour la position d'un diamant
    /*private void UpdateDiamant(int x, int y, int new_x, int new_y) {
        for (int i=0; i< 4; i++) {
            if ((diamants[i][1] == x) && (diamants[i][0] == y)) {
                diamants[i][1] = new_x;
                diamants[i][0] = new_y;
            }
        }
    }
    // fonction permettant de recuperer les retours clavier
    //@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.i("-> FCT <-", "onKeyUp: "+ keyCode);

        int xTmpPlayer	= xPlayer;
        int yTmpPlayer  = yPlayer;
        int xchange 	= 0;
        int ychange 	= 0;

        if (keyCode == KeyEvent.KEYCODE_0) {
            initparameters();
        }

        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            ychange = -1;
        }

        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            ychange = 1;
        }

        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            xchange = -1;
        }

        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            xchange = 1;
        }
        xPlayer = xPlayer+ xchange;
        yPlayer = yPlayer+ ychange;

        if (IsOut(xPlayer, yPlayer) || IsCell(xPlayer, yPlayer, CST_espace)) {
            xPlayer = xTmpPlayer;
            yPlayer = yTmpPlayer;
        } else if (IsDiamant(xPlayer, yPlayer)) {
	            int xTmpDiamant = xPlayer;
	            int yTmpDiamant = yPlayer;
	            xTmpDiamant = xTmpDiamant+ xchange;
	            yTmpDiamant = yTmpDiamant+ ychange;
	            if (IsOut(xTmpDiamant, yTmpDiamant) || IsCell(xTmpDiamant, yTmpDiamant, CST_espace) || IsDiamant(xTmpDiamant, yTmpDiamant)) {
	                xPlayer = xTmpPlayer;
	                yPlayer = yTmpPlayer;
	            } else {
	                //UpdateDiamant(xTmpDiamant- xchange, yTmpDiamant- ychange, xTmpDiamant, yTmpDiamant);
	            }
        }
        return true;
    }

    // fonction permettant de recuperer les evenements tactiles
   public boolean onTouchEvent (MotionEvent event) {
        Log.i("-> FCT <-", "onTouchEvent: "+ event.getX());
        if (event.getY()<50) {
            onKeyDown(KeyEvent.KEYCODE_DPAD_UP, null);
        } else if (event.getY()>getHeight()-50) {
            if (event.getX()>getWidth()-50) {
                onKeyDown(KeyEvent.KEYCODE_0, null);
            } else {
                onKeyDown(KeyEvent.KEYCODE_DPAD_DOWN, null);
            }
        } else if (event.getX()<50) {
            onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
        } else if (event.getX()>getWidth()-50) {
            onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        }
        return super.onTouchEvent(event);
    }*/
}
