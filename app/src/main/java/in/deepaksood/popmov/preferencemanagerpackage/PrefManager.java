package in.deepaksood.popmov.preferencemanagerpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import in.deepaksood.popmov.moviemodelpackage.MovieModel;

/**
 * Created by deepak on 29/4/16.
 */

//prefmanager class for performing all operations related to storing and retriving movies from and to preference manager.
public class PrefManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "PopMov";
    private static String KEY_MOVIE_ID = "MOVIE_ID_ARR";

    @SuppressLint("CommitPrefEdits")
    public PrefManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void saveMovieModel(List<MovieModel> favList) {
        Gson gson = new Gson();
        String json = gson.toJson(favList);
        editor.putString(KEY_MOVIE_ID,json);
        editor.commit();

    }

    public List<MovieModel> getMovieModel() {
        String json = pref.getString(KEY_MOVIE_ID, "");
        Type type = new TypeToken<List<MovieModel>>(){}.getType();
        return new Gson().fromJson(json, type);
    }

    //method to clear all the favorites from preference manager.
    public void clearPref() {
        editor.clear();
        editor.commit();
    }

}
