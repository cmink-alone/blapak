package view;

import java.util.ArrayList;
import java.util.List;

import model.Product;

import com.example.bukalapaklogin.R;
import com.example.bukalapaklogin.R.layout;
import com.example.bukalapaklogin.R.menu;

import controller.APIController;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class LapakActivity extends Activity {

	private List<Product>  mListProduct;
	private ProductItemAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lapak);
		ListView productList = (ListView) findViewById(R.id.listProduct);
		//buat list produk
		mListProduct = new ArrayList<Product>();
		//buat adapter
		adapter = new ProductItemAdapter(this,mListProduct);
		//set adapter
		productList.setAdapter(adapter);
		//update produk
		new BrowseTask().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_lapak, menu);
		return true;
	}
	
	//ASYNC TASK TO AVOID CHOKING UP UI THREAD DOWNLOAD STRING
		private class BrowseTask extends AsyncTask<String, String, List<Product>> {
		
		    @Override
		    protected void onPreExecute() {}
		
		    protected List<Product> doInBackground(String... param) {
		    	APIController api;
				try {
					api = new APIController(getApplicationContext(),"rizkivmaster","18091992gnome");
					return api.listLapak(false, false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	return null;
		    }
		
		    protected void onPostExecute(List<Product> ret) {
		        if (ret != null) {
					for (Product product : ret) {
						mListProduct.add(product);	
					}
				}
		        if (adapter != null) {
		                adapter.notifyDataSetChanged();
		        }
		    }
		}
//		private class ImageDownloader extends AsyncTask<Product,String,Bitmap> {
//			Product product;
//	        protected Bitmap doInBackground(Product... prd) {
//	            // TODO Auto-generated method stub
//	        	product = prd[0];
//	        	Bitmap bitmap =  Global.downloadBitmap(product.getAttributes().get("image"));
//	            return bitmap;
//	        }
//	        @Override
//	        protected void onPreExecute() {
//	            Log.i("Async-Example", "onPreExecute Called");
//
//	        }
//	        protected void onPostExecute(Bitmap result) {
//	    			product.setImage(result);
//	    			if(adapter!=null)
//	                adapter.notifyDataSetChanged();	
//	        }
	
	

}
