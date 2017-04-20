package gr.kcodex.applib.io.util;

import android.text.Html;

import com.bluelinelabs.logansquare.typeconverters.StringBasedTypeConverter;

public class HtmlTextConverter extends StringBasedTypeConverter<String> {
    @Override
    public String getFromString(String string) {
        return convertToString(string);
    }

    @Override
    public String convertToString(String object) {
        return Html.fromHtml(object).toString();
    }
}
