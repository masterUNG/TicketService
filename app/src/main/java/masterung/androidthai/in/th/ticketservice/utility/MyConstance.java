package masterung.androidthai.in.th.ticketservice.utility;

import masterung.androidthai.in.th.ticketservice.R;

public class MyConstance {

    private String[] titleTabLayout = new String[]{"NewItem", "Active", "Critical", "Last"};

    private String urlGetAllUser = "https://android.skyict.co.th/getAllUserKet.php";

    private String[] titleListStrings = new String[]{"Ticker", "New Ticker",
            "Report", "About"};

    private int[] iconInts = new int[]{
            R.drawable.ic_action_ticker,
            R.drawable.ic_action_new_ticker,
            R.drawable.ic_action_repord,
            R.drawable.ic_action_about};


    public String[] getTitleTabLayout() {
        return titleTabLayout;
    }

    public String getUrlGetAllUser() {
        return urlGetAllUser;
    }

    public String[] getTitleListStrings() {
        return titleListStrings;
    }

    public int[] getIconInts() {
        return iconInts;
    }
}
