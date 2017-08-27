package com.saucecode.packer.xml;

import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Main {
	
	public static void main(String[] args) throws JAXBException {
		Item item1 = new Item();
		item1.name = "Klobürste";
		item1.category = "Toilettenartikel";
		item1.setSets(new Sets());
		item1.sets.set = new ArrayList<String>();
		item1.sets.set.add("HaDiKo");
		item1.sets.set.add("ashahsd");
		Items items = new Items();
		items.getItem().add(item1);

		JAXBContext context = JAXBContext.newInstance(Items.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Write to System.out
		m.marshal(items, System.out);
	}

}
