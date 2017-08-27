package com.saucecode.packer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

class OldReader {

	OldReader(String path) {

	}

	static void read(String path, ArrayList<Item> itemsList) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(ItemContainer.class);
		Unmarshaller um = context.createUnmarshaller();
        ItemContainer items = (ItemContainer) um.unmarshal(new FileReader(path));
        itemsList.addAll(items.getItems());
	}

	ArrayList<Item> read(String path) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(ItemContainer.class);
		Unmarshaller um = context.createUnmarshaller();
		ItemContainer items = (ItemContainer) um.unmarshal(new FileReader(path));
		System.out.println(items);
		System.out.println(items.getItems());
		for (Item item : items.getItems()) {
			System.out.println(item);
		}
		return items.getItems();
	}

	static void main(String[] args) throws JAXBException {
		Item item1 = new Item();
		item1.setName("Klobürste");
		item1.setCategory("uhu");
		ArrayList<String> sets1 = new ArrayList<String>();
		sets1.add("HaDiKo");
		sets1.add("Zu Hause");
		item1.setSets(sets1);

		Item item2 = new Item();
		item2.setName("Zahnbürste");
		item2.setCategory("lalal");
		ArrayList<String> sets2 = new ArrayList<String>();
		sets2.add("Camping");
		sets2.add("Zu Hause");
		item2.setSets(sets2);

		ItemContainer items = new ItemContainer();
		ArrayList<Item> itemsList = new ArrayList<Item>();
		itemsList.add(item1);
		itemsList.add(item2);
		items.setItems(itemsList);

		JAXBContext context = JAXBContext.newInstance(ItemContainer.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Write to System.out
		m.marshal(items, System.out);
	}

}
