package list.Resource;

import java.util.ListResourceBundle;

public class Resource extends ListResourceBundle {

    @Override
    protected final Object[][] getContents() {
        return new Object[][]{
                {"title", "Authors"},
                {"authors", "Pawel Bialek and Lukasz Kostrzewa"}
        };
    }
}
