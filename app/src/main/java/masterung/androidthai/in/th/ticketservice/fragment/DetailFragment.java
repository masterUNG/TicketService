package masterung.androidthai.in.th.ticketservice.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import masterung.androidthai.in.th.ticketservice.R;

public class DetailFragment extends Fragment {

    private String idString, nameUserString;
    private String[] detailStrings;
    private ImageView showPhotoImageView;
    private Uri uri;


    public static DetailFragment detailInstance(String[] detailStrings) {

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Detail", detailStrings);
        detailFragment.setArguments(bundle);
        return detailFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        GetValue Argument
        detailStrings = getArguments().getStringArray("Detail");

//        Show Text
        showText();

//        Camera Controller
        cameraController();

//        InsertPhoto Controller
        intsertPhotoController();

    }   // Main Method

    private void intsertPhotoController() {
        ImageView imageView = getView().findViewById(R.id.imvInsPhoto);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Please Choole Image From App"), 2);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {

            uri = data.getData();

            try {

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;

                Bitmap bitmap = BitmapFactory
                        .decodeStream(getActivity()
                                .getContentResolver()
                                .openInputStream(uri), null, options);

                showPhotoImageView.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }   // if1


    }

    private void cameraController() {
        showPhotoImageView = getView().findViewById(R.id.imvShowPhoto);
        ImageView imageView = getView().findViewById(R.id.imvCamera);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void showText() {
        TextView docNoTextView = getView().findViewById(R.id.txtDocNo);
        TextView serialTextView = getView().findViewById(R.id.txtSerial);
        TextView dueDateTextView = getView().findViewById(R.id.txtDueDate);
        TextView detailTextView = getView().findViewById(R.id.txtDetail);
        TextView severityTextView = getView().findViewById(R.id.txtServarity);
        TextView assignTextView = getView().findViewById(R.id.txtAssign);
        TextView statusTextView = getView().findViewById(R.id.txtStatus);

        docNoTextView.setText("DocNo ==> " + detailStrings[0]);
        serialTextView.setText("DocNo ==> " + detailStrings[1]);
        dueDateTextView.setText("DocNo ==> " + detailStrings[2]);
        detailTextView.setText("DocNo ==> " + detailStrings[3]);
        severityTextView.setText("DocNo ==> " + detailStrings[4]);
        assignTextView.setText("DocNo ==> " + detailStrings[5]);
        statusTextView.setText("DocNo ==> " + detailStrings[6]);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }
}
