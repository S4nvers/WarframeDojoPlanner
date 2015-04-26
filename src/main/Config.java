package main;

import java.util.HashMap;

import main.security.NameCollisionException;
import main.security.NoSuchConfigException;
import main.security.NoSuchKeyException;

public class Config {
	
	private HashMap<String, HashMap<String, Object>> configs;
	
	public Config() {
		configs = new HashMap<String, HashMap<String, Object>>();
	}
	
	public void registerConfiguration(String name, HashMap<String, Object> config)
			throws NameCollisionException {
		if (!configs.containsKey(name)) {
			configs.put(name, config);
		} else {
			throw new NameCollisionException(name);
		}
	}
	
	public Object getEntry(String module, String key) throws NoSuchConfigException, NoSuchKeyException {
		if (configs.containsKey(module)) {
			if (configs.get(module).containsKey(key)) {
				return configs.get(module).get(key);
			} else {
				throw new NoSuchKeyException(module, key);
			}
		} else {
			throw new NoSuchConfigException(module);
		}
	}
	
	public void setEntry(String module, String key, Object value) throws NoSuchKeyException, NoSuchConfigException {
		if (configs.containsKey(module)) {
			if (configs.get(module).containsKey(key)) {
				configs.get(module).put(key, value);
			} else {
				throw new NoSuchKeyException(module, key);
			}
		} else {
			throw new NoSuchConfigException(module);
		}
	}
	
}