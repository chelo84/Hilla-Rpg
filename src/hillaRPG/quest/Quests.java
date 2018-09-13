package hillaRPG.quest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.codename1.io.Util;
import com.codename1.ui.Display;

import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;

public class Quests extends ArrayList<Quest> {
    
    public Quests() throws IOException {
    	try {
        	InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/quests.json");
        	String fileStr = Util.readToString(is);
        	
			JSONArray ja = new JSONArray(fileStr);
			for (int i = 0; i < ja.length(); i++) {
				JSONObject jobj = ja.getJSONObject(i);
				
				String item = jobj.getString("item");
				int quantidade = jobj.getInt("quantidade");
				int xpRecompensa = jobj.getInt("xpRecompensa");
				
				Quest quest = new Quest();
				quest.setItem(item);
				quest.setItemQtd(quantidade);
				quest.setXpRecompensa(xpRecompensa);
				
				this.add(quest);
			}
    	} catch (JSONException ex) {
    		
    	}
    }
}
