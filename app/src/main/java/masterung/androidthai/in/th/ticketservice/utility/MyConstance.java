package masterung.androidthai.in.th.ticketservice.utility;

import masterung.androidthai.in.th.ticketservice.R;

public class MyConstance {

    private String[] titleListStrings = new String[]{"Ticker", "New Ticker",
            "Report", "About"};

    private int[] iconInts = new int[]{
            R.drawable.ic_action_ticker,
            R.drawable.ic_action_new_ticker,
            R.drawable.ic_action_repord,
            R.drawable.ic_action_about};

    public String[] getTitleListStrings() {
        return titleListStrings;
    }

    public int[] getIconInts() {
        return iconInts;
    }
}
