// IPPO Assignment 1, Version 20.3, 18/10/2020
package ippo.assignment1.controller;

import ippo.assignment1.library.Picture;
import ippo.assignment1.library.controller.Controller;
import ippo.assignment1.library.service.Service;
import ippo.assignment1.library.service.ServiceFromProperties;
import ippo.assignment1.library.utils.HashMap;
import ippo.assignment1.library.utils.Properties;
import ippo.assignment1.library.view.View;
import ippo.assignment1.library.view.ViewFromProperties;

/**
 * A "Hash" controller for the PictureViewer application.
 * 
 * @author Paul Anderson &lt;dcspaul@ed.ac.uk&gt, amended by student for assigment 1;
 * @version 1.1 student amended verison, 17/10/2020
 */
public class ProptertyController implements Controller {

	private View view;
	private Service service;
	HashMap<Integer, String> MunroMap = new HashMap<>();

	/**
	 * Start the controller.
	 */
	public void start() {

		String MunroList;

		// create the view and the service objects
		view = new ViewFromProperties(this);
		service = new ServiceFromProperties();
		MunroList = Properties.get("controller.subjects");
		generateOptions(MunroList);

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

	/* adds an item to the Hashmap */

	public void addSubject(String Munro) {

		int MKey;
		MKey = view.addSelection(Munro);
		MunroMap.put(MKey, Munro);

	}
	//this method was found on the interwebs, should probably get the reference
	public char getCharFromString(String stringToProcess, int index) {
		char Character;
		Character = stringToProcess.toCharArray()[index];
		return Character;
	}

	//returns the first comma seperated value
	public String getItemFromString(String stringToProcess) {
		String returnItem;
		char tempItem;
		int index;

		index = 0;
		returnItem = "";
		while(index < stringToProcess.length()) {
			tempItem = getCharFromString(stringToProcess, index);
			if (tempItem != ',') {
			returnItem = returnItem + tempItem;
			index++;
			}
			else {
				return returnItem;

			}
		}
		return returnItem;
	}

	//Genterates a list of Munro options for views classes to use
	public void generateOptions(String MunroList) {

		String CurrentMunro;

		//gets first Munro from list
		while(MunroList.length() > 0) {
			CurrentMunro = getItemFromString(MunroList);
			addSubject(CurrentMunro);
			MunroList = MunroList.replace(CurrentMunro + ",", ""); //this line removes the Current Munro from the list of Munro's
			if(CurrentMunro.equals(MunroList)) { /* checks if end of list and clears the list, required as the list does not end with a comma */
				MunroList = "";
			}
		}
	}
}

