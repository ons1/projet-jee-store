package test;

import java.util.ArrayList;
import java.util.Collections;

public class TestBinarySearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Integer> ma_liste = new ArrayList<>();
		ma_liste.add(1);
		ma_liste.add(2);
		ma_liste.add(5);
		System.out.println("Liste initiale : "+ma_liste);
		int index = Collections.binarySearch(ma_liste, 3);
		System.out.println("Index : "+index+" => "+(index < 0 ? "3 non trouve, insertion en position "+(-index-1):"3 trouvé en "+index));
		System.out.println("Ajout de 3 dans la liste en gardant le tri !");
		ma_liste.add(-index-1,3);
		System.out.println("Liste : "+ma_liste);
		index = Collections.binarySearch(ma_liste, 3);
		System.out.println("Index : "+index+" => "+(index < 0 ? "3 non trouve, insertion en position "+(-index-1):"3 trouvé en "+index));
	}

}
