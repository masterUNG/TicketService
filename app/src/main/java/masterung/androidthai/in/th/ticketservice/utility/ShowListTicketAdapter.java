package masterung.androidthai.in.th.ticketservice.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import masterung.androidthai.in.th.ticketservice.R;

public class ShowListTicketAdapter extends BaseAdapter {

    private Context context;
    private String[] docNoStrings, serialNumberStrings, detailStrings,
            severityStrings, assignStrings, statusStrings, dueDateStrings;

    public ShowListTicketAdapter(Context context,
                                 String[] docNoStrings,
                                 String[] serialNumberStrings,
                                 String[] detailStrings,
                                 String[] severityStrings,
                                 String[] assignStrings,
                                 String[] statusStrings,
                                 String[] dueDateStrings) {
        this.context = context;
        this.docNoStrings = docNoStrings;
        this.serialNumberStrings = serialNumberStrings;
        this.detailStrings = detailStrings;
        this.severityStrings = severityStrings;
        this.assignStrings = assignStrings;
        this.statusStrings = statusStrings;
        this.dueDateStrings = dueDateStrings;
    }

    @Override
    public int getCount() {
        return docNoStrings.length;
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
        View view = layoutInflater.inflate(R.layout.listview_show_item, parent, false);

        TextView docNoTextView = view.findViewById(R.id.txtDocNo);
        TextView serialTextView = view.findViewById(R.id.txtSerial);
        TextView detailTextView = view.findViewById(R.id.txtDetail);
        TextView servarityTextView = view.findViewById(R.id.txtServarity);
        TextView assignTextView = view.findViewById(R.id.txtAssign);
        TextView statusTextView = view.findViewById(R.id.txtStatus);
        TextView dueDateTextView = view.findViewById(R.id.txtDueDate);

        docNoTextView.setText(docNoStrings[position]);
        serialTextView.setText(serialNumberStrings[position]);
        detailTextView.setText(detailStrings[position]);
        servarityTextView.setText(severityStrings[position]);
        assignTextView.setText(assignStrings[position]);
        statusTextView.setText(statusStrings[position]);
        dueDateTextView.setText(dueDateStrings[position]);

        return view;
    }
}
