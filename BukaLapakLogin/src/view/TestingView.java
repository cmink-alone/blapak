package view;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;

import com.example.bukalapaklogin.R;
import com.example.bukalapaklogin.R.layout;
import com.example.bukalapaklogin.R.menu;

import controller.APIController;
import controller.AsyncTaskResult;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.Toast;

public class TestingView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testing_view);
		new PostData().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_testing_view, menu);
		return true;
	}
	class PostData extends AsyncTask<String, AsyncTaskResult<String>, AsyncTaskResult<String>>
    {
    	APIController api;
		@Override
		protected AsyncTaskResult<String> doInBackground(String... arg0) {
			// TODO Auto-generated method stub
	        // Create a new HttpClient and Post Header
			try {
				api = new APIController(getApplicationContext(),"rizkivmaster","18091992gnome");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return new AsyncTaskResult<String>(e);
			}
			return null;
		}
		@Override
		protected void onPostExecute(AsyncTaskResult<String> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

	        new Test().execute();
		}
			
    }

	class Test extends AsyncTask<String, String, String>
    {
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			APIController api = new APIController(getApplicationContext());
			String result = null;
			InputStream is;
			try {
				is = getAssets().open("sepeda.jpg");
				Bitmap bm = BitmapFactory.decodeStream(is);
				JSONObject response =  api.createImage(bm);
				result = response.getString("id");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			new  TestUploadProduk().execute(result);
		}
    	
    }
	class TestUploadProduk extends AsyncTask<String, String, String>
    {
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			APIController api = new APIController(getApplicationContext());
			String result = null;
			InputStream is;
			try {
				api.createProduct(params[0]);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
		}
    	
    }
}
