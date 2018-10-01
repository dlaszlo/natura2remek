package hu.dlaszlo.natura2remek.gui.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

/**
 * Megjelenítésekhez használt
 */
public class FormatUtils
{
    /**
     * Összeg formázása
     * @param amount
     * @return Formázott összeg
     */
    public static String formatAmount(BigDecimal amount)
    {
        if (amount != null)
        {
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            decimalFormatSymbols.setDecimalSeparator(',');
            decimalFormatSymbols.setGroupingSeparator(' ');
            decimalFormatSymbols.setMinusSign('-');
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", decimalFormatSymbols);
            return decimalFormat.format(amount);
        }
        else
        {
            return StringUtils.EMPTY;
        }
    }

    /**
     * CSV összeg beolvasása BigDecimal-ba
     * @param d String összeg (CSV-ből)
     * @return Beolvasott BigDecimal
     */
    public static BigDecimal parseCsvBigDecimal(String d)
    {
        BigDecimal ret;
        if (StringUtils.isNotBlank(d))
        {
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            decimalFormatSymbols.setDecimalSeparator(',');
            decimalFormatSymbols.setMinusSign('-');
            DecimalFormat decimalFormat = new DecimalFormat("0.0000", decimalFormatSymbols);
            decimalFormat.setGroupingUsed(false);
            try
            {
                ret = new BigDecimal(decimalFormat.parse(d).toString());
            }
            catch (ParseException e)
            {
                throw new RuntimeException(e);
            }
        }
        else
        {
            ret = null;
        }
        return ret;
    }
}
