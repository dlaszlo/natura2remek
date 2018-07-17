package hu.dlaszlo.natura2remek.config;

import java.util.Map;

/**
 * Beállítások
 */
public interface ConfigService
{
    /**
     * Inicializálás
     */
    void init();

    /**
     * @return Főkönyvi számok szótár
     */
    Map<String, String> getFokonyviSzamok();

    /**
     * @return Natura default könyvtár
     */
    String getNaturaDir();

    /**
     * @return Remek default könyvtár
     */
    String getRemekDir();

}
