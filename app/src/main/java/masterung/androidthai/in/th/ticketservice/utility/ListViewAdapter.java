package masterung.androidthai.in.th.ticketservice.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import masterung.androidthai.in.th.ticketservice.R;

public class ListViewAdapter extends BaseAdapter{

    private Context context;
    private String[] strings;
    private int[] ints;

    public ListViewAdapter(Context context,
                           String[] strings,
                           int[] ints) {
        this.context = context;
        this.strings = strings;
        this.ints = ints;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.listview_drawer, parent, false);

        ImageView imageView = view.findViewById(R.id.imvIcon);
        imageView.setImageResource(ints[position]);

        TextView textView = view.findViewById(R.id.txtTitle);
        textView.setText(strings[position]);

        return view;
    }
}
