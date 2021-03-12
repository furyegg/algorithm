package week1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class URLTest {
    private URL url;
    
    @BeforeEach
    public void before() {
        url = new URL();
    }
    
    @Test
    public void test() {
        Assertions.assertEquals(
                "nwmog%20q%20k%20%20gW%20%20c%20%20%20%20H%20%20DYpIE%20%20%20%20Lcz%20%20%20%20%20%20%20%20%20gV%20%20%20%20Bj%20%20%20vkH%20X%20g%20%20%20%20%20%20%20l%20%20",
                url.replaceSpaces("nwmog q k  gW  c    H  DYpIE    Lcz         gV    Bj   vkH X g       l                                                                                        ", 72));
        Assertions.assertEquals("%20%20%20%20%20%20dsfasdfasdfas", url.replaceSpaces("      dsfasdfasdfas               ", 19));
        Assertions.assertEquals("Mr%20John%20Smith", url.replaceSpaces("Mr John Smith    ", 13));
        Assertions.assertEquals("%20%20%20%20%20", url.replaceSpaces("     ", 5));
    }
}