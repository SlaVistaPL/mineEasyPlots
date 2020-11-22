package pl.mineEasyPlots.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LiteDataUtil {

	public int ID;

	public LiteDataUtil() {
		this.ID = 0;
	}

	private static LiteDataUtil instance;

	public static LiteDataUtil getInstance() {
		if (instance == null) {
			instance = fromDefaults();
		}
		return instance;
	}

	public static void load(File file) {
		instance = fromFile(file);

		// no config file found
		if (instance == null) {
			instance = fromDefaults();
		}
	}

	public static void load(String file) {
		load(new File(file));
	}

	private static LiteDataUtil fromDefaults() {
		LiteDataUtil config = new LiteDataUtil();
		return config;
	}


	public void toFile(File file) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonConfig = gson.toJson(this);
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			writer.write(jsonConfig);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static LiteDataUtil fromFile(File configFile) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(configFile)));
			return gson.fromJson(reader, LiteDataUtil.class);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
}