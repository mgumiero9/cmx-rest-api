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
        assertEquals(new BigDecimal(4000), IMDatabase.getInstance().getStats().getMedia());
        assertEquals(new BigDecimal(12000), IMDatabase.getInstance().getStats().getSoma());
        assertEquals(new BigDecimal(6000), IMDatabase.getInstance().getStats().getMax());
        assertEquals(new BigDecimal(2000), IMDatabase.getInstance().getStats().getMin());
        assertEquals(Integer.valueOf(3), IMDatabase.getInstance().getStats().getQtde());
    }

}
