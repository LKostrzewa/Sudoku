package list.Resource;

import java.util.ListResourceBundle;

public class ResourcePL extends ListResourceBundle {

    @Override
    protected final Object[][] getContents() {
        return new Object[][]{
                {"title", "Autorzy"},
                {"authors", "Paweł Białek i Łukasz Kostrzewa"}
        };
    }
}
