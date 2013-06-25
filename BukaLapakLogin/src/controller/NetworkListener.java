package controller;

import org.json.JSONObject;

public interface NetworkListener {
	public abstract void onFinish(JSONObject res);
	public abstract void onHold();
	public abstract void onStart();
}
