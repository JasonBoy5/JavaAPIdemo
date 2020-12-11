package com.jason.util;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;

public class TResourceBundle {
	public static void main(String[] args) {
		Locale locale = new Locale("en", "US");
		ResourceBundle resource = ResourceBundle.getBundle("com.jason.properties.Message",locale);
		String val = resource.getString("info");
		System.out.println(val);
		System.out.println(MessageFormat.format(val, "Today",
				new SimpleDateFormat("y-M-d").format(System.currentTimeMillis())));
		System.out.println(UUID.randomUUID());
	}
}
