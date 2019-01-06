import application.model.Accounting;
import application.model.IMDatabase;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AssertTests {

    @Test
    public void testAccounting() {
        Accounting account = new Accounting(1234, 20181111, new BigDecimal(1000));
        assertEquals(new BigDecimal(1000), account.getValor());
    }

    @Test
    public void testIMDatabase() {
        IMDatabase.getInstance().save(new Accounting(1234, 20181111, new BigDecimal(2000)));
        IMDatabase.getInstance().save(new Accounting(1234, 20181111, new BigDecimal(4000)));
        IMDatabase.getInstance().save(new Accounting(1234, 20181111, new BigDecimal(6000)));
        assertEquals(4000.0D,   IMDatabase.getInstance().getStats(null).getMedia().doubleValue(),0.00);
        assertEquals(12000.0D,  IMDatabase.getInstance().getStats(null).getSoma().doubleValue(),0.00);
        assertEquals(6000.0D,   IMDatabase.getInstance().getStats(null).getMax().doubleValue(),0.00);
        assertEquals(2000.0D,   IMDatabase.getInstance().getStats(null).getMin().doubleValue(),0.00);
        assertEquals(Integer.valueOf(3), IMDatabase.getInstance().getStats(null).getQtde());
    }

}
