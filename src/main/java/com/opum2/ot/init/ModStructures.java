package com.opum2.ot.init;

import java.util.ArrayList;
import java.util.List;

import com.opum2.ot.structures.House;
import com.opum2.ot.structures.ModStructure;

public class ModStructures {

	public static final List<ModStructure> STRUCTURES = new ArrayList();
	
	public static void init()
	{
		ModStructures.STRUCTURES.add(new House(null));
	}
}
