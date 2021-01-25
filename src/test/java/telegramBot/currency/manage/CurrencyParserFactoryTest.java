package telegramBot.currency.manage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import telegramBot.currency.service.CurrencyParser;
import telegramBot.currency.service.impl.HarkivBankParser;
import telegramBot.currency.service.impl.OschadBankParser;
import telegramBot.currency.service.impl.PrivatBankParser;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyParserFactoryTest {

    @Mock
    private PrivatBankParser privatBankParser;

    @Mock
    private HarkivBankParser harkivBankParser;

    @Mock
    private OschadBankParser oschadBankParser;

    @Mock
    private CurrencyParserFactory factory;

    @Before
    public void init() {
        when(factory.findProvider(CurrencyParser.RateProvider.PB)).thenReturn(privatBankParser);
        when(factory.findProvider(CurrencyParser.RateProvider.OB)).thenReturn(oschadBankParser);
        when(factory.findProvider(CurrencyParser.RateProvider.HB)).thenReturn(harkivBankParser);
    }

    @Test
    public void findProviderPB() {
        CurrencyParser pb = factory.findProvider(CurrencyParser.RateProvider.PB);
        assertTrue(pb instanceof PrivatBankParser);
    }

    @Test
    public void findProviderOB() {
        CurrencyParser ob = factory.findProvider(CurrencyParser.RateProvider.OB);
        assertTrue(ob instanceof OschadBankParser);
    }

    @Test
    public void findProviderHB() {
        CurrencyParser hb = factory.findProvider(CurrencyParser.RateProvider.HB);
        assertTrue(hb instanceof HarkivBankParser);
    }

}