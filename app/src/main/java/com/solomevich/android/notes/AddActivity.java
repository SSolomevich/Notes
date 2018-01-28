package com.solomevich.android.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;




import java.util.Date;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.res.TypedArray;
        import android.os.Bundle;
//import android.util.Log;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup.LayoutParams;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.Gallery;
        import android.widget.ImageView;
        import android.widget.TextView;

public class AddActivity extends Activity  {

    private Gallery mGallery;
    private ImageAdapter mImageAdapter;
    private Button butSave, butCancel;
    private TextView mTextView;
    private DatePicker mDatePicker;
    Context mContext;
    private long MyDataID;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        // Галерея
        mGallery = (Gallery) findViewById(R.id.gallery);
        mImageAdapter = new ImageAdapter(this);
        mGallery.setAdapter(mImageAdapter);


        // Текст и дата
        mTextView = (TextView) findViewById(R.id.DescText);
        mDatePicker = (DatePicker) findViewById(R.id.Date);

        if (getIntent().hasExtra("MyData")) {
            MyData md = (MyData) getIntent().getSerializableExtra("MyData");
            Date d = new Date (md.getDate());
            mDatePicker.updateDate(d.getYear() + 1900, d.getMonth(), d.getDate());
            mGallery.setSelection(mImageAdapter.getPositionbyResId(md.getIcon()));
            mTextView.setText(md.getTitle());
            MyDataID = md.getID();
        }
        else {
            MyDataID = -1;
            mGallery.setSelection(mImageAdapter.getCount() / 2);
        }

        // Кнопки
        butSave = (Button) findViewById(R.id.butSave);
        butCancel = (Button) findViewById(R.id.butCancel);

        butSave.setOnClickListener (new OnClickListener() {
            public void onClick(View v) {

                Date date = new Date(mDatePicker.getYear()-1900, mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
                MyData md = new MyData (MyDataID, date.getTime(), mTextView.getText().toString(), mImageAdapter.getResourceId(mGallery.getSelectedItemPosition()));
                Intent intent = getIntent();
                intent.putExtra("MyData", md);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        butCancel.setOnClickListener (new OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_CANCELED, new Intent());
                finish();
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        int bg;

        private int[] mImageIds = {
                R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4,
                R.drawable.s5, R.drawable.s6, R.drawable.s7, R.drawable.s8,
                R.drawable.s9, R.drawable.s10, R.drawable.s11, R.drawable.s12,R.drawable.s13,
                R.drawable.s14,R.drawable.s15,R.drawable.s16,R.drawable.s17,
                R.drawable.s18,R.drawable.s19,R.drawable.s20,R.drawable.s21,
                R.drawable.s22,R.drawable.s23,R.drawable.s24,R.drawable.s25,
                R.drawable.s26,R.drawable.s27,R.drawable.s28,R.drawable.s29,
                R.drawable.s30,R.drawable.s31,R.drawable.s32,R.drawable.s33,
                R.drawable.s34,R.drawable.s35,R.drawable.s36,R.drawable.s37,
                R.drawable.s38,R.drawable.s39,R.drawable.s40};

        public ImageAdapter(Context c) {
            mContext = c;
            TypedArray attr = mContext.obtainStyledAttributes(R.styleable.MyGallery);
            bg = attr.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
            attr.recycle();
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public int getResourceId(int position) {

            int id = mImageIds[position];
            return id;
        }

        public int getPositionbyResId(int ResId) {

            for (int i = 0; i < mImageIds.length; i++)
                if (mImageIds[i] == ResId)
                    return i;
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mImageIds[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setPadding(2, 2, 2, 2);
            imageView.setBackgroundResource(bg);
            imageView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            return imageView;
        }
    }
}
