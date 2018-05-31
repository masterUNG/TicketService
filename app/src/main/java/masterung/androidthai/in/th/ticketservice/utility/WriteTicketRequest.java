package masterung.androidthai.in.th.ticketservice.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class WriteTicketRequest extends AsyncTask<String, Void, String>{

    private Context context;

    public WriteTicketRequest(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("CreatedBy", strings[0])
                    .add("SerialNumber", strings[1])
                    .add("Detail", strings[2])
                    .add("Assignee", strings[3])
                    .add("SeverityID", strings[4])
                    .add("DueDate", strings[5])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[6]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }
}
