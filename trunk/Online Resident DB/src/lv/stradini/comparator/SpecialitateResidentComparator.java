package lv.stradini.comparator;

import java.util.Comparator;

import lv.stradini.domain.Resident;

public class SpecialitateResidentComparator implements Comparator<Resident> {

	@Override
	public int compare(Resident res1, Resident res2) {
	    final int BEFORE = -1;
	    final int EQUAL = 0;
	    final int AFTER = 1;

	    return res1.getSpecialitate().compareTo(res2.getSpecialitate());
	}
}
