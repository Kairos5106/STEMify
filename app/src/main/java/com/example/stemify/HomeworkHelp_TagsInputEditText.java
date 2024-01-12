/*package com.example.stemify;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeworkHelp_TagsInputEditText extends TextInputEditText {
    private TextWatcher textWatcher;
    private String lastString;
    private String separator = " ";
    private List<String> tags = new ArrayList<>(); // List to store entered tags

    // Constructor method
    public HomeworkHelp_TagsInputEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialise();
    }

    private void initialise() {
        setMovementMethod(LinkMovementMethod.getInstance());

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String thisString = editable.toString();
                if (thisString.length() > 0 && !thisString.equals(lastString)) {
                    format();
                }
            }
        };

        addTextChangedListener(textWatcher);
    }

    private void format() {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        String fullString = Objects.requireNonNull(getText().toString());

        String[] strings = fullString.split(separator);

        for (int i = 0; i < strings.length ; i++) {
            String s = strings[i];
            stringBuilder.append(s);

            if (fullString.charAt(fullString.length() - 1) != separator.charAt(0) && i == strings.length - 1) {
                break;
            }

            BitmapDrawable bitmapDrawable = (BitmapDrawable) convertViewToDrawable(createTokenView(s));
            bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());

            int startIndex = stringBuilder.length() - (s.length());
            int endIndex = stringBuilder.length();

            ClickableSpan span = new ClickableSpan(startIndex, endIndex);
            stringBuilder.setSpan(span, Math.max(endIndex - 2, startIndex), endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            stringBuilder.setSpan(new ImageSpan(bitmapDrawable), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            if (i < strings.length - 1) {
                stringBuilder.append(separator);
            } else if (fullString.charAt(fullString.length() - 1) == separator.charAt(0)) {
                stringBuilder.append(separator);
            }
        }
        lastString = stringBuilder.toString();

        setText(stringBuilder);
        setSelection(stringBuilder.length());
    }

    public View createTokenView(String text) {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);

        // Inflate the layout containing the Chip
        View view = LayoutInflater.from(getContext()).inflate(R.layout.homeworkhelp_newquestion_tokenlayout, layout, false);

        Chip chip = view.findViewById(R.id.chip);
        chip.setText(text);

        // Add the Chip to the LinearLayout with proper LayoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        chip.setLayoutParams(params);

        layout.addView(chip);

        // Add the entered tag to the list
        tags.add(text);

        return layout;
    }

    public Object convertViewToDrawable(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.translate(-view.getScrollX(), -view.getScrollY());
        view.draw(canvas);
        view.setDrawingCacheEnabled(true);
        Bitmap cache = view.getDrawingCache();
        Bitmap viewBitmap = cache.copy(Bitmap.Config.ARGB_8888, true);
        view.destroyDrawingCache();

        return new BitmapDrawable(getContext().getResources(), viewBitmap);
    }

    public List<String> getTags() {
        return tags;
    }

    public  class ClickableSpan extends android.text.style.ClickableSpan {
        int startIndex;
        int endIndex;

        public ClickableSpan(int startIndex, int endIndex) {
            super();
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void onClick(@NonNull View view) {
            String s = Objects.requireNonNull(getText().toString());
            String s1 = s.substring(0, startIndex);
            String s2 = s.substring(Math.min(endIndex + 1, s.length() - 1));
            HomeworkHelp_TagsInputEditText.this.setText(MessageFormat.format("{0}{1}", s1, s2));
        }
    }
}
*/