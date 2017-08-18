package com.saucecode.packer.in;

import java.util.ArrayList;

import com.saucecode.packer.core.Item;

public class Reader {
	
	public Reader(String path) {
		
	}

	public ArrayList<Item> read() {
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<String> categories1 = new ArrayList<String>();
		categories1.add("Camping");
		categories1.add("Zu Hause");
		Item item1 = new Item("Zahnbürste", categories1);
		items.add(item1);
		ArrayList<String> categories2 = new ArrayList<String>();
		categories2.add("HaDiKo");
		categories2.add("Zu Hause");
		Item item2 = new Item("Klobürste", categories2);
		items.add(item2);
		return items;
	}

}
