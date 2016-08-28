package mohammed.hatoon.hadeety;

import android.graphics.drawable.Drawable;

public class post{

    private String Text;
    private String id;
    private Drawable img;

    public post(String text, String id, Drawable img) {
        Text = text;
        this.id = id;
        this.img = img;
    }

    public String getText() {
        return Text;
    }

    public String getId() {
        return id;
    }

    public Drawable getImg() {
        return img;
    }
}
