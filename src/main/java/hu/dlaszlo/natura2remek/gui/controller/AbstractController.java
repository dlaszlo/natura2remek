package hu.dlaszlo.natura2remek.gui.controller;

import java.util.function.Consumer;

/**
 * JAVAFX kontroller - ősosztály
 */
public abstract class AbstractController
{
    private Consumer<String> onSetPage = null;

    public void setOnSetPage(Consumer<String> consumer)
    {
        onSetPage = consumer;
    }

    public void setPage(String page)
    {
        if (onSetPage != null)
        {
            onSetPage.accept(page);
        }
    }

    public String getTitle()
    {
        return null;
    }

}
