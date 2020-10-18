// IPPO Assignment 1, Version 20.3, 14/10/2020
package ippo.assignment1.controller;

import ippo.assignment1.library.Picture;
import ippo.assignment1.library.controller.Controller;
import ippo.assignment1.library.service.Service;
import ippo.assignment1.library.service.ServiceFromProperties;
import ippo.assignment1.library.view.View;
import ippo.assignment1.library.view.ViewFromProperties;
import ippo.assignment1.library.utils.HashMap;

/**
 * A "Hash" controller for the PictureViewer application.
 * 
 * @author Paul Anderson &lt;dcspaul@ed.ac.uk&gt, amended by student for assigment 1;
 * @version 1.1 student amended verison, 17/10/2020
 */
public class HashController implements Controller {

	private View view;
	private Service service;

	private int selection1;
	private int selection2;
	private int selection3;
	private int ListLenght;
	HashMap<Integer, String> MunroMap = new HashMap<Integer, String>();

	/**
	 * Start the controller.
	 */
	public void start() {

		// create the view and the service objects
		view = new ViewFromProperties(this);
		service = new ServiceFromProperties();

		// adds the default elements to the Map
		ListLenght = 0;
		addSubject("Stob Binnein");
		addSubject("Gairich");
		addSubject("Ben Lomond");

		// start the interface
		view.start();
	}

	/**
	 * Handle the specified selection from the interface.
	 *
	 * @param selectionID the id of the selected item
	 */
	public void select(int selectionID) {
		
		// a picture corresponding to the selectionID
		// by default, this is an empty picture
		// (this is used if the selectionID does not match)
		Picture picture = new Picture();
		//create a picture using the hash map
		picture = service.getPicture(MunroMap.get(selectionID),1);

		// show the picture in the interface
		view.showPicture(picture);
	}

	public void addSubject(String Munro) {

		int MKey;

		ListLenght += 1;
		MKey = view.addSelection(Munro);
		MunroMap.put(MKey, Munro);


	}
}

