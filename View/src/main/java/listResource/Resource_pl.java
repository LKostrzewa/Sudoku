package listResource;

import java.util.ListResourceBundle;

public class Resource_pl extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"title", "Autorzy"},
                {"authors", "Paweł Białek i Łukasz Kostrzewa"}
        };
    }
}
